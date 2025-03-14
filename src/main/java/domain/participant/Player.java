package domain.participant;

import domain.Nickname;
import domain.card.Card;
import domain.card.Cards;

import java.util.List;
import java.util.Objects;

public class Player extends Participant {
    private final Nickname name;

    private Player(Nickname name, Cards cards) {
        super(cards);
        this.name = name;
    }

    public static Player init(Nickname name) {
        return new Player(name, Cards.empty());
    }

    public static Player from(Nickname name, Cards cards) {
        return new Player(name, cards);
    }

    @Override
    public List<Card> getShowCards() {
        return getCards().getValues();
    }

    public String getName() {
        return name.value();
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
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
