package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.dto.PlayerDto;
import java.util.ArrayList;

public class Player implements Gamer {

    private static final int THRESHOLD = 21;

    private final Name name;
    private final Cards cards;

    private Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Player from(final Name name) {
        final Cards cards = new Cards(new ArrayList<>());
        return new Player(name, cards);
    }

    @Override
    public void draw(final Card card) {
        cards.add(card);
    }

    @Override
    public boolean canDraw() {
        return cards.sum() <= THRESHOLD;
    }

    @Override
    public int calculateScore() {
        return cards.sum();
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public PlayerDto toDto() {
        return new PlayerDto(name, cards.getCards(), calculateScore());
    }
}
