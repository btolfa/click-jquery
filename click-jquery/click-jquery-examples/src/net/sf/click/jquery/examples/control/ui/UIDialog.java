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
package net.sf.click.jquery.examples.control.ui;

import java.util.List;
import net.sf.click.jquery.behavior.JQBehavior;
import net.sf.click.jquery.examples.control.html.Div;
import net.sf.click.jquery.examples.util.JQUILibrary;
import org.apache.click.element.CssImport;
import org.apache.click.element.JsImport;

/**
 * Provide a Dialog control based on the JQuery UI Dialog widget:
 * http://docs.jquery.com/UI/Dialog.
 */
public class UIDialog extends Div {

    // ----------------------------------------------------------- Constructors

    /**
     * Create a default dialog.
     */
    public UIDialog() {
        this(null);
    }

    /**
     * Create a dialog with the given name.
     *
     * @param name the name of the dialog
     */
    public UIDialog(String name) {
        super(name);
        setAttribute("class", JQUILibrary.style);
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Return the JQDialog resources: 
     * {@link net.sf.clickclick.jquery.helper.JQHelper#jqueryImport},
     * {@link net.sf.clickclick.jquery.util.UIUtils#jqueryUICssImport},
     * {@link net.sf.clickclick.jquery.util.UIUtils#jqueryUIJsImport}.
     *
     * @return the list of head elements
     */
    @Override
    public List getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();

            CssImport cssImport = new CssImport(JQUILibrary.jqueryUICssPath);
            cssImport.setAttribute("media", "screen");
            headElements.add(cssImport);

            JsImport jsImport = new JsImport(JQBehavior.jqueryPath);
            headElements.add(jsImport);

            jsImport = new JsImport(JQUILibrary.jqueryUIJsPath);
            headElements.add(jsImport);
        }
        return headElements;
    }
}
