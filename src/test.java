import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.minseo.*;


public class test 
{
	private static Button btnRun;
	private static Browser browser;
	private static StyledText editeur;
	private static Button btnSave;
	private static Button btnQuit;
	 
	private static com.minseo.compiler.Compiler compiler=new com.minseo.compiler.Compiler();
	 
	public static void main (String [] args) throws IOException 
	{
		Display display = new Display ();
		Shell shlHop = new Shell(display);
		shlHop.setText("MinseoBasic");
		shlHop.setMinimumSize(new Point(500, 800));
		shlHop.setSize(500, 573);
		
		browser = new Browser(shlHop, SWT.NONE);

		browser.setBounds(10, 19, 472, 316);
		browser.setUrl("file://"+new java.io.File(".").getCanonicalPath()+"/Test.html");
		new CustomFunction (browser, "theJavaFunction");
		
		shlHop.pack();
		browser.addListener(SWT.MenuDetect, new Listener()
			{
			@Override
			public void handleEvent(Event event) {
			event.doit = false;
			}
			}); 
		btnRun = new Button(shlHop, SWT.NONE);
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) 
			{
				
				String e=compiler.Process(editeur.getText());
				browser.execute("eval('"+e+"');");
//				browser.refresh();
			}
		});
		btnRun.setBounds(403, 586, 68, 23);
		btnRun.setText("Run");
		
		editeur = new StyledText(shlHop, SWT.BORDER);
		editeur.setBounds(10, 360, 472, 207);
		
		final File f=new File("Test.txt");
		try {
			BufferedReader r=new BufferedReader(new FileReader(f));
			String line;
			StringBuffer result = new StringBuffer();
			while ((line = r.readLine()) != null)
			{
				result.append(line);
				result.append("\n");
			}			
			editeur.setText(result.toString());
			r.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		btnSave = new Button(shlHop, SWT.NONE);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				String s=editeur.getText();
				File f=new File("Test.txt");
				try {
					Writer w=new BufferedWriter(new FileWriter(f));
					w.write(s);
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnSave.setBounds(403, 615, 68, 23);
		btnSave.setText("Save");
		
		btnQuit = new Button(shlHop, SWT.NONE);
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(403, 644, 68, 23);
		btnQuit.setText("Quit");
		shlHop.open ();
		while (!shlHop.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
	 
	 static class CustomFunction extends BrowserFunction 
	 {
	    Browser browser = null;
	    CustomFunction (Browser browser, String name) {
	        super (browser, name);
	        this.browser = browser;
	    }
	    public Object function (Object[] arguments) 
	    {
	        return null;
	    }
	 }
}
