package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.GameResult;

public class Player extends Human {

    private final Name name;
    private final Cards cards;
    private boolean isCardNeeded = true;
    private GameResult gameResult;

    private Player(Name name) {
        this.name = name;
        this.cards = Cards.create();
    }

    public static Player of(Name name) {
        return new Player(name);
    }

    public void setResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public GameResult getResult() {
        return gameResult;
    }

    @Override
    public boolean isOneMoreCard() {
        return cards.getPoint() < 21 && isCardNeeded;
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }
}
