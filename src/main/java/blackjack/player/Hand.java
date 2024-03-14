package blackjack.player;

import blackjack.card.Card;
import blackjack.game.BlackJackGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int INIT_CARD_COUNT = 2;
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    Hand() {
        this(new ArrayList<>());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        Score score = calculateBaseScore();
        boolean hasAceInCards = cards.stream()
                .anyMatch(Card::isAce);

        if (hasAceInCards) {
            return score.addAceScoreOnSoftHand().toInt();
        }
        return score.toInt();
    }

    private Score calculateBaseScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.zero(), Score::add);
    }

    public boolean isBust() {
        return calculateScore() > BlackJackGame.BLACKJACK_MAX_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == INIT_CARD_COUNT && calculateScore() == BlackJackGame.BLACKJACK_MAX_SCORE;
    }

    public boolean hasSameScoreWith(Hand hand) {
        return calculateScore() == hand.calculateScore();
    }

    public boolean hasHigherScoreThan(Hand hand) {
        return calculateScore() > hand.calculateScore();
    }

    public Card getFirstCard() {
        return getCard(0);
    }

    public Card getSecondCard() {
        return getCard(1);
    }

    private Card getCard(int index) {
        try {
            return cards.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("[ERROR] 카드를 가지고 있지 않습니다.");
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
