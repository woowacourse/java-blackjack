package domain.card;

import domain.card.Card;
import domain.card.CardKind;
import domain.card.CardValue;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameCards {
    private List<Card> cards;

    public GameCards(int amount) {

       this.cards = Arrays.stream(CardKind.values())
                .flatMap(cardKind -> Arrays.stream(CardValue.values())
                        .flatMap(cardValue ->
                            IntStream.range(0, amount)
                                    .mapToObj(card -> new Card(cardValue.getValue(),
                                                    cardKind.getKind()))
                                ))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public boolean containsCard(String string) {
        return cards.stream()
                .map(card -> card.getCardInfo())
                .anyMatch(s -> s.equals(string));
    }

    public int getSize() {
        return cards.size();
    }
}
