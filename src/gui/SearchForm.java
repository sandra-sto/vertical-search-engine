/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classification.DurationResults;
import classification.TextClassificationProcess;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import search.SearchResult;

/**
 *
 * @author Sandra Stojanović
 */
public class SearchForm extends javax.swing.JFrame {
    TextClassificationProcess classificationProcess;
    DurationResults durationResults;
    public SearchForm() {
        initComponents();
        customizeForm();
        
        spinnerNumberOfDocs.setValue(10);
        
        buttonSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonSearch.setBackground(new Color(124, 170, 217));
          
        setDocumentsTable();
        setComboBoxClasses();
        prepareForClassification();
    }
    
    private void prepareForClassification(){
        classificationProcess = new TextClassificationProcess();
        classificationProcess.setModelFile("textModel");
        classificationProcess.deserializeParameters();
        durationResults = classificationProcess.getDurationResults();
    }
    
    private void customizeForm() {              
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Vertical Search Engine");
        setResizable(false);
        
        this.getContentPane().setBackground(new Color(248, 250, 250));
    }
    
     private void setComboBoxClasses(){
        comboClasses.addItem("none");
        String indexesForClasses = TextClassificationProcess.CLASSES_INDEX_DIR;
        File file = new File(indexesForClasses);
        for (File classIndex : file.listFiles()){
            comboClasses.addItem(classIndex.getName());
        }
        comboClasses.setSelectedIndex(0);
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tvQuery = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        spinnerNumberOfDocs = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableResultDocuments = new javax.swing.JTable();
        labelClass = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        searchLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboClasses = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vertical search engine");

        jLabel2.setText("Result:");

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        jLabel3.setText("Enter query:");

        jLabel4.setText("Number of results:");

        jScrollPane2.setViewportView(tableResultDocuments);

        label6.setText("Search:");

        searchLabel.setText("          ");

        jLabel5.setText("Specify class:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tvQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerNumberOfDocs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label6)
                        .addGap(35, 35, 35)
                        .addComponent(searchLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(labelClass)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tvQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(spinnerNumberOfDocs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSearch)
                    .addComponent(jLabel5)
                    .addComponent(comboClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label6)
                    .addComponent(searchLabel))
                .addGap(26, 26, 26)
                .addComponent(labelClass)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void querySearch(String query, int numOfDocs){
        SearchResult result;
         result = comboClasses.getSelectedItem().equals("none") ? 
                 classificationProcess.regularQuerySearch(query, numOfDocs) :
                 classificationProcess.querySearch(query, numOfDocs, TextClassificationProcess.CLASSES_INDEX_DIR + (String)comboClasses.getSelectedItem());
              
        showResultDocumentsInTable(result);
    }
  
    private void showResultDocumentsInTable(SearchResult result){
        DefaultTableModel model = (DefaultTableModel)tableResultDocuments.getModel();
        model.setRowCount(0);
        List<File> documents = result.getResultDocuments(); 
        Image image = Toolkit.getDefaultToolkit().getImage("images\\book2.png");
        for(int i = 0; i < documents.size(); i++){                      
            File document = documents.get(i);
            
            Object [] documentData = new Object[4];
            documentData[0] = i + 1;
            documentData[1] = document.getName();
            documentData[2] = document.getPath();
            documentData[3] = image;
            
            model.addRow(documentData);
        }
        tableResultDocuments.setModel(model);      
    }
    
    private void setColumnsWidths(){
        TableColumnModel columnModel = tableResultDocuments.getColumnModel();
        
        float width = tableResultDocuments.getWidth();
        columnModel.getColumn(0).setPreferredWidth((int)(width * 0.1));
        columnModel.getColumn(1).setPreferredWidth((int)(width * 0.3));
        columnModel.getColumn(2).setPreferredWidth((int)(width * 0.4));
        
        tableResultDocuments.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }
    
    private void setTableColumns(){
        Object[] columns = new Object[4];
        columns[0] = "No";
        columns[1] = "Name";
        columns[2] = "Path";
        columns[3] = "View";
        
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        tableResultDocuments.setModel(model);
    }
        
    private void setDocumentsTable(){
        tableResultDocuments.setAutoscrolls(true);
        tableResultDocuments.getTableHeader().setBackground(new Color(149, 198, 250));
        tableResultDocuments.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));

        setTableColumns();
        setColumnsWidths();
        
        setCellRenderers();
        setMouseListenerForTableCell();
    }
    
    private void setCellRenderers(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableResultDocuments.getColumn("No").setCellRenderer(centerRenderer);
        
        tableResultDocuments.getColumn("Path").setCellRenderer(new ToolTipRenderer());        
        tableResultDocuments.getColumn("View").setCellRenderer(new ImageIconRenderer());
    }
    
    private void setMouseListenerForTableCell(){
         tableResultDocuments.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == tableResultDocuments) {
                    JTable target = (JTable) e.getSource();
                    
                    int row = target.getSelectedRow();
                    if (target.getSelectedColumn() == 3) {
                        openFile((String) target.getValueAt(row, 2));
                    }
                }
            }
        });
    }
    
    private void openFile(String file){
        try{
            Desktop.getDesktop().open(new File(file)); 
        }catch(IOException exc){
            
        } 
    }
    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        searchLabel.setText(" ");
        
        String query = tvQuery.getText();
        Integer numOfDocs = (Integer) spinnerNumberOfDocs.getValue();
    
        querySearch(query, numOfDocs); 
        
        printDurationResults ();       
        durationResults.clearDurationResults(); 
    }//GEN-LAST:event_buttonSearchActionPerformed

    
    public void printDurationResults(){
        durationResults = classificationProcess.getDurationResults();
        searchLabel.setText(durationResults.getDurationResult("search"));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSearch;
    private javax.swing.JComboBox<String> comboClasses;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel labelClass;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JSpinner spinnerNumberOfDocs;
    private javax.swing.JTable tableResultDocuments;
    private javax.swing.JTextField tvQuery;
    // End of variables declaration//GEN-END:variables

}