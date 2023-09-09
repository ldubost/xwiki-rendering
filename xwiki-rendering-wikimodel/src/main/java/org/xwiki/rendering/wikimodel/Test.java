
package org.xwiki.rendering.wikimodel;

import java.io.StringReader;

import org.xwiki.rendering.wikimodel.IWemListener;
import org.xwiki.rendering.wikimodel.IWikiParser;
import org.xwiki.rendering.wikimodel.IWikiPrinter;
import org.xwiki.rendering.wikimodel.TestPrinter;
import org.xwiki.rendering.wikimodel.WikiParameters;
import org.xwiki.rendering.wikimodel.WikiParserException;
import org.xwiki.rendering.wikimodel.xhtml.PrintListener;
import org.xwiki.rendering.wikimodel.xwiki.xwiki21.XWikiParser;


public class Test extends PrintListener {
    private boolean fOutputEnabled;

    private boolean fShowSections;

    private boolean supportImage;

    private boolean supportDownload;

 public Test(IWikiPrinter printer) {
        super(printer);
//        this.supportImage = false; 
//        this.supportDownload = false;
        this.fOutputEnabled = true;
        this.fShowSections = false; 
     }

     public Test(IWikiPrinter printer, boolean supportImage, boolean supportDownload) {
        super(printer, supportImage, supportDownload);
//        this.supportImage = supportImage; 
//        this.supportDownload = supportDownload;
        this.fOutputEnabled = true;
        this.fShowSections = false; 
     }

  public void parse(String str) throws Exception {
      IWikiParser parser = new XWikiParser(); 
      parser.parse(str, this);
  }


  public static void main(String[] args) throws Exception {
      StringBuffer buf = new StringBuffer();
      Test test = new Test(new TestPrinter(buf), false, false); 
      String str = "Hello **bold**";
      test.parse(str);
      String testResult = buf.toString();
      test.println(testResult);
  }
}
