package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Score;
import blackjack.domain.card.strategy.CardShuffleStrategy;
import blackjack.utils.Constants;

public class Dealer extends Participant {
    private final CardDeck cardDeck;
    private final CardShuffleStrategy cardShuffleStrategy;

    public Dealer(final CardDeck cardDeck, final CardShuffleStrategy cardShuffleStrategy) {
        super(Constants.DEFAULT_NAME_OF_DEALER);
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
        final Score standardScore = Score.valueOf(Constants.DEALER_MIN_SCORE_POLICY);

        return score.isLessThan(standardScore) || score.equals(standardScore);
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        throw new UnsupportedOperationException("딜러는 수익률을 계산할 수 없습니다.");
    }
}
