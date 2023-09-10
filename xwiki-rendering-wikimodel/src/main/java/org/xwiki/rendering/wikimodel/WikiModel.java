
package org.xwiki.rendering.wikimodel;

import java.io.StringReader;

import org.xwiki.rendering.wikimodel.ParsePrinter;
import org.xwiki.rendering.wikimodel.Parse;
import jsinterop.annotations.JsType;
import jsinterop.annotations.JsPackage;

@JsType(namespace = JsPackage.GLOBAL)
public class WikiModel  {
 
    public WikiModel() {
    }

    public static String parse(String str) throws Exception {
      StringBuffer buf = new StringBuffer();
      Parse parse = new Parse(new ParsePrinter(buf), false, false); 
      parse.parseText(str);
      return buf.toString();
    }
}
