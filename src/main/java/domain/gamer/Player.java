package domain.gamer;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public class Player {

    private static final int MAX_BLACK_JACK_SCORE = 21;
    private static final int BUST = 0;
    private final Name name;
    private final Cards cards;

    public Player(Name name) {
        cards = new Cards();
        this.name = name;
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isDrawable() {
        return cards.getMinGameScore() <= MAX_BLACK_JACK_SCORE && cards.countMaxScore() != BUST;
    }

    public int getMaxGameScore() {
        return cards.countMaxScore();
    }

    public List<String> getCardStatus() {
        return cards.getCardsName();
    }

    public Name getName() {
        return name;
    }
}
