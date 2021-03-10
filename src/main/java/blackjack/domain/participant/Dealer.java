package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;

import java.util.NoSuchElementException;

import static blackjack.domain.GameResult.*;

public class Dealer extends Participant {

    private final CardDeck cardDeck;

    public Dealer() {
        this.cardDeck = CardDeck.newShuffledDeck();
    }

    public Card drawCard() {
        try {
            return cardDeck.draw();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("더이상 뽑을 카드가 없습니다.");
        }
    }

    public GameResult judgeHand(final Player player) {
        Score playerScore = player.getTotalScore();
        Score dealerScore = this.getTotalScore();
        if (playerScore.isBust() || (!dealerScore.isBust() && dealerScore.compareTo(playerScore))) {
            return LOSE;
        }
        if (playerScore.compareTo(dealerScore) || dealerScore.isBust()) {
            return WIN;
        }
        return TIE;
    }

    @Override
    public boolean isOverLimitScore() {
        return getTotalScore().isDealerStateStay();
    }

    public void initDealerHand(int receiveCount) {
        for (int i = 0; i < receiveCount; i++) {
            receiveCard(drawCard());
        }
    }
}
