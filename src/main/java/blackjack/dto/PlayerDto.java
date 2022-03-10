package blackjack.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Player;

public class PlayerDto {

    private final List<CardDto> cards;
    private final String name;

    private PlayerDto(List<CardDto> cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    public static PlayerDto from(Player player) {
        List<CardDto> cardDtos = player.getCards()
            .stream()
            .map(CardDto::from)
            .collect(Collectors.toList());
        return new PlayerDto(cardDtos, player.getName());
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCards() {
        return cards;
    }
}
