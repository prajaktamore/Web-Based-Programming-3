import netscape.ldap.util.*;
import java.awt.*;
import java.applet.*;


/**
 * this is a test application that shows how to use the
 * netscape.ldap.util.LDAPFilter* classes.  
 * To run the applet, make sure that 
 * 
 *   netscape.ldap.util.*
 *
 * are in your classpath.  Then run "java FilterTest"
 *
 * The "Tag Pattern" is the string that you would use to select from the
 * set of filters (see the documentation on ldapfilter.conf).  And the
 * Value is the string that your user might potentially type in.
 *
 * You can set the filter Affixes by clicking on the "Modify Affixes" Button
 *
 * Try starting out with "xax500" for the Tag Pattern and
 * "yourname@netscape.com" for the Value.  The text view at the bottom of
 * the window will show you what filters were matched.  The "FILTER"
 * string is what you get back from LDAPFilter.getFilter() and is usable
 * in LDAPConnection.search().
 *
 * If you're looking for the bits of code that use the
 * LDAPFilterDescriptor, look for "LDAPFilter Example Code" in the
 * comments.  It is near all of the relavent code.
 */

public class FilterTest2 extends Applet {

    TextField m_tfValue;
    TextField m_tfTag;
    TextArea m_textView;
    AffixLabel m_labPrefixData;
    AffixLabel m_labSuffixData;
    TextField m_tfDialogPrefix;
    TextField m_tfDialogSuffix;
    Button m_bSetAffixes;
    Button m_bOK;
    Button m_bClear;
    Button m_bExit;
    Button m_bModify;

    boolean m_bIsApplet = true;

    FilterTest2Frame m_ftf2;

    LDAPFilterDescriptor m_filterDescriptor;

    public FilterTest2 () {
    }

    public FilterTest2 ( FilterTest2Frame ftf2 ) {
	m_ftf2 = ftf2;
    }

/*************************************************************************
 * LDAPFilter Example Code:
 * 
 * This function is called when the user selects the "Search"
 * button.  We get a list of filters in the filterList variable.
 * Then we cycle through each filter contained in the filterList
 * and print out some information about each.
 *************************************************************************/
    public void GetLDAPFilters () {
	String str1 = m_tfTag.getText();
	String str2 = m_tfValue.getText();
	LDAPFilterList  filterList;
	try {
	    filterList = m_filterDescriptor.getFilters ( str1, str2 );
	} catch ( IllegalArgumentException e ) {
	    AppendMessage ( "ERROR!\n" + e.toString() );
	    return;
	}

	LDAPFilter filter;
	AppendMessage ( "There are: " + filterList.numFilters() + " filters" );
	int nCount = 0;
	while ( filterList.hasMoreElements() ) {
	    nCount++;
	    filter = filterList.next();
	    AppendMessage ( "Filter Number: " + nCount );
	    AppendMessage ( filter.toString() );
	    AppendMessage ( "    Normally, you'd call filter.getFilter() to get the filter string: " );
	    AppendMessage ( "      " + filter.getFilter() );
	    AppendMessage ( "" );

	}
    }


/***************************************************************************
 * 
 * LDAPFilter Example Code
 *
 * setup the filter affixes.  I know that it's a humongous hack to have the
 * dialog call a function in the parent class, but we need to get around
 * the win32 modal dialog bug, and this is the quickest way I could think
 * of.  
 *
 * Not only do we setup gui elements here, we're calling
 * LDAPFilterDescriptor.setFilterAffixes() to set the affixes in the
 * LDAPFilterDescriptorObject as well.
 ****************************************************************************/
    void setAffixesFromDialog ( String strPrefix, String strSuffix ) {
	m_labPrefixData.setText ( strPrefix ); 
	m_labSuffixData.setText ( strSuffix ); 

	m_filterDescriptor.setFilterAffixes ( strPrefix, strSuffix );
    }
	
