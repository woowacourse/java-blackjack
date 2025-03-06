package domain.gamer;

import domain.card.CardGenerator;
import domain.card.CardGroup;

import java.util.List;

public class GamerGenerator {
    public static List<Player> generatePlayer(List<String> playerNames, CardGenerator cardGenerator){
        return playerNames.stream()
                .map(name -> new Player(name,new CardGroup(),cardGenerator))
                .toList();
    }
}
