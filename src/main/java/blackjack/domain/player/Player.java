package blackjack.domain.player;

import blackjack.domain.DrawStatus;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player extends Participant {

    private static final int FIRST_OPEN_COUNT = 2;

    public Player(String name, Cards cards) {
        super(name, cards);
    }

    public GameResult findResult(Dealer dealer) {
        return GameResult.findPlayerResult(this, dealer);
    }

    @Override
    public List<Card> openFirstCards() {
        return cards.getCards().subList(0, FIRST_OPEN_COUNT);
    }

    public boolean isPossibleToHit(DrawStatus drawStatus) {
        return isBust() && isHit(drawStatus);
    }

    @Override
    public boolean isBust() {
        return cards.calculateScore() > MAX_BLACKJACK_SCORE;
    }

    public boolean isHit(DrawStatus drawStatus) {
        return drawStatus == DrawStatus.YES;
    }
}
