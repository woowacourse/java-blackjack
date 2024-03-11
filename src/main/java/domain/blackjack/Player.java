package domain.blackjack;

import domain.card.CardSelectStrategy;
import domain.card.Deck;

public class Player extends Gamer {
    private final String name;

    public static Player from(String name, HoldingCards holdingCards) {
        return new Player(name, new BlackJackGameMachine(holdingCards));
    }

    private Player(String name, BlackJackGameMachine blackJackGameMachine) {
        super(blackJackGameMachine);
        this.name = name;
    }

    @Override
    public DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy) {
        return blackJackGameMachine.draw(deck, cardSelectStrategy, new PlayerCardDrawCondition(blackJackGameMachine));
    }

    @Override
    public String getRawName() {
        return name;
    }
}
