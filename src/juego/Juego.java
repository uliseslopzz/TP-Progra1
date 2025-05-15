package juego;
import entorno.Entorno;
import entorno.InterfaceJuego;
import java.util.ArrayList;

public class Juego extends InterfaceJuego
{
    // El objeto Entorno que controla el tiempo y otros
    private Entorno entorno;
    private Personaje mago;
    
    private ArrayList<Murcielagos> murcielagos;
    
    // Cantidad de murcielagos a aparecer en pantalla
    private int cantInicial = 5;
    // private int tiempoNuevoMurcielago = 3;
    // private static int tiempoEntreMurcielagos = 500; 
    
    
    // Control del movimiento
    private boolean enMovimiento;
    
    
    Juego()
    {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "El camino de Gondolf", 800, 600);
        this.mago = new Personaje(400, 300);
        
        // Inicializar variables de control
        this.enMovimiento = false;
        
        // Iniciar murcielagos
        this.murcielagos = new ArrayList<Murcielagos>();
        crearMurcielagosIniciales();
        
        
        // Inicia el juego!
        this.entorno.iniciar();
    }
    
    private void crearMurcielagosIniciales() {
    	for (int i = 0; i < cantInicial; i++) {
    		crearMurcielagosAleatorios();
    	}
    }
    
    // Crea murcielagos en un borde aleatorio y los agrega a la lista
    private void crearMurcielagosAleatorios() {
    	int borde = (int)(Math.random() * 4);
    	
    	double x = 0;
    	double y = 0;
    	
    	switch (borde) {
    		case 0: // Borde Arriba
    			x = Math.random() * entorno.ancho();
    			y = 10;
    			break;
    		case 1: // Borde Derecho
    			x = entorno.ancho() - 10;
    			y = Math.random() * entorno.alto();
    			break;
    		case 2: // Borde Abajo
    			x = Math.random() * entorno.ancho();
    			y = entorno.alto() - 10;
    			break;
    		case 3: // Borde Izquierdo
    			x = 10;
    			y = Math.random() * entorno.alto();
    			break;
    	}
    	
    	Murcielagos nuevo = new Murcielagos(x, y);
    	
    	nuevo.setVelocidad(1 + Math.random());
    	murcielagos.add(nuevo);
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
        if(entorno.estaPresionada('W') || entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
        	dy = -2;
        }
        
        if(entorno.estaPresionada('S') || entorno.estaPresionada(entorno.TECLA_ABAJO)) {
        	dy = 2;
        }
        
        if(entorno.estaPresionada('D') || entorno.estaPresionada(entorno.TECLA_DERECHA)) {
        	dx = 2;
        }
        
        if(entorno.estaPresionada('A') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
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
        
        // Actualizar y dibujar murcielagos
        actualizarMurcielagos();
    }
    
    private void actualizarMurcielagos() {
    	
    	// ArrayList<Murcielagos> murcielagosQueEliminar = new ArrayList<Murcielagos>();
    	
    	for (Murcielagos m : murcielagos) {
    		
    		m.perseguir(mago.getX(), mago.getY());
    		
    		m.limitarMovimiento(entorno);
    		
    		m.dibujar(entorno);
    	}
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        Juego juego = new Juego();
    }
}