package domain.participant;

import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int PLAYER_SCORE_THRESHOLD = 21;

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
        return cardsScore <= PLAYER_SCORE_THRESHOLD;
    }
}
