import java.awt.*;

public class FilterAffixesPanel extends Panel {
    public FilterAffixesPanel() {
	super();
    }

    public void paint ( Graphics g ) {
	Color c = getBackground();
	Color darker = c.darker();
	Color lighter = c.brighter();
	g.setColor ( lighter );
	g.drawRect ( 1, 1, size().width - 2, size().height - 2 );
	g.setColor ( darker );
	g.drawRect ( 0, 0, size().width - 2, size().height - 2 );
    }
}
