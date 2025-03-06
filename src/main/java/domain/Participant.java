package domain;

import static domain.Role.*;

import java.util.Objects;

public record Participant(
        String name,
        Cards cards,
        Role role
) {

    public static Participant createDealer() {
        return new Participant("딜러", Cards.createEmpty(), DEALER);
    }

    public static Participant createPlayer(String name, Cards cards) {
        return new Participant(name, cards, PLAYER);
    }

    public void addCards(Cards receivedCards) {
        this.cards.addAll(receivedCards);
    }

    public boolean isPlayer() {
        return role.isPlayer();
    }

    public int calculateCardsSum() {
        return cards.calculateSumResult();
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Participant that = (Participant) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
