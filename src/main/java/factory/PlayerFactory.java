package factory;

import domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {
    public static List<Player> create(String userInput) {
        List<Player> players = new ArrayList<>();
        String[] userInputs = userInput.split(",");
        for(int i=0;i<userInputs.length;i++){
            players.add(new Player(userInputs[i]));
        }
        return players;
    }
}
