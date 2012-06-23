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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;
import munkres.*;

/**Ventana para mostrar informacion*/
public class Show extends JDialog {

    /**
     * Creates new form Show
     */
    public Show(JFrame parent) {
        //super(parent,true);
        initComponents();
        pack();
        Rectangle parentBounds = parent.getBounds();
        Dimension size = getSize();
        // Center in the parent
        int x = Math.max(0, parentBounds.x + (parentBounds.width - size.width) / 2);
        int y = Math.max(0, parentBounds.y + (parentBounds.height - size.height) / 2);
        setLocation(new Point(x, y));        
    }

     /** This method is called from within the constructor to
      * initialize the form.
      * WARNING: Do NOT modify this code.
      */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("");
        mainPanel.setLayout(new java.awt.GridBagLayout());

        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 11, 12, 12));
        closeButton.setMnemonic('C');
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(closeButton, gridBagConstraints);

        textArea.setColumns(25);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setRows(8);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textArea.setFocusable(false);
        scrollPane.setViewportView(textArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(scrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainPanel, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    /**Salir.*/
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed
    
    /**Muestra el Archivo de entrada o salida.*/
    public void showInOut(File file){
        
        setTitle(file.toString());
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            try {
                textArea.setText("");
                while ((str = br.readLine()) != null)
                {
                    str += "\n";
                    textArea.append(str);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    /**Procesa los datos, mostrandolos tanto por pantalla como guardandolos
     *en el archivo de salida
     */
    public void process(File file) throws IOException{
        
      setTitle(file.toString());
        try {
            FileReader     fr     = new FileReader(file);
            BufferedReader br     = new BufferedReader(fr);
            FileWriter fw = null;
            try {
                fw = new FileWriter("asignacion.out");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            int            Q, N, M;
            String         str  = new String();
            double[][] test;
            
            //munkres(w,test);
            

            try {
                str = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            textArea.setText("");
            Q = Integer.valueOf(str).intValue();
            for(int i=0; i<Q; i++){
                StringTokenizer aux;
                
                try {
                    str = br.readLine();
                    str = br.readLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                aux=new StringTokenizer(str);
                N = Integer.valueOf(aux.nextToken());
                M = Integer.valueOf(aux.nextToken());
                if(M<N){
                    M=N;
                }
                test = new double[M][M];
                int[] resultados = new int[M];
                double costo = 0;
		for(int j=0; j<N; j++)
                {
                    
                    try {
                        str = br.readLine();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    aux=new StringTokenizer(str);
                    int k=0;
                    while (aux.hasMoreElements()){
                        test[j][k]=Integer.valueOf(aux.nextToken());
                        k++;
                    }
                    
                }
                resultados = munkres.munkres(M,test);
                for (int j = 0; j < M; j++) {
                    costo = costo + test[j][resultados[j]];
                }
                textArea.append("Caso "+ String.valueOf(i+1)+"\n\n");
                for (int j = 0; j < M; j++) {
                    textArea.append("A sujeto "+String.valueOf(j+1).toString()+" asignarle trabajo "+String.valueOf(resultados[j]+1).toString()+" con un costo de: "+String.valueOf(test[j][resultados[j]]).toString());
                    textArea.append("\n");
                    fw.write("A sujeto"+String.valueOf(j+1).toString()+" asignarle trabajo "+String.valueOf(resultados[j]+1).toString()+" con un costo de: "+String.valueOf(test[j][resultados[j]]).toString()+"\n");
                }
                textArea.append("Costo total minimo: "+String.valueOf(costo).toString()+"\n\n");
                fw.write("Costo total minimo: "+String.valueOf(costo).toString()+"\n\n\n");
            }           
            
            try {
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.gc();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }        
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

}
