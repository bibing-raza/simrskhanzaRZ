/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package widget;

import java.awt.Color;
import javax.swing.JTabbedPane;

/**
 *
 * @author khanzamedia
 */
public class TabPane extends JTabbedPane {
    public TabPane(){
        super();
        setBackground(new Color(250,255,245));    
        setForeground(new Color(90,120,80)); 
        //this.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(245,140,245)));       
    }
}
