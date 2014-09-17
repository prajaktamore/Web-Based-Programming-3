/*
 * Copyright (c) 1997.  Netscape Communications Corporation.  All
 * rights reserved.
 * 
 * Modify the RDN (relative distinguished name) of an entry.  In this
 * example, we change the dn "uid=jsmith, ou=People, o=Airius.com"
 * to "uid=jmsmith, ou=People, o=Airius.com".
 *
 * Since it is an error to either (1) attempt to modrdn an entry which
 * does not exist, or (2) modrdn an entry where the destination name
 * already exists, we take some steps, for this example, to make sure
 * we'll succeed.  We (1) add "uid=jsmith" (if the entry exists,
 * we just ignore the error, and (2) delete "uid=jmsmith" (if the
 * entry doesn't exist, we ignore the error).
 *
 * We pass false for the "deleteoldrdn" argument to the rename method. This
 * means that after we change the RDN, the server will put the value
 * "jsmith" into the uid attribute of the new entry, in addition to
 * "jmsmith".
 */

import netscape.ldap.*;
import java.util.*;

public class ModRdn {
    public static void main( String[] args )
	{
		/* Values we'll use in creating the entry */
		String objectclass_values[] = { "top",
										"person", 
										"organizationalPerson",
										"inetOrgPerson" };
		String cn_values[] = { "Jacques Smith" };
		String sn_values[] = { "Smith" };
		String givenname_values[] = { "Jacques" };

		/* Specify the DN we're adding */
		String base = "ou=People, o=Airius.com";
		String dn = "uid=jsmith" + "," + base;
		/* the new RDN */
		String nrdn = "uid=jmsmith";
		/* the destination DN */
		String ndn = base + "," + nrdn;

		/* Create an attribute set with all desired attributes */
		LDAPAttributeSet attrs = new LDAPAttributeSet();
		LDAPAttribute attr = new LDAPAttribute( "objectclass",
												objectclass_values );
		attrs.add( attr );
		attr = new LDAPAttribute( "cn",
								  cn_values );
		attrs.add( attr );
		attr = new LDAPAttribute( "sn",
								  sn_values );
		attrs.add( attr );
		attr = new LDAPAttribute( "givenname",
								  givenname_values );
		attrs.add( attr );
		attrs.add( new LDAPAttribute( "uid", nrdn ) );

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

			/* Add the entry */
			try {
				ld.add( myEntry );
			}
			catch( LDAPException e ) {
				/* If entry exists already, fine.  Ignore this error. */
				if ( e.getLDAPResultCode() !=
					 LDAPException.ENTRY_ALREADY_EXISTS )
					throw e;
			}

			/* Delete the destination entry, for this example */
			try {
				ld.delete( ndn );
			}
			catch( LDAPException e ) {
				/* If entry does not exist, fine.  Ignore this error. */
				if ( e.getLDAPResultCode() !=
					 LDAPException.NO_SUCH_OBJECT )
					throw e;
			}

			/* Do the modrdn operation */
			ld.rename( dn, nrdn, false );

			System.out.println( "The modrdn operation was successful. " );
			System.out.println( "Entry " + dn + " has been changed to " +
								ndn );
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
