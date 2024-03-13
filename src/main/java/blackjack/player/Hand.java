package blackjack.player;

import blackjack.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    Hand(List<Card> cards) {
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
            return score.addAceScoreOnNotBust().toInt();
        }
        return score.toInt();
    }

    private Score calculateBaseScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.zero(), Score::add);
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
