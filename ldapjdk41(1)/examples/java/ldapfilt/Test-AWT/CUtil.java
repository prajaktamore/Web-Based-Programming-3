import java.awt.*;
import java.util.*;
/*
 *
 * CUtil
 *
 * A class that will hold utility functions.
 *
 */
public class CUtil {

	public static final boolean c_bDebug = false;
	
	public static void AddGBComponent ( 
			Component		comp,		// The component we're adding
			Container		cont,		// Container we're inserting into
			GridBagLayout	gbLayout,	// gridbag we're inserting into
			int				gridx,		// The X grid position
			int				gridy,		// The Y grid position
			int				gridw,		// The width (in columns)
			int				gridh,		// The Height (in columns)
			double			weightx,	// The Horizontal weight
			double			weighty,	// The Vertical weight
			int				fill,		// How the component should fill
			int				anchor ) {	// Where component is positioned
		AddGBComponent ( comp,
						 cont,			 
						 gbLayout,
						 gridx, gridy,
						 gridw, gridh,
						 weightx, weighty,
						 fill, 
						 anchor,
						 new Insets ( 3, 3, 3, 3 ),
						 0, 0 );
	}


	public static void AddGBComponent ( 
			Component		comp,		// The component we're adding
			Container		cont,		// Container we're inserting into
			GridBagLayout	gbLayout,	// gridbag we're inserting into
			int				gridx,		// The X grid position
			int				gridy,		// The Y grid position
			int				gridw,		// The width (in columns)
			int				gridh,		// The Height (in columns)
			double			weightx,	// The Horizontal weight
			double			weighty,	// The Vertical weight
			int				fill,		// How the component should fill
			int				anchor,		// Where component is positioned
			Insets			insets,		// The insets for this gridbox
			int				ipadx,		// The x padding
			int				ipady ) {	// The y padding  
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = gridw;
		c.gridheight = gridh;
		c.weightx = weightx;
		c.weighty = weighty;
		c.anchor = anchor;
		c.ipadx = 3;
		c.ipady = 3;
		c.insets = insets;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.fill = fill;
		gbLayout.setConstraints ( comp, c );
		cont.add ( comp );
	}

	public static void DPrint ( String s ) {
		if ( CUtil.c_bDebug ) {
			System.out.println ( s );
		}
	}

	public static void DPrintln ( String s ) {
		if ( CUtil.c_bDebug ) {
			System.out.println ( s );
		}
	}

	public static String ConvertDate ( Date d ) {
		String strMonth = String.valueOf ( d.getMonth() + 1 );
		String strHour = ( d.getHours() < 10 ) ? "0" + String.valueOf ( d.getHours() ) : String.valueOf ( d.getHours() ); 
		String strMin = ( d.getMinutes() < 10 ) ? "0" + String.valueOf ( d.getMinutes() ) : String.valueOf ( d.getMinutes() ); 
		String strSec = ( d.getSeconds() < 10 ) ? "0" + String.valueOf ( d.getSeconds() ) : String.valueOf ( d.getSeconds() ); 

		String strDate = new String ( strMonth + "/" + d.getDate() + "/" + d.getYear() + ", " +
								      strHour + ":" + strMin + ":" + strSec );

		return strDate;
	}

}

