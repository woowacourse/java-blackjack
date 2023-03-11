package domain;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Cards;

public class Player {

    private static final int INITIAL_CARD_COUNT = 2;

    private final String name;
    private final Cards cards;
    private Score score;
    private boolean blackJack;

    public Player(final String name) {
        this.name = name;
        this.cards = Cards.empty();
        this.blackJack = false;
    }

    public void addCard(final Card card) {
        cards.add(card);
        score = Score.of(cards);
        if (cards.size() == INITIAL_CARD_COUNT) {
            blackJack = score.isBlackJackScore();
        }
    }

    public boolean canReceiveCard() {
        return !score.isBust();
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards.getCards());
    }

    public Score getScore() {
        return score;
    }
}
