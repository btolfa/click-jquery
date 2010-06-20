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
package net.sf.click.jquery.examples.page.repeater;

import java.util.List;
import net.sf.click.jquery.examples.control.panel.HorizontalPanel;
import net.sf.click.jquery.examples.control.panel.VerticalPanel;
import net.sf.click.jquery.examples.control.repeater.Repeater;
import net.sf.click.jquery.examples.control.repeater.RepeaterRow;
import net.sf.click.jquery.examples.domain.Customer;
import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.extras.control.SubmitLink;

public class RepeatFormPage extends AbstractRepeatPage {

    @Override
    public void onInit() {
        super.onInit();

        Form form = new Form("form");

        addControl(form);

        repeater = new Repeater("repeater") {

            public void buildRow(final Object item, final RepeaterRow row,
                final int index) {

                HorizontalPanel horizontalPanel = new HorizontalPanel();
                VerticalPanel verticalPanel = new VerticalPanel();
                verticalPanel.addStyleClass("vertical-panel");

                final SubmitLink moveUp = new SubmitLink("up");
                final SubmitLink moveDown = new SubmitLink("down");
                verticalPanel.add(moveUp);
                verticalPanel.add(moveDown);

                Form form = new Form("form");
                row.add(form);

                FieldSet fieldSet = new FieldSet("customer");
                form.add(horizontalPanel);
                horizontalPanel.add(fieldSet);
                horizontalPanel.add(verticalPanel);

                fieldSet.add(new TextField("name")).setRequired(true);

                Submit save = new Submit("save");
                save.setActionListener(new ActionListener() {
                    public boolean onAction(Control source) {
                        return onSubmit(item, index);
                    }
                });
                fieldSet.add(save);

                Submit insert = new Submit("insert");
                insert.setActionListener(new ActionListener() {
                    public boolean onAction(Control source) {
                        Customer customer = new Customer();
                        repeater.insertItem(customer, index);
                        return true;
                    }
                });
                fieldSet.add(insert);

                Submit delete = new Submit("delete");
                delete.setActionListener(new ActionListener() {
                    public boolean onAction(Control source) {
                        repeater.removeItem(item);
                        return true;
                    }
                });
                fieldSet.add(delete);

                moveUp.setActionListener(new ActionListener() {
                    public boolean onAction(Control source) {
                        repeater.moveUp(item);
                        return true;
                    }
                });

                moveDown.setActionListener(new ActionListener() {
                    public boolean onAction(Control source) {
                        repeater.moveDown(item);
                        return true;
                    }
                });

                form.copyFrom(item);
            }
        };

        repeater.setDataProvider(new DataProvider() {
            public List<Customer> getData() {
                return getTopCustomers();
            }
        });

        addControl(repeater);
    }

    public boolean onSubmit(Object item, int index) {
        RepeaterRow row = (RepeaterRow) repeater.getControls().get(index);
        Form form = (Form) row.getControl("form");
        if (form.isValid()) {
            repeater.copyTo(item);
        }
        return true;
    }

    @Override
    public void onRender() {
        toggleLinks(getTopCustomers().size());
    }

    // -------------------------------------------------------- Private Methods

    private List getTopCustomers() {
        List<Customer> customers = getCustomerService().getCustomers();
        int size = Math.min(5, customers.size());
        return getCustomerService().getCustomers().subList(0, size);
    }
}
