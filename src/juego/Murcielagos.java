package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;


public class Murcielagos {
	
	private double x;
	private double y;
	private double velocidad;
	
	private double direccion;
	private boolean mirandoDerecha;
	
	private double ancho;
	private double alto;
	
	private Image murcielagoDer;
	private Image murcielagoIzq;
	
	public Murcielagos(double x, double y) {
		
		this.x = x;
		this.y = y;
		this.velocidad = 1.0;
		this.direccion = 0;
		this.mirandoDerecha = true;
		
		this.ancho = 30;
		this.alto = 20;
		
		this.murcielagoDer = Herramientas.cargarImagen("murcielagoDer.gif");
		this.murcielagoIzq = Herramientas.cargarImagen("murcielagoIzq.gif");
	}
	
	// Elegir animacion segun donde mira
	public void dibujar(Entorno entorno) {
		
		Image imagenActual = mirandoDerecha ? murcielagoDer : murcielagoIzq;
		
		if (imagenActual != null) {
			entorno.dibujarImagen(imagenActual, x, y, 0, 0.2);
		}
	}
	
	// Calcula la posicion del Mago y actualiza la posicion del Murcielago
	public void perseguir(double magoX, double magoY) {
	
		double dx = magoX - this.x;
		double dy = magoY - this.y;
		
		this.direccion = Math.atan2(dy, dx);
		
		if (dx > 0) {
			this.mirandoDerecha = true;
		} else if (dx < 0) {
			this.mirandoDerecha = false;
		}
		
		this.x += Math.cos(direccion) * velocidad;
		this.y += Math.sin(direccion) * velocidad;
	}
	
	// Verifica si el Murcielago colisiona con el personaje
	// Todavia no esta implementado
	public boolean colisiona(double personajeX, double personajeY, double radioColision) {
		
		double distancia = Math.sqrt(Math.pow(this.x - personajeX, 2) + Math.pow(this.y - personajeY, 2));
		
		return distancia < (radioColision + ancho/2);
	}
	
	
	// Evita que el Murcielago se salga de la pantalla
	public void limitarMovimiento(Entorno entorno) {
		
		if (this.x < ancho / 2) {
			this.x = ancho / 2;
		
		} else if (this.x > entorno.ancho() - ancho / 2) {
		
			this.x = entorno.ancho() - ancho/2; 
		}
		
		if (this.y < alto/2) {
			this.y = alto/2;
		} else if (this.y > entorno.alto() - alto/2) {
			this.y = entorno.alto() - alto/2;
		}
	}
	
	public void setVelocidad(double velocidad) {
		
		this.velocidad = velocidad;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setPosicion(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getAncho() {
		return this.ancho;
	}
	
	public double getAlto() {
		return this.alto;
	}
}