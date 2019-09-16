package cilada;

public class PecaTres {

    /*
    1 - Circulo
    2 - Quadrado
    3 - Cruz
    
    Orientação:
    p2 p3
    p1
    */
    
    private int p1, p2, p3;
    private int tipo = 3;
    private char letra;

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public int getP3() {
        return p3;
    }

    public char getLetra() {
        return letra;
    }

    public int getTipo() {
        return tipo;
    }

    public PecaTres(int a, int b, int c){
        this.p1 = a;
        this.p2 = b;
        this.p3 = c;
        this.letra = '0';
    }

    public PecaTres(char a){
        switch(a){
            case 'g':
                this.p1 = 2;
                this.p2 = 2;
                this.p3 = 1;
                this.letra = 'g';
            break;
            case 'h':
                this.p1 = 2;
                this.p2 = 3;
                this.p3 = 1;
                this.letra = 'h';
            break;
            case 'i':
                this.p1 = 3;
                this.p2 = 1;
                this.p3 = 2;
                this.letra = 'i';
            break;
            case 'j':
                this.p1 = 2;
                this.p2 = 3;
                this.p3 = 3;
                this.letra = 'j';
            break;
            case 'k':
                this.p1 = 2;
                this.p2 = 1;
                this.p3 = 1;
                this.letra = 'k';
            break;
            case 'l':
                this.p1 = 1;
                this.p2 = 1;
                this.p3 = 2;
                this.letra = 'l';
            break;
            case 'm':
                this.p1 = 1;
                this.p2 = 3;
                this.p3 = 2;
                this.letra = 'm';
            break;
            case 'n':
                this.p1 = 1;
                this.p2 = 2;
                this.p3 = 3;
                this.letra = 'n';
            break;
            default:
                this.p1 = 1;
                this.p2 = 1;
                this.p3 = 1;
                this.letra = '0';
            break;
        }
    }
    
}