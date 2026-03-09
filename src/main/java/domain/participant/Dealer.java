package domain.participant;

import domain.GameScore;
import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final String NAME_VALUE = "딜러";
    private static final int STAND_SCORE = 17;

    public Dealer(Hand hand) {
        super(NAME_VALUE, hand);
    }

    @Override
    public List<String> getInitialCards() {
        List<String> cards = hand.getCards().stream()
                .map(Card::getCardName)
                .toList();
        return List.of(cards.getFirst());
    }

    public boolean isStand() {
        GameScore standScore = new GameScore(STAND_SCORE);

        return getScore().isBiggerThan(standScore)
                || getScore().isSame(standScore);
    }
}
