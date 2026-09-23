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
            System.out.println("3. Edit Pokemon");
            System.out.println("4. Quit");

            int choice = readInt(scan, "Choose one: ");

            switch (choice) {

                case 1 -> showAllPokemon(pokedex);
                case 2 -> addPokemon(scan, pokedex);
                case 3 -> editPokemon(scan, pokedex);
                case 4 -> {
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

        Type pokemonType = readType(scan, "Enter pokemon TYPE");

        int hp = readInBetween(scan, "Enter POKEMONS HP, 1 - 999", 1, 999);
        Pokemon newPokemon = new Pokemon(name, pokemonType, hp);
        System.out.println("Your Pokemon needs atleast 1 ATTACK");
        String attackName = readString(scan, "Enter the Attack name ");
        int baseDamage = readInBetween(scan, "Enter ATTACK damage, 1 - 200", 1, 200);
        int accuracy = readInBetween(scan, "Enter ATTACK Accuracy, 1 - 100",0, 100);
        Type attackType = readType(scan, "which Pokemon Type do you want");


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
    public static Pokemon selectPokemon(Scanner scan, ArrayList<Pokemon> pokedex) {

        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i+1 ) + ". " + pokedex.get(i).name);
        }
        int choice = readInBetween(scan, "Select one pokemon", 1, pokedex.size());
        return pokedex.get(choice - 1);
    }

    public static Type readType(Scanner scan, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String type = scan.nextLine();

              return Type.valueOf(type.toUpperCase());

            } catch (IllegalArgumentException e) {
                System.out.println("That is not a valid Type");
            }
        }
    }

    public static void editPokemon(Scanner scan, ArrayList<Pokemon> pokedex){

        Pokemon pokemonToEdit = selectPokemon(scan, pokedex);
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Type");
        System.out.println("3. HP");

        int choice = readInBetween(scan, "Choose", 1, 3);

        switch(choice){

            case 1 -> { String name =readString(scan, "What name do you want");
                pokemonToEdit.name = name;
                  }
            case 2 -> { Type type = readType(scan, "What TYPE do you want");
                pokemonToEdit.type = type;

            }
            case 3 -> { int hp = readInBetween(scan, "What HP do you want, 1 - 999", 1, 999);
                pokemonToEdit.maxHp = hp;
                pokemonToEdit.currentHp = hp;

            }

            }
        }
    }




