package pokemons;

import game.Move;
import game.Pokemon;

public class  Charmander extends Pokemon{	
	public Charmander() {
		nome = "Charmander";
		Move[] ataques= {new Move("teste0",10),new Move("teste1",10),new Move("teste2",10),new Move("teste3",10)};
		tipo = "Fire";
	}
}