package cilada;

public class PecaDuas {

    private int p1, p2;
    private int tipo = 2;
    private char letra;

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public char getLetra() {
        return letra;
    }

    public int getTipo() {
        return tipo;
    }
    
    public PecaDuas(int a, int b){
        this.p1 = a;
        this.p2 = b;
        this.letra = '0';
    }
    
    public PecaDuas(char a){
        switch(a){
            case 'a':
                this.p1 = 1;
                this.p2 = 3;
                this.letra = 'a';
            break;
            case 'b':
                this.p1 = 2;
                this.p2 = 3;
                this.letra = 'b';
            break;
            case 'c':
                this.p1 = 1;
                this.p2 = 2;
                this.letra = 'c';
            break;
            case 'd':
                this.p1 = 3;
                this.p2 = 3;
                this.letra = 'd';
            break;
            case 'e':
                this.p1 = 2;
                this.p2 = 2;
                this.letra = 'e';
            break;
            case 'f':
                this.p1 = 1;
                this.p2 = 1;
                this.letra = 'f';
            break;
            default:
                this.p1 = 1;
                this.p2 = 1;
                this.letra = '0';
            break;
        }
    }
    
}
