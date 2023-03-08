package domain;

import java.util.List;

public class Player implements Gambler {
    private final PlayerName playerName;
    private final Cards cards;

    public Player(PlayerName playerName, Cards cards) {
        this.playerName = playerName;
        this.cards = cards;
        initialPick();
    }

    @Override
    public void initialPick() {
        if (cards.getCards().isEmpty()) {
            pickCard();
            pickCard();
        }
    }

    @Override
    public void pickCard() {
        cards.addCard(Deck.pickCard());
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public String getName() {
        return playerName.getName();
    }

    @Override
    public int getScore() {
        return cards.calculateScore();
    }

    @Override
    public boolean isBustedGambler() {
        return this.cards.isBusted(cards.calculateScore());
    }
}
