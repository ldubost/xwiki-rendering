
package org.xwiki.rendering.wikimodel;

import java.io.StringReader;

import org.xwiki.rendering.wikimodel.IWikiParser;
import org.xwiki.rendering.wikimodel.IWikiPrinter;
import org.xwiki.rendering.wikimodel.ParsePrinter;
import org.xwiki.rendering.wikimodel.xhtml.PrintListener;
import org.xwiki.rendering.wikimodel.xwiki.xwiki21.XWikiParser;

public class Parse extends PrintListener {
    private boolean fOutputEnabled;

    private boolean fShowSections;

    private boolean supportImage;

    private boolean supportDownload;

    public Parse(IWikiPrinter printer) {
        super(printer);
//        this.supportImage = false; 
//        this.supportDownload = false;
        this.fOutputEnabled = true;
        this.fShowSections = false; 
     }

    public Parse(IWikiPrinter printer, boolean supportImage, boolean supportDownload) {
        super(printer, supportImage, supportDownload);
//        this.supportImage = supportImage; 
//        this.supportDownload = supportDownload;
        this.fOutputEnabled = true;
        this.fShowSections = false; 
     }

    public void parseText(String str) throws Exception {
      IWikiParser parser = new XWikiParser(); 
      parser.parse(str, this);
    }

    public static String parse(String str) throws Exception {
      StringBuffer buf = new StringBuffer();
      Parse parse = new Parse(new ParsePrinter(buf), false, false); 
      parse.parseText(str);
      return buf.toString();
    }

    public static void main(String[] args) throws Exception {
      String str = "Hello **bold**";
      String result = parse(str);
      System.out.println(result);
    }
}
