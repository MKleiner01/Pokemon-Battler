import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;


    public Battle(Pokemon playerPokemon, Pokemon enemyPokemon) {
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;

    }

    public void showMatchup() {
        System.out.println(playerPokemon.getName() + " VS " + enemyPokemon.getName());
    }


    public boolean isBattleOver() {
        return enemyPokemon.isFainted() || playerPokemon.isFainted();
    }


    public void showWinner() {
        if (!isBattleOver()) {
            System.out.println("There is no winner yet");
            return;
        }
        if (playerPokemon.isFainted()) {
            System.out.println("Winner: " + enemyPokemon.getName());
        } else {
            System.out.println("Winner: " + playerPokemon.getName());
        }

    }

    public void showPlayerAttacks() {
        System.out.println(playerPokemon.getName() + " Has " + playerPokemon.getAttacks().size() + " attacks available");

        int counter = 1;
        for (Attack attack : playerPokemon.getAttacks()) {
            System.out.println(counter + ". " + attack.getName());
            counter++;
        }
    }

    public boolean playerAttack(int choice) {
        if (choice < 1 || choice > playerPokemon.getAttacks().size()) {
            System.out.println("Invalid attack choice");
            return false;
        }
        Attack selectedAttack = playerPokemon.getAttacks().get(choice - 1);
        System.out.println(playerPokemon.getName() + " used: " + selectedAttack.getName());
        Random random = new Random();
        int accuracy = selectedAttack.getAccuracy();
        int roll = random.nextInt(100) + 1;

        if (roll > accuracy) {
            System.out.println("Attack missed!");
            return true;
        }

        int damage = selectedAttack.getBaseDamage();
        enemyPokemon.takeDamage(damage);
        System.out.println(enemyPokemon.getName() + " has " + enemyPokemon.getCurrentHp() + " HP left");
        return true;
    }

    public void enemyAttack() {
        if (enemyPokemon.getAttacks().isEmpty()) {
            System.out.println(enemyPokemon.getName() + " has no attacks");
            return;
        }
        Random random = new Random();
        int randomChoice = random.nextInt(enemyPokemon.getAttacks().size());
        Attack selectedAttack = enemyPokemon.getAttacks().get(randomChoice);

        System.out.println(enemyPokemon.getName() + " used: " + selectedAttack.getName());

        int accuracy = selectedAttack.getAccuracy();
        int roll = random.nextInt(100) + 1;

        if (roll > accuracy) {
            System.out.println("Attack missed!");
            return;
        }

        int damage = selectedAttack.getBaseDamage();
        playerPokemon.takeDamage(damage);
        System.out.println(playerPokemon.getName() + " has " + playerPokemon.getCurrentHp() + " HP left");
    }

    public void playTurn(int choice) {
        if (isBattleOver()) {
            System.out.println("The battle is already over!");
            return;
        }
        if (!playerAttack(choice)) {
            return;
        }
        if(enemyPokemon.isFainted()){
            System.out.println(enemyPokemon.getName() + " has fainted");
            showWinner();
            return;
        }
        enemyAttack();
        if(playerPokemon.isFainted()){
            System.out.println(playerPokemon.getName() + " has fainted");
            showWinner();
            return;
        }
    }
    public void startBattle() {
        if(playerPokemon.getAttacks().isEmpty()) {
            System.out.println(playerPokemon.getName() + " has no attacks ");
            return;
        }

        if(enemyPokemon.getAttacks().isEmpty()) {
            System.out.println(enemyPokemon.getName() + " has no attacks ");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        showMatchup();
        while (!isBattleOver()) {
            System.out.println();
            System.out.println("----- YOUR TURN ------");
            System.out.println(playerPokemon.getName() + ": " + playerPokemon.getCurrentHp() + "/" + playerPokemon.getMaxHp() + " HP");
            System.out.println(enemyPokemon.getName() + ": " + enemyPokemon.getCurrentHp() + "/" + enemyPokemon.getMaxHp() + " HP");
            System.out.println("----------------------");
            showPlayerAttacks();
            System.out.print("Choose attack: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Enter a number!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            playTurn(choice);
        }
    }
    public String getWinnerName() {
        if (enemyPokemon.isFainted()) {
            return playerPokemon.getName();
        }

        if (playerPokemon.isFainted()) {
            return enemyPokemon.getName();
        }

        return "No winner yet";
    }



}

