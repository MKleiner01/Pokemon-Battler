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

    public void playerAttack(int choice) {
        if (choice < 1 || choice > playerPokemon.getAttacks().size()) {
            System.out.println("Invalid attack choice");
            return;
        }
        Attack selectedAttack = playerPokemon.getAttacks().get(choice - 1);
        System.out.println(playerPokemon + " used: " + selectedAttack.getName());
    }
}

