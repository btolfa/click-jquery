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
import net.sf.click.jquery.examples.control.html.table.HtmlTable;
import net.sf.click.jquery.examples.control.html.table.Row;
import net.sf.click.jquery.examples.control.html.table.TableBody;
import net.sf.click.jquery.examples.control.html.table.TableHeader;
import net.sf.click.jquery.examples.control.paginator.SimplePaginator;
import net.sf.click.jquery.examples.control.repeater.Repeater;
import net.sf.click.jquery.examples.control.repeater.RepeaterRow;
import net.sf.click.jquery.examples.domain.Customer;
import net.sf.click.jquery.examples.page.BorderPage;
import org.apache.click.Context;
import org.apache.click.dataprovider.DataProvider;

/**
 *
 */
public class BasicRepeaterPage extends BorderPage {

    private Repeater repeater;

    @Override
    public void onInit() {
        Context context = getContext();

        int currentPage = 0;
        int pageSize = 10;
        int rowCount = 3000;
        SimplePaginator paginator = new SimplePaginator("paginator");
        //paginator.setCurrentPage(currentPage);
        paginator.calcPageTotal(pageSize, rowCount);

        addControl(paginator);
        
        final HtmlTable table = new HtmlTable("table");
        table.setAttribute("class", "gray");
        table.setBorder(0);

        TableHeader thead = new TableHeader();
        thead.setColumns("Name", "Age", "Holdings");
        table.add(thead);

        TableBody tbody = new TableBody();
        table.add(tbody);

        repeater = new Repeater() {

            @Override
            public void buildRow(Object item, RepeaterRow row, int index) {
                Customer customer = (Customer) item;

                Row tableRow = new Row();
                row.add(tableRow);

                tableRow.add(customer.getName());
                tableRow.add(customer.getAge());
                tableRow.add(getFormat().currency(customer.getHoldings()));
            }
        };

        tbody.add(repeater);

        repeater.setDataProvider(new DataProvider() {
            public List<Customer> getData() {
                return getTopCustomers();
            }
        });

        addControl(table);
    }

    public List<Customer> getTopCustomers() {
        List<Customer> customers = getCustomerService().getCustomers();
        int size = Math.min(5, customers.size());
        return getCustomerService().getCustomers().subList(0, size);
    }
}
