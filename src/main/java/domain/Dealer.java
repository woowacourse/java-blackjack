package domain;

import java.util.List;
import java.util.Objects;

public class Dealer extends Participant{

    public Dealer(Cards cards) {
        super(cards);
    }

    public boolean checkExceedSixteen() {
        return cards.checkExceedSixteen();
    }

    @Override
    public Participant createParticipant(List<Card> providedCards) {
        return new Dealer(cards.addCards(providedCards));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(cards, dealer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
