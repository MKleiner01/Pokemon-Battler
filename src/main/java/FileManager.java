import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final Path pokemonPath = Path.of("Pokemon.txt");
    private static final Path attackPath = Path.of("Attack.txt");

    public static void saveToFile(ArrayList<Pokemon> pokedex) {
        ArrayList<String> pokemonLines = new ArrayList<>();
        ArrayList<String> attackLines = new ArrayList<>();
        for (Pokemon pokemon : pokedex) {
            pokemonLines.add(pokemon.getName() + "," + pokemon.getType() + "," + pokemon.getCurrentHp() + "," + pokemon.getMaxHp() + "," + pokemon.getSpeed());
            for (Attack attack : pokemon.getAttacks()) {
                attackLines.add(attack.getName() + "," + attack.getType() + "," + attack.getBaseDamage() + "," + attack.getAccuracy() + "," + pokemon.getName());
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
    public static void loadFromFile(ArrayList<Pokemon> pokedex) {

        try {
            List<String> pokemonLines = Files.readAllLines(pokemonPath);

            for (String line : pokemonLines) {
                String[] parts = line.split(",");

                String name = parts[0];
                Type type = Type.valueOf(parts[1]);
                int currentHp = Integer.parseInt(parts[2]);
                int maxHp = Integer.parseInt(parts[3]);
                int speed = Integer.parseInt(parts[4]);

                Pokemon pokemon = new Pokemon(name, type, maxHp, speed);
                pokemon.setCurrentHp(currentHp);

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
                    if (pokemon.getName().equals(pokemonName)) {
                        pokemon.getAttacks().add(attack);
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
    public static boolean filesExist() {
        return Files.exists(pokemonPath) && Files.exists(attackPath);
    }
}