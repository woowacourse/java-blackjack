package player;

import card.Card;
import card.HandCards;
import card.Deck;
import java.util.List;
import java.util.Objects;

public class Participant {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

    private final String name;
    private final Player player;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.player = new Player();
    }

    public List<Card> openInitialCards() {
        return player.getCards(2);
    }

    public void receiveInitialCards(Deck deck) {
        player.receiveInitialCards(deck);
    }

    public void drawOneCard(Deck deck) {
        player.drawOneCard(deck);
    }

    public int computeOptimalSum() {
        return player.computeOptimalSum();
    }

    public boolean isBust() {
        return player.isBust();
    }

    public boolean isBlackjack() {
        return player.isBlackjack();
    }

    public HandCards getHandCards() {
        return player.getHandCards();
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(name + ": 이름은 2자 이상 10자 이하여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant participant = (Participant) o;
        return Objects.equals(name, participant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
