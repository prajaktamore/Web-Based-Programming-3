/*
 * Copyright (c) 199l.  Netscape Communications Corporation.  All
 * rights reserved.
 * 
 * Use compare() to compare values agains values contained in entry
 * "uid=bjensen, ou=Product Development, o=Airius.com".
 * We test to see if (1) the value "person" is one of the values in the
 * objectclass attribute (it is), and if (2) the value "xyzzy" is in the
 * objectlass attribute (it isn't, or at least, it shouldn't be).
 *
 */

import netscape.ldap.*;
import java.util.*;

public class Compare {
    public static void main( String[] args )
	{
		LDAPConnection ld = null;
		int status = -1;
		try {
			ld = new LDAPConnection();
			/* Connect to server */
			String MY_HOST = "localhost";
			int MY_PORT = 389;
			ld.connect( MY_HOST, MY_PORT );

			/* Authenticate to the server as directory manager */
			String MGR_DN = "cn=Directory Manager";
			String MGR_PW = "secretdog";
			ld.authenticate( MGR_DN, MGR_PW );

			/* Entry to compare */
			String ENTRYDN = "uid=bjensen, ou=People, o=Airius.com";

			/* compare the value "person" against the objectclass attribute */
			LDAPAttribute attr = new LDAPAttribute( "objectclass", "person" );
			boolean ok = ld.compare( ENTRYDN, attr );
			reportResults( ok, attr );

			/* compare the value "xyzzy" against the objectclass attribute */
			attr = new LDAPAttribute( "objectclass", "xyzzy" );
			ok = ld.compare( ENTRYDN, attr );
			reportResults( ok, attr );
		}
		catch( LDAPException e ) {
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

	private static void reportResults( boolean ok, LDAPAttribute attr ) {
		String result;
		if ( ok )
			result = new String();
		else
			result = new String( "not " );
		Enumeration en = attr.getStringValues();
		if (en != null) {
			String val = (String)en.nextElement();
			System.out.println( 
		 	  "The value *" + val + "* is " + result + "contained in the " +
			  attr.getName() + " attribute." );
		}
	}
}

