package controlador;

import persistencia.TemaDAO;
import mundo.Tema;
import java.util.ArrayList;

public class ControladorTema {
	
	private TemaDAO temaDAO;
	
	public ControladorTema() {
		temaDAO = new TemaDAO();
	}
	
	public ArrayList<Tema> obtenerTodosLosTemas() {
		return temaDAO.obtenerTodosLosTemas();
	}
	
	public boolean insertarTema(String nombre) {
		Tema nuevoTema = new Tema(0, nombre);
		return temaDAO.insertarTema(nuevoTema);
	}
	
	public boolean eliminarTema(int id) {
		return temaDAO.eliminarTema(id);
	}
	
	public boolean actualizarTema(int id, String nombre) {
		Tema tema = new Tema(id, nombre);
		return temaDAO.actualizarTema(tema);
	}
}
