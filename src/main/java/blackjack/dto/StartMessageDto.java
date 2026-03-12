package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record StartMessageDto(
    List<String> playerNicknames,
    List<String> dealerOpenedCardNames,
    Map<String, List<String>> playerCardNamesMap
) {

    public static StartMessageDto of(final Players players, final Dealer dealer) {
        final List<String> playerNicknames = players.all().stream()
            .map(Player::getNickname)
            .toList();
        final List<String> dealerOpenedCardNames = dealer.getOpenCardNames();
        final Map<String, List<String>> playerCardNamesMap = new LinkedHashMap<>();
        players.all().forEach(player ->
            playerCardNamesMap.put(player.getNickname(), player.getAllCardNames()));

        return new StartMessageDto(playerNicknames, dealerOpenedCardNames, playerCardNamesMap);
    }
}
