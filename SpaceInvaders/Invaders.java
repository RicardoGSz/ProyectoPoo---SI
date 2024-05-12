// Importar las clases necesarias para trabajar con gráficos, imágenes, ventanas y colecciones
package SpaceInvaders;

import java.awt.Canvas;//Permite dibujar graficos dentro de la ventana
import java.awt.Dimension;//Sirve para manejar mejor la anchura y altura de un componente en un objeto
import java.awt.Graphics;//Maneja clases abstractas para contextos graficos que permiten dibujar componentes en la ventana
import java.awt.event.WindowAdapter;//Sirve para preparar a la ventana a recibir eventos
import java.awt.event.WindowEvent;//Encapsula diversos eventos para la ventana
import java.awt.image.BufferedImage;//Se usa para trabajar con imagenes en memoria
import java.net.URL;//Para obtener referencias de una pagina web
import java.util.HashMap;//Permite almacenar pares clave-valor

import javax.imageio.ImageIO;//Lee diversos formatos de imagenes
import javax.swing.JFrame;//Crea una area en la ventana para colocar componentes
import javax.swing.JPanel;//Sirve para crear una ventana

//Creacion de la ventana del juego mediante una clase llamada Invaders, incluye propiedades de la libreria Canvas
public class Invaders extends Canvas {

    // Declarar constantes para el tamaño de la ventana
    public static final int WIDTH = 640; /*Anchura*/
    public static final int HEIGHT = 480; /*Altura*/
    public static final int SPEED = 10; /*Velocidad */

    
    public HashMap<String, BufferedImage> sprites = new HashMap<>();// Crear un HashMap parametrizado para almacenar imágenes con nombres
    public int posX, posY, vX;// Declarar variables para la posición del objeto en la ventana

    public BufferedImage buffer;//Declara la variable buffer de la imagen, importando la clase BufferedImage

    // Constructor de la clase Invaders
    public Invaders() {

        // Calcular la posición inicial del objeto en la ventana
        posX = WIDTH / 2;
        posY = HEIGHT / 2;
        
        vX = 2; //se establece la velocidad inicial
        
        //se crea un nuevo objeto para el buffer con el mismo ancho y alto que la ventana del juego.
        buffer = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB); 
        
        JFrame ventana = new JFrame("Invaders");// Crear una nueva ventana JFrame llamada "Invaders"
        JPanel panel = (JPanel) ventana.getContentPane();// Obtener el panel de contenido de la ventana
        setBounds(0, 0, WIDTH, HEIGHT);// Configurar el tamaño y la posición del objeto Canvas en la ventana


        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));// Establecer el tamaño preferido del panel
        panel.setLayout(null);// Configurar el diseño del panel
        panel.add(this);// Agregar el objeto Canvas al panel

        
        ventana.setBounds(0, 0, WIDTH, HEIGHT);// Configurar el tamaño y la posición de la ventana (la posicion x, posicion y, Anchura, Altura)
        ventana.setVisible(true);// Hacer visible la ventana

        // Agregar un escuchador de eventos para cerrar la ventana correctamente
        ventana.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                System.exit(0);//Sirve para cuando se presione la "X" de la ventana, se cierre y deje de ejecutar el programa
            }
        });
        
        ventana.setResizable(false);// Evitar que la ventana sea redimensionable
    }

    // Método para cargar una imagen desde un archivo
    public BufferedImage loadImage(String nombre) {
        URL url = null;
        try {
            
            url = getClass().getClassLoader().getResource(nombre);// Obtener la URL del archivo de imagen
            
            return ImageIO.read(url);// Leer la imagen desde la URL y devolverla
        } catch (Exception e) {

            // Manejar errores al cargar la imagen
            System.out.println("No se pudo cargar la imagen " + nombre + " de " + url);
            System.out.println("El error fue: " + e.getClass().getName() + " " + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    // Método para obtener una imagen del HashMap de sprites
    public BufferedImage getSprite(String nombre) {
        BufferedImage img = sprites.get("bicho.gif");
        if (img == null) {
            
            BufferedImage image = loadImage("SpaceInvaders/bicho.gif");// Cargar la imagen desde el archivo si no está en el HashMap
            sprites.put("bicho.gif", image);// Agregar la imagen al HashMap
        }
        return img;
    }

    // Método para dibujar en la ventana
    public void paintWorld(){

        Graphics g = buffer.getGraphics();
        g.setColor(getBackground()); //se le asigna le color al grafico y devuelve el color de fondo del objeto canvas
        g.fillRect(0,0,getWidth(),getHeight()); // dibuja un cuadrado del tamaño de la ventana y lo rellena con el color de fondo

        g.drawImage(getSprite("bicho.gif"), posX, posY,this);// dibuja en el buffer la imagen bicho y lo ordena en las cordenadas
        getGraphics().drawImage(buffer,0,0,this);//finalmente se dibuja el contenido del buffer en la ventana, todo esto en la coordenadas
    }

    public void paint(Graphics g) {}
    public void update(Graphics g) {}

    // Método para actualizar la posición del objeto en la ventana
    public void updateWorld() {

        posX += vX;//aqui se incrementa la posicion horizontal por la cantidad de la velocidad

        /*Esto significa que el objeto se moverá hacia la derecha si vX es positivo y 
        hacia la izquierda si vX es negativo. */

        if (posX < 0 || posX > WIDTH) vX = -vX;
        /* Esta es una condición que verifica
        Si el objeto ha llegado al borde izquierdo (posX < 0) o al borde derecho (posX > WIDTH) de la ventana del juego. 
        Si el objeto ha llegado a uno de estos bordes, cambia la dirección del movimiento multiplicando vX por -1. 
        Esto invierte la dirección del movimiento, haciendo que el objeto se mueva en la dirección opuesta. */

    }

    // Método para ejecutar el juego
    public void game() {

        //Esto establece un bucle que se ejecutará mientras la ventana del juego sea visible
        while (isVisible()) {
            
            updateWorld();// para actualizar la posición del objeto en la ventana.
            paintWorld();// para dibujar el contenido de la ventana del juego.
            paint(getGraphics());// Llama al método paint() con el contexto gráfico de la ventana del juego para volver a dibujar la ventana.

            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException e) {}
            /*Esto pausa la ejecución del hilo actual durante un tiempo determinado. En este caso,
            el tiempo de pausa está determinado por la constante SPEED, que representa la velocidad del juego.*/
        }
    }

    // Método principal para iniciar el programa
    public static void main(String[] args) {
        
        Invaders inv = new Invaders();// Crear una instancia de la clase Invaders
        inv.game();// Iniciar el juego

    }
}
