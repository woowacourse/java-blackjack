package dto;

import domain.deck.Deck;
import domain.participants.Name;
import domain.participants.Player;
import java.util.List;

public record PlayerDto(String playerName, List<String> deck, int score) {
    public static PlayerDto from(Player player) {
        Name name = player.getName();
        Deck deck = player.getDeck();
        return new PlayerDto(name.getName(), DeckDto.from(deck).cardNames(), player.calculateScore());
    }
}
