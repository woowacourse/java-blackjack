package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static blackjack.domain.GameResult.*;

public class Dealer extends Participant {

    private static final int LIMIT_SCORE = 17;
    private static final int MAX_SCORE_NUMBER = 21;

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
        int playerScore = player.getTotalScore();
        int dealerScore = this.getTotalScore();
        if (playerScore > MAX_SCORE_NUMBER || (dealerScore <= MAX_SCORE_NUMBER && dealerScore > playerScore)) {
            return LOSE;
        }
        if (dealerScore < playerScore || dealerScore > MAX_SCORE_NUMBER) {
            return WIN;
        }
        return TIE;
    }

    @Override
    public boolean isOverLimitScore() {
        return getTotalScore() >= LIMIT_SCORE;
    }

    public void initDealerHand(int receiveCount) {
        IntStream.range(0, receiveCount).forEach(i -> receiveCard(drawCard()));
    }
}
