/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilada;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Nathael
 */
public class CiladaSwing extends javax.swing.JFrame {

    /**
     * Creates new form CiladaSwing
     */
    
    //VARIAVEIS---------------------------------------------------------
    
    boolean flag = false;
    boolean flag2 = false;

    char charP2[];
    char charP3[];

    Tabuleiro t = new Tabuleiro();

    int matriz[][] = t.getMatrizTeste();
    char resposta[][] = new char[matriz.length][matriz[0].length];

    PecaDuas p2[];
    PecaTres p3[];

    List[] posP2;
    List[] posP3;
    List[] pos;

    int posAtual[];
    
    //---------------------------------------------------------------
    
    //FUNCOES--------------------------------------------------------
    public static void printMatriz(int m[][]){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }
    
    public static void printMatrizChar(char m[][]){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }
    
    public static boolean preencheMatriz(int p[][], int mat[][], int t){
        
        if(t == 2){
            if((mat[ p[0][0] ][ p[0][1] ] != 1) &&
               (mat[ p[1][0] ][ p[1][1] ] != 1)){
                    mat[ p[0][0] ][ p[0][1] ] = 1;
                    mat[ p[1][0] ][ p[1][1] ] = 1;
                    return true;
            }
            return false;
        }else if(t == 3){
            if((mat[ p[0][0] ][ p[0][1] ] != 1) &&
               (mat[ p[1][0] ][ p[1][1] ] != 1) &&
               (mat[ p[2][0] ][ p[2][1] ] != 1)){
                    mat[ p[0][0] ][ p[0][1] ] = 1;
                    mat[ p[1][0] ][ p[1][1] ] = 1;
                    mat[ p[2][0] ][ p[2][1] ] = 1;
                    return true;
            }
            return false;
        }
        return false;
        
    }
    
    public static void LimpaPosicao(int p[][], int mat[][], int t){
        if(t == 2){
            mat[ p[0][0] ][ p[0][1] ] = 0;
            mat[ p[1][0] ][ p[1][1] ] = 0;
        }else if(t == 3){
            mat[ p[0][0] ][ p[0][1] ] = 0;
            mat[ p[1][0] ][ p[1][1] ] = 0;
            mat[ p[2][0] ][ p[2][1] ] = 0;
        }
    }
    
