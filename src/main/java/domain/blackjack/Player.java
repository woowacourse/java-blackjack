package domain.blackjack;

import domain.card.CardSelectStrategy;
import domain.card.Deck;

public class Player extends Gamer {
    private final String name;

    static Player from(String name, HoldingCards holdingCards) {
        return new Player(name, new BlackJackGameMachine(holdingCards), 0);
    }

    static Player from(String name, HoldingCards holdingCards, int bettingMoney) {
        return new Player(name, new BlackJackGameMachine(holdingCards), bettingMoney);
    }

    private Player(String name, BlackJackGameMachine blackJackGameMachine, int bettingMoney) {
        super(blackJackGameMachine, bettingMoney);
        this.name = name;
    }

    @Override
    DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy) {
        return blackJackGameMachine.draw(deck, cardSelectStrategy, new PlayerCardDrawCondition(blackJackGameMachine));
    }

    public String getRawName() {
        return name;
    }
}
