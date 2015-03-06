package model.printing;

/*
 * Application to print Crown Hotel Diary Sheet
 * 
 * Copyright 2014 L Hughes
 * 
 * Rooms11To19.java
 * 
 */

import java.awt.*;
import java.awt.print.*;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;


public class Rooms11To19 extends PrintDiarySheet {
		
	/*
	 * Rooms11To19
	 * 
	 * setup for printing sheet including
	 * 		- page margins
	 * 		- A4 size
	 * 		- lookup services linked to computer
	 * 		- chooses preferred printer
	 * 
	 * prints page
	 */
	public Rooms11To19( String currentDay, String currentMonth) {
		// TODO Auto-generated constructor stub
		dayString = currentDay;
		monthString = currentMonth;
		
		/* Construct the print request specification.
         * The print data is a Printable object.
         * the request additonally specifies a job name, 2 copies, and
         * landscape orientation of the media.
         */
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        
        pras.add(MediaSizeName.ISO_A4);
        int x = 10; //left and right margin
        int y = 10; //top and bottom margin. Note that bottom margin cannot be less than 15 mm
        int w = 190; //Width
        int h = 277; //Height
        int units = MediaPrintableArea.MM;
        pras.add(new MediaPrintableArea(x, y, w, h, units));

        /* locate a print service that can handle the request */
        PrintService services =
                PrintServiceLookup.lookupDefaultPrintService();

        if (services != null) {

                /* create a print job for the chosen service */
                DocPrintJob pj = services.createPrintJob();

                try {
                        /* 
                        * Create a Doc object to hold the print data.
                        */
                        Doc doc = new SimpleDoc(this, flavor, null);

                        pj.addPrintJobListener(this);
                        
                        /* print the doc as specified */
                        pj.print(doc, pras);

                        /*
                        * Do not explicitly call System.exit() when print returns.
                        * Printing can be asynchronous so may be executing in a
                        * separate thread.
                        * If you want to explicitly exit the VM, use a print job 
                        * listener to be notified when it is safe to do so.
                        */

                } catch (PrintException e) { 
                        System.err.println(e);
                }
        }
		
	}

	/*
	 * print
	 * setup page graphics prints
	 */
	
	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		// TODO Auto-generated method stub
		
		if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
 
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
 
        int currentY = DATESPACING;
        
        String dateString = dayString + " " + monthString;
        
        FontMetrics fm = g.getFontMetrics( dateArial );
        int textWidth = fm.stringWidth( dateString );
        
        g.setFont( dateArial );
        
        int dateTextPointX = ( int )pf.getImageableWidth() - textWidth;
        
        g.drawString(dateString, dateTextPointX, currentY );
        
        currentY = currentY + DATESPACING;
        
        for ( int i = 11; i <= 19; i++ ){
        	if ( i == 13 ) continue;
        	g.setFont( boldArial );
        	g.drawString("Room " + i + ": " + roomType( i ), ZERO, currentY );
        	g.setFont( regularArial );
        	currentY = currentY + SPACING;
        	g.drawString("Name: ................................................... Tel No.: ............................................ Nights: .......", ZERO, currentY );
        	currentY = currentY + SPACING;
        	g.drawString("C/C No.: ................................................................. Ex. Date: ............... Sec. Code: .........", ZERO, currentY );
        	currentY = currentY + SPACING;
        	g.drawString("Room Cost/night: .................. Total Room Cost: ....................... B/B.: ............. Paid?: .......", ZERO, currentY );
        	currentY = currentY + SPACING;
        }
        
        g.setFont( boldArial );
    	g.drawString("Cottage", ZERO, currentY );
    	g.setFont( regularArial );
    	currentY = currentY + SPACING;
    	g.drawString("Name: ................................................... Tel No.: ............................................ Nights: .......", ZERO, currentY );
    	currentY = currentY + SPACING;
    	g.drawString("C/C No.: ................................................................. Ex. Date: ............... Sec. Code: .........", ZERO, currentY );
    	currentY = currentY + SPACING;
    	g.drawString("Room Cost/night: .................. Total Room Cost: ....................... B/B.: ............. Paid?: .......", ZERO, currentY );
    	currentY = currentY + DATESPACING;
    	
    	String jenynsString = "If full telephone Jenyns 01366 383366";
    	fm = g.getFontMetrics( dateArial );
    	textWidth = fm.stringWidth( jenynsString );
    	
    	int xcoord = ( int )( pf.getImageableWidth() / 2  ) - ( textWidth / 2 );
		
    	g.setFont( dateArial );
    	
    	g.drawString(jenynsString, xcoord, currentY );
    	
		return PAGE_EXISTS;
	}
	
	/*
	 * retrieve room type eg twin room
	 * 
	 * @param int room
	 * @return room string
	 * 
	 */

	@Override
	protected String roomType(int room) {
		String str = null;
		
		switch ( room ){
			case 11:
				str = "Twin";
				break;
			case 12:
				str = "Double, Two Singles";
				break;
			case 14:
				str = "King Size";
				break;
			case 15:
			case 16:
			case 17:
			case 18:
				str = "Double";
				break;
			case 19:
				str = "Double, One Single";
				break;
		}
		
		return str;
	}
}
