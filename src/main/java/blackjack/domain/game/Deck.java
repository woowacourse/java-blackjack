package blackjack.domain.game;

import blackjack.domain.card.*;
import blackjack.strategy.CardShuffle;
import java.util.ArrayList;
import java.util.List;

public class Deck {

    private static final int STANDARD_LOWER_BOUNDARY = 2;
    private static final int STANDARD_UPPER_BOUNDARY = 10;
    private static final List<String> COURT_VALUES = List.of("J", "Q", "K");

    private List<Card> cards = new ArrayList<>();

    public Deck(CardShuffle cardShuffle) {
        final Pattern[] values = Pattern.values();

        for (Pattern pattern : values) {
            addAllCardByPattern(pattern);
        }
        this.cards = cardShuffle.shuffle(cards);
    }

    private void addAllCardByPattern(Pattern pattern) {
        cards.add(new AceCard(pattern));
        addStandardCard(pattern);
        addCourtCard(pattern);
    }

    private void addCourtCard(Pattern pattern) {
        for (String courtValue : COURT_VALUES) {
            cards.add(new CourtCard(pattern, courtValue));
        }
    }

    private void addStandardCard(Pattern pattern) {
        for (int value = STANDARD_LOWER_BOUNDARY; value <= STANDARD_UPPER_BOUNDARY; value++) {
            cards.add(new StandardCard(pattern, String.valueOf(value)));
        }
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        validateSize();
        return cards.remove(0);
    }

    private void validateSize() {
        if (cards.isEmpty()) {
            throw new IndexOutOfBoundsException("더 이상 카드가 없습니다.");
        }
    }
}
