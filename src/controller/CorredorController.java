package controller;

import java.util.concurrent.Semaphore;

public class CorredorController extends Thread{
    private static int  posChegada;
    private int idPessoa;
    private Semaphore semaforo;

    public CorredorController(int idPessoa,Semaphore semaforo) {
        this.idPessoa=idPessoa;
        this.semaforo=semaforo;
    }


    @Override
    public void run() {
    pessoasCaminhando();
        try {
            semaforo.acquire();
            cruzarPorta();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaforo.release();
        }
    }
    private void pessoasCaminhando() {
        int distanciaTotal=200;
        int distanciaPercorrida=0;
        int tempo=(int)((Math.random()*1001)+1000);
        int pessoaAnda=(int)((Math.random()*2000)+4000)/tempo;

        while(distanciaPercorrida<distanciaTotal){
            distanciaPercorrida+=pessoaAnda;
            try {
                sleep(tempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A pessoa #"+this.idPessoa+" já andou "+ distanciaPercorrida);

        }
        posChegada++;

    }

    private void cruzarPorta() {
        System.out.println("Pessoa #"+this.idPessoa+" cruzou a porta");
        System.out.println("#"+this.idPessoa+" foi o "+this.posChegada+".o a chegar");
    }
}
