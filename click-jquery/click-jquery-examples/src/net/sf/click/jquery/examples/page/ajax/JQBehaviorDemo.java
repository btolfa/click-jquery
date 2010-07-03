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
import org.apache.click.control.Button;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;

public class JQBehaviorDemo extends BorderPage {

	private static final long serialVersionUID = 1L;

    private Form form = new Form("form");

    private Button start = new Button("start") {

        @Override
        public String getCssSelector() {
            return ".callback";
        }
    };

    private Submit end = new Submit("end");

    private JQBehavior behavior = new JQBehavior(JQEvent.CLICK);// ("helper-id")

    public JQBehaviorDemo() {
        // Bind any element with the callback class to the click event
        //helper.bind(".callback", JQEvent.CLICK);

        // Disable further bindings
        //behavior.setBindingDisabled(true);

        // Replace JQActionButton helper with a custom helper
        start.addBehavior(behavior);

        start.addBehavior(new JQBehavior() {

            @Override
            public Partial onAction(Control source, JQEvent event) {
                JQTaconite taconite = new JQTaconite();
                taconite.after(form, "<div>Start clicked!</div>");
                return taconite;
            }
        });
        start.setAttribute("class", "callback");

        end.addBehavior(new JQBehavior() {
            @Override
            public Partial onAction(Control source, JQEvent event) {
                JQTaconite taconite = new JQTaconite();
                taconite.after(form, "<div>End clicked!</div>");
                return taconite;
            }
        });
        end.setAttribute("class", "callback");

        form.add(start);
        form.add(end);
        addControl(form);
    }

    /*
    public List getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            helper.addHeadElements(headElements);
        }
        return headElements;
    }*/
}
