package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Dealer implements GameAction {

    private static final String NICKNAME = "딜러";
    private static final int DEALER_THRESHOLD = 16;

    private final List<Card> cards;

    public Dealer(final List<Card> cards) {
        this.cards = cards;
    }

    public void receiveCards(final List<Card> givenCards) {
        cards.addAll(givenCards);
    }

    @Override
    public boolean canGetMoreCard() {
        int sum = cards.stream()
                .mapToInt(Card::getCardMinNumber)
                .sum();
        return sum <= DEALER_THRESHOLD;
    }

    @Override
    public List<Card> getCards() {
        return List.of(cards.getFirst());
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Dealer dealer)) {
            return false;
        }
        return Objects.equals(cards, dealer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

    @Override
    public String getNickname() {
        return NICKNAME;
    }
}
