package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardRank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Hand {

    private static final int INITIAL_APPEND_COUNT = 2;
    private static final Score INITIAL_SCORE = new Score(0);

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void append(Card card) {
        cards.add(card);
    }

    public void appendInitial(CardDeck cardDeck) {
        IntStream.range(0, INITIAL_APPEND_COUNT).forEach(i -> append(cardDeck.popCard()));
    }

    public Score calculateHandScore() {
        Score score = sumCardScore();
        if (hasAce()) {
            return CardRank.adjustAceScore(score);
        }
        return score;
    }

    private Score sumCardScore() {
        return cards.stream()
                .map(Card::score)
                .reduce(INITIAL_SCORE, Score::add);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_APPEND_COUNT && calculateHandScore().isBlackjack();
    }

    public boolean isBust() {
        return calculateHandScore().isBust();
    }

    public boolean isPlayerHit() {
        return calculateHandScore().isPlayerHit();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
