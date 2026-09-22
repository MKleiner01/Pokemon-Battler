import java.util.ArrayList;
import java.util.Scanner;

public class Poedex {
    public static void main(String[] args) {

        ArrayList<Pokemon> pokedex = new ArrayList<>();

        Pokemon pikachu = new Pokemon("Pikachu", Type.ELECTRIC, 100);
        Attack thunderbolt = new Attack("Thunderbolt", 80, 95, Type.ELECTRIC);

        pikachu.attacks.add(thunderbolt);

        System.out.println(pikachu.name + " Used: " + thunderbolt.name);


    }


}