    public static boolean verificaValorMatriz(int m[][], int v){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                if(m[i][j] != v){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static List encontraPosicaoP2(PecaDuas p, Tabuleiro T){
        List pos = new ArrayList<>();
        
        int matriz[][] = T.getMatriz();
        
        for(int i = 0; i < T.getAltura(); i++){
            for(int j = 0; j < T.getLargura(); j++){
                
                if(matriz[i][j] == p.getP1()){
                    
                    try{
                        if(matriz[i+1][j] == p.getP2()){
                            int m[][] = new int[2][2];
                            m[0][0] = i;
                            m[0][1] = j;
                            m[1][0] = i+1;
                            m[1][1] = j;
                            pos.add(m);
                            //printMatriz(m);
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){
                    //ignora, apenas evita o crash e economiza codigo
                    }
                    try{
                        if(matriz[i-1][j] == p.getP2()){
                            int m[][] = new int[2][2];
                            m[0][0] = i;
                            m[0][1] = j;
                            m[1][0] = i-1;
                            m[1][1] = j;
                            pos.add(m);
                            //printMatriz(m);
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){
                    //ignora, apenas evita o crash e economiza codigo
                    }
                    try{
                        if(matriz[i][j+1] == p.getP2()){
                            int m[][] = new int[2][2];
                            m[0][0] = i;
                            m[0][1] = j;
                            m[1][0] = i;
                            m[1][1] = j+1;
                            pos.add(m);
                            //printMatriz(m);
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){
                    //ignora, apenas evita o crash e economiza codigo
                    }
                    try{
                        if(matriz[i][j-1] == p.getP2()){
                            int m[][] = new int[2][2];
                            m[0][0] = i;
                            m[0][1] = j;
                            m[1][0] = i;
                            m[1][1] = j-1;
                            pos.add(m);
                            //printMatriz(m);
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){
                    //ignora, apenas evita o crash e economiza codigo
                    }
                }
                
            }
        }
        return pos;
    }
    
    public static List encontraPosicaoP3(PecaTres p, Tabuleiro T){
        List pos = new ArrayList<>();
        
        int matriz[][] = T.getMatriz();
        
        for(int i = 0; i < T.getAltura(); i++){
            for(int j = 0; j < T.getLargura(); j++){
                
                if(matriz[i][j] == p.getP1()){
                    
                    try{
                        if(matriz[i+1][j] == p.getP2()){
                            if(matriz[i+1][j-1] == p.getP3()){
                                int m[][] = new int[3][2];
                                m[0][0] = i;
                                m[0][1] = j;
                                m[1][0] = i+1;
                                m[1][1] = j;
                                m[2][0] = i+1;
                                m[2][1] = j-1;
                                pos.add(m);
                            }
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){
                    //ignora, apenas evita o crash e economiza codigo
                    }
                    try{
                        if(matriz[i-1][j] == p.getP2()){
                            if(matriz[i-1][j+1] == p.getP3()){
                                int m[][] = new int[3][2];
                                m[0][0] = i;
                                m[0][1] = j;
                                m[1][0] = i-1;
                                m[1][1] = j;
                                m[2][0] = i-1;
                                m[2][1] = j+1;
                                pos.add(m);
                            }
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){
                    //ignora, apenas evita o crash e economiza codigo
                    }
                    try{
                        if(matriz[i][j+1] == p.getP2()){
                            if(matriz[i+1][j+1] == p.getP3()){
                                int m[][] = new int[3][2];
                                m[0][0] = i;
                                m[0][1] = j;
                                m[1][0] = i;
                                m[1][1] = j+1;
                                m[2][0] = i+1;
                                m[2][1] = j+1;
                                pos.add(m);
                            }
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){
                    //ignora, apenas evita o crash e economiza codigo
                    }
                    try{
                        if(matriz[i][j-1] == p.getP2()){
                            if(matriz[i-1][j-1] == p.getP3()){
                                int m[][] = new int[3][2];
                                m[0][0] = i;
                                m[0][1] = j;
                                m[1][0] = i;
                                m[1][1] = j-1;
                                m[2][0] = i-1;
                                m[2][1] = j-1;
                                pos.add(m);
                            }
                        }
                    }catch(ArrayIndexOutOfBoundsException ex){
                    //ignora, apenas evita o crash e economiza codigo
                    }
                }
                
            }
        }
        return pos;
    }
    
    public static void preencheResposta(int p[][], char mat[][], int a, PecaDuas peca2[], PecaTres peca3[]){
        if(a < peca2.length){
            mat[p[0][0]][p[0][1]] = peca2[a].getLetra();
            mat[p[1][0]][p[1][1]] = peca2[a].getLetra();
        }else{
            mat[p[0][0]][p[0][1]] = peca3[a-peca2.length].getLetra();
            mat[p[1][0]][p[1][1]] = peca3[a-peca2.length].getLetra();
            mat[p[2][0]][p[2][1]] = peca3[a-peca2.length].getLetra();
        }
    }
    //------------------------------------------------------------------
    
    public CiladaSwing() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        peca2_txt = new javax.swing.JTextField();
        botao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        peca3_txt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botao.setText("ok");
        botao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoActionPerformed(evt);
            }
        });

        jLabel1.setText("Peça de 2 espaços:");

        jLabel2.setText("Peca de 3 espaços:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(peca2_txt)
                    .addComponent(jLabel2)
                    .addComponent(peca3_txt)
                    .addComponent(botao, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(peca2_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(peca3_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botao)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoActionPerformed
        
        //INICIALIZACAO---------------------------------------------------
        String str2 = peca2_txt.getText();
        String str3 = peca3_txt.getText();
        
        charP2 = new char[str2.length()];
        charP3 = new char[str3.length()];
        
        charP2 = str2.toCharArray();
        charP3 = str3.toCharArray();
        
        p2 = new PecaDuas[charP2.length];
        p3 = new PecaTres[charP3.length];

        posP2 = new ArrayList[charP2.length];
        posP3 = new ArrayList[charP3.length];
        pos = new ArrayList[charP2.length + charP3.length];

        posAtual = new int[charP2.length + charP3.length];
        
        for(int i = 0; i < posAtual.length; i++){
            posAtual[i] = 0;
        }
        
        for(int i = 0; i < p2.length; i++){
            p2[i] = new PecaDuas(charP2[i]);
        }
        for(int i = 0; i < p3.length; i++){
            p3[i] = new PecaTres(charP3[i]);
        }
        
        for(int i = 0; i < p2.length; i++){
            posP2[i] = encontraPosicaoP2(p2[i], t);
            pos[i] = posP2[i];
        }
        for(int i = 0; i < p3.length; i++){
            posP3[i] = encontraPosicaoP3(p3[i], t);
            pos[p2.length+i] = posP3[i];
        }
        
        //-----------------------------------------------------------------------
        
        //INTELIGENCIA------------------------------------------------------------------
        for(int i = 0; i < pos.length; i++){                    //para cada peça
            try{
                for(int j = posAtual[i]; j < pos[i].size(); j++){   //para cada posição possivel de cada peça
                    int p[][];
                    p = (int[][]) pos[i].get(j);

                    if(flag){                                       //se voltou no loop

                        LimpaPosicao(p, matriz, p.length);                 //limpa a posição da peça atual
                        if((j+1) == pos[i].size()){
                            posAtual[i] = 0;
                            i = i-2;
                            flag = true;
                            break;
                        }

                        flag = false;

                    }else{

                        if(preencheMatriz(p, matriz, p.length)){               //se consegue colocar a peça no tabuleiro
                            posAtual[i] = j;                            //atualiza a posição atual da peça
                            break;
                        }else if(j == (pos[i].size()-1)){               //se não consegue colocar a peça no tabuleiro e era a última posição possível
                            posAtual[i] = 0;                            //pra testar todas as opções do inicio, ja que a peça foi removida
                            i = i-2;                                    //para voltar para a peça anterior
                            flag = true;                                //seta pra voltar no loop, pra peça anterior
                            break;
                        }

                    }
                }
            }catch(ArrayIndexOutOfBoundsException ex){          //caso o posAtual[i] do segundo for estoure (não tenho certeza)
                flag = true;
                break;
            }
        }
        
        if(verificaValorMatriz(matriz, 1)){
            for (int i = 0; i < posAtual.length; i++) {
                int p[][];
                p = (int[][]) pos[i].get(posAtual[i]);
                preencheResposta(p, resposta, i, p2, p3);   
            }
            printMatrizChar(resposta);
        }else{
            System.out.println("Não é possível completar o jogo");
        }
        
    }//GEN-LAST:event_botaoActionPerformed

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
            java.util.logging.Logger.getLogger(CiladaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CiladaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CiladaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CiladaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CiladaSwing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField peca2_txt;
    private javax.swing.JTextField peca3_txt;
    // End of variables declaration//GEN-END:variables
}
