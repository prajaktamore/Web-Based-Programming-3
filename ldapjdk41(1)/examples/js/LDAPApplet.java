/*
	A basic extension of the java.applet.Applet class, to export
	methods of the LDAP client SDK
 */
import java.awt.*;
import java.util.*;
import java.applet.*;
import java.io.*;
import netscape.ldap.*;
import netscape.security.*;

public class LDAPApplet extends Applet {

	public void init()
	{
		super.init();
		setLayout(null);
		addNotify();
		resize(10,10);
	}

	public boolean handleEvent(java.awt.Event event) 
	{
		return super.handleEvent(event);
	}

	// Read all attributes of the entry corresponding to this
	// distinguished name, and return them formatted with \r\n
	// after each line, and a two-character indentation for each
	// attribute value.
	public String read( String dn )
	{
		String str = new String();
		try 
		{
			/* In Communicator, you have to have the following privilege to
			   make network connections */
			PrivilegeManager.enablePrivilege("UniversalConnect");

			/* If not in a Netscape environment, just fall through to a
			   connection failure */
			String host = getParameter("host");
			int port = Integer.parseInt(getParameter("port"));

			// Create an LDAP connection to the specified host and port
			LDAPConnection conn = new LDAPConnection();
			conn.connect ( host, port );
			// Read all attributes
			LDAPEntry entry = conn.read( dn );
			LDAPAttributeSet attrs = entry.getAttributeSet();
			Enumeration enum = attrs.getAttributes();
			// Enumerate attributes
			while( enum.hasMoreElements() )
			{
				LDAPAttribute attr = (LDAPAttribute)enum.nextElement();
				str += attr.getName() + "\r\n";
				Enumeration vals = attr.getStringValues();
                if (vals != null) {
				  // Enumerate values of this attribute
				  while( vals.hasMoreElements() )
					str += "  " + (String)vals.nextElement() + "\r\n";
                }
			}
		}
		// Return all exceptions in the string
		catch( Exception e )
		{
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PrintStream pStream = new PrintStream( bStream );
			e.printStackTrace( pStream );
			str += e.toString() + "; " + bStream.toString();
			return str;
		}
		
		return str;
	}


	// Search down from the specified directory base for all
	// entries matching the specified filter, and return
	// search results
	private LDAPSearchResults searchAttrs( String base, String filter,
										   String[] attrNames )
		throws Exception
	{
		String host = getParameter("host");
		int port = Integer.parseInt(getParameter("port"));
		try
		{
			// Create an LDAP connection to the specified host and port
			LDAPConnection conn = new LDAPConnection();
			conn.connect ( host, port );
			// Search
			LDAPSearchResults results = conn.search( base,
						LDAPConnection.SCOPE_SUB,
					   filter, attrNames, false );
			return results;
		}
		catch( NullPointerException e )
		{
			// The current build of the SDK crashes if the filter is
			// not correctly formed (also if an invalid attribute is
			// specified in attrNames)
			throw new NullPointerException(
				"Probably incorrect filter syntax" );
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	// Search down from the specified directory base for all
	// entries matching the specified filter, and return all
	// matching distinguished names, separated by \r\n.
	public String search( String base, String filter )
	{
		String str = new String();
		try 
		{
			/* In Dogbert, you have to have the following privilege to
			   make network connections */
			PrivilegeManager.enablePrivilege("UniversalConnect");
			PrivilegeManager.enablePrivilege("UniversalThreadAccess");
			/* If not in a Netscape environment, just fall through to a
			   connection failure */

			// Specify some attribute, so we don't unnecessarily get
			// all attributes of each entry
			String[] attrNames = new String[1];
			attrNames[0] = "cn";
			LDAPSearchResults results = searchAttrs( base, filter,
														 attrNames );
			// Enumerate results
			while( results.hasMoreElements() )
			{
				LDAPEntry entry = results.next();
				// Get the distinguished name of the entry
				String dn = entry.getDN();
				str += dn + "\r\n";
			}
		}
		// Return all exceptions in the string
		catch( Exception e )
		{
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PrintStream pStream = new PrintStream( bStream );
			e.printStackTrace( pStream );
			str += e.toString() + "; " + bStream.toString();
			return str;
		}
		return str;
	}

	// Search down from the specified directory base for all
	// entries matching the specified filter, and return
	// a single attribute's values for each match, separated by \r\n.
	public String searchOneAttr( String base, String filter,
								 String attrName )
	{
		String str = new String();
		String[] attrNames = new String[1];
		attrNames[0] = attrName;
		try
		{
			LDAPSearchResults results = searchAttrs( base, filter, attrNames );
			// Enumerate results
			while( results.hasMoreElements() )
			{
				LDAPEntry entry = results.next();
				LDAPAttributeSet attrs = entry.getAttributeSet();
				Enumeration enum = attrs.getAttributes();
				// Enumerate attributes
				while( enum.hasMoreElements() )
				{
					LDAPAttribute attr = (LDAPAttribute)enum.nextElement();
					Enumeration vals = attr.getStringValues();
					// Enumerate values of this attribute
					while( vals.hasMoreElements() )
						str +=	(String)vals.nextElement() + "\r\n";
				}
			}
		}
		// Return all exceptions in the string
		catch( Exception e )
		{
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PrintStream pStream = new PrintStream( bStream );
			e.printStackTrace( pStream );
			str += e.toString() + "; " + bStream.toString();
			return str;
		}
		return str;
	}
}
