package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Personaje {
    double x, y;
    Image magoQuietoDer;
    Image magoQuietoIzq;
    Image magoCorriendoDer;
    Image magoCorriendoIzq;
    
    // Estado del personaje
    private boolean enMovimiento;
    private boolean mirandoDerecha; // true = derecha, false = izquierda
    
    public Personaje(double x, double y) {
        this.x = x;
        this.y = y;
        
        // Cargar las animaciones
        this.magoQuietoDer = Herramientas.cargarImagen("magoQuietoDer.gif");
        this.magoQuietoIzq = Herramientas.cargarImagen("magoQuietoIzq.gif");
        this.magoCorriendoDer = Herramientas.cargarImagen("magoCorriendoDer.gif");
        this.magoCorriendoIzq = Herramientas.cargarImagen("magoCorriendoIzq.gif");
        
        // Estado inicial
        this.enMovimiento = false;
        this.mirandoDerecha = true;
    }
    
    public void dibujar(Entorno e) {
        // Seleccionar la animacion correcta según el estado
        Image imagenActual;
        
        if (enMovimiento) {
            // Si está en movimiento, usar animación de correr
            imagenActual = mirandoDerecha ? magoCorriendoDer : magoCorriendoIzq;
        } else {
            // Si está quieto, usar animación de quieto
            imagenActual = mirandoDerecha ? magoQuietoDer : magoQuietoIzq;
        }
        
        // Dibujar la animación seleccionada
        e.dibujarImagen(imagenActual, this.x, this.y, 0, 0.5);
    }
    
    public void mover(double dx, double dy) {
    	
        // Actualizar posición
        this.x += dx;
        this.y += dy;
        
        // Actualizar estado
        this.enMovimiento = (dx != 0 || dy != 0); 
            
            
        // Actualizar dirección horizontal en la que mira
        if (dx > 0) {
            this.mirandoDerecha = true;
        } else if (dx < 0) {
            this.mirandoDerecha = false;
        }
    } // dx == 0, mantiene la direccion actual
    
    // Método para detener el personaje
    public void detener() {
        this.enMovimiento = false;
    }
}