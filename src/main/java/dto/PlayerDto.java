package dto;

import domain.card.Card;
import domain.card.Cards;
import domain.player.Player;
import java.util.List;

public class PlayerDto {
    private static final int FIRST_CARD_INDEX = 0;

    private final String name;
    private final List<Card> cards;

    private PlayerDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = List.copyOf(cards);
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), player.getCards());
    }

    public String getName() {
        return name;
    }

    public String getFirstCardName() {
        return cards.get(FIRST_CARD_INDEX)
                .getCardName();
    }

    public int getScore() {
        Cards cardsForScore = new Cards();
        for (Card card : cards) {
            cardsForScore.addCard(card);
        }

        return cardsForScore.getScore();
    }

    public List<Card> getCards() {
        return cards;
    }
}
