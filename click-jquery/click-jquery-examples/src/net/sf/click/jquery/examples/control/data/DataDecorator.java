/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.click.jquery.examples.control.data;

import org.apache.click.Context;
import org.apache.click.util.HtmlStringBuffer;

/**
 *
 */
public interface DataDecorator {

    public void render(HtmlStringBuffer buffer, Object object, Context context);
}
