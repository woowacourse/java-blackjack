package blackJack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BLACK_JACK = 21;

    private final Set<Card> cards;
    private final Score score;

    public Cards() {
        cards = new HashSet<>();
        score = new Score();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_COUNT && calculateFinalScore() == BLACK_JACK;
    }

    public boolean isBust() {
        return calculateFinalScore() > BLACK_JACK;
    }

    public int calculateFinalScore() {
        return score.calculateFinalScore(cards);
    }

    public List<Card> getCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
