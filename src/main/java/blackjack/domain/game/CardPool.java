package blackjack.domain.game;

import blackjack.domain.card.*;
import blackjack.strategy.CardShuffle;
import java.util.ArrayList;
import java.util.List;

public class CardPool {

    private static final int MIN_INCLUSIVE = 2;
    private static final int MAX_INCLUSIVE = 10;
    private static final List<String> COURT_SYMBOLS = List.of("J", "Q", "K");

    private List<Card> cards = new ArrayList<>();

    public CardPool(CardShuffle cardShuffle) {
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
        for (String courtSymbol : COURT_SYMBOLS) {
            cards.add(new CourtCard(pattern, courtSymbol));
        }
    }

    private void addStandardCard(Pattern pattern) {
        for (int symbol = MIN_INCLUSIVE; symbol <= MAX_INCLUSIVE; symbol++) {
            cards.add(new StandardCard(pattern, String.valueOf(symbol)));
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
