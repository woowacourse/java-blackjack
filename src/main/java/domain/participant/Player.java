package domain.participant;

import domain.GameStatus;
import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int PLAYER_SCORE_THRESHOLD = 21;

    public Player(String name) {
        super(name);
    }

    public GameStatus determineGameStatusByScore(Participant participant) {
        return cards.determineGameStatusByScore(participant.cards);
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
