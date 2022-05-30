package wordpredictorgui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class WordPredictorGUI extends javax.swing.JFrame {

    private static final Trie currentTrie;
    
    static {
        currentTrie = new Trie();
    }
    
    /**
     * Creates new form WordPredictorGUI
     */
    public WordPredictorGUI() {
        initComponents();
        Scanner scanner = null;
        try {
            InputStream in = WordPredictorGUI.class.getResourceAsStream("simple-dictionary.txt");
            scanner = new Scanner(in);
        } catch (Exception e) {
            System.out.println("Incorrect file path, please change the file path and try again. :D");
            return;
        }
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String currentWord = scanner.nextLine();
            words.add(currentWord);
            currentTrie.insert(currentWord);    
        }
        DefaultTableModel model = (DefaultTableModel) possibleWordsTable.getModel();
        // calling the getRowCount() method to obtain the number of rows in the table
        int numRows = model.getRowCount();
        // looping through the rows (zero-based)
        for (int i = numRows - 1; i >= 0; i--) {
            // deleting each row with its index value
            model.removeRow(i);
        }
    }
    
    private ArrayList<String> obtainPrefixWords(String text) {
        ArrayList<String> currentWords = new ArrayList<>();
        if (text.length() != 0) 
            currentWords = currentTrie.possibleWords(text);
        return currentWords;
    }
    
    private void provideRelatedWords() {
        ArrayList<String> relatedWordsFound = obtainPrefixWords(wordTextField.getText());
        // declaring and instantiating a DefaultTableModel object to be able to add rows to it later on
        DefaultTableModel model = (DefaultTableModel) possibleWordsTable.getModel();
        // calling the getRowCount() method to obtain the number of rows in the table
        int numRows = model.getRowCount();
        // looping through the rows (zero-based)
        for (int i = numRows - 1; i >= 0; i--) {
            // deleting each row with its index value
            model.removeRow(i);
        }
        // declaring and instantiating a DefaultTableModel object to be able to add rows to it later on
        model = (DefaultTableModel) possibleWordsTable.getModel();
        // adding words to it now
        for (String word: relatedWordsFound) {
            model.addRow(new Object[]{word});
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detailsLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        possibleWordsTable = new javax.swing.JTable();
        wordTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setName("mainFrame"); // NOI18N

        detailsLabel.setFont(new java.awt.Font("Cascadia Code PL", 0, 11)); // NOI18N
        detailsLabel.setText("Enter a prefix to see possible words in the dictionary with that prefix.");

        titleLabel.setFont(new java.awt.Font("Cascadia Code PL", 1, 25)); // NOI18N
        titleLabel.setText("Word Predictor");

        possibleWordsTable.getTableHeader().setFont(new java.awt.Font("Cascadia Code PL", 0, 12));
        possibleWordsTable.setFont(new java.awt.Font("Cascadia Code PL", 0, 12)); // NOI18N
        possibleWordsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Possible Words"
            }
        ));
        jScrollPane1.setViewportView(possibleWordsTable);
        possibleWordsTable.getAccessibleContext().setAccessibleDescription("");

        wordTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(wordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(138, 138, 138))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(detailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addComponent(detailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(wordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        wordTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                provideRelatedWords();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                provideRelatedWords();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                provideRelatedWords();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WordPredictorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WordPredictorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WordPredictorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WordPredictorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WordPredictorGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel detailsLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable possibleWordsTable;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField wordTextField;
    // End of variables declaration//GEN-END:variables
}
