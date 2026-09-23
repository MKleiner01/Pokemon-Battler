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

        while (running) {
            System.out.println("-- MENU --");
            System.out.println("1. Show all Pokemon");
            System.out.println("2. Add Pokemon");
            System.out.println("3. Quit");

            int choice = readInt(scan, "Choose one: ");

            switch (choice) {

                case 1 -> showAllPokemon(pokedex);
                case 2 -> addPokemon(scan, pokedex);

                case 3 -> {
                    System.out.println("Closing...");
                    running = false;
                }
                default -> System.out.println("Invalid, try again");
            }
        }


    }

    public static void showAllPokemon(ArrayList<Pokemon> pokedex) {
        for (Pokemon pokemon : pokedex) {
            System.out.println("Name: " + pokemon.name);
            System.out.println("Type: " + pokemon.type);
            System.out.println("HP: " + pokemon.currentHp + "/" + pokemon.maxHp);

            for (Attack attack : pokemon.attacks) {
                System.out.println("Attack name: " + attack.name + " Type: " + attack.type + " Damage: " + attack.baseDamage + " Accuracy: " + attack.accuracy);
            }
            System.out.println();
        }
    }

    public static int readInt(Scanner scan, String prompt) {

        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid number");
            }
        }
    }

    public static void addPokemon(Scanner scan, ArrayList<Pokemon> pokedex) {
        String name = readString(scan, "Enter the POKEMON NAME");

        Type pokemonType;
        while (true) {
            try {
                System.out.print("Enter the Pokemon TYPE: ");
                String type = scan.nextLine();
                pokemonType = Type.valueOf(type.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("That is not a valid Type");
            }
        }
        int hp = readInBetween(scan, "Enter POKEMONS HP", 1, 999);
        Pokemon newPokemon = new Pokemon(name, pokemonType, hp);
        String attackName = readString(scan, "Enter the ATTACK name: ");
        int baseDamage = readInBetween(scan, "Enter ATTACK damage", 1, 200);
        int accuracy = readInBetween(scan, "Enter ATTACK Accuracy",1, 100);

        Type attackType;
        while (true) {
            try {
                System.out.print("Enter the ATTACK type: ");
                String type = scan.nextLine();
                attackType = Type.valueOf(type.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("That is not a valid Type");
            }
        }


       Attack newAttack = new Attack(attackName, baseDamage, accuracy, attackType);
        newPokemon.attacks.add(newAttack);
        pokedex.add(newPokemon);


    }

    public static String readString(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine();
            if (!input.isBlank()) {
                return input;
            } else {
                System.out.println("Not a valid input");
            }
        }
    }
    public static int readInBetween(Scanner scan, String prompt, int min, int max){
        while (true) {
            int number = readInt(scan, prompt);
            if (number >= min && number <= max){
                return number;
            } else {
                System.out.println("Number must be between: " + min + " and " + max);
            }

        }

    }



}

