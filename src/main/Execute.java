package main;

import main.game.Game;

public class Execute {
	public static void main(String[] args) {
		int i = 0;
		int[] metrics = {};
		int sucess = 0;
		int fails = 0;
		int steps = 0;
		while(i <= 20){
			Game g = new Game();
			metrics = g.run();
			++i;
			sucess += metrics[0];
			fails += metrics[1];
			steps += metrics[2];
		}
		System.out.println("Número de sucessos: " + sucess);
		System.out.println("Número de fracassos: " + fails);
		System.out.println("Número de passos totais: " + steps);
	}

}
