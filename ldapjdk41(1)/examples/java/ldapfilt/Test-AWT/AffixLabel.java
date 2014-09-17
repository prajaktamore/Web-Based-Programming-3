import java.awt.*;

public class AffixLabel extends Label {
    public AffixLabel() {
	super();
    }
    public AffixLabel ( String str ) {
	super ( str );
    }

    public void paint ( Graphics g ) {
	Color c = getBackground();
	Color darker = c.darker();
	Color darker2 = darker.darker();
	Color lighter = c.brighter();
	int width = size().width - 1;
	int height = size().height - 1;
	g.setColor ( darker2 );
	g.drawLine ( 0, 0, width, 0 );
	g.drawLine ( 0, 0, 0, height );

	g.setColor ( lighter );
	g.drawLine ( 0, height, width, height );
	g.drawLine ( width, 0, width, height );
    }
}
