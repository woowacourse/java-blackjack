package blackjack.view.dto;

import blackjack.domain.Round;

import java.util.List;
import java.util.stream.Collectors;

public class RoundStatusDto {
    private final String dealerName;
    private final List<String> dealerCardStatus;
    private final List<PlayerStatusDto> playerStatusDto;
    private final int dealerScore;

    private RoundStatusDto(final String dealerName, final List<String> dealerCardStatus, final List<PlayerStatusDto> playerStatusDto, final int dealerScore) {
        this.dealerName = dealerName;
        this.dealerCardStatus = dealerCardStatus;
        this.playerStatusDto = playerStatusDto;
        this.dealerScore = dealerScore;
    }

    public static RoundStatusDto toDto(Round round) {
        return new RoundStatusDto(round.getDealerName(),
                round.getDealer().getCardsStatus(),
                round.getPlayers().stream()
                        .map(player -> new PlayerStatusDto(player.getName(), player.getCardsStatus(), player.getScore()))
                        .collect(Collectors.toList()),
                round.getDealer().getScore());
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public String getDealerName() {
        return dealerName;
    }


    public List<String> getDealerCardStatus() {
        return dealerCardStatus;
    }

    public List<PlayerStatusDto> getPlayerStatusDto() {
        return playerStatusDto;
    }
}
