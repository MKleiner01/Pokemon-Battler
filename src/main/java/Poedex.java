import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;

public class Poedex {
    public static void main(String[] args) {

        ArrayList<Pokemon> pokedex = new ArrayList<>();
        Path pokemonPath = Path.of("Pokemon.txt");
        Path attackPath = Path.of("Attack.txt");

        Scanner scan = new Scanner(System.in);

        if(Files.exists(pokemonPath) && Files.exists(attackPath)){
           loadFromFile(pokedex,pokemonPath,attackPath);
        }else {seedData(pokedex);
        }

        boolean running = true;

        while (running) {
            System.out.println("-- MENU --");
            System.out.println("1. Show all Pokemon");
            System.out.println("2. Add Pokemon");
            System.out.println("3. Edit Pokemon");
            System.out.println("4. Delete Pokemon");
            System.out.println("5.Save to file");
            System.out.println("6.Load from File");
            System.out.println("7.Reset to Seed data");
            System.out.println("8.Quit");



            int choice = readInt(scan, "Choose one: ");

            switch (choice) {

                case 1 -> showAllPokemon(pokedex);
                case 2 -> addPokemon(scan, pokedex);
                case 3 -> editPokemon(scan, pokedex);
                case 4 -> deletePokemon(scan, pokedex);
                case 5 -> saveToFile(pokedex, pokemonPath, attackPath);
                case 6 -> {
                    if (Files.exists(pokemonPath) && Files.exists(attackPath)) {
                        pokedex.clear();
                        loadFromFile(pokedex, pokemonPath, attackPath);
                    } else {
                        System.out.println("No save files found");
                    }
                }
                case 7 -> seedData(pokedex);
                case 8 -> {
                    System.out.println("Closing...");
                    saveToFile(pokedex, pokemonPath, attackPath);
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
        int baseDamage = readInBetween(scan, "Enter ATTACK damage, 1 - 500", 1, 500);
        int accuracy = readInBetween(scan, "Enter ATTACK Accuracy, 1 - 100",0, 100);
        Type attackType = readType(scan, "Enter ATTACK Type");


       Attack newAttack = new Attack(attackName, baseDamage, accuracy, attackType);
        newPokemon.attacks.add(newAttack);
        pokedex.add(newPokemon);

    }

    public static String readString(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine();
            if (!input.isBlank() && !input.contains(",")) {
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

        if(pokedex.isEmpty()){
            System.out.println("There is no Pokemon to Edit4");
        }else {Pokemon pokemonToEdit = selectPokemon(scan, pokedex);
            System.out.println("What do you want to edit?");
            System.out.println("1. Name");
            System.out.println("2. Type");
            System.out.println("3. HP");
            System.out.println("4. Add attack");
            System.out.println("5. Remove attack");

            int choice = readInBetween(scan, "Choose", 1, 5);

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
                case 4 -> {
                    if (pokemonToEdit.attacks.size() < 4) {
                   String name = readString(scan, "Whats the Attack name");
                   int damage = readInBetween(scan, "Enter ATTACK damage, 1 - 500", 0, 500);
                   int accuracy = readInBetween(scan, "Enter ATTACK Accuracy, 1 - 100", 0, 100);
                   Type type = readType(scan, "Whats the Attack Type");
                   Attack attack = new Attack(name, damage, accuracy, type);
                   pokemonToEdit.attacks.add(attack);
                }else {
                        System.out.println("Too many attacks");
                    }

                }
                case 5 -> {
                    if (pokemonToEdit.attacks.size() > 1) {

                        for (int i = 0; i < pokemonToEdit.attacks.size(); i++) {
                            System.out.println((i + 1) + ". " + pokemonToEdit.attacks.get(i).name);

                        }
                        int attackChoice= readInBetween(scan, "Choose attack to remove", 1, pokemonToEdit.attacks.size() );
                        pokemonToEdit.attacks.remove(attackChoice - 1);
                    } else {
                        System.out.println( "A Pokemon must have at least 1 attack");
                    }
                }

            }
        }

        }
        public static void deletePokemon(Scanner scan, ArrayList<Pokemon> pokedex){
        if(pokedex.isEmpty()){
            System.out.println("There is no Pokemon to delete");
        }else {
            Pokemon pokemon = selectPokemon(scan, pokedex);
            pokedex.remove(pokemon);

            System.out.println("Deleting " + pokemon.name + "...");
        }


        }
        public static void saveToFile(ArrayList<Pokemon> pokedex, Path pokemonPath, Path attackPath){
        ArrayList<String> pokemonLines = new ArrayList<>();
            ArrayList<String> attackLines = new ArrayList<>();

        for(Pokemon pokemon : pokedex){
            pokemonLines.add(pokemon.name + "," + pokemon.type + "," + pokemon.currentHp + "," + pokemon.maxHp);
            for (Attack attack : pokemon.attacks) {
                attackLines.add(attack.name + "," +  attack.type + "," + attack.baseDamage + "," + attack.accuracy +","+ pokemon.name);
            }
        }
            String allPokemon = String.join("\n", pokemonLines);
            try {
                Files.writeString(pokemonPath, allPokemon);
            } catch (IOException e) {
                System.out.println("Cannot save to file");
            }

            String allAttacks = String.join("\n", attackLines);
            try {
                Files.writeString(attackPath, allAttacks);
            } catch (IOException e) {
                System.out.println("Cannot save to file");
            }

        }
    public static void loadFromFile(ArrayList<Pokemon> pokedex, Path pokemonPath, Path attackPath) {
        try {
            List<String> pokemonLines = Files.readAllLines(pokemonPath);

            for (String line : pokemonLines) {
                String[] parts = line.split(",");

                String name = parts[0];
                Type type = Type.valueOf(parts[1]);
                int currentHp = Integer.parseInt(parts[2]);
                int maxHp = Integer.parseInt(parts[3]);

                Pokemon pokemon = new Pokemon(name, type, maxHp);
                pokemon.currentHp = currentHp;

                pokedex.add(pokemon);
            }

            List<String> attackLines = Files.readAllLines(attackPath);

            for (String line : attackLines) {
                String[] parts = line.split(",");

                String attackName = parts[0];
                Type type = Type.valueOf(parts[1]);
                int baseDamage = Integer.parseInt(parts[2]);
                int accuracy = Integer.parseInt(parts[3]);
                String pokemonName = parts[4];

                Attack attack = new Attack(attackName, baseDamage, accuracy, type);

                for (Pokemon pokemon : pokedex) {
                    if (pokemon.name.equals(pokemonName)) {
                        pokemon.attacks.add(attack);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Cannot read from file");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in file");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type in file");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data in file");
        }
    }

    public static void seedData(ArrayList<Pokemon> pokedex) {
        pokedex.clear();

        Pokemon p1 = new Pokemon("Pikachu", Type.ELECTRIC, 100);
        p1.attacks.add(new Attack("Thunderbolt", 90, 100, Type.ELECTRIC));
        p1.attacks.add(new Attack("Quick Attack", 40, 100, Type.NORMAL));

        Pokemon p2 = new Pokemon("Charmander", Type.FIRE, 95);
        p2.attacks.add(new Attack("Ember", 40, 100, Type.FIRE));

        Pokemon p3 = new Pokemon("Squirtle", Type.WATER, 105);
        p3.attacks.add(new Attack("Water Gun", 40, 100, Type.WATER));

        Pokemon p4 = new Pokemon("Bulbasaur", Type.GRASS, 100);
        p4.attacks.add(new Attack("Vine Whip", 45, 100, Type.GRASS));

        Pokemon p5 = new Pokemon("Eevee", Type.NORMAL, 110);
        p5.attacks.add(new Attack("Tackle", 40, 100, Type.NORMAL));

        Pokemon p6 = new Pokemon("Snorlax", Type.NORMAL, 200);
        p6.attacks.add(new Attack("Body Slam", 85, 100, Type.NORMAL));

        pokedex.add(p1);
        pokedex.add(p2);
        pokedex.add(p3);
        pokedex.add(p4);
        pokedex.add(p5);
        pokedex.add(p6);

        System.out.println("Pokedex has been reset to seed data.");
    }

}







