package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Player;

import java.util.List;

public class CardInfoDto {
    private final String name;
    private final List<Card> cards;

    public CardInfoDto(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public CardInfoDto(final String name, final Cards cards) {
        this(name, cards.getCards());
    }

    public CardInfoDto(final Player player) {
        this(player.getNameValue(), player.getCards());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
