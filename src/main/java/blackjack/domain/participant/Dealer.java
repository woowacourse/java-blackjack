package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dealer extends Gamer {

    private static final String NICKNAME = "딜러";
    private static final int DEALER_THRESHOLD = 16;

    public Dealer(final Cards cards) {
        super(cards);
    }

    public static Dealer createEmpty() {
        return new Dealer(new Cards(new ArrayList<>()));
    }

    @Override
    public Cards showInitialCards() {
        return new Cards(List.of(cards.getFirstCard()));
    }

    @Override
    public boolean canGetMoreCard() {
        int sum = cards.calculateMinSum();
        return sum <= DEALER_THRESHOLD;
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
