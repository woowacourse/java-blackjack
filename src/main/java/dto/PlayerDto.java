package dto;

import domain.Deck;
import domain.Name;
import domain.Player;
import java.util.List;

public record PlayerDto(String playerName, List<String> deck, int score) {
    public static PlayerDto from(Player player) {
        Name name = player.getName();
        Deck deck = player.getDeck();
        return new PlayerDto(name.getName(), DeckDto.from(deck).cardNames(), player.calculateScore());
    }
}
