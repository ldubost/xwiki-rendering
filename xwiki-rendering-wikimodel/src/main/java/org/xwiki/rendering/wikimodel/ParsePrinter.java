  
  package org.xwiki.rendering.wikimodel;
  

  import org.xwiki.rendering.wikimodel.IWikiPrinter;

public class ParsePrinter implements IWikiPrinter {
        
     private StringBuffer buf;

            public ParsePrinter(StringBuffer buf) {
                this.buf = buf;
            }
            public void print(String str)
            {
                buf.append(str);
            }


            public void println(String str)
            {
                buf.append(str);
                buf.append("\n");
            }
    
  }