/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.click.jquery.examples.page.ajax.form;

import net.sf.click.jquery.examples.control.html.HtmlLabel;
import org.apache.click.control.AbstractControl;
import org.apache.click.control.Field;
import org.apache.click.util.HtmlStringBuffer;

/**
 *
 */
public class FormRow extends AbstractControl {

	private static final long serialVersionUID = 1L;

    private Field field;

    public FormRow() {
    }

    public FormRow(Field field) {
        this.field = field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public void render(HtmlStringBuffer buffer) {
        buffer.elementStart("tr");
        buffer.appendAttribute("class", "fields");
        buffer.appendAttribute("id", "row_" + field.getId());
        buffer.closeTag();

        buffer.elementStart("td");
        buffer.appendAttribute("class", "fields");
        buffer.appendAttribute("align", "left");
        buffer.closeTag();
        HtmlLabel label = new HtmlLabel(field);
        buffer.append(label);
        buffer.elementEnd("td");

        buffer.elementStart("td");
        buffer.appendAttribute("align", "left");
        buffer.closeTag();
        buffer.append(field);
        buffer.elementEnd("td");

        buffer.elementEnd("tr");
    }
}
