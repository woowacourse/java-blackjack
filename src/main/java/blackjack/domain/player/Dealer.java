package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Dealer extends Player {

    private static final int MAX_SCORE_TO_PICK = 16;
    private static final int FIRST_OPEN_COUNT = 1;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    @Override
    public List<Card> openFirstCards() {
        return cards.getCards().subList(0, FIRST_OPEN_COUNT);
    }

    @Override
    public boolean isPossibleToReceiveCard() {
        return cards.calculateScore() <= MAX_SCORE_TO_PICK;
    }
}
