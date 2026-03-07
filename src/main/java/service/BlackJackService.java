package service;

import domain.CardCreationStrategy;
import domain.Game;
import java.util.List;

public class BlackJackService {
    private final CardCreationStrategy strategy;

    public BlackJackService(CardCreationStrategy strategy) {
        this.strategy = strategy;
    }

    public Game prepareGame(List<String> playerNames) {
        return Game.ready(playerNames, strategy);
    }


}