    public void init() {
	m_tfTag = new TextField ();
	m_tfValue = new TextField ();
	m_textView = new TextArea();
	m_labPrefixData = new AffixLabel ();
	m_labSuffixData = new AffixLabel ();

	Font fontLabel = new Font ( "Helvetica", Font.BOLD, 12 );
	Font fontButton = new Font ( "Helvetica", Font.PLAIN, 12 );

	Label labTag = new Label ( "Tag String:" ); labTag.setFont ( fontLabel );
	Label labValue = new Label ( "Value:" ); labValue.setFont ( fontLabel );
		// First setup the button panel...
	Panel pButton = new Panel();
	//GridBagLayout gblButton = new GridBagLayout();
	pButton.setLayout ( new GridLayout ( 4, 1, 2, 2 ) );

	m_bOK = new Button ( "Search" ); m_bOK.setFont ( fontButton );
	m_bClear = new Button ( "Clear" ); m_bClear.setFont ( fontButton );
	m_bExit = new Button ( "Exit" ); m_bExit.setFont ( fontButton );
	m_bModify = new Button ( "Modify Affixes" ); m_bModify.setFont ( fontButton );
	pButton.add ( m_bOK );
	pButton.add ( m_bClear );
	pButton.add ( new Label() );
	if ( ! m_bIsApplet ) {
	    pButton.add ( m_bExit );
	}


	Panel pInput = new Panel();
	GridBagLayout gblInput = new GridBagLayout();
	pInput.setLayout ( gblInput );

	CUtil.AddGBComponent ( labTag, pInput, gblInput,
				1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NONE, 
				GridBagConstraints.WEST );

	CUtil.AddGBComponent ( m_tfTag, pInput, gblInput,
				2, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.HORIZONTAL, 
				GridBagConstraints.CENTER );

	CUtil.AddGBComponent ( labValue, pInput, gblInput,
				1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.NONE, 
				GridBagConstraints.WEST );

	CUtil.AddGBComponent ( m_tfValue, pInput, gblInput,
				2, 2, 1, 1, 1.0, 0.0,
				GridBagConstraints.HORIZONTAL, 
				GridBagConstraints.CENTER );


	FilterAffixesPanel pAffixes = new FilterAffixesPanel();
	GridBagLayout gblAffixes = new GridBagLayout();
	pAffixes.setLayout ( gblAffixes );


	Label labPrefix = new Label ( "Prefix: " ); 
	Label labSuffix = new Label ( "Suffix: " );

	CUtil.AddGBComponent ( m_bModify, pAffixes, gblAffixes,
				1, 1, 1, 2, 0.0, 0.0,
				GridBagConstraints.NONE, 
				GridBagConstraints.CENTER );

	CUtil.AddGBComponent ( labPrefix, pAffixes, gblAffixes,
				2, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NONE, 
				GridBagConstraints.WEST,
				new Insets ( 6, 6, 0, 6 ),
				0, 0 ) ;

	CUtil.AddGBComponent ( labSuffix, pAffixes, gblAffixes,
				3, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NONE, 
				GridBagConstraints.WEST, 
				new Insets ( 6, 6, 0, 6 ),
				0, 0 ) ;

	CUtil.AddGBComponent ( m_labPrefixData, pAffixes, gblAffixes,
				2, 2, 1, 1, 1.0, 0.0,
				GridBagConstraints.HORIZONTAL, 
				GridBagConstraints.WEST,
				new Insets ( 0, 6, 6, 6  ), 
				0, 0 );

	CUtil.AddGBComponent ( m_labSuffixData, pAffixes, gblAffixes,
				3, 2, 1, 1, 1.0, 0.0,
				GridBagConstraints.HORIZONTAL, 
				GridBagConstraints.WEST, 
				new Insets ( 0, 6, 6, 6  ), 
				0, 0 );



	GridBagLayout gbl = new GridBagLayout();
	setLayout ( gbl );
	CUtil.AddGBComponent ( pInput, this, gbl,
				1, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.HORIZONTAL, 
				GridBagConstraints.CENTER );

	CUtil.AddGBComponent ( pButton, this, gbl,
				2, 1, 1, 2, 0.0, 0.0,
				GridBagConstraints.BOTH, 
				GridBagConstraints.CENTER );

	CUtil.AddGBComponent ( pAffixes, this, gbl,
				1, 2, 1, 1, 1.0, 0.0,
				GridBagConstraints.HORIZONTAL, 
				GridBagConstraints.CENTER );

	CUtil.AddGBComponent ( m_textView, this, gbl,
				1, 3, 2, 1, 1.0, 1.0,
				GridBagConstraints.BOTH,
				GridBagConstraints.CENTER );

	m_labPrefixData.setText ( "(&(objectClass=person)" );
	m_labSuffixData.setText ( ")" );
		//
		// We initialize the LDAPFilterDescriptor here.  
		//
	SetupLDAPFilterDescriptor();
    }


