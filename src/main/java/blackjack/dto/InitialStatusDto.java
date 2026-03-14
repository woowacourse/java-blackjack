package blackjack.dto;

import blackjack.model.Dealer;
import blackjack.model.Player;
import blackjack.model.Players;

import java.util.List;

public class InitialStatusDto {
    private final List<String> playerNames;
    private final String dealerName;
    private final String dealerFirstCardName;
    private final List<ParticipantResultDto> playerDtos;

    private InitialStatusDto(List<String> playerNames, String dealerName,
                             String dealerFirstCardName, List<ParticipantResultDto> playerDtos) {
        this.playerNames = playerNames;
        this.dealerName = dealerName;
        this.dealerFirstCardName = dealerFirstCardName;
        this.playerDtos = playerDtos;
    }

    public static InitialStatusDto from(Dealer dealer, Players players) {
        List<String> names = players.getPlayers().stream()
                .map(Player::getName)
                .toList();

        String dealerCard = dealer.firstCardOpen().getCardName();

        List<ParticipantResultDto> playerDtos = ParticipantResultDto.from(players);

        return new InitialStatusDto(names, "딜러", dealerCard, playerDtos);
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public String getDealerName() {
        return dealerName;
    }

    public String getDealerFirstCardName() {
        return dealerFirstCardName;
    }

    public List<ParticipantResultDto> getPlayerDtos() {
        return playerDtos;
    }
}
