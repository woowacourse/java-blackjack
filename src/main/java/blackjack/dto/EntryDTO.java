package blackjack.dto;

import blackjack.Entry;
import blackjack.Game;
import java.util.List;
import java.util.stream.Collectors;

public class EntryDTO extends PlayerDTO {

    private EntryDTO(String name, DeckDTO deck, int score) {
        super(name, deck, score);
    }

    public static List<EntryDTO> from(Game game) {
        return game.getEntries().stream()
                .map(EntryDTO::from)
                .collect(Collectors.toList());
    }

    public static EntryDTO fromCurrent(Game game) {
        return from(game.getCurrentEntry());
    }

    private static EntryDTO from(Entry entry) {
        return new EntryDTO(entry.getName(), DeckDTO.from(entry), entry.getScore());
    }
}
