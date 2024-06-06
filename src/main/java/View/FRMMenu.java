/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.HistoricoPersonalizadoDAO;
import Model.HistoricoPersonalizado;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 10723114087
 */
public class FRMMenu extends javax.swing.JFrame {

    private Connection connection;
    /**
     * Creates new form FRMMenu
     */
    public FRMMenu() {
        initComponents();
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
        // Ao receber a conexão, você pode fazer o que quiser com ela, como usar para
        // executar consultas ou outras operações no banco de dados
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLmenu_BemVindo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jM_Cadastro = new javax.swing.JMenu();
        jMIcadastro_amigos = new javax.swing.JMenuItem();
        jMIcadastro_ferramentas = new javax.swing.JMenuItem();
        jM_Registros = new javax.swing.JMenu();
        jMIregistros_registrosemprestimos = new javax.swing.JMenuItem();
        jM_Relatorio = new javax.swing.JMenu();
        jMIrelatorios_ativos = new javax.swing.JMenuItem();
        jMIrelatorios_ferramentas = new javax.swing.JMenuItem();
        jMIrelatorios_todosrelatorios = new javax.swing.JMenuItem();
        jM_Sair = new javax.swing.JMenu();
        jMIsair_sairaplicativo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");

        jLmenu_BemVindo.setText("BEM VINDO!");

        jM_Cadastro.setText("Cadastro");
        jM_Cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_CadastroActionPerformed(evt);
            }
        });

        jMIcadastro_amigos.setText("Amigos");
        jMIcadastro_amigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIcadastro_amigosActionPerformed(evt);
            }
        });
        jM_Cadastro.add(jMIcadastro_amigos);

        jMIcadastro_ferramentas.setText("Ferramentas");
        jMIcadastro_ferramentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIcadastro_ferramentasActionPerformed(evt);
            }
        });
        jM_Cadastro.add(jMIcadastro_ferramentas);

        jMenuBar1.add(jM_Cadastro);

        jM_Registros.setText("Registro");

        jMIregistros_registrosemprestimos.setText("Empréstimo");
        jMIregistros_registrosemprestimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIregistros_registrosemprestimosActionPerformed(evt);
            }
        });
        jM_Registros.add(jMIregistros_registrosemprestimos);

        jMenuBar1.add(jM_Registros);

        jM_Relatorio.setText("Relatório");

        jMIrelatorios_ativos.setText("Amigos");
        jMIrelatorios_ativos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIrelatorios_ativosActionPerformed(evt);
            }
        });
        jM_Relatorio.add(jMIrelatorios_ativos);

        jMIrelatorios_ferramentas.setText("Ferramentas");
        jMIrelatorios_ferramentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIrelatorios_ferramentasActionPerformed(evt);
            }
        });
        jM_Relatorio.add(jMIrelatorios_ferramentas);

        jMIrelatorios_todosrelatorios.setText("Todos os relatórios");
        jMIrelatorios_todosrelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIrelatorios_todosrelatoriosActionPerformed(evt);
            }
        });
        jM_Relatorio.add(jMIrelatorios_todosrelatorios);

        jMenuBar1.add(jM_Relatorio);

        jM_Sair.setText("Sair");

        jMIsair_sairaplicativo.setText("Sair do aplicativo");
        jMIsair_sairaplicativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIsair_sairaplicativoActionPerformed(evt);
            }
        });
        jM_Sair.add(jMIsair_sairaplicativo);

        jMenuBar1.add(jM_Sair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLmenu_BemVindo)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLmenu_BemVindo)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMIcadastro_amigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIcadastro_amigosActionPerformed
        FRMAmigo objeto = new FRMAmigo();
        objeto.setVisible(true);
    }//GEN-LAST:event_jMIcadastro_amigosActionPerformed

    private void jMIrelatorios_todosrelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIrelatorios_todosrelatoriosActionPerformed
        FRMHistorico objeto = new FRMHistorico();
        objeto.setVisible(true);
    }//GEN-LAST:event_jMIrelatorios_todosrelatoriosActionPerformed

    private void jM_CadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_CadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jM_CadastroActionPerformed

    private void jMIcadastro_ferramentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIcadastro_ferramentasActionPerformed
        FRMFerramenta objeto = new FRMFerramenta();
        objeto.setVisible(true);
    }//GEN-LAST:event_jMIcadastro_ferramentasActionPerformed

    private void jMIrelatorios_ativosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIrelatorios_ativosActionPerformed
        FRMRelatorioAmigos objeto = new FRMRelatorioAmigos();
        objeto.setVisible(true);
    }//GEN-LAST:event_jMIrelatorios_ativosActionPerformed

    private void jMIsair_sairaplicativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIsair_sairaplicativoActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMIsair_sairaplicativoActionPerformed

    private void jMIrelatorios_ferramentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIrelatorios_ferramentasActionPerformed
        FRMRelatorioFerramentas objeto = new FRMRelatorioFerramentas();
        objeto.setVisible(true);
    }//GEN-LAST:event_jMIrelatorios_ferramentasActionPerformed

    private void jMIregistros_registrosemprestimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIregistros_registrosemprestimosActionPerformed
        FRMRegistroEmprestimos objeto = new FRMRegistroEmprestimos();
        objeto.setVisible(true);
    }//GEN-LAST:event_jMIregistros_registrosemprestimosActionPerformed

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
            java.util.logging.Logger.getLogger(FRMMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRMMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLmenu_BemVindo;
    private javax.swing.JMenuItem jMIcadastro_amigos;
    private javax.swing.JMenuItem jMIcadastro_ferramentas;
    private javax.swing.JMenuItem jMIregistros_registrosemprestimos;
    private javax.swing.JMenuItem jMIrelatorios_ativos;
    private javax.swing.JMenuItem jMIrelatorios_ferramentas;
    private javax.swing.JMenuItem jMIrelatorios_todosrelatorios;
    private javax.swing.JMenuItem jMIsair_sairaplicativo;
    private javax.swing.JMenu jM_Cadastro;
    private javax.swing.JMenu jM_Registros;
    private javax.swing.JMenu jM_Relatorio;
    private javax.swing.JMenu jM_Sair;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
