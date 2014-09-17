/*
 * Copyright (c) 1997.  Netscape Communications Corporation.  All
 * rights reserved.
 * 
 * Add a new entry to the directory.
 *
 * Since it is an error to attempt to add an entry which already exists,
 * you cannot run this example program twice in a row.  You can use the
 * del.java example program to delete the entry which this example adds.
 *
 */

import netscape.ldap.*;
import java.util.*;

public class Add {
    public static void main( String[] args )
	{
		/* Specify the DN we're adding */
		String dn = "uid=wbjensen, ou=People, o=Airius.com";

		/* Specify the attributes of the entry */
		String objectclass_values[] = { "top",
										"person",
										"organizationalPerson",
										"inetOrgPerson" };
		String cn_values[] = { "William B Jensen",
							   "William Jensen",
							   "Bill Jensen" };
		String sn_values[] = { "Jensen" };
		String givenname_values[] = { "William", "Bill" };
		String telephonenumber_values[] = { "+1 415 555 1212" };

		LDAPAttributeSet attrs = new LDAPAttributeSet();
		LDAPAttribute attr = new LDAPAttribute( "objectclass" );
		for( int i = 0; i < objectclass_values.length; i++ ) {
			attr.addValue( objectclass_values[i] );
		}
		attrs.add( attr );
		attr = new LDAPAttribute( "cn" );
		for( int i = 0; i < cn_values.length; i++ ) {
			attr.addValue( cn_values[i] );
		}
		attrs.add( attr );
		attr = new LDAPAttribute( "sn" );
		for( int i = 0; i < sn_values.length; i++ ) {
			attr.addValue( sn_values[i] );
		}
		attrs.add( attr );
		attr = new LDAPAttribute( "givenname" );
		for( int i = 0; i < givenname_values.length; i++ ) {
			attr.addValue( givenname_values[i] );
		}
		attrs.add( attr );
		attr = new LDAPAttribute( "telephonenumber" );
		for( int i = 0; i < telephonenumber_values.length; i++ ) {
			attr.addValue( telephonenumber_values[i] );
		}
		attrs.add( attr );
		attrs.add( new LDAPAttribute( "uid", "wbjensen" ) );

		/* Create an entry with this DN and these attributes */
		LDAPEntry myEntry = new LDAPEntry( dn, attrs );

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

			/* Now add the entry to the directory */
			ld.add( myEntry );
			System.out.println( "Entry added"  );
		}
		catch( LDAPException e ) {
			if ( e.getLDAPResultCode() == LDAPException.ENTRY_ALREADY_EXISTS )
				System.out.println( "Error: Entry already present" );
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
