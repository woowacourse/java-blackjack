package domain.gamer;

import domain.cards.Card;
import domain.cards.CardPack;
import domain.cards.gamercards.PlayerCards;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player {

    private final GamerName name;
    protected final PlayerCards cards;

    public Player(String name, PlayerCards cards) {
        this.name = new GamerName(name);
        this.cards = cards;
    }

    public void receiveCards(CardPack cardPack, int count) {
        for (int i = 0; i < count; i++) {
            hit(cardPack.pickOneCard());
        }
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public boolean isBust() {
        return !cards.hasScoreUnderBustThreshold();
    }

    public boolean isNotBust() {
        return cards.hasScoreUnderBustThreshold();
    }

    public int finalizeCardsScore() {
        return cards.calculateScore();
    }

    public String getPlayerName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
