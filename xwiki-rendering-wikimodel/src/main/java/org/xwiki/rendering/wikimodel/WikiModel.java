/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.rendering.wikimodel;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSBody;
import org.xwiki.rendering.wikimodel.IWemListener;
import org.xwiki.rendering.wikimodel.IWikiParser;
import org.xwiki.rendering.wikimodel.IWikiPrinter;
import org.xwiki.rendering.wikimodel.xhtml.PrintListener;
import org.xwiki.rendering.wikimodel.xwiki.xwiki21.XWikiParser;
import java.io.StringReader;


interface Exported extends JSObject {
    String parse(String source);
}

public class WikiModel {
    public static void main(String[] args) {
        exportAPI(new Exported() {
            @Override
            public String parse(String source) {
              String result = "loading";
              try {
                 StringBuffer resultBuffer = new StringBuffer();
                 IWikiPrinter pPrinter = newPrinter(resultBuffer);
                 IWemListener pListener = new PrintListener(pPrinter, false, false);
                 IWikiParser parser = new XWikiParser();
                 StringReader reader = new StringReader(source);
                 parser.parse(reader, pListener);
                 result = resultBuffer.toString();
              } catch (Exception e) {
                 result = e.getMessage();
                 e.printStackTrace();
              }
              return result;
            }
        });
    }

    /**
     * @param buf
     * @return
     */
    protected static IWikiPrinter newPrinter(final StringBuffer buf)
    {
        IWikiPrinter printer = new IWikiPrinter()
        {
            public void print(String str)
            {
                buf.append(str);
            }

            public void println(String str)
            {
                buf.append(str);
                buf.append("\n");
            }
        };
        return printer;
    }


    @JSBody(params = "o", script = "main.api = o;")
    private static native void exportAPI(Exported o);
}
