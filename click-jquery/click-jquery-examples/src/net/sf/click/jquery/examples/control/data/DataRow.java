/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.click.jquery.examples.control.data;

import net.sf.click.jquery.examples.control.html.table.Row;

/**
 *
 */
public class DataRow extends Row {

    // ----------------------------------------------------------- Constructors

    /**
     * Create a default table row.
     */
    public DataRow() {
    }

    /**
     * Create a table row with the given name.
     *
     * @param name the name of the row
     */
    public DataRow(String name) {
        super(name);
    }

    // --------------------------------------------------------- Public methods

    public DataCell add(Object dataSource, String expr) {
        return add(dataSource, expr, (String) null);
    }


    public DataCell add(Object dataSource, String expr, String format) {
        if (dataSource == null) {
            throw new IllegalArgumentException("dataSource cannot be null");
        }
        if (expr == null) {
            throw new IllegalArgumentException("expr cannot be null");
        }

        DataCell dc = new DataCell(dataSource, expr, format);
        add(dc);
        return dc;
    }

    public DataCell add(Object dataSource, String expr, DataDecorator decorator) {
        if (dataSource == null) {
            throw new IllegalArgumentException("dataSource cannot be null");
        }
        if (expr == null) {
            throw new IllegalArgumentException("expr cannot be null");
        }

        DataCell dc = new DataCell(dataSource, expr);
        dc.setDecorator(decorator);
        add(dc);
        return dc;
    }
}
