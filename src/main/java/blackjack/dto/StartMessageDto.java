package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record StartMessageDto(
    List<String> playerNicknames,
    Map<String, List<CardDto>> participantCardDtosMap
) {

    public static StartMessageDto of(final Players players, final Dealer dealer) {
        final List<String> playerNicknames = players.all().stream()
            .map(Player::getNickname)
            .toList();
        final Map<String, List<CardDto>> participantCardDtosMap = new LinkedHashMap<>();

        participantCardDtosMap.put(dealer.getNickname(), dealer.getOpenCardNames());
        players.all().forEach(player ->
            participantCardDtosMap.put(player.getNickname(), player.getAllCardNames()));

        return new StartMessageDto(playerNicknames, participantCardDtosMap);
    }
}
