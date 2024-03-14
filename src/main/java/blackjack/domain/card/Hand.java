package blackjack.domain.card;

import blackjack.domain.game.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Hand {

    private static final int INITIAL_APPEND_COUNT = 2;

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

    public List<Card> revealHand(int count) {
        return cards.stream()
                .limit(count)
                .toList();
    }

    public Score calculateHandScore() {
        Score score = sumCardScore();
        if (hasAce()) {
            return score.adjustAceScore();
        }
        return score;
    }

    private Score sumCardScore() {
        return cards.stream()
                .map(card -> new Score(card.getCardRank().getScore()))
                .reduce(new Score(0), Score::add);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
