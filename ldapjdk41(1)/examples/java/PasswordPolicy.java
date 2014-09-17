/* -*- Mode: C++; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * The contents of this file are subject to the Netscape Public License
 * Version 1.0 (the "NPL"); you may not use this file except in
 * compliance with the NPL.  You may obtain a copy of the NPL at
 * http://www.mozilla.org/NPL/
 *
 * Software distributed under the NPL is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the NPL
 * for the specific language governing rights and limitations under the
 * NPL.
 *
 * The Initial Developer of this code under the NPL is Netscape
 * Communications Corporation.  Portions created by Netscape are
 * Copyright (C) 1999 Netscape Communications Corporation.  All Rights
 * Reserved.
 *
 */

/*
 * Process password policy controls returned on authentication.
 *
 */

import netscape.ldap.*;
import netscape.ldap.controls.*;

public class PasswordPolicy {

final static int NO_PASSWORD_CONTROLS = 0;
final static int PASSWORD_EXPIRED = -1;

    public static void main( String[] args ) {
		if ( args.length < 4 ) {
			doUsage();
			System.exit( 1 );
		}
		LDAPConnection ld = null;
		int status = -1;
		try {
			ld = new LDAPConnection();
			/* Connect to server */
			String MY_HOST = args[0];
			int MY_PORT = Integer.parseInt( args[1] );
			ld.connect( MY_HOST, MY_PORT );

			/* Authenticate to the server */
			String DN = args[2];
			String PW = args[3];
			ld.authenticate( 3, DN, PW );

			System.out.println( "Authentication successful" );
		}
		catch( LDAPException e ) {
			if ( e.getLDAPResultCode() == LDAPException.INVALID_CREDENTIALS ) {
				System.out.println( "Invalid credentials" );
			} else if ( e.getLDAPResultCode() == LDAPException.NO_SUCH_OBJECT ) {
				System.out.println( "No such user" );
			} else {
				System.out.println( "Error on authentication: " + e.toString() );
			}
		}

		/* Were any controls returned? */
		if ( ld != null ) {
			int seconds = checkControls( ld );
			switch( seconds ) {
			case NO_PASSWORD_CONTROLS:
				System.out.println( "No controls returned" );
				break;
			case PASSWORD_EXPIRED:
				System.out.println( "Password expired and must be reset" );
				break;
			default:
				System.out.println( "Password expires in " + seconds +
									" seconds" );
			}
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

	private static int checkControls( LDAPConnection ld ) {
		LDAPControl[] controls = ld.getResponseControls();
		int status = NO_PASSWORD_CONTROLS;

		if ( (controls != null) && (controls.length >= 1) ) {
            LDAPPasswordExpiringControl expgControl = null;
            for ( int i = 0; i < controls.length; i++ ) {
                if ( controls[i] instanceof LDAPPasswordExpiredControl ) {
                    return PASSWORD_EXPIRED;
                }
                if ( controls[i] instanceof LDAPPasswordExpiringControl ) {
                    expgControl = (LDAPPasswordExpiringControl)controls[i];
                }
            }

			if ( expgControl != null ) {
				try {
					/* Return the number of seconds until expiration */
					return expgControl.getSecondsToExpiration();
				} catch ( NumberFormatException e ) {
					System.err.println( "Unexpected message <" + 
                                        expgControl.getMsg() +
										"> in password expiring control" );
				}
			}
		}
		return NO_PASSWORD_CONTROLS;
	}

	private static void doUsage() {
		System.err.println( "Usage: PasswordPolicy HOST PORT DN PASSWORD" );
	}
}
