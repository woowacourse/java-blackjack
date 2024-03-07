package dto;

import domain.Deck;
import domain.Name;
import java.util.List;

public record PlayerDto (String playerName, List<String> deck) {

    public static PlayerDto from(Name name, Deck deck) {
        return new PlayerDto(name.getName(), DeckDto.from(deck).cardNames());
    }
}
