package blackjack.dto;

import blackjack.model.Game;
import blackjack.model.player.Entry;
import java.util.List;
import java.util.stream.Collectors;

public final class EntryDto extends PlayerDto {
    private EntryDto(String name, HandDto deck, int score) {
        super(name, deck, score);
    }

    public static List<EntryDto> from(Game game) {
        return game.getEntries().stream()
                .map(EntryDto::from)
                .collect(Collectors.toList());
    }

    public static EntryDto fromCurrentEntryOf(Game game) {
        return from(game.getCurrentEntry());
    }

    public static EntryDto from(Entry entry) {
        return new EntryDto(entry.getName(), HandDto.from(entry), entry.getScore());
    }
}
