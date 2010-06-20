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
package net.sf.click.jquery.examples.page.ajax;

import net.sf.click.jquery.JQEvent;
import net.sf.click.jquery.behavior.JQBehavior;
import net.sf.click.jquery.examples.page.BorderPage;
import net.sf.click.jquery.taconite.JQTaconite;
import org.apache.click.Control;
import org.apache.click.Partial;
import org.apache.click.control.ActionLink;
import org.apache.commons.lang.math.NumberUtils;

public class ActionLinkDemo extends BorderPage {

	private static final long serialVersionUID = 1L;

    private ActionLink link = new ActionLink("link", "Counter");

    public ActionLinkDemo() {
        addControl(link);
        link.setId("linkId");

        link.addBehavior(new JQBehavior() {

            @Override
            public Partial onAction(Control source, JQEvent event) {
                JQTaconite partial = new JQTaconite();

                incrementCounter();

                // Replace the link with the updated link
                partial.replace(link);

                // Using a CSS selector, replace the target counter with the
                // updated link value
                partial.replaceContent("#target", link.getValue());
                return partial;
            }
        });
    }

    private void incrementCounter() {
        int value = NumberUtils.toInt(link.getValue());
        value++;
        link.setValue(Integer.toString(value));
    }
}
