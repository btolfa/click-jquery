/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.click.jquery.examples.page.charts.bar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.click.jquery.behavior.JQBehavior;
import net.sf.click.jquery.examples.page.BorderPage;
import org.apache.click.element.CssImport;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.util.HtmlStringBuffer;

/**
 *
 */
public class BarChart extends BorderPage {

    private static final long serialVersionUID = 1L;

    @Override
    public List getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();

            // Import excanvas.js for Internet Explorer which doesn't support the canvas element
            JsImport jsImport = new JsImport("/click/jquery/example/jqplot/excanvas.min.js");
            jsImport.setConditionalComment(JsImport.IF_IE);
            headElements.add(jsImport);

            // Import jquery.js
            headElements.add(new JsImport(JQBehavior.jqueryPath));

            // Import JQPlot libraries
            headElements.add(new JsImport("/click/jquery/example/jqplot/jquery.jqplot.min.js"));
            headElements.add(new JsImport("/click/jquery/example/jqplot/plugins/jqplot.barRenderer.min.js"));
            headElements.add(new JsImport("/click/jquery/example/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"));

            // Render BarChart JavaScript template
            Map jsModel = new HashMap();
            jsModel.put("data", getChartData());
            jsModel.put("label", "Browser usage %");
            headElements.add(new JsScript("/charts/bar/bar-chart.js", jsModel));

            headElements.add(new CssImport("/click/jquery/example/jqplot/jquery.jqplot.min.css"));
        }
        return headElements;
    }

    private String getChartData() {
        HtmlStringBuffer buffer = new HtmlStringBuffer(20);
        buffer.append("[[2006,69],[2007,55], [2008,40], [2009,35]],");
        buffer.append("[[2006,25],[2007,35], [2008,45], [2009,50]],");
        buffer.append("[[2006,5] ,[2007,6] , [2008,8] , [2009,8]],");
        buffer.append("[[2006,1] ,[2007,4] , [2008,7] , [2009,7]]");
        return buffer.toString();
    }
}
