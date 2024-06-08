package View;

import Model.Emprestimo;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FRMHistorico extends javax.swing.JFrame {
    
    private Emprestimo objetoemprestimo;
    
    public FRMHistorico() {
        initComponents();
        this.objetoemprestimo = new Emprestimo();
        this.carregaTabela();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jThistorico = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBhistorico_voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Todos os relatórios");

        jThistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, "", null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, "", null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Telefone", "Ferramenta", "Data retirada", "Prev. entrega"
            }
        ));
        jThistorico.setToolTipText("");
        jThistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jThistoricoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jThistorico);

        jLabel1.setText("Amigo que faz mais empréstimos:");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jBhistorico_voltar.setText("Voltar");
        jBhistorico_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhistorico_voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jBhistorico_voltar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jBhistorico_voltar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
    private void jBhistorico_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhistorico_voltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBhistorico_voltarActionPerformed

    private void jThistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jThistoricoMouseClicked
        if (this.jThistorico.getSelectedRow() != -1) {
            String nome = this.jThistorico.getValueAt(this.jThistorico.getSelectedRow(), 1).toString();
            String telefone = this.jThistorico.getValueAt(this.jThistorico.getSelectedRow(), 2).toString();
            String ferramenta = this.jThistorico.getValueAt(this.jThistorico.getSelectedRow(), 3).toString();
        
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
            String dataRetiradaString = this.jThistorico.getValueAt(this.jThistorico.getSelectedRow(), 4).toString();
            String dataPrevistaDevolucaoString = this.jThistorico.getValueAt(this.jThistorico.getSelectedRow(), 5).toString();

            try {
                Date dataRetirada = sdf.parse(dataRetiradaString);
                Date dataPrevistaDevolucao = sdf.parse(dataPrevistaDevolucaoString);
                // Agora você pode usar dataRetirada e dataPrevistaDevolucao conforme necessário
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jThistoricoMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public void carregaTabela() {
    DefaultTableModel modelo = (DefaultTableModel) this.jThistorico.getModel();
    modelo.setNumRows(0);
    ArrayList<Emprestimo> minhalista = objetoemprestimo.getMinhaLista();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    for (Emprestimo a : minhalista) {
        modelo.addRow(new Object[]{
            a.getId(),
            a.getNomeAmigo(),
            a.getTelefone(), // Incluindo o telefone
            a.getNomeFerramenta(),
            sdf.format(a.getDataRetirada()),
            sdf.format(a.getDataPrevistaDevolucao())
        });
    }
}

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FRMHistorico().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBhistorico_voltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable jThistorico;
    // End of variables declaration//GEN-END:variables
}
