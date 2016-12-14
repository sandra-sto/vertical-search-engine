/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;
import javax.swing.ImageIcon;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Sandra Stojanović
 */
public class ImageIconRenderer extends DefaultTableCellRenderer {
  public ImageIconRenderer() { 
      super(); 
  }

  @Override
  public void setValue(Object value) {
      Image image = (Image)value;
      Image scalled = image.getScaledInstance(15, 15, 30);
      setIcon(new ImageIcon(scalled));
      setHorizontalAlignment(CENTER);
    }
}