package br.com.senac.leiloestdsat;

import br.com.senac.leiloestdsat.view.CadastroVIEW;

/**
 *
 * @author djfco
 */
public class LeiloesTDSat {
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    new CadastroVIEW().setVisible(true);
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroVIEW.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null,
                    ex);
        }
    }
}
