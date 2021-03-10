package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static blackjack.domain.GameResult.*;

public class Dealer extends Participant {

    private static final int INIT_HAND_COUNT = 2;

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

    public List<Card> drawCards() {
        return Stream.generate(this::drawCard)
                .limit(INIT_HAND_COUNT)
                .collect(Collectors.toList());
    }

    @Override
    public void receiveCard(final Card card) {
        validateState();
        this.hand.addCard(card);
        this.state = state.check(this.hand);
        if (isOverLimitScore() && !isFinished()) {
            stay();
        }
    }
}
