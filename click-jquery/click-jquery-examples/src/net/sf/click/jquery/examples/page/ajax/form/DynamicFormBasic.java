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
package net.sf.click.jquery.examples.page.ajax.form;

import net.sf.click.jquery.JQEvent;
import net.sf.click.jquery.behavior.JQBehavior;
import net.sf.click.jquery.examples.page.BorderPage;
import net.sf.click.jquery.taconite.JQTaconite;
import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Partial;
import org.apache.click.ajax.AjaxBehavior;
import org.apache.click.control.Checkbox;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Radio;
import org.apache.click.control.RadioGroup;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;

/**
 * Provides a dynamically built Form using Ajax.
 *
 * Couple of things to note:
 *
 * - This demo uses a single CssSelector binding called '.callback'. All controls
 * with the class 'callback' will be ajaxified.
 *
 * - A JQBehavior is registered on the start button
 *
 * - AjaxBehaviors are registered on the radios
 */
public class DynamicFormBasic extends BorderPage {

	  private static final long serialVersionUID = 1L;

    private Form form = new Form("form");

    private RadioGroup typeGroup = new RadioGroup("type");

    private FieldSet desktopFS = new FieldSet("desktop");

    private FieldSet laptopFS = new FieldSet("laptop");

    private String cssSelector = ".callback";

    private Submit start = new Submit("start");

    private Submit save = new Submit("save");

    public DynamicFormBasic() {
        // Note: Page is set to stateful
        setStateful(true);

        JQBehavior behavior = new JQBehavior(JQEvent.CLICK) {

            @Override
            public Partial onAction(Control source, JQEvent event) {
                JQTaconite taconite = new JQTaconite();
                if (!form.contains(typeGroup)) {
                    form.add(typeGroup);
                    buildAjaxForm(typeGroup);
                    taconite.replace(form);
                }
                return taconite;
            }
        };
        behavior.setCssSelector(cssSelector);
        start.addBehavior(behavior);
        start.setAttribute("class", "callback");

        form.add(start);
        addControl(form);
    }

    private void buildAjaxForm(final RadioGroup type) {
        form.add(save);

        save.setActionListener(new ActionListener() {

            public boolean onAction(Control source) {
                System.out.println("SAVED");
                return true;
            }
        });

        final Radio laptop = new Radio("laptop");
        laptop.setAttribute("class", "callback");

        laptop.addBehavior(new AjaxBehavior() {

            @Override
            public Partial onAction(Control source) {
                JQTaconite taconite = new JQTaconite();
                if (form.contains(laptopFS)) {
                    return taconite;
                }

                typeGroup.setValue(laptop.getValue());

                form.remove(desktopFS);

                initLaptop(laptopFS);
                form.add(laptopFS);

                taconite.replace(form);
                return taconite;
            }
        });

        final Radio desktop = new Radio("desktop");
        desktop.setAttribute("class", "callback");

        desktop.addBehavior(new AjaxBehavior() {

            @Override
            public Partial onAction(Control source) {
                JQTaconite taconite = new JQTaconite();
                if (form.contains(desktopFS)) {
                    return taconite;
                }

                typeGroup.setValue(desktop.getValue());

                form.remove(laptopFS);

                initDesktop(desktopFS);
                form.add(desktopFS);

                taconite.replace(form);
                return taconite;
            }
        });

        type.add(desktop);
        type.add(laptop);
    }

    private void initDesktop(FieldSet fieldSet) {
        if (!fieldSet.getControls().isEmpty()) {
            return;
        }
        fieldSet.add(new TextField("quantity", true));
    }

    private void initLaptop(FieldSet fieldSet) {
        if (!fieldSet.getControls().isEmpty()) {
            return;
        }
        fieldSet.add(new TextField("quantity", true));
        fieldSet.add(new Checkbox("adapter", "Include adapter?"));
    }
}
