/**
 * <p>Title: Algoritmo Kuhn-Munkres</p>
 *
 * <p>Description: es un algoritmo de optimización el cual resuelve problemas 
 * de asignación. La primera versión conocida del método Húngaro, fue inventado
 * y publicado por Harold Kuhn en 1955. Este fue revisado por James Munkres en 
 * 1957, y ha sido conocido desde entonces como el algoritmo Húngaro, 
 * el algoritmo de asignamiento de Munkres, o el algoritmo de Kuhn-Munkres.
 * 
 * El algoritmo desarrollado por Kuhn está basado fundamentalmente en los 
 * primeros trabajos de otros dos matemáticos Húngaros: Dénes König y 
 * Jenő Egerváry. La gran ventaja del método de Kuhn es que es fuertemente 
 * polinómico (ver Complejidad computacional para más detalles).
 * 
 * El algoritmo construye una solución del problema primal partiendo de una 
 * solución no admisible (que corresponde a una solución admisible del dual) 
 * haciéndola poco a poco más admisible.</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * @author Gustavo Bazan, Armando Bracho, Juan Sandomingo & Carlos Proano
 *
 * @version 1.0
 */

package ventana;

import java.awt.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import utils.*;

/**Ventana principal*/

public class Main extends JFrame {

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(new Point((screenSize.width - frameSize.width) / 2,
        (screenSize.height - frameSize.width) / 2));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        showInOutMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        process = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        aboutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setTitle("Kuhn-Munkres");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        mainPanel.setMinimumSize(new java.awt.Dimension(297, 200));
        mainPanel.setLayout(new java.awt.GridBagLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventana/Logo facyt.GIF"))); // NOI18N
        mainPanel.add(logo, new java.awt.GridBagConstraints());

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        mainMenu.setForeground(new java.awt.Color(0, 0, 255));

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        showInOutMenuItem.setMnemonic('S');
        showInOutMenuItem.setText("Show Files");
        showInOutMenuItem.setToolTipText("Muestra el contenido del archivo seleccionado");
        showInOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showInOutMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(showInOutMenuItem);
        fileMenu.add(jSeparator1);

        process.setMnemonic('P');
        process.setText("Process");
        process.setToolTipText("Procesar el archivo de entrada");
        process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processActionPerformed(evt);
            }
        });
        fileMenu.add(process);
        fileMenu.add(jSeparator2);

        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText("About");
        aboutMenuItem.setToolTipText("About Quadtree");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(aboutMenuItem);

        exitMenuItem.setMnemonic('E');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mainMenu.add(fileMenu);

        setJMenuBar(mainMenu);
    }// </editor-fold>//GEN-END:initComponents

    /**Procesar el archivo de entrada.*/
    private void processActionPerformed(java.awt.event.ActionEvent evt) {
//GEN-FIRST:event_processActionPerformed
        Show archivo = new Show(this);
        JFileChooser fc;
        fc = new JFileChooser();
        fc.addChoosableFileFilter(new InFilter());
        fc.setAcceptAllFileFilterUsed(false);
        
        int returnVal = fc.showOpenDialog(Main.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                archivo.process(file);
                archivo.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }            
    }//GEN-LAST:event_processActionPerformed

    /**Mostrar el archivo de entrada o el archivo de salida".*/
    private void showInOutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
//GEN-FIRST:event_showInMenuItemActionPerformed
        Show archivo = new Show(this);
        JFileChooser fc;
        fc = new JFileChooser();
        fc.addChoosableFileFilter(new InOutFilter());
        fc.setAcceptAllFileFilterUsed(false);
        
        int returnVal = fc.showOpenDialog(Main.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            archivo.showInOut(file);
            archivo.setVisible(true);
        }
    }//GEN-LAST:event_showInMenuItemActionPerformed

    /**Mostrar la ventana con la informacion del producto.*/
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
//GEN-FIRST:event_aboutMenuItemActionPerformed
        new About(this).setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    /**Salir*/
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
    
    /**Salir*/
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel logo;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem process;
    private javax.swing.JMenuItem showInOutMenuItem;
    // End of variables declaration//GEN-END:variables

}
