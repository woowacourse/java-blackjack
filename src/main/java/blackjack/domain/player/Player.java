package blackjack.domain.player;

import blackjack.domain.DrawStatus;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player extends Participant {

    private static final int MAX_SCORE_TO_PICK = 21;
    private static final int FIRST_OPEN_COUNT = 2;

    public Player(String name, Cards cards) {
        super(name, cards);
    }

    public GameResult findResult(int dealerScore) {
        return GameResult.findPlayerResult(calculateScore(), dealerScore);
    }

    public boolean isHit(DrawStatus drawStatus) {
        return drawStatus == DrawStatus.YES;
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
