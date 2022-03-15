package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayingCards {
    private static final int BLACKJACK = 21;

    private final List<Card> playingCards = new ArrayList<>();

    public List<Card> getCards() {
        return Collections.unmodifiableList(playingCards);
    }

    public List<String> getFirstCardName() {
        Card card = playingCards.get(0);
        return List.of(card.getName());
    }

    public List<String> getAllNames() {
        return playingCards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public void add(final List<Card> cards) {
        playingCards.addAll(cards);
    }

    public void add(final Card card) {
        playingCards.add(card);
    }

    public int calculateTotal() {
        List<CardNumber> cardNumbers = playingCards.stream()
                .map(Card::getCardNumber)
                .collect(Collectors.toList());
        return CardNumber.getTotal(cardNumbers);
    }

    public boolean isOverBlackjack() {
        return calculateTotal() > BLACKJACK;
    }

    public boolean isUnderBlackjack() {
        return calculateTotal() < BLACKJACK;
    }
}
