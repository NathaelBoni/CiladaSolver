/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilada;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nathael
 */
public class Cilada {

    /*
    1 - Circulo
    2 - Quadrado
    3 - Cruz
    */
    
    public static void main(String[] args) {
        
        boolean flag = false;
        boolean flag2 = false;

        char charP2[] = {'a', 'a', 'b', 'c', 'd', 'd', 'e', 'f'};
        char charP3[] = {'g', 'i', 'k', 'n'};
        
        Tabuleiro t = new Tabuleiro();
        
        int matriz[][] = t.getMatrizTeste();
        char resposta[][] = new char[matriz.length][matriz[0].length];
        
        PecaDuas p2[] = new PecaDuas[charP2.length];
        PecaTres p3[] = new PecaTres[charP3.length];
        
        List[] posP2 = new ArrayList[charP2.length];
        List[] posP3 = new ArrayList[charP3.length];
        List[] pos = new ArrayList[charP2.length + charP3.length];
        
        int N = 0;
        
        int posAtual[] = new int[charP2.length + charP3.length];
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
        //------------------------------------------------------
        /*
        Testar todas as permutações possíveis com todas as peças:
        Verificar a primeira peça e encaixar na primeira posição possivel...
        ...verificar a segunda peça na primeira posição possível...
        ...verificar a enesima peça na primeira posição possível...
        ...até alguma peça não ter posição possível...
        ...Então, voltar na peça anterior e testar a próxima posição possível...
        ...até preencher todo o tabuleiro ou acabar as permutações.
        
        Variáveis:
        boolean flag: teste se voltou uma iteração ou não (true se voltou)
        char charP2, charP3: vetor contem as letras para a criação de peças. O tamanho define o numero de peças
        Tabuleiro t: é o tabuleiro do jogo
        int[][] matriz: matriz de teste do jogo, quando tiver 1 em todas as posições, jogo terminado
        PecaDuas P2: vetor que contem todas as pecas de 2 espaços
        PecaTres P3: vetor que contem todas as pecas de 3 espaços
        List PosP2, PosP3: lista que contem matrizes com as coordenadas que a respectiva peça ocupa no tabuleiro
        List Pos: concatenação das listas PosP2 e PosP3
        int posAtual: vetor que carrega o indice da lista da posição que tal peça usou pra entrar no tabuleiro
        
        Funções:
        void printMatriz: printa a matriz do parametro no formato certo
        boolean preencheMatriz: dadas as coordenadas da matriz de parametro, preenche com 1 na matriz do parametro, exceto se la ja tem 1. Nesse caso, retorna false
        void LimpaPosicao: dadas as coordendas da matriz de parametro, preenche com 0 na matriz do parametro
        boolean verificaValorMatriz: verifica se todas as posições da matriz tem o valor de parametro
        List encontraPosicaoP2: retorna uma lista com as coordenadas que a peça (2) de parametro encaixa no tabuleiro
        List encontraPosicaoP3: retorna uma lista com as coordenadas que a peça (3) de parametro encaixa no tabuleiro
        */
        
        /*
        for(int i = 0; i < pos.length; i++){
            for(int j = posAtual[i]; j < pos[i].size(); j++){
                int p[][];
                p = (int[][]) pos[i].get(j);
                System.out.println(p.length);
            }
        }
        */
        
        for(int i = 0; i < pos.length; i++){                            //para cada peça
            try{
                for(int j = posAtual[i]; j < pos[i].size(); j++){       //para cada posição possivel de cada peça
                    int p[][];
                    p = (int[][]) pos[i].get(j);

                    if(flag){                                           //se voltou no loop

                        LimpaPosicao(p, matriz, p.length);              //limpa a posição da peça atual
                        if((j+1) == pos[i].size()){
                            posAtual[i] = 0;
                            i = i-2;
                            flag = true;
                            break;
                        }

                        flag = false;

                    }else{

                        if(preencheMatriz(p, matriz, p.length)){        //se consegue colocar a peça no tabuleiro
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
        
        
    }
    
    public static void promptEnterKey(){
        System.out.println("...\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
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

}
