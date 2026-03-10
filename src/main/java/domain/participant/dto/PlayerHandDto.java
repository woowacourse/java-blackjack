package domain.participant.dto;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.List;

public record PlayerHandDto(String playerName, List<String> handOnCards) {

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

    public static PlayerHandDto generateAllCard(Dealer dealer) {
        String playerName = dealer.toDisplayMyName();
        List<String> handOnCards = dealer.disPlayMyCardBundle();
        return new PlayerHandDto(playerName, handOnCards);
    }


}
