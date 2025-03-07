package domain;

import static domain.Cards.BLACKJACK_SCORE;

import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    protected Player(String name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        return super.getCards();
    }

    @Override
    public boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= BLACKJACK_SCORE;
    }
}
