package edd.src.Automata;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Clase que representa una imagen en donde se va a pintar el automata celular. 
 * @author Manuel
 */
public class Imagen extends JPanel {

    /**
     * Tamanio de la celda para cada celula. 
     */
    public static final int sizeCell = 15;

    /**
     * Numero de celdas que contendra la malla. Este valor se aplica tanto para altura como para anchura.
     * Es decir la malla tendra numCellsxnumCell numero de celdas.
     */
    public static final int numCells = 40;

    /**
     * Tamanio real que debe de tener la malla considerando una linea divisoria entre las celdas, y el taminio de cada una. 
     */
    private static final int tam = numCells * sizeCell + numCells + 1;

    /**
     * Objeto en donde se va a pintar.
     */
    private BufferedImage imagen;

    /**
     * Constructor de la clase.
     */
    public Imagen() {
        setSize(tam, tam);
        imagen = new BufferedImage(tam, tam, BufferedImage.TYPE_INT_RGB);
        createGrid();
    }

    /**
     * Metodo que dibuja las lineas en la imagen en color gris, para dar la apariencia de que es un entramado.
     */
    private void createGrid() {
        Graphics2D gc = imagen.createGraphics();
        //Rectangulo Blanco POR VALOR DE PENCIL DEFAULT.
        gc.fillRect(0,0,tam,tam);
        gc.setColor(Color.GRAY);
        for (int i=0;i<=numCells;i++) {
            gc.drawLine((sizeCell*i)+i,0,(sizeCell*i)+i,tam);
            gc.drawLine(0,(sizeCell*i)+i,tam,(sizeCell*i)+i);
        }        
    }

    /**
     * Metodo que mapea la matriz del automata a su representacion grafica. Este metodo considera
     * el tamanio de cada celda para poderla pintar. Se le pasa como parametro un arreglo de colores, 
     * para que cada vez que vea un valor en la matriz este lo busque en el arreglo de colores y pinte la celda de ese color.
     * Es necesario que la longitud del arreglo sea igual al maximo de los valores que se encuentran en la matriz.
     * @param matriz Representa la malla del automata con sus posibles estados. 
     * @param colores Se mapean a cada estado de la matriz para pintarlo de su respectivo color.
     */
    public void pinta(int[][] matriz, Color[] colores){
	    Graphics2D gc = imagen.createGraphics();
        for (int i=1; i<matriz.length+1; i++){
            for (int j=1; j<matriz.length+1; j++){
                int aux1i = (sizeCell*(i-1)+i);
                int aux1j = (sizeCell*(j-1)+j);

                Color cero = new Color(0,0,0);
                Color uno = new Color(61,38,69);
                Color dos = new Color(131,33,97);
                Color tres = new Color(218,65,103);
                Color cuatro = new Color(240,239,244);

                Color blanco= new Color(255, 255, 255);
                Color AzulM= new Color(1, 25, 54);
                Color cafe= new Color(79, 49, 48);
                Color Rojo= new Color(242, 67, 51);
                Color cyan= new Color(3, 83, 164);
                Color asombra= new Color(0, 95, 115);
                Color rsombra= new Color(157, 2, 8);
                Color sombraVerde= new Color(0, 114, 0);

               switch (matriz [i-1][j-1]) {
                    case 0:
                        gc.setColor(AzulM); break;
                    case 1:
                        gc.setColor(blanco); break;
                    case 2:
                        gc.setColor(Color.BLACK); break;
                    case 3:
                        gc.setColor(cafe); break;
                    case 4:
                        gc.setColor(sombraVerde); break;
                    case 5: 
                        gc.setColor(Rojo); break;
                    case 6: 
                        gc.setColor(cyan); break;
                    case 7: 
                        gc.setColor(Color.green); break;
                    case 8: 
                        gc.setColor(Color.darkGray); break;
                    case 9:
                        gc.setColor(Color.magenta); break;
                    case 10:
                        gc.setColor(Color.RED); break;
                    case 11:
                        gc.setColor(Color.CYAN); break;
                    case 12:
                        gc.setColor(asombra); break;
                    case 13:
                        gc.setColor(rsombra); break;
                    case 14:
                        gc.setColor(Color.pink); break;

                    case 15:
                        //gc.setColor(Color.YELLOW); break;
                        gc.setColor(cero); break;
                    case 16:
                        //gc.setColor(Color.BLUE); break;
                        gc.setColor(uno); break;
                    case 17:
                        //gc.setColor(Color.WHITE); break;
                        gc.setColor(dos); break;
                    case 18:
                        //gc.setColor(Color.BLACK); break;
                        gc.setColor(tres); break;
                    case 19:
                        //gc.setColor(Color.RED); break;
                        gc.setColor(cuatro); break;
                }
                gc.fillRect(aux1i,aux1j,sizeCell,sizeCell);
            }
       }       
        updateUI();
    }

    @Override
    public void paint(Graphics g) {
        try{
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(imagen, null, 0, 0);
        }catch(NullPointerException e){}
    }
}
