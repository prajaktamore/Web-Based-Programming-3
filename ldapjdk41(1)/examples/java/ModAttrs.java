/*
 * Copyright (c) 1997.  Netscape Communications Corporation.  All
 * rights reserved.
 * 
 * Modify an entry:
 *  - replace any existing values in the "mail" attribute with "babs@airius.com"
 *  - add a new value to the "description" attribute
 *
 */

import netscape.ldap.*;
import java.util.*;

public class ModAttrs {
    public static void main( String[] args )
	{
		String ENTRYDN = "uid=bjensen, ou=People, o=Airius.com";

		LDAPModificationSet mods = new LDAPModificationSet();
		LDAPAttribute attrEmail = new LDAPAttribute( "mail", "babs@airius.com" );
		mods.add( LDAPModification.REPLACE, attrEmail );
		LDAPAttribute attrDesc = new LDAPAttribute( "description",
				   "This entry was modified with the modattrs program" );
		mods.add( LDAPModification.ADD, attrDesc );

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

			/* Now modify the entry in the directory */
			ld.modify( ENTRYDN, mods );
			System.out.println( "Entry modified"  );
		}
		catch( LDAPException e ) {
			if ( e.getLDAPResultCode() == LDAPException.NO_SUCH_OBJECT )
				System.out.println( "Error: No such entry" );
			else if ( e.getLDAPResultCode() ==
					  LDAPException.INSUFFICIENT_ACCESS_RIGHTS )
				System.out.println( "Error: Insufficient rights" );
			else if ( e.getLDAPResultCode() ==
					  LDAPException.ATTRIBUTE_OR_VALUE_EXISTS )
				System.out.println( "Error: Attribute or value exists" );
			else
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
