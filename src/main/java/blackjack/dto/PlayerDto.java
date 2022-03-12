package blackjack.dto;

import blackjack.Player;
import blackjack.trumpcard.Card;
import blackjack.trumpcard.Deck;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto {
    private final String name;
    private final List<CardDto> cards;

    private PlayerDto(Player player) {
        this.name = player.getName();
        this.cards = createCardsDto(player.getDeck());
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player);
    }

    private List<CardDto> createCardsDto(Deck deck) {
        return deck.getCards().stream()
                .map(CardDto::new)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCardsDto() {
        return cards;
    }

    public CardDto getCardDto() {
        return cards.get(0);
    }
}
