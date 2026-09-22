import java.util.ArrayList;
import java.util.Scanner;

public class Poedex {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        ArrayList<Pokemon> pokedex = new ArrayList<>();

        Pokemon pikachu = new Pokemon("Pikachu", Type.ELECTRIC, 100);
        Pokemon bulbasaur = new Pokemon("Bulbasaur", Type.GRASS, 100);

        Attack thunderbolt = new Attack("Thunderbolt", 80, 95, Type.ELECTRIC);
        Attack flamethrower = new Attack("Flamethrower", 90, 100, Type.FIRE);

        pokedex.add(pikachu);
        pokedex.add(bulbasaur);

        pikachu.attacks.add(thunderbolt);
        pikachu.attacks.add(flamethrower);

        bulbasaur.attacks.add(flamethrower);

        showAllPokemon(pokedex);




    }

    public static void showAllPokemon (ArrayList<Pokemon> pokedex){
        for (Pokemon pokemon : pokedex) {
            System.out.println("Name: " + pokemon.name);
            System.out.println("Type: " + pokemon.type);
            System.out.println("HP: " + pokemon.currentHp + "/" + pokemon.maxHp);

            for (Attack attack : pokemon.attacks) {
                System.out.println("Attack: " + attack.name);
            }
            System.out.println();
        }
    }
}

