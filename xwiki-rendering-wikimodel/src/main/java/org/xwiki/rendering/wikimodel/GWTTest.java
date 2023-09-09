package org.xwiki.rendering.wikimodel;

import java.io.StringReader;

import org.xwiki.rendering.wikimodel.Test;
import org.xwiki.rendering.wikimodel.TestPrinter;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;

import com.google.gwt.core.client.EntryPoint;

public class GWTTest implements EntryPoint {

    public GWTTest() {
        System.out.println("Constructor");
    }

	public String parse(String str) {
     try {
            StringBuffer buf = new StringBuffer();
            Test test = new Test(new TestPrinter(buf), false, false); 
            test.parse(str);
            String testResult = buf.toString();
            return testResult;
      } catch (Exception e) {
            return "Exception " + e.getMessage();
      }
	}

    public void onModuleLoad() {
        System.out.println("OnModuleLoaded Started");
        //SimplePanel  contentPanel = new SimplePanel ();
        final TextArea input        = new TextArea();
        final TextArea parseResults = new TextArea();
        KeyUpHandler handler = new KeyUpHandler() {
            @Override public void onKeyUp(KeyUpEvent event) { 
                try {
                    String result = parse(input.getText());
                    parseResults.setText("Parsed Following Input OK :: \n\n" + input.getText() + "\n\n" + result);
                    Document.get().getElementById("content").setInnerHTML(result);
                } catch (Exception e) {
                    parseResults.setText("ERROR IN PARSE:: \n\n" + e.getMessage());
                }
            }
        };
        // Adds a handler so that we parse every time a key up event is received from the left panel
        input.addKeyUpHandler(handler);
        SplitLayoutPanel splitPanel = new SplitLayoutPanel();
        splitPanel.addWest(input, 600);
        splitPanel.add    (parseResults);
        RootLayoutPanel rp = RootLayoutPanel.get();
        rp.add(splitPanel);
}
}
