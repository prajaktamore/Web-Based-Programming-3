import java.awt.*;

public class AffixDialog extends Dialog {

    TextField m_tfPrefix = new TextField( 30 );
    TextField m_tfSuffix = new TextField( 30 );
    boolean m_bOK = false;
    FilterTest2 m_ft2;

    public AffixDialog ( Frame frParent, FilterTest2 ft2,
    			 String strPrefix, String strSuffix ) {
	super ( frParent, "Modify Filter Affixes", true );
		
		// We send in the FilterTest2 object to work around the
		// AWT modal dialog bug in Win32.
	m_ft2 = ft2;

	Panel pButton = new Panel();
	pButton.setLayout ( new GridLayout ( 1, 3, 2, 2 ) );
	Button bOK = new Button ( "OK" );
	Button bCancel = new Button ( "Cancel" );

	Font f = new Font ( "Helvetica", Font.BOLD, 12 );
	Label labPrefix = new Label ( "Prefix:" ); 
	Label labSuffix = new Label ( "Suffix:" ); 
	labPrefix.setFont ( f );
	labSuffix.setFont ( f );
	pButton.add ( bCancel );
	pButton.add ( new Label()  );
	pButton.add ( bOK );
	m_tfPrefix.setText ( strPrefix );
	m_tfSuffix.setText ( strSuffix );

	GridBagLayout gbl = new GridBagLayout();
	setLayout ( gbl );
	CUtil.AddGBComponent ( labPrefix, this, gbl,
				1, 1, 1, 1, 0.0, 0.0 ,
				GridBagConstraints.NONE,
				GridBagConstraints.WEST );

	CUtil.AddGBComponent ( labSuffix, this, gbl,
				1, 2, 1, 1, 0.0, 0.0 ,
				GridBagConstraints.NONE,
				GridBagConstraints.WEST );

	CUtil.AddGBComponent ( m_tfPrefix, this, gbl,
				2, 1, 1, 1, 1.0, 0.0 ,
				GridBagConstraints.HORIZONTAL,
				GridBagConstraints.CENTER );

	CUtil.AddGBComponent ( m_tfSuffix, this, gbl,
				2, 2, 1, 1, 1.0, 0.0 ,
				GridBagConstraints.HORIZONTAL,
				GridBagConstraints.CENTER );

	CUtil.AddGBComponent ( pButton, this, gbl,
				1, 3, 2, 1, 1.0, 0.0 ,
				GridBagConstraints.NONE,
				GridBagConstraints.CENTER );

	pack();
	show();
    }

    public boolean action ( Event evt, Object arg ) {
	if ( arg.equals ( "OK" ) ) {
	    m_ft2.setAffixesFromDialog ( m_tfPrefix.getText(), 
	    				 m_tfSuffix.getText() );
	    hide();
	    return true;

	} else if ( arg.equals ( "Cancel" ) ) {
	    hide();
	}
	return super.action ( evt, arg );
    }

}



	
