/*
 * Copyright (c) 1997.  Netscape Communications Corporation.  All
 * rights reserved.
 * 
 * Retrieve several attributes of a particular entry.
 *
 */

import netscape.ldap.*;
import java.util.*;

public class GetAttrs {
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

			String ENTRYDN = "uid=bjensen, ou=People, o=Airius.com";
			String[] attrNames = {
				"cn",			/* Get canonical name(s) (full name) */
				"sn",			/* Get surname(s) (last name) */
				"mail",		    /* Get email address(es) */
				"telephonenumber" };	/* Get telephone number(s) */
			LDAPSearchResults res = ld.search( ENTRYDN,
												LDAPConnection.SCOPE_BASE,
												"objectclass=*",
												attrNames,
												false );

			/* Loop on results until finished; will only be one! */
			while ( res.hasMoreElements() ) {

				/* Next directory entry, really only one at most */
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

				/* Get the attributes of the entry */
				LDAPAttributeSet findAttrs = findEntry.getAttributeSet();
				Enumeration enumAttrs = findAttrs.getAttributes();

				/* Loop on attributes */
				while ( enumAttrs.hasMoreElements() ) {
					LDAPAttribute anAttr =
						(LDAPAttribute)enumAttrs.nextElement();
					String attrName = anAttr.getName();
					if ( attrName.equals( "cn" ) )
						System.out.println( "Full name:" );
					else if ( attrName.equals( "sn" ) )
						System.out.println( "Last name (surname):" );
					else if ( attrName.equals( "mail" ) )
						System.out.println( "Email address:" );
					else if ( attrName.equals( "telephonenumber" ) )
						System.out.println( "Telephone number:" );
					/* Loop on values for this attribute */
					Enumeration enumVals = anAttr.getStringValues();
					if (enumVals != null) {
						while ( enumVals.hasMoreElements() ) {
					   	  String aVal = ( String )enumVals.nextElement();
						  System.out.println( "\t" + aVal );
						}
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
