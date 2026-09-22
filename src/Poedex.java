import java.util.ArrayList;
import java.util.Scanner;

public class Poedex {
    public static void main(String[] args) {

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

        Scanner scan = new Scanner(System.in);

        boolean running = true;

        while(running){
            System.out.println("-- MENU --");
            System.out.println("1. Show all Pokemon");
            System.out.println("2. Quit");

            int choice = readInt(scan, "Choose one: ");

            switch(choice){

                case 1 -> showAllPokemon(pokedex);

                case 2 -> {
                    System.out.println("Closing...");
                    running = false;
                }
                default -> System.out.println("Invalid, try again");
            }
        }


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
    public static int readInt(Scanner scan, String prompt) {

        while(true){
            System.out.print(prompt);
            String input = scan.nextLine();

            try{
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("That is not a valid number");
            }

        }

    }
}

