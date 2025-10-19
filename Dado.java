package demo;

import java.util.Random;

public class Dado
{
    private int caras;
    private int valor;
    private boolean apartado;

    public Dado()
    {
        caras = 6;
        valor = 1;
        apartado = false;
    }
    public int lanzar() {
        if (!apartado) {
            Random aleatorio;
            aleatorio = new Random();
            valor = aleatorio.nextInt(caras) + 1;
        }
        return valor;
    }
    public int getCaras(){
        return caras;
    }
    public void setCaras(int caras){
        this.caras = caras;
    }
    public int getValor(){
        return valor;
    }
    public void setValor(int nuevoValor){
        valor = nuevoValor;
    }

    public void setApartado(boolean apartado) {
        this.apartado = apartado;
    }
    public boolean getApartado(){
        return apartado;
    }
}