/*
 * Copyright (c) 1996.  Netscape Communications Corporation.  All
 * rights reserved.
 * 
 * Delete an entry from the directory.
 *
 * Since it is an error to attempt to delete an entry which does not
 * exist, you cannot run this example until you have added the entry
 * with the Add.java example program.
 *
 */

import netscape.ldap.*;
import java.util.*;

public class Del {
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

			/* Specify the DN we're deleting */
			String dn = "uid=wbjensen, ou=People, o=Airius.com";

			ld.delete( dn );

			System.out.println( "Entry deleted" );
		}
		catch( LDAPException e ) {
			if ( e.getLDAPResultCode() == LDAPException.NO_SUCH_OBJECT )
				System.out.println( "Error: No such entry" );
			else if ( e.getLDAPResultCode() ==
					  LDAPException.INSUFFICIENT_ACCESS_RIGHTS )
				System.out.println( "Error: Insufficient rights" );
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
