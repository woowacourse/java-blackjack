package team.blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Deck {
    private final Set<Card> cards;

    public Deck() {
        this.cards = Arrays.stream(Card.values())
                .collect(Collectors.toSet());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없습니다.");
        }

        final Card drawnCard = getShuffledCards().getFirst();
        cards.remove(drawnCard);

        return drawnCard;
    }

    private List<Card> getShuffledCards() {
        final List<Card> cardList = new ArrayList<>(cards);
        Collections.shuffle(cardList);
        return cardList;
    }
}
