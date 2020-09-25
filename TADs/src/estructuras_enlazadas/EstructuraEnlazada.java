/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras_enlazadas;

/**
 *
 * @author pauli
 */
public class EstructuraEnlazada<T> {
    private Nodo primero;
    
    public EstructuraEnlazada() {
        primero=null;
    }
    
    public void agregaAlInicio(T dato){
        Nodo<T> segundo = primero;
        primero = new Nodo(dato);
        primero.setSig(segundo);
    }
    
    public void agregaAlFinal(T dato){
        Nodo<T> aux = primero;
        Nodo<T> nuevo = new Nodo<T> (dato);
        while(aux.getSig()!=null)
            aux = aux.getSig();
        aux.setSig(nuevo);
    }
    
    public void agregaDespuesDe (T datoNvo, T datoAnt){
        Nodo<T> nuevo = new Nodo<T>(datoNvo);
        Nodo<T> aux = primero;
        if(aux==null)
            primero=nuevo;
        else{
            while(aux.getSig()!=null && !aux.getDato().equals(datoAnt))
                aux=aux.getSig();
            nuevo.setSig(aux.getSig());
            aux.setSig(nuevo);
        }
    }
    
    public T eliminaPrimero() {
        T res=null;
        Nodo<T> aux = primero;
        if(aux!=null){
            primero = primero.getSig();
            res = aux.getDato();
            // Optativo:
            aux.setSig(null);
        }
        return res;
    }
    
    public T eliminaUltimo() {
        T res = null;
        Nodo<T> aux = primero;
        if(aux!=null){
            //si el último dato resulta ser también el primer (y unico):
            if(aux.getSig()==null){
                res=aux.getDato();
                primero = null;
            }
            //en caso contario:
            else {
                //se 8mueve "aux" hasta que apunte al nodo previo al ultimo
                while(aux.getSig().getSig()!=null)
                    aux = aux.getSig();
                res = aux.getSig().getDato();
                aux.setSig(null);
            }
        }
        return res;
    }
    
    public T eliminaDato (T dato) {
        T res = null;
        Nodo<T> aux=primero;
        if(aux!=null){
            if(aux.getSig()==null){
                if(aux.getDato().equals(dato)){
                    res = aux.getDato();
                    primero = null;
                }
            }else{
                while(aux.getSig()!=null && !aux.getSig().getSig().equals(dato))
                    aux = aux.getSig();
                if(aux.getSig()!=null){
                    res = aux.getSig().getDato();
                    aux.setSig(aux.getSig().getSig());
                }
            }
        }
        return res;
    }
    
    public boolean contains(T dato){
        boolean resp = false;
        Nodo<T> aux = primero;
        while(aux.getSig()!=null && aux.getDato()!=dato)
            aux=aux.getSig();
        if(aux!=null)
           resp = true;
        return resp;
    }
    
    
    
}
