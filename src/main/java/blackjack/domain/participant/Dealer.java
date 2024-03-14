package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Score;
import blackjack.domain.game.ResultStatus;
import blackjack.domain.strategy.CardShuffleStrategy;

public class Dealer extends Participant {
    private static final String DEFAULT_NAME_OF_DEALER = "딜러";
    private static final int DEALER_MIN_SCORE_POLICY = 16;

    private final CardDeck cardDeck;
    private final CardShuffleStrategy cardShuffleStrategy;

    public Dealer(final CardDeck cardDeck, final CardShuffleStrategy cardShuffleStrategy) {
        super(DEFAULT_NAME_OF_DEALER);
        this.cardDeck = cardDeck;
        this.cardShuffleStrategy = cardShuffleStrategy;
    }

    public void shuffleCards() {
        cardDeck.shuffle(cardShuffleStrategy);
    }

    public Card pickCard() {
        if (cardDeck.isEmpty()) {
            cardDeck.reset();
            shuffleCards();
        }
        return cardDeck.draw();
    }

    @Override
    public boolean canReceiveCard() {
        final Score score = calculateScore();
        final Score standardScore = Score.valueOf(DEALER_MIN_SCORE_POLICY);

        return score.isLessThan(standardScore) || score.equals(standardScore);
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        throw new UnsupportedOperationException("딜러는 수익률을 계산할 수 없습니다.");
    }
}
