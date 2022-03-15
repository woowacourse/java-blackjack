package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class State {
    private final static String HIT = "Hit";
    private static final String BLACKJACK = "Blackjack";
    private static final String BUST = "Bust";
    private static final String FINISH = "Finish";

    private final Cards cards;
    private String state;

    public State() {
        this.cards = new Cards();
        this.state = HIT;
    }

    private State(Cards cards) {
        this.cards = cards;
    }

    public State addCard(Card card) {
        Cards cards = this.cards.add(card);
        return new State(cards);
    }

    public String state() {
        choiceState();
        return state;
    }

    private void choiceState() {
        int score = cards.sumScore();
        if (score > 21) {
            state = BUST;
        }
        if (score == 21) {
            state = FINISH;
        }
        if (score == 21 && cards.hasTwoCard()) {
            state = BLACKJACK;
        }
    }
}
