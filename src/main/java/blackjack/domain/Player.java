package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Player {

    private final String name;
    private final List<Card> cards;

    public Player(final String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public Player() {
        this("딜러");
    }

    public void pushDealCard(final CardPack cardPack, final int count) {
        IntStream.range(0, count)
                .mapToObj(i -> cardPack.getDeal())
                .forEach(cards::add);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
