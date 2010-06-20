/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.click.jquery.examples.control.paginator;

import org.apache.click.control.Renderable;

/**
 *
 */
public interface Paginator extends Renderable {

    /**
     * Set the current page value.
     *
     * @param currentPage the current page value
     */
    public void setCurrentPage(int currentPage);

    /**
     * Return the current page value.
     *
     * @return the current page value
     */
    public int getCurrentPage();

    public int getNextPage();

    public void calcPageTotal(int pageSize, int rows);
}
