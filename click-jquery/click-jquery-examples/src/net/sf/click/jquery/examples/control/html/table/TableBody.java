/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.click.jquery.examples.control.html.table;

import org.apache.click.control.AbstractContainer;

/**
 *
 */
public class TableBody extends AbstractContainer {

    public final String getTag() {
        return "tbody";
    }

    /**
     * Add the given text array to the table. A new {@link Row} is created and
     * for each text object a new {@link Cell} is created and added to the row.
     * <p/>
     * This method delegates to {@link #insert(java.lang.Object, int)}.
     *
     * @param text the text array to add to the row
     * @return the row that was created
     */
    public Row addRow(Object... textArray) {
        Row row = new Row();
        add(row);
        for (Object text : textArray) {
            row.add(text);
        }
        return row;
    }
}
