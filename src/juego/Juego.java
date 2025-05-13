package juego;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
    // El objeto Entorno que controla el tiempo y otros
    private Entorno entorno;
    private Personaje mago;
    
    // Control del movimiento
    private boolean movimientoVertical;
    private boolean movimientoHorizontal;
    
    Juego()
    {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "El camino de Gondolf", 800, 600);
        this.mago = new Personaje(400, 300);
        
        // Inicializar variables de control
        this.movimientoVertical = false;
        this.movimientoHorizontal = false;
        
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
        // Resetear variables de control en cada tick
        movimientoVertical = false;
        movimientoHorizontal = false;
        
        // Procesamiento de un instante de tiempo
        // Comprobar teclas y mover al personaje
        if(entorno.estaPresionada('W')) {
            this.mago.mover(-2, 0);
            movimientoVertical = true;
        }
        if(entorno.estaPresionada('S')) {
            this.mago.mover(2, 0);
            movimientoVertical = true;
        }
        if(entorno.estaPresionada('D')) {
            this.mago.mover(0, 2);
            movimientoHorizontal = true;
        }
        if(entorno.estaPresionada('A')) {
            this.mago.mover(0, -2);
            movimientoHorizontal = true;
        }
        
        // Si no hay movimiento, detener al personaje
        if (!movimientoVertical && !movimientoHorizontal) {
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