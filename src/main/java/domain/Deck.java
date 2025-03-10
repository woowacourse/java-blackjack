package domain;

import domain.constant.TrumpSuit;
import domain.constant.TrumpRank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final Cards cards;

    private Deck(Cards cards) {
        this.cards = cards;
    }

    public static Deck initialize() {
        List<Card> cards = Arrays.stream(TrumpRank.values())
                .flatMap(rank -> Arrays.stream(TrumpSuit.values())
                        .map(suit -> new Card(rank, suit)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Deck(new Cards(cards));
    }

    public Cards drawInitialCards() {
        return new Cards(new ArrayList<>(List.of(cards.drawOneCard(), cards.drawOneCard())));
    }

    public Card drawOneCard() {
        return cards.drawOneCard();
    }

    public int getSize() {
        return cards.getSize();
    }

}
