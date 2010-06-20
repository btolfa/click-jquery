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
package net.sf.click.jquery.examples.page.taconite.example;

import net.sf.click.jquery.JQEvent;
import net.sf.click.jquery.behavior.JQBehavior;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import net.sf.click.jquery.examples.page.BorderPage;
import net.sf.click.jquery.taconite.JQCommand;
import net.sf.click.jquery.taconite.JQTaconite;
import org.apache.click.Partial;
import org.apache.commons.lang.math.NumberUtils;

/**
 *
 */
public class DecoratorTest extends BorderPage {

    private static final long serialVersionUID = 1L;

    final String cssSelector = "a.test";

    public ActionLink button = new ActionLink("button", "Counter: 0") {
        @Override
        public String getCssSelector() {
            return cssSelector;
        }
    };

    //public ActionLink button2 = new ActionLink("button2", "Counter: 0");

    @Override
    public void onInit() {
        button.setAttribute("class", "test");
        //button2.setAttribute("class", "test");

        button.addBehavior(new JQBehavior(JQEvent.CLICK) {

            @Override
            public Partial onAction(Control source, JQEvent eventType) {
                JQTaconite partial = new JQTaconite();
                int count = NumberUtils.toInt(button.getParameter("count"));
                ++count;
                button.setParameter("count", Integer.toString(count));
                button.setLabel("Counter: " + Integer.toString(count));
                JQCommand command = new JQCommand(JQTaconite.REPLACE, cssSelector, button).characterData(true);

                // link normally contian '&' which breaks XML parsing. TODO update
                // Click Link's to use &amp; instead

                //command.add(button2);
                partial.add(command);
                return partial;
            }
        });
    }
}
