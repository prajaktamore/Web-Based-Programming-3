/*
 * Copyright (c) 1997.  Netscape Communications Corporation.  All
 * rights reserved.
 *
 * Persistent Search is a blocking process which waits for changes on
 * the server, and then returns search results and blocks until there
 * are more changes. A client application should create
 * a new thread for the persistent search, or the whole process will
 * block.
 */
import netscape.ldap.*;
import netscape.ldap.controls.*;
import java.util.*;

public class PersistSearch implements Runnable{
  public static Thread t1;
  public static Thread t2;
  public static LDAPConnection ld = null;
  public static LDAPConnection ld1 = null;

  public PersistSearch() {
  }

  public static void main(String[] argv) {
    String hostname = "localhost";
    int portnum = 389;
    try {
      ld = new LDAPConnection();

      if (argv.length > 0)
        hostname = argv[0];
      if (argv.length > 1)
        portnum = Integer.valueOf(argv[1]).intValue();

      ld.connect(hostname, portnum);

      // clone the LDAPConnection
      ld1 = (LDAPConnection)ld.clone();

      PersistSearch s = new PersistSearch();
      t1 = new Thread(s, "conn");
      t2 = new Thread(s, "conn1");
      t2.start();
      t1.start();

    } catch (LDAPException e) {
      System.out.println("Exception: " +e.toString());
    }
  }

  public static void printResults(String str,LDAPSearchResults myResults) {
    LDAPEntry myEntry = null;

    while ( myResults.hasMoreElements() ) {
      System.out.println("**** " + str + "****");
      try {
        myEntry = myResults.next();
      } catch (LDAPReferralException e) {
        LDAPUrl[] urls = e.getURLs();
        System.out.println("Referral received:" );
		for( int i = 0; i < urls.length; i++ ) {
			System.out.println("  " + urls[i].getUrl() );
		}
		continue;
      } catch (LDAPException e) {
		System.out.println("Error: " + e.toString() );
		continue;
	  }

      String nextDN = myEntry.getDN();
      System.out.println( nextDN );
      LDAPAttributeSet entryAttrs = myEntry.getAttributeSet();
      Enumeration attrsInSet = entryAttrs.getAttributes();
      while ( attrsInSet.hasMoreElements() ) {
        LDAPAttribute nextAttr = (LDAPAttribute)attrsInSet.nextElement();

        String attrName = nextAttr.getName();
        System.out.println( "\t" + attrName + ":" );
        Enumeration valsInAttr = nextAttr.getStringValues();
        if (valsInAttr != null) {
          while ( valsInAttr.hasMoreElements() ) {
            String nextValue = (String)valsInAttr.nextElement();
            System.out.println( "\t\t" + nextValue );
          }
        }
      }
      System.out.println("");
    }
  }

  public void run() {
    String filter = "givenname=Richard";
    String searchbase = "o=Airius.com";
    String[] attrs = {"mail"};

    if (Thread.currentThread() == t1) {
      LDAPSearchConstraints cons = ld.getSearchConstraints();
      cons.setBatchSize(0);
      try {
        LDAPSearchResults res = ld.search(searchbase, LDAPv3.SCOPE_SUB,
       	  filter, attrs, false, cons);
        printResults("Ordinary Search ", res);
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    } else {
      try {
        LDAPSearchConstraints cons1 = ld1.getSearchConstraints();
        cons1.setBatchSize(1);
        LDAPPersistSearchControl control = new LDAPPersistSearchControl(
          LDAPPersistSearchControl.ADD, true, false, false);
        cons1.setServerControls(control);
        LDAPSearchResults res1 = ld1.search(searchbase, LDAPv3.SCOPE_SUB,
          filter, attrs, false, cons1);
        printResults("Persistent Search ", res1);
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }
  }
}

