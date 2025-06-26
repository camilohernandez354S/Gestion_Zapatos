package controlador;

import persistencia.ParametroDAO;
import mundo.Parametro;

public class ControladorParametro {
	
	private ParametroDAO parametroDAO;
	
	public ControladorParametro() {
		parametroDAO = new ParametroDAO();
	}
	
	public Parametro obtenerParametroPorId(int id) {
	   return parametroDAO.obtenerParametroPorId(id);
	}
	
	public boolean insertarParametro(String nombre) {
		Parametro nuevoParametro = new Parametro(0, nombre);
		return parametroDAO.insertarParametro(nuevoParametro);
	}
	
	public boolean eliminarParametro(int id) {
		return parametroDAO.eliminarParametro(id);
	}
	
	public boolean actualizarParametro(int id, String nombre) {
		Parametro parametro = new Parametro(id, nombre);
		return parametroDAO.actualizarParametro(parametro);
	}
	
}
