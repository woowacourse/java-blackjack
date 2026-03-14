package dto;

import domain.participants.Participant;
import java.util.List;

public record NamesDto(String dealerName, List<String> playerNames) {
    public static NamesDto fromDealerAndPlayers(Participant dealer, List<Participant> players) {
        return new NamesDto(
                dealer.getName(),
                players.stream().map(Participant::getName).toList());
    }
}
