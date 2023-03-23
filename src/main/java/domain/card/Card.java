package domain.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Card {

    public static final Map<Denomination, List<Card>> CARDS;

    private final Denomination denomination;
    private final Suits suit;

    static {
        CARDS = new LinkedHashMap<>();
        for (Denomination denomination : Denomination.values()) {
            List<Card> cards = createCards(denomination);
            CARDS.put(denomination, cards);
        }
    }

    private static List<Card> createCards(final Denomination denomination) {
        int suitLength = Suits.values().length;
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < suitLength; i++) {
            cards.add(new Card(denomination, Suits.values()[i]));
        }
        return cards;
    }

    private Card(Denomination denomination, Suits suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static Card of(final Denomination denomination, final Suits suit) {
        return CARDS.get(denomination).stream()
            .filter(card -> card.getSuit() == suit)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("카드가 초기화되지 않았습니다."));
    }

    public static List<Card> getInitializedCards() {
        return CARDS.values().stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int getScore() {
        return denomination.getScore();
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suits getSuit() {
        return suit;
    }

    public List<String> getCardName() {
        return List.of(denomination.getPoint(), suit.getName());
    }
}
