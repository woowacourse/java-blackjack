package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayingCards {

    public static final int BLACKJACK = 21;

    private final List<Card> playingCards = new ArrayList<>();

    public void add(final List<Card> cards) {
        playingCards.addAll(cards);
    }

    public void add(final Card card) {
        playingCards.add(card);
    }

    public int calculateTotal() {
        List<CardNumber> cardNumbers = getCardNumbers();
        return CardNumber.getTotal(cardNumbers);
    }

    public boolean isOverBlackjack() {
        return calculateTotal() > BLACKJACK;
    }

    public boolean isUnderBlackjack() {
        return calculateTotal() < BLACKJACK;
    }

    public boolean isBlackjack() {
        List<CardNumber> cardNumbers = getCardNumbers();
        return CardNumber.isBlackjack(cardNumbers);
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

    private List<CardNumber> getCardNumbers() {
        return playingCards.stream()
                .map(Card::getCardNumber)
                .collect(Collectors.toUnmodifiableList());
    }
}
