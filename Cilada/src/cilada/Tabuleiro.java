package cilada;

public class Tabuleiro {

    private int altura, largura;
    private int matriz[][], matrizTeste[][];

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public int[][] getMatrizTeste() {
        return matrizTeste;
    }

    public Tabuleiro(int a, int b, int mat[][]){
        this.altura = a;
        this.largura = b;
        this.matriz = mat;
        this.matrizTeste = new int[a][b];
        for(int i = 0; i < a; i++){
            for(int j = 0; j < b; j++){
                matrizTeste[i][j] = 0;
            }
        }
    }

    public Tabuleiro(){
        this.altura = 7;
        this.largura = 4;
        this.matriz = new int[][]{{2,3,1,1},
                                  {1,2,2,3},
                                  {3,3,1,3},
                                  {3,2,1,2},
                                  {3,2,3,3},
                                  {1,2,1,1},
                                  {1,1,2,2}};
        
        this.matrizTeste = new int[this.altura][this.largura];
        for(int i = 0; i < altura; i++){
            for(int j = 0; j < largura; j++){
                matrizTeste[i][j] = 0;
            }
        }
    }
    
}
