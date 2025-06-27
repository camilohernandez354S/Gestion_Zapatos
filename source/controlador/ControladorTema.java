package controlador;

import persistencia.TemaDAO;
import mundo.Tema;
import java.util.List;
import java.util.ArrayList;

public class ControladorTema {
	
	private TemaDAO temaDAO;
	
	public ControladorTema() {
		temaDAO = new TemaDAO();
	}
	
	public ArrayList<Tema> obtenerTodosLosTemas() {
		return temaDAO.obtenerTodosLosTemas();
	}
	
	public boolean insertarTema(Tema tema) {
		return temaDAO.insertarTema(tema);
	}
	
	public boolean eliminarTema(int id) {
		return temaDAO.eliminarTema(id);
	}
	
	public boolean actualizarTema(int id, String nombre) {
		Tema tema = new Tema(id, nombre);
		return temaDAO.actualizarTema(tema);
	}
	
<<<<<<< HEAD
	public List<Tema> obtenerTemas() {
        return temaDAO.obtenerTodosLosTemas();
    }
=======
	public Tema obtenerTemaPorId(int id) {
		return temaDAO.obtenerTemaPorId(id);
	}
>>>>>>> feature/CRUDT-P
}
