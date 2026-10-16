
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Poedex {
    public static void main(String[] args) {

        ArrayList<Pokemon> pokedex = new ArrayList<>();
        BattleHistory battleHistory = new BattleHistory();
        Scanner scan = new Scanner(System.in);

        battleHistory.load();

        if (FileManager.filesExist()) {
            FileManager.loadFromFile(pokedex);
        } else {
            seedData(pokedex);
        }

        boolean running = true;

        while (running) {
            System.out.println("-- MENU --");
            System.out.println("1. Show all Pokemon");
            System.out.println("2. Add Pokemon");
            System.out.println("3. Edit Pokemon");
            System.out.println("4. Delete Pokemon");
            System.out.println("5. Save to file");
            System.out.println("6. Load from File");
            System.out.println("7. Reset to Seed data");
            System.out.println("8. Start Battle");
            System.out.println("9. View Battle History");
            System.out.println("10. Quit");


            int choice = InputHelper.readInt(scan, "Choose one: ");

            switch (choice) {

                case 1 -> showAllPokemon(pokedex);
                case 2 -> addPokemon(scan, pokedex);
                case 3 -> editPokemon(scan, pokedex);
                case 4 -> deletePokemon(scan, pokedex);
                case 5 -> FileManager.saveToFile(pokedex);
                case 6 -> {
                    if (FileManager.filesExist()) {
                        pokedex.clear();
                        FileManager.loadFromFile(pokedex);
                    } else {
                        System.out.println("No save files found");
                    }
                }
                case 7 -> seedData(pokedex);
                case 8 -> {
                    if (pokedex.size() < 2) {
                    System.out.println("Need 2 pokemon to start battle");
                } else {
                    Pokemon playerPokemon = selectPokemon(scan, pokedex);
                    Random random = new Random();
                    int randomChoice = random.nextInt(pokedex.size());
                    Pokemon enemyPokemon = pokedex.get(randomChoice);
                        while (enemyPokemon == playerPokemon) {
                            randomChoice = random.nextInt(pokedex.size());
                            enemyPokemon = pokedex.get(randomChoice);
                        }

                        Battle battle = new Battle(playerPokemon.createBattleCopy(), enemyPokemon.createBattleCopy());
                        battle.startBattle(scan);

                        if (battle.isBattleOver()) {
                            String result = playerPokemon.getName() + " VS "
                                    + enemyPokemon.getName() + " - Winner: "
                                    + battle.getWinnerName();

                            battleHistory.addResult(result);
                        }
                }
                }
                case 9 -> {
                    battleHistory.showHistory();
                }
                case 10 -> {
                    System.out.println("Closing...");
                    FileManager.saveToFile(pokedex);
                    running = false;
                }
                default -> System.out.println("Invalid, try again");
            }
        }
    }

    public static void showAllPokemon(ArrayList<Pokemon> pokedex) {
        for (Pokemon pokemon : pokedex) {
            System.out.println(pokemon);

            for (Attack attack : pokemon.getAttacks()) {
                System.out.println(attack);
            }
        }
    }

    public static void addPokemon(Scanner scan, ArrayList<Pokemon> pokedex) {
        String name = InputHelper.readString(scan, "Enter the POKEMON NAME");

        Type pokemonType = InputHelper.readType(scan, "Enter pokemon TYPE");

        int hp = InputHelper.readInBetween(scan, "Enter POKEMONS HP, 1 - 999", 1, 999);
        int speed = InputHelper.readInBetween(scan, "Enter POKEMONS SPEED, 1 - 255", 1, 255);
        Pokemon newPokemon = new Pokemon(name, pokemonType, hp, speed);
        System.out.println("Your Pokemon needs atleast 1 ATTACK");
        String attackName = InputHelper.readString(scan, "Enter the Attack name ");
        int baseDamage = InputHelper.readInBetween(scan, "Enter ATTACK damage, 1 - 500", 1, 500);
        int accuracy = InputHelper.readInBetween(scan, "Enter ATTACK Accuracy, 1 - 100", 1, 100);
        Type attackType = InputHelper.readType(scan, "Enter ATTACK Type");

        Attack newAttack = new Attack(attackName, baseDamage, accuracy, attackType);
        newPokemon.getAttacks().add(newAttack);
        pokedex.add(newPokemon);
    }


    public static Pokemon selectPokemon(Scanner scan, ArrayList<Pokemon> pokedex) {

        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i + 1) + ". " + pokedex.get(i).getName());
        }
        int choice = InputHelper.readInBetween(scan, "Select one pokemon", 1, pokedex.size());
        return pokedex.get(choice - 1);
    }


    public static void editPokemon(Scanner scan, ArrayList<Pokemon> pokedex) {

        if (pokedex.isEmpty()) {
            System.out.println("There is no Pokemon to Edit4");
        } else {
            Pokemon pokemonToEdit = selectPokemon(scan, pokedex);
            System.out.println("What do you want to edit?");
            System.out.println("1. Name");
            System.out.println("2. Type");
            System.out.println("3. HP");
            System.out.println("4. Add attack");
            System.out.println("5. Remove attack");
            System.out.println("6. Speed");

            int choice = InputHelper.readInBetween(scan, "Choose", 1, 6);

            switch (choice) {

                case 1 -> {
                    String name = InputHelper.readString(scan, "What name do you want");
                    pokemonToEdit.setName(name);
                }
                case 2 -> {
                    Type type = InputHelper.readType(scan, "What TYPE do you want");
                    pokemonToEdit.setType(type);
                }
                case 3 -> {
                    int hp = InputHelper.readInBetween(scan, "What HP do you want, 1 - 999", 1, 999);
                    pokemonToEdit.setMaxHp(hp);
                    pokemonToEdit.setCurrentHp(hp);

                }
                case 4 -> {
                    if (pokemonToEdit.getAttacks().size() < 4) {
                        String name = InputHelper.readString(scan, "Whats the Attack name");
                        int damage = InputHelper.readInBetween(scan, "Enter ATTACK damage, 1 - 500", 1, 500);
                        int accuracy = InputHelper.readInBetween(scan, "Enter ATTACK Accuracy, 1 - 100", 1, 100);
                        Type type = InputHelper.readType(scan, "Whats the Attack Type");
                        Attack attack = new Attack(name, damage, accuracy, type);
                        pokemonToEdit.getAttacks().add(attack);
                    } else {
                        System.out.println("Too many attacks");
                    }

                }
                case 5 -> {
                    if (pokemonToEdit.getAttacks().size() > 1) {

                        for (int i = 0; i < pokemonToEdit.getAttacks().size(); i++) {
                            System.out.println((i + 1) + ". " + pokemonToEdit.getAttacks().get(i).getName());

                        }
                        int attackChoice = InputHelper.readInBetween(scan, "Choose attack to remove", 1, pokemonToEdit.getAttacks().size());
                        pokemonToEdit.getAttacks().remove(attackChoice - 1);
                    } else {
                        System.out.println("A Pokemon must have at least 1 attack");
                    }
                }
                case 6 -> {
                    int speed = InputHelper.readInBetween(scan, "Enter new SPEED, 1 - 255", 1, 255);
                    pokemonToEdit.setSpeed(speed);
                }

            }
        }

    }

    public static void deletePokemon(Scanner scan, ArrayList<Pokemon> pokedex) {
        if (pokedex.isEmpty()) {
            System.out.println("There is no Pokemon to delete");
        } else {
            Pokemon pokemon = selectPokemon(scan, pokedex);
            pokedex.remove(pokemon);

            System.out.println("Deleting " + pokemon.getName() + "...");
        }


    }


    public static void seedData(ArrayList<Pokemon> pokedex) {
        pokedex.clear();

        Pokemon p1 = new Pokemon("Pikachu", Type.ELECTRIC, 100, 110);
        p1.getAttacks().add(new Attack("Thunderbolt", 90, 100, Type.ELECTRIC));
        p1.getAttacks().add(new Attack("Quick Attack", 40, 100, Type.NORMAL));

        Pokemon p2 = new Pokemon("Charmander", Type.FIRE, 95, 90);
        p2.getAttacks().add(new Attack("Ember", 40, 100, Type.FIRE));

        Pokemon p3 = new Pokemon("Squirtle", Type.WATER, 105, 20);
        p3.getAttacks().add(new Attack("Water Gun", 40, 100, Type.WATER));

        Pokemon p4 = new Pokemon("Bulbasaur", Type.GRASS, 100, 55);
        p4.getAttacks().add(new Attack("Vine Whip", 45, 100, Type.GRASS));

        Pokemon p5 = new Pokemon("Eevee", Type.NORMAL, 110, 55);
        p5.getAttacks().add(new Attack("Tackle", 40, 100, Type.NORMAL));

        Pokemon p6 = new Pokemon("Snorlax", Type.NORMAL, 20, 70);
        p6.getAttacks().add(new Attack("Body Slam", 85, 100, Type.NORMAL));

        pokedex.add(p1);
        pokedex.add(p2);
        pokedex.add(p3);
        pokedex.add(p4);
        pokedex.add(p5);
        pokedex.add(p6);

        System.out.println("Pokedex has been reset to seed data.");
    }

}







