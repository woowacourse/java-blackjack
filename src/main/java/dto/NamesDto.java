package dto;

import domain.participants.Dealer;
import domain.participants.Player;
import java.util.List;

public record NamesDto(String dealerName, List<String> playerNames) {
    public static NamesDto fromEntity(Dealer dealer, List<Player> players) {
        return new NamesDto(dealer.getName(), players.stream().map(Player::getName).toList());
    }
}
