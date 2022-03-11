package blackjack.dto;

import blackjack.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersDTO {
    private final DealerDTO dealer;
    private final List<EntryDTO> entries;

    public PlayersDTO(DealerDTO dealer, List<EntryDTO> entries) {
        this.dealer = dealer;
        this.entries = entries;
    }

    public static PlayersDTO from(Game game) {
        return new PlayersDTO(DealerDTO.from(game), EntryDTO.from(game));
    }

    public List<PlayerDTO> getPlayers() {
        List<PlayerDTO> players = new ArrayList<>();
        players.add(getDealer());
        players.addAll(getEntries());
        return players;
    }

    public DealerDTO getDealer() {
        return dealer;
    }

    public List<EntryDTO> getEntries() {
        return entries;
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        names.add(dealer.getName());
        names.addAll(entries.stream()
                .map(EntryDTO::getName)
                .collect(Collectors.toList()));
        return names;
    }
}