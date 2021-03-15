package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.Money;
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
        super(Money.of(1));
        this.cardDeck = CardDeck.newShuffledDeck();
    }

    public Card drawCard() {
        try {
            return cardDeck.draw();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("더이상 뽑을 카드가 없습니다.");
        }
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
        this.state = this.state.receiveCard(card);
        if (isOverLimitScore() && !isFinished()) {
            stay();
        }
    }

    public GameResult judgePlayer(final Player player) {
        if (didLose(player)) {
            return LOSE;
        }
        if (didWin(player)) {
            return WIN;
        }
        return TIE;
    }

    private boolean didWin(final Player player) {
        return (player.isBlackjack() && !this.isBlackjack()) || (this.isBust() && player.isStay()) ||
                (player.isStay() && this.isStay() && player.isHigherThan(this));
    }

    private boolean didLose(final Player player) {
        return player.isBust() || (this.isBlackjack() && !player.isBlackjack()) ||
                (player.isStay() && this.isStay() && this.isHigherThan(player));
    }
}
