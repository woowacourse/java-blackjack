package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACKJACK = 21;

    private final List<Card> cards = new ArrayList<>();

    public void add(final List<Card> initCards) {
        cards.addAll(initCards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateTotal() {
        List<CardNumber> cardNumbers = cards.stream()
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

    public List<Card> getPartOfDealerCard() {
        // TODO: 방식 수정
        return List.of(cards.get(0));
    }

    public List<Card> getAllCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
