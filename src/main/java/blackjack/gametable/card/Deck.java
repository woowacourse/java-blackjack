package blackjack.gametable.card;

import blackjack.constant.TrumpRank;
import blackjack.constant.TrumpSuit;
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
        return new Cards(cards.drawInitialCards());
    }

    public List<Cards> drawInitialCards(int count) {
        List<Cards> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cards.add(drawInitialCards());
        }
        return new ArrayList<>(cards);
    }

    public Card drawCard() {
        return cards.drawCard();
    }

    public int getSize() {
        return cards.getSize();
    }

}
