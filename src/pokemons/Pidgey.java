package pokemons;
import game.Pokemon;
import game.Move;

public class Pidgey extends Pokemon {
	
	public Pidgey() {
		nome = "Pidgey";
		Move[] ataques= {new Move("teste0",10),new Move("teste1",10), new Move("teste2",10),new Move("teste3",10)};
		tipo = "Flying";
	}
}