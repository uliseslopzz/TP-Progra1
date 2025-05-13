package juego;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
    // El objeto Entorno que controla el tiempo y otros
    private Entorno entorno;
    private Personaje mago;
    
    // Control del movimiento
    private boolean enMovimiento;
    
    Juego()
    {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "El camino de Gondolf", 800, 600);
        this.mago = new Personaje(400, 300);
        
        // Inicializar variables de control
        this.enMovimiento = false;
        
        // Inicia el juego!
        this.entorno.iniciar();
    }
    
    /**
     * Durante el juego, el método tick() será ejecutado en cada instante y 
     * por lo tanto es el método más importante de esta clase. Aquí se debe 
     * actualizar el estado interno del juego para simular el paso del tiempo 
     * (ver el enunciado del TP para mayor detalle).
     */
    public void tick()
    {
        // Acumular el movimiento
        double dx = 0;
        double dy = 0;
        
        // Procesamiento de un instante de tiempo
        // Comprobar teclas y mover al personaje
        if(entorno.estaPresionada('W')) {
        	dy = -2;
        }
        
        if(entorno.estaPresionada('S')) {
        	dy = 2;
        }
        
        if(entorno.estaPresionada('D')) {
        	dx = 2;
        }
        
        if(entorno.estaPresionada('A')) {
        	dx = -2;
        }
        
        // Actualizar estado del Movimiento
        this.enMovimiento = (dx != 0 || dy != 0);
        
        // Mover al personaje
        this.mago.mover(dx, dy);
        
        // Si no hay movimiento, detener al personaje
        if (!enMovimiento) {
            this.mago.detener();
        }
        
        // Dibujar al personaje
        this.mago.dibujar(entorno);
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        Juego juego = new Juego();
    }
}