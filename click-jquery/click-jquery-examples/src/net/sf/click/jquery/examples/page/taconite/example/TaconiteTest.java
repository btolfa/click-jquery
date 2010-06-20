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

import java.util.ArrayList;
import java.util.List;
import net.sf.click.jquery.JQEvent;
import net.sf.click.jquery.behavior.JQBehavior;
import net.sf.click.jquery.examples.control.html.Div;
import net.sf.click.jquery.taconite.JQTaconite;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.extras.control.DateField;
import org.apache.click.Partial;
import org.apache.click.control.ActionLink;

/**
 *
 */
public class TaconiteTest extends Page {

	private static final long serialVersionUID = 1L;

    private ActionLink link = new ActionLink("link");

    private static final List SORTABLE_OPTIONS = new ArrayList();

    static {
        for (int i = 1; i <= 6; i++) {
            SORTABLE_OPTIONS.add(new Option(Integer.toString(i),
                "Drag to sort me " + i));
        }
    }

    @Override
    public void onInit() {
        Form form = new Form("form");
        Div wrapper = new Div();
        final String wrapperId = "date_wrapper";
        wrapper.setId(wrapperId);
        wrapper.add(new DateField("date"));
        form.add(wrapper);
        addControl(form);

        link.addBehavior(new JQBehavior() {

            public Partial onAjaxAction(Control source, JQEvent event) {
                JQTaconite partial = new JQTaconite();
                partial.replaceContent('#' + wrapperId, new DateField("date"));
                return partial;
            }
        });

        addControl(link);
    }
}
