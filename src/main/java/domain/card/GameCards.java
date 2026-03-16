package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameCards {

    public static final int DEFAULT_CARD_SET = 1;
    private final List<Card> cards;

    public GameCards() {
        this.cards = Arrays.stream(CardKind.values())
                .flatMap(cardKind -> Arrays.stream(CardScore.values())
                        .flatMap(cardScore ->
                                IntStream.range(0, DEFAULT_CARD_SET)
                                        .mapToObj(card -> new Card(cardScore.getScore(),
                                                cardKind.getKind()))
                        ))
                .collect(Collectors.toCollection(LinkedList::new));
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.removeFirst();
    }

    public boolean containsCard(String string) {
        return cards.stream()
                .map(card -> card.getInfo())
                .anyMatch(s -> s.equals(string));
    }

    public int getSize() {
        return cards.size();
    }
}
