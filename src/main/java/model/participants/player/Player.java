package model.participants.player;

import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Cards;
import model.participants.Participant;

public class Player extends Participant {

    private static final int ADD_CARD_CONDITION = 22;

    public Player(String name) {
        super(name, new Cards(List.of()));
    }

    public Player(String name, Cards cards) {
        super(name, cards);
    }

    public boolean isNotBust() {
        return cards.calculateTotalNumbers() < ADD_CARD_CONDITION;
    }

    @Override
    public Player withAdditionalCard(Card card) {
        Cards addedCards = cards.add(card);
        return new Player(name, addedCards);
    }

    @Override
    public Player withAdditionalCards(List<Card> cardsElement) {
        Cards addedCards = cards.addAll(cardsElement);
        return new Player(name, addedCards);
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
