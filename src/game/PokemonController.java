package game;

import pokemons.Bubasauro;
import pokemons.Caterpie;
import pokemons.Charmander;
import pokemons.Cubone;
import pokemons.Ekans;
import pokemons.Gardevoir;
import pokemons.Gyarados;
import pokemons.Pidgeot;
import pokemons.Pikachu;
import pokemons.Rattata;
import pokemons.Raychu;
import pokemons.Spearow;
import java.util.Scanner;

public class PokemonController extends Controller{
	public static final boolean comum = false;
	public static final boolean gramado = true;

	public static final String direita = "D", cima = "C";
	public static final String esquerda = "E", baixo = "B";

	public PokemonController() {
		super();
	}

	public void addEvent() {
		System.out.println("Evento adicionado");
	}
	
	
	private class Select extends Event{
		private String saida;
		Treinador a = new Treinador("Esponja", true);
		int selecao;

		public Select(Treinador a, int selecao) {
			this.a=a;
			this.selecao=selecao;
		}

		public void prioridade() {
			saida = a.selectedPoke().getNome();

		}

		public String description() {
			return ("(" + a.getNome() +") selecionou "+ saida);
		}

	}
	private class Acao extends Event{
		Treinador a;
		Treinador b;
		String move= "";
		int acao_t1=0;
		int sel_t1=0;
		int acao_t2=0;
		int sel_t2=0;
		boolean alvo;//true: alvo � o inimigo / false: o alvo � ele mesmo 

		public Acao(Treinador a, int acao_t1, int sel_t1, 
				Treinador b,  int acao_t2, int sel_t2, boolean alvo) {
			this.a=a;
			this.b=b;

			this.acao_t1=acao_t1;
			this.sel_t1=sel_t1;

			this.acao_t2=acao_t2;
			this.sel_t2=sel_t2;
		}

		public void prioridade() {

			if(acao_t1>acao_t2) {
				if(alvo==true) {
					move += a.movimentoSelect(acao_t1,sel_t1,b.selectedPoke());	
					move += b.movimentoSelect(acao_t2,sel_t2,a.selectedPoke());
				}
				else {
					move += a.movimentoSelect(acao_t1,sel_t1,a.selectedPoke());	
					move += b.movimentoSelect(acao_t2,sel_t2,b.selectedPoke());	
				}

			}
			else {
				if(alvo==true) {
					move += b.movimentoSelect(acao_t2,sel_t2,a.selectedPoke());
					move += a.movimentoSelect(acao_t1,sel_t1,b.selectedPoke());	
				}
				else {
					move += b.movimentoSelect(acao_t2,sel_t2,b.selectedPoke());
					move += a.movimentoSelect(acao_t1,sel_t1,a.selectedPoke());		
				}
			}
		}

		public String description() {

			return(move);
			/*return ("(" + a.getNome() + ") usou " + 
					a.selPoke().getNome() + " para atacar o "
					+ b.selPoke().getNome()+" de ("+ b.getNome() + ") com " + move);
			 */
		}

	}

	private class Restart extends Event {

		public Restart() {
			System.out.print("COERAPAZIADA");
		}
		public String description() {
			return "";
		}

		private Treinador esponja = new Treinador("Bob esponja", true);
		private Treinador construtor = new Treinador("Bob, o construtor", true);


		public void prioridade() {
			long tm = System.currentTimeMillis();
			
			Scanner scanf = new Scanner(System.in);
			String poke;


			// Esqueleto do jogo de verdade
			int n = 3;
			System.out.println("Adicione " + n + " Pokemons � pokedex");
			for(int i = 0; i < n; i++) {
				poke = scanf.nextLine();
				esponja.addPoke(poke);
			}

			/*System.out.println("\nCOMECOU A BATALHA\n");
			addEvent(new Select(esponja,0));
			addEvent(new Select(construtor,0));
			addEvent(new Acao(esponja,0, 0,construtor,2,1,true));
			addEvent(new Acao(esponja,2, 1,construtor,0,0,false)); */	

			Tile[][] mapa;
			Tile aux = new Tile();
			String direcao;

			int linhas = 20;
			int colunas = 20;

			mapa = aux.criaMapa(linhas, colunas);
			System.out.print("Pra qual lado voc� vai? (D = direita, E = esquerda"
					+ ", C = cima, B = baixo)");					
			direcao = scanf.next();
			aux.anda(direcao, linhas, colunas);
			System.out.print(", onde h� ");

			if(mapa[ aux.atualLin() ][ aux.atualCol() ].tipo() == gramado) {
				System.out.println("grama");
			}

			else if(mapa[ aux.atualLin() ][ aux.atualCol() ].tipo() == comum) {
				System.out.println("ch�o");
			}

			addEvent( new Restart(System.currentTimeMillis() ) );
		}

		public Restart(long time) {
			super(time);
		}

	}

	public static void main(String[] args) {

		PokemonController poke = new PokemonController();
		long tm = System.currentTimeMillis();
		poke.addEvent(poke.new Restart(tm));
		poke.run();
	}

}
