/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.click.jquery.examples.control.html.table;

import org.apache.click.control.AbstractContainer;

/**
 *
 */
public class TableHeader extends AbstractContainer {

    public final String getTag() {
        return "thead";
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
    public HeaderRow setColumns(Object... textArray) {
        HeaderRow headerRow = new HeaderRow();
        add(headerRow);
        for (Object text : textArray) {
            headerRow.add(text);
        }
        return headerRow;
    }
}
