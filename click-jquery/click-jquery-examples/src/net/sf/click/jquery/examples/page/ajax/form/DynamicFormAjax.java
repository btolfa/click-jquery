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
import net.sf.click.jquery.examples.control.JQActionButton;
import net.sf.click.jquery.examples.page.BorderPage;
import net.sf.click.jquery.taconite.JQTaconite;
import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.ActionResult;
import org.apache.click.control.Checkbox;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Radio;
import org.apache.click.control.RadioGroup;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;

/**
 * Provides a dynamically built Form using Ajax.
 */
public class DynamicFormAjax extends BorderPage {

	private static final long serialVersionUID = 1L;

    private Form form = new Form("form");

    private JQActionButton start = new JQActionButton("start");

    private RadioGroup typeGroup = new RadioGroup("type");

    private FieldSet desktopFS = new FieldSet("desktop");

    private FieldSet laptopFS = new FieldSet("laptop");

    private Submit save = new Submit("save");

    public DynamicFormAjax() {
        setStateful(true);

        start.addBehavior(new JQBehavior() {

            @Override
            public ActionResult onAction(Control source, JQEvent event) {
                JQTaconite taconite = new JQTaconite();
                if (!form.contains(typeGroup)) {
                    form.add(typeGroup);
                    buildAjaxForm(typeGroup);
                    taconite.replace(form);
                }
                return taconite;
            }
        });

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

        laptop.addBehavior(new JQBehavior() {

            @Override
            public ActionResult onAction(Control source, JQEvent event) {
                JQTaconite taconite = new JQTaconite();
                if (form.contains(laptopFS)) {
                    return taconite;
                }

                typeGroup.setValue(laptop.getValue());
                form.remove(desktopFS);
                form.add(laptopFS);
                initLaptop(laptopFS);
                taconite.replace(form);
                return taconite;
            }
        });

        final Radio desktop = new Radio("desktop");

        desktop.addBehavior(new JQBehavior() {

            @Override
            public ActionResult onAction(Control source, JQEvent event) {
                JQTaconite taconite = new JQTaconite();
                if (form.contains(desktopFS)) {
                    return taconite;
                }

                typeGroup.setValue(desktop.getValue());
                form.remove(laptopFS);
                form.add(desktopFS);
                initDesktop(desktopFS);
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
