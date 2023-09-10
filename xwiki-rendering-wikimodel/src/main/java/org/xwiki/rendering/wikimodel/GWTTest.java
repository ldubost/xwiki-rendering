package org.xwiki.rendering.wikimodel;

import java.io.StringReader;

import org.xwiki.rendering.wikimodel.WikiModel;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
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
            return WikiModel.parse(str);
      } catch (Exception e) {
            return "Exception " + e.getMessage();
      }
	}

    public void onModuleLoad() {
        System.out.println("OnModuleLoaded Started");
        Element el = Document.get().getElementById("wikimodelcontent");
        if (el!=null) {
            final TextArea input        = new TextArea();
            final TextArea parseResults = new TextArea();
            KeyUpHandler handler = new KeyUpHandler() {
                @Override public void onKeyUp(KeyUpEvent event) { 
                    try {
                        String result = parse(input.getText());
                        parseResults.setText("Parsed Following Input OK :: \n\n" + input.getText() + "\n\n" + result);
                        Document.get().getElementById("wikimodelcontent").setInnerHTML(result);
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
}
