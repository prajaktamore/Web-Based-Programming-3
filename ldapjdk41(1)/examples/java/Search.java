/*
 * Copyright (c) 1997.  Netscape Communications Corporation.  All
 * rights reserved.
 * 
 * Search the directory for all people whose surname (last name) is
 * "Jensen".  Since the "sn" attribute is a caseignorestring (cis), case
 * is not significant when searching.
 *
 * You can control whether to block on receiving all results or on one
 * at a time by setting the batchSize.
 *
 */

import netscape.ldap.*;
import java.util.*;

public class Search {
    public static void main( String[] args )
	{
		LDAPConnection ld = null;
		LDAPEntry findEntry = null;
		int status = -1;
		try {
			ld = new LDAPConnection();
			/* Connect to server */
			String MY_HOST = "localhost";
			int MY_PORT = 389;
			ld.connect( MY_HOST, MY_PORT );

			/* search for all entries with surname of Jensen */
			String MY_FILTER = "sn=Jensen";
			String MY_SEARCHBASE = "o=Airius.com";

			LDAPSearchConstraints cons = ld.getSearchConstraints();
			/* Setting the batchSize to one will cause the result
			   enumeration below to block on one result at a time,
			   allowing us to update a list or do other things as
			   results come in. */
			/* We could set it to 0 if we just wanted to get all
			   results and were willing to block until then */
			cons.setBatchSize( 1 );
			LDAPSearchResults res = ld.search( MY_SEARCHBASE,
												LDAPConnection.SCOPE_SUB,
												MY_FILTER,
												null,
												false,
												cons );

			/* Loop on results until finished */
			while ( res.hasMoreElements() ) {

				/* Next directory entry */
				try {
					findEntry = res.next();
				} catch ( LDAPReferralException e ) {
					System.out.println( "Search reference: " );
					LDAPUrl refUrls[] = e.getURLs();
					for (int i=0; i<refUrls.length; i++) {
						System.out.println( "\t" + refUrls[i].getUrl() );
					}
					continue;
				} catch ( LDAPException e ) {
					System.out.println( "Error: " + e.toString() );
					continue;
				}

				System.out.println( findEntry.getDN() );

				/* Get the attributes of the entry */
				LDAPAttributeSet findAttrs = findEntry.getAttributeSet();
				Enumeration enumAttrs = findAttrs.getAttributes();
				System.out.println( "\tAttributes: " );
				/* Loop on attributes */
				while ( enumAttrs.hasMoreElements() ) {
					LDAPAttribute anAttr =
						(LDAPAttribute)enumAttrs.nextElement();
					String attrName = anAttr.getName();
					System.out.println( "\t\t" + attrName );
					/* Loop on values for this attribute */
					Enumeration enumVals = anAttr.getStringValues();
					if (enumVals != null) {
					  while ( enumVals.hasMoreElements() ) {
						String aVal = ( String )enumVals.nextElement();
						System.out.println( "\t\t\t" + aVal );
					  }
					}
				}
			}
			status = 0;
		} catch( LDAPException e ) {
          System.out.println( "Error: " + e.toString() );
		}

		/* Done, so disconnect */
		if ( (ld != null) && ld.isConnected() ) {
			try {
			    ld.disconnect();
			} catch ( LDAPException e ) {
				System.out.println( "Error: " + e.toString() );
			}
		}
		System.exit(status);
	}
}


