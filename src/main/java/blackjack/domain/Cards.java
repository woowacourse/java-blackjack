package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACKJACK = 21;

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void generate() {
        cards.addAll(Card.getTotalCard());
        Collections.shuffle(cards);
    }

    public Card giveCard() {
        return cards.remove(0);
    }

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

    public List<String> getPartOfDealerCard() {
        return List.of(cards.get(0).getName());
    }

    public List<String> getAllCards() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
