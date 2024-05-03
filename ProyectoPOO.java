package proyectopoo;

//Permite dibujar graficos dentro de la ventana
import java.awt.Canvas;
//Encapsula colores RGB
import java.awt.Color;
//Sirve para manejar mejor la anchura y altura de un componente en un objeto
import java.awt.Dimension;
//Maneja clases abstractas para contextos graficos que permiten dibujar componentes en la ventana
import java.awt.Graphics;
//Sirve para preparar a la ventana a recibir eventos
import java.awt.event.WindowAdapter;
//Encapsula diversos eventos para la ventana
import java.awt.event.WindowEvent;
//Se usa para trabajar con imagenes en memoria
import java.awt.image.BufferedImage;
//Para obtener referencias de una pagina web
import java.net.URL;
//Permite almacenar pares clave-valor
import java.util.HashMap;
//Lee diversos formatos de imagenes
import javax.imageio.ImageIO;
//Crea una area en la ventana para colocar componentes
import javax.swing.JPanel;
//Sirve para crear una ventana
import javax.swing.JFrame;


//Creacion de la ventana del juego mediante una clase llamada Invaders, incluye propiedades de la libreria Canvas
class Invaders extends Canvas{
    //Margen de la ventana
    /*Anchura*/
    public static final int WIDTH = 800;
    /*Altura*/
    public static final int HEIGHT = 600;
    //Se declara un HashMap
    public HashMap sprites;
    
    public Invaders(){
        sprites = new HashMap();
        /*Aqui se crea la ventana y se le asigna el nombre*/
        JFrame ventana = new JFrame("Invaders");
        //Se encarga de crear un panel dentro de la ventana
        JPanel panel = (JPanel)ventana.getContentPane();
        //Se crea el panel dentro de las siguientes coordenadas de la ventana
        setBounds(0,0,WIDTH,HEIGHT);
        //Se ajusta el tamaño del panel
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //Se declara vacio
        panel.setLayout(null);
        //Se agrega el panel
        panel.add(this);
        /*Se asignan los valores a las dimensiones de la ventana*/
        /*Posicion inicial de X de la ventana||Posicion inicial de Y de la ventana||Anchura de la ventana||Altura de la ventana*/
        ventana.setBounds(0,0,WIDTH,HEIGHT);
        //Se establece que la ventana sea visible
        ventana.setVisible(true);
        
        ventana.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                //Sirve para cuando se presione la "X" de la ventana, se cierre y deje de ejecutar el programa
                System.exit(0);
            }
        });
    }
    //Este metodo se encarga de cargar una imagen y regresarla como un objeto
    public BufferedImage loadImage(String nombre){
        URL url=null;
        try{
            //Obtiene una referencia hacia un url
            url = getClass().getClassLoader().getResource(nombre);
            //Regresa la imagen ingresa por el url y la regresa como un objeto
            return ImageIO.read(url);
        } catch (Exception e){
            System.out.println("No se pudo cargar la imagen " + nombre + " de "+url);
            System.out.println("El error fue: "+e.getClass().getName()+" "+e.getMessage());
            System.exit(0);
            return null;
        }
    }
    
    //Obtiene una imagen proveniente de un HashMap de sprites
    public BufferedImage getSprite(String nombre){
        //Se encarga de recibir la imagen proveniente del HashMap y accede a ella mediante la clave nombre
        BufferedImage img = (BufferedImage)sprites.get(nombre);
        //Si la imagen no fue encontrada, se encarga de cargarla desde los archivos del ordenador
        if(img == null){
            img = loadImage("Imagen/"+nombre);
            sprites.put(nombre,img);
        }
        //Regresa la imagen
        return img;
    }
    
    //Se llama a la imagen y posteriormente se agrega a la ventana
    public void paint(Graphics g){
        g.drawImage(getSprite("bicho.gif"),40,40,this);
    }
}


public class ProyectoPOO {
    public static void main(String[] args) {
        //Se crea el objeto de la ventana para que se muestre
        Invaders vent = new Invaders();
    }
    
}
