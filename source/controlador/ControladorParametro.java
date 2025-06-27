package controlador;

import persistencia.ParametroDAO;
import mundo.Parametro;
import java.util.ArrayList;

public class ControladorParametro {
	
	private ParametroDAO parametroDAO;
	
	public ControladorParametro() {
		parametroDAO = new ParametroDAO();
	}
	
	public Parametro obtenerParametroPorId(int id) {
	   return parametroDAO.obtenerParametroPorId(id);
	}
	
	public ArrayList<Parametro> obtenerTodosLosParametros() {
		return parametroDAO.obtenerTodosLosParametros();
	}
	
	public boolean insertarParametro(Parametro parametro) {
		return parametroDAO.insertarParametro(parametro);
	}
	
	public boolean eliminarParametro(int id) {
		return parametroDAO.eliminarParametro(id);
	}
	
	public boolean actualizarParametro(int id, String nombre) {
		Parametro parametro = new Parametro(id, nombre);
		return parametroDAO.actualizarParametro(parametro);
	}
	
}
