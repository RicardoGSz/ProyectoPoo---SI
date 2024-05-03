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

    
    public HashMap<String, BufferedImage> sprites = new HashMap<>();// Crear un HashMap parametrizado para almacenar imágenes con nombres
    public int posX, posY;// Declarar variables para la posición del objeto en la ventana

    // Constructor de la clase Invaders
    public Invaders() {

        // Calcular la posición inicial del objeto en la ventana
        posX = WIDTH / 2;
        posY = HEIGHT / 2;

        
        JFrame ventana = new JFrame("Invaders");// Crear una nueva ventana JFrame llamada "Invaders"
        JPanel panel = (JPanel) ventana.getContentPane();// Obtener el panel de contenido de la ventana
        setBounds(0, 0, WIDTH, HEIGHT);// Configurar el tamaño y la posición del objeto Canvas en la ventana


        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));// Establecer el tamaño preferido del panel
        panel.setLayout(null);// Configurar el diseño del panel
        panel.add(this);// Agregar el objeto Canvas al panel

        
        ventana.setBounds(0, 0, WIDTH, HEIGHT);// Configurar el tamaño y la posición de la ventana (la posicion x, posicion y, Anchura, Altura)
        ventana.setVisible(true);// Hacer visible la ventana
        ventana.addWindowListener(new WindowAdapter() {// Agregar un escuchador de eventos para cerrar la ventana correctamente
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
    public void paint(Graphics g) {
        
        g.drawImage(getSprite("bicho.gif"), posX, posY, this);// Dibujar la imagen del "bicho" en la posición especificada
    }

    // Método para actualizar la posición del objeto en la ventana
    public void updateWorld() {

        // Generar nuevas coordenadas aleatorias para el objeto
        posX = (int) (Math.random() * WIDTH);
        posY = (int) (Math.random() * HEIGHT);
    }

    // Método para ejecutar el juego
    public void game() {
        // Bucle principal del juego
        while (isVisible()) {
            
            updateWorld();// Actualizar la posición del objeto
            paint(getGraphics());// Volver a dibujar la ventana
        }
    }

    // Método principal para iniciar el programa
    public static void main(String[] args) {
        
        Invaders inv = new Invaders();// Crear una instancia de la clase Invaders
        inv.game();// Iniciar el juego

    }
}