    public boolean action ( Event evt, Object arg ) {
	if ( evt.target == m_bOK ) {
	    GetLDAPFilters();

	} else if ( evt.target == m_bClear ) {
	    String str = m_textView.getText();
	    m_textView.replaceText ( "", 0, str.length() );

	} else if ( evt.target == m_bModify ) {
	    for ( Component c = getParent(); c != null; c = c.getParent() ) { 
		if ( c instanceof Frame ) { 
		    AffixDialog dlgAffix = new AffixDialog 
		    	( (Frame)c, this, m_labPrefixData.getText(), 
			  m_labSuffixData.getText() );
		    return true;
		}
	    }

	} else if ( evt.target == m_bExit ) {
	    if ( ! m_bIsApplet ) {
		System.exit(0);
	    }
	}
	return super.action ( evt, arg );
    };

    public boolean handleEvent ( Event evt ) {
	if ( evt.id == Event.WINDOW_DESTROY ) {
	    if ( ! m_bIsApplet ) {
		System.exit(0);
	    }
	}
	return super.handleEvent ( evt );

    }

    public void SetupLDAPFilterDescriptor () {
	try {
	    if ( m_bIsApplet ) {
		m_filterDescriptor = new LDAPFilterDescriptor ( 
			new java.net.URL ( "http://gromit.mcom.com/ldapfilter.conf" ) );
	    } else {
		m_filterDescriptor = new LDAPFilterDescriptor ( "ldapfilter.conf" );
	    }
	} catch ( Exception e ) {
	    AppendMessage ( "ERROR!!!\n" + e.toString() );
	    AppendMessage ( "Please fix the error and restart the program" );
	    DisableAll();
	}

		// LDAP Example: We initialize the FilterAffixes here.
	m_filterDescriptor.setFilterAffixes ( m_labPrefixData.getText(), 
					      m_labSuffixData.getText() );
    }

	/**
	 * Add a string to the text field.
	 */
    public void AppendMessage ( String str ) {
	m_textView.appendText ( str + "\n" );
    }

	/** 
	 * Disable all the buttons so the only thing the user can do is
	 * exit.
	 */
    public void DisableAll() {
	m_bOK.disable();
	m_bClear.disable();
	m_bModify.disable();
	m_tfTag.disable();
	m_tfValue.disable();
	m_textView.disable();
    }


    // This lets the application run as a standalone
    public static void main(String args[]) {
	FilterTest2Frame frParent = new FilterTest2Frame ( "Filter Test (AWT Version)" );
	frParent.resize ( 480, 375 );

	FilterTest2 appletFilterTest = new FilterTest2 ( frParent );
	frParent.Setup ( appletFilterTest );
	frParent.add ( "Center", appletFilterTest );

	appletFilterTest.m_bIsApplet = false;
	appletFilterTest.init();
	frParent.show();

    }

}

