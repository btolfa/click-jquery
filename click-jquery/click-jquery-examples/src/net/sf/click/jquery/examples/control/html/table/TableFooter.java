/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.click.jquery.examples.control.html.table;

import org.apache.click.control.AbstractContainer;

/**
 *
 */
public class TableFooter extends AbstractContainer {

    public final String getTag() {
        return "tfoot";
    }

    /**
     * Add the given headers to the table. A new {@link HeaderRow} is created
     * and for each header a new {@link HeaderCell} is created and added to the
     * row.
     * <p/>
     * This method delegates to {@link #insert(java.lang.Object, int)}.
     *
     * @param text the text array to add to the row
     * @return the header row that was created
     */
    public Row setFooter(Object... textArray) {
        Row row = new Row();
        add(row);
        for (Object text : textArray) {
            row.add(text);
        }
        return row;
    }
}
