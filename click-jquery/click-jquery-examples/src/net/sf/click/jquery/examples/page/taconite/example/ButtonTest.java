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
import net.sf.click.jquery.examples.page.BorderPage;
import net.sf.click.jquery.taconite.JQTaconite;
import org.apache.click.Control;
import org.apache.click.Partial;
import org.apache.click.control.Submit;
import org.apache.commons.lang.math.NumberUtils;

/**
 *
 */
public class ButtonTest extends BorderPage {

	private static final long serialVersionUID = 1L;

    public Submit button = new Submit("button", "Counter: 0");

    public Submit button2 = new Submit("button2", "Counter: 0");

    @Override
    public void onInit() {
        JQBehavior behavior = new JQBehavior(JQEvent.CLICK) {

            @Override
            public Partial onAction(Control source, JQEvent eventType) {
                JQTaconite partial = new JQTaconite();

                // Make simpler counter
                int count = NumberUtils.toInt(button.getValue().substring(9));

                button.setLabel("Counter: " + Integer.toString(++count));

                partial.replace(button);
                partial.remove(button2);
                partial.after('#' + button.getId(), button2);
                return partial;
            }
        };

        button.addStyleClass("test");
        button.addBehavior(behavior);

        JQBehavior behavior2 = new JQBehavior(JQEvent.CLICK) {
            @Override
            public Partial onAction(Control source, JQEvent eventType) {
                JQTaconite partial = new JQTaconite();
                int count = NumberUtils.toInt(button2.getValue().substring(9));
                ++count;

                button2.setLabel("Counter: " + Integer.toString(count));

                partial.replace(button2);
                return partial;
            }
        };

        button2.addBehavior(behavior2);
    }
}
