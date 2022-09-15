package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ServiceThread extends Thread{
	public int id;
	Semaphore semaphore;
	
	public ServiceThread (int Id, Semaphore semaphore) {
		this.semaphore = semaphore;
		this.id = Id;
	}
// Calculo de maluco... Meu jeova ajuda a tia :) 	
	public void calculo (int Id) {
		Random r = new Random();
		int tempo = 0;
		if (Id % 3 == 1) {
			tempo = r.nextInt((1000-200)+1)+200;
		} else if (Id % 3 == 2) {
			tempo = r.nextInt((1500-500)+1)+500;
			} else if (Id % 3 == 0) {
				tempo=r.nextInt ((2000-100)+1)+1000;
			}
		System.out.println ("Cálculo " + id);
			try {
				sleep (tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	public void tbd (int id){
		Random r = new Random();
				int tempo = 0;
				if(id % 3 == 1){
					tempo = r.nextInt((1000-200)+1)+200;
	} else if (id % 3 == 2 || id % 3 == 0) {
		tempo = 1500;
	}
	System.out.println("Enviado para o Banco de Dados " + id);
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	}
	

	
	@Override
	
	public void run () {
		if(id % 3 ==1) {
			try {
				calculo (id);
				semaphore.acquire();
				tbd (id);
				semaphore.release();
			} catch (InterruptedException e){
				e.printStackTrace(); 
			} finally{ 
				semaphore.release();
			}
		} else if (id % 3 == 2 || id % 3 == 0){ 
			try {
				calculo(id);
				semaphore.acquire();
				tbd(id);
				semaphore.release(); 
				calculo(id); 
				semaphore.acquire(); 
				tbd(id); 
				semaphore.release(); 
				calculo(id); 
				semaphore.acquire(); 
				tbd(id); 
				semaphore.release(); 
			}catch (InterruptedException e){ 
				e.printStackTrace();
		} finally {
			semaphore.release(); 
		}
		}
	}
}

		
		
		
	