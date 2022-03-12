package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayingCards {

    private static final int BLACKJACK = 21;

    private final List<Card> playingCards = new ArrayList<>();

    public void add(final List<Card> initCards) {
        playingCards.addAll(initCards);
    }

    public void add(final Card card) {
        playingCards.add(card);
    }

    public int calculateTotal() {
        List<CardNumber> cardNumbers = playingCards.stream()
                .map(Card::getCardNumber)
                .collect(Collectors.toUnmodifiableList());
        return CardNumber.getTotal(cardNumbers);
    }

    public boolean isOverBlackjack() {
        return calculateTotal() > BLACKJACK;
    }

    public boolean isUnderBlackjack() {
        return calculateTotal() < BLACKJACK;
    }

    public List<Card> getPartOfCard() {
        if (playingCards.isEmpty()) {
            return new ArrayList<>();
        }
        return List.of(playingCards.get(0));
    }

    public List<Card> getAllCards() {
        return playingCards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
