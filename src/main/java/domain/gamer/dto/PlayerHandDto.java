package domain.gamer.dto;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;

public record PlayerHandDto(
        String playerName,
        String handOnCards
) {
    public static PlayerHandDto of(Player player) {
        String playerName = player.toDisplayMyName();
        List<String> handOnCards = player.disPlayMyCardBundle();
        return new PlayerHandDto(playerName, joining(handOnCards));
    }

    public static PlayerHandDto of(Dealer dealer) {
        String playerName = dealer.toDisplayMyName();
        List<String> handOnCards = List.of(dealer.disPlayMyCardBundle().getFirst());
        return new PlayerHandDto(playerName, joining(handOnCards));
    }

    public static PlayerHandDto generateAllCard(Dealer dealer) {
        String playerName = dealer.toDisplayMyName();
        List<String> handOnCards = dealer.disPlayMyCardBundle();
        return new PlayerHandDto(playerName, joining(handOnCards));
    }

    private static String joining(List<String> strings) {
        return String.join(", ", strings);
    }

}
