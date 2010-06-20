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
package net.sf.click.jquery.examples.page.charts.dashboard;

import net.sf.click.jquery.JQEvent;
import net.sf.click.jquery.behavior.JQBehavior;
import net.sf.click.jquery.examples.control.Window;
import net.sf.click.jquery.examples.page.BorderPage;
import net.sf.click.jquery.taconite.JQTaconite;
import org.apache.click.Control;
import org.apache.click.Partial;
import org.apache.click.util.HtmlStringBuffer;

/**
 *
 */
public class DashboardPage extends BorderPage {

    private static final long serialVersionUID = 1L;

    @Override
    public void onInit() {
        final Window dashboard = new Window("dashboard");
        dashboard.setWidth(700);
        dashboard.setHeight(250);

        // Set the event to DOMREADY, meaning the Ajax request is invoked
        // as soon as the browser DOM is available
        JQBehavior behavior = new JQBehavior(JQEvent.DOMREADY) {
            public Partial onAjaxAction(Control source) {
                JQTaconite taconite = new JQTaconite();
                taconite.append("#dashboard-content", new RevenueChart("chart"));
                taconite.append("#dashboard-content", new RevenueChart("chart1"));
                taconite.append("#dashboard-content", new RevenueChart("chart2"));
                return taconite;
            }
        };

        dashboard.addBehavior(behavior);
        setupHelper(behavior, dashboard);
        addControl(dashboard);
    }

    // -------------------------------------------------------- Private Methods

    private void setupHelper(JQBehavior behavior, Control indicatorTarget) {
        // Set the target of the Ajax indicator (busy indicator)
        //helper.setBusyIndicatorTarget(indicatorTarget);

        HtmlStringBuffer buffer = new HtmlStringBuffer();
        String contextPath = getContext().getRequest().getContextPath();

        // Set a custom indicator message that displays an animated gif
        /*
        helper.setIndicatorMessage("<b>Loading...&nbsp;</b> <img src=\""
            + contextPath + "/assets/images/ajax-loader.gif\"/>");
         */

        // Set indicator options to align the message box in the upper left
        // hand corner
        buffer.append("centerX: false,");
        buffer.append("centerY: false,");
        buffer.append("css: {");
        buffer.append("  textAlign: 'right',");
        buffer.append("  padding: 5,");
        buffer.append("  width: '',");
        buffer.append("  top:  '5px',");
        buffer.append("  left: '5px'");
        buffer.append("}");

        //helper.setIndicatorOptions(buffer.toString());

        //helper.ajaxify();
    }
}
