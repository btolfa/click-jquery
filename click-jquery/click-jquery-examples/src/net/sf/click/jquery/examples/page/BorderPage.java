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
package net.sf.click.jquery.examples.page;

import net.sf.click.jquery.examples.control.DesktopMenu;
import net.sf.click.jquery.examples.services.ApplicationRegistry;
import net.sf.click.jquery.examples.services.CustomerService;
import net.sf.click.jquery.examples.services.PostCodeService;
import net.sf.click.jquery.examples.util.JQUILibrary;
import org.apache.click.Page;
import org.apache.click.extras.control.Menu;
import org.apache.click.extras.control.MenuFactory;
import org.apache.click.util.ClickUtils;
import org.apache.click.util.HtmlStringBuffer;

public class BorderPage extends Page {

    private static final long serialVersionUID = 1L;

    private Menu rootMenu = new MenuFactory().getRootMenu(DesktopMenu.class);

    public BorderPage() {
        // Set the default JQuery UI style
        JQUILibrary.style = "ui-lightness";

        String className = getClass().getName();

        String shortName = className.substring(className.lastIndexOf('.') + 1);
        String title = ClickUtils.toLabel(shortName);
        addModel("title", title);

        final String srcPath = className.replace('.', '/') + ".java";
        addModel("srcPath", srcPath);

        addControl(rootMenu);
        addSourceCodeMenus(rootMenu, srcPath);
    }

    // Public Methods----------------------------------------------------------

    /**
     * @see #getTemplate()
     */
    @Override
    public String getTemplate() {
        return "border-template.htm";
    }

    public CustomerService getCustomerService() {
        return ApplicationRegistry.getInstance().getCustomerService();
    }

    public PostCodeService getPostCodeService() {
        return ApplicationRegistry.getInstance().getPostCodeService();
    }

    // Private Methods --------------------------------------------------------

    private void addSourceCodeMenus(final Menu rootMenu, final String srcPath) {

        final String contextPath = getContext().getRequest().getContextPath();

        // Add menu for Java Source code
        DesktopMenu pageJavaMenu = new DesktopMenu("pageJava", " Page Java") {
            @Override
            protected void renderMenuHref(HtmlStringBuffer buffer) {
                buffer.appendAttribute("href", contextPath + "/source-viewer.htm?filename=WEB-INF/classes/" + srcPath);
            }
        };

        if (!rootMenu.contains(pageJavaMenu)) {
            pageJavaMenu.setImageSrc("/assets/images/lightbulb1.png");
            pageJavaMenu.setTitle("Page Java source");
            pageJavaMenu.setTarget("_blank");
            rootMenu.add(pageJavaMenu);
        }

        // Add menu for Html Source code
        DesktopMenu pageHtmlMenu = new DesktopMenu("pageHtml", " Page HTML") {
            @Override
            protected void renderMenuHref(HtmlStringBuffer buffer) {
                // Render the path of the page
                buffer.appendAttribute("href", contextPath + "/source-viewer.htm?filename=" + BorderPage.this.getPath());
            }
        };

        if (!rootMenu.contains(pageHtmlMenu)) {
            pageHtmlMenu.setTitle("Page Content source");
            pageHtmlMenu.setTarget("_blank");
            pageHtmlMenu.setImageSrc("/assets/images/lightbulb2.png");
            rootMenu.add(pageHtmlMenu);
        }
    }
}
