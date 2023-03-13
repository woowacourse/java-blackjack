package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.HoldingCards;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerStatusDto {

    private final String name;
    private final List<String> cards;

    protected PlayerStatusDto(final String name, final List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerStatusDto from(final Player player) {
        String name = player.getName();
        HoldingCards holdingCards = player.getHoldingCards();
        List<String> cards = extractCardInfo(holdingCards.getCards());
        return new PlayerStatusDto(name, cards);
    }

    protected static List<String> extractCardInfo(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getShape().getName() + card.getNumber().getName())
                .collect(Collectors.toUnmodifiableList());
    }

    public String getName() {
        return name;
    }

    public String getOneCard() {
        return cards.get(0);
    }

    public List<String> getCards() {
        return cards;
    }
}
