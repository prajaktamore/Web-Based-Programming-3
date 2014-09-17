/*
 * Copyright (c) 1997.  Netscape Communications Corporation.  All
 * rights reserved.
 * 
 * Search the directory for the specific entry
 * "uid=bjensen, ou=People, o=Airius.com".
 * Retrieve all attributes from the entry.
 *
 */

import netscape.ldap.*;
import java.util.*;

public class RdEntry {
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

			String ENTRYDN = "uid=bjensen, ou=People, o=Airius.com";

			/* Read all attributes */
			LDAPEntry findEntry = ld.read( ENTRYDN );

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
}


