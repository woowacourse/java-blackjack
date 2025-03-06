package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public class Dealer implements Actionable {

    private static final String NICKNAME = "딜러";
    private static final int DEALER_THRESHOLD = 16;

    private final Cards cards;

    public Dealer(final Cards cards) {
        this.cards = cards;
    }

    public void receiveCards(final Cards givenCards) {
        cards.addAll(givenCards);
    }

    @Override
    public boolean canGetMoreCard() {
        int sum = cards.calculateMinSum();
        return sum <= DEALER_THRESHOLD;
    }

    @Override
    public List<Card> showCards() {
        return List.of(cards.getFirstCard());
    }

    public int calculateMaxSum() {
        return cards.calculateResult();
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
