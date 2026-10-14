public class Battle {
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;


public Battle(Pokemon playerPokemon, Pokemon enemyPokemon){
this.playerPokemon = playerPokemon;
this.enemyPokemon = enemyPokemon;

}

public void showMatchup(){
    System.out.println(playerPokemon.getName() + " VS " + enemyPokemon.getName());
}


public boolean isBattleOver(){
   return enemyPokemon.isFainted() || playerPokemon.isFainted();
    }


public void showWinner(){
    if(!isBattleOver()){
        System.out.println("There is no winner yet");
        return;
    }
    if (playerPokemon.isFainted()){
        System.out.println("Winner: " + enemyPokemon.getName());
    }else {
        System.out.println("Winner: " + playerPokemon.getName());
    }

}


}

