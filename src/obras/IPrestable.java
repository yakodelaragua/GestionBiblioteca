package obras;

import java.util.ArrayList;

import exception.ObraEnPrestamo;
import exception.ObraNoPrestada;

public interface IPrestable {
	//public boolean estado = false;
	public void realizarPrestamo() throws ObraEnPrestamo ;
	public void devolverPrestamo() throws ObraNoPrestada;
	public boolean estaPrestada();
	public ArrayList<String> formarListaPrestable();
	
}
