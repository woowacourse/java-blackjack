package blackjack.dto;

import blackjack.model.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class PlayersDto {
    private final DealerDto dealer;
    private final List<EntryDto> entries;

    private PlayersDto(DealerDto dealer, List<EntryDto> entries) {
        this.dealer = dealer;
        this.entries = entries;
    }

    public static PlayersDto from(Game game) {
        return new PlayersDto(DealerDto.from(game), EntryDto.from(game));
    }

    public List<PlayerDto> getPlayers() {
        List<PlayerDto> players = new ArrayList<>();
        players.add(getDealer());
        players.addAll(getEntries());
        return players;
    }

    public DealerDto getDealer() {
        return dealer;
    }

    public List<EntryDto> getEntries() {
        return entries;
    }

    public List<String> getEntryNames() {
        return entries.stream()
                .map(EntryDto::getName)
                .collect(Collectors.toList());
    }
}