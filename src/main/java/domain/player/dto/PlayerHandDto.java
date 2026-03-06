package domain.player.dto;

import domain.dealer.Dealer;
import domain.player.Player;

import java.util.List;

// TODO 추후 Gamer로 개선 예정.
public record PlayerHandDto(
        String playerName,
        List<String> handOnCards
) {
    public static PlayerHandDto of(Player player) {
        String playerName = player.toDisplayMyName();
        List<String> handOnCards = player.disPlayMyCardBundle();
        return new PlayerHandDto(playerName, handOnCards);
    }

    public static PlayerHandDto of(Dealer dealer) {
        String playerName = dealer.toDisplayMyName();
        List<String> handOnCards = List.of(dealer.disPlayMyCardBundle().getFirst());
        return new PlayerHandDto(playerName, handOnCards);
    }

}