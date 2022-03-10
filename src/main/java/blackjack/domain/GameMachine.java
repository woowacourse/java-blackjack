package blackjack.domain;

import java.util.List;

public class GameMachine {

    public static BlackJackGame createBlackJackGame(List<String> playerNames) {
        return new BlackJackGame(playerNames);
    }

}
