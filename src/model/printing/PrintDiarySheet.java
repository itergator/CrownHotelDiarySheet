package model.printing;

/*
 * Application to print Crown Hotel Diary Sheet
 * 
 * Copyright 2014 L Hughes
 * 
 * PrintDiarySheet.java
 * 
 */

/*
 * Abstract class PrintDiarySheet.java
 */

/*
 * import java files
 */

import java.awt.Font;
import java.awt.print.Printable;

import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import javax.swing.JOptionPane;

public abstract class PrintDiarySheet implements Printable, PrintJobListener{
	
	/*
	 * public feedback from printer
	 */
	public boolean sent = false;
	public boolean complete = false;
	public boolean error = false;
	public boolean cancelled = false;
	
	public String dayString;
	public String monthString;
	
	/*
	 * private constants for printing
	 */
	protected static final int ZERO = 0;
	protected static final int ROOMFONT = 14;
	protected static final int DATEFONT = 18;
	protected static final int SPACING = 18;
	protected static final int DATESPACING = 24;
	
	/*
	 * font constants
	 */
	
    protected static Font boldArial = new Font ( "Arial", Font.BOLD , ROOMFONT );
    protected static Font regularArial = new Font ( "Arial", Font.PLAIN , ROOMFONT );
    protected static Font dateArial = new Font ( "Arial", Font.BOLD , DATEFONT );
	
    /*
     * abstract method for room type
     */
	protected abstract String roomType( int room );
	
	/*
	 * (non-Javadoc)
	 * @see javax.print.event.PrintJobListener#printDataTransferCompleted(javax.print.event.PrintJobEvent)
	 * 
	 * printJobListener methods
	 */
	
	@Override
	public void printDataTransferCompleted(PrintJobEvent pje) {
		// TODO Auto-generated method stub
		sent = true;
	}

	@Override
	public void printJobCompleted(PrintJobEvent pje) {
		// TODO Auto-generated method stub
		complete = true;
	}

	@Override
	public void printJobFailed(PrintJobEvent pje) {
		// TODO Auto-generated method stub
		error = true;
	}

	@Override
	public void printJobCanceled(PrintJobEvent pje) {
		// TODO Auto-generated method stub
		cancelled = true;
	}

	@Override
	public void printJobNoMoreEvents(PrintJobEvent pje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printJobRequiresAttention(PrintJobEvent pje) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "alert", "printer requires attention...", JOptionPane.ERROR_MESSAGE);
		error = true;
	}
}
