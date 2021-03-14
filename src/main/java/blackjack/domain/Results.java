package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.ResultsDto;

import java.util.stream.Collectors;

public class Results {
    private final Dealer dealer;
    private final Players players;

    public Results(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public ResultsDto dto() {
        ResultsDto resultsDto = new ResultsDto();
        setDealerData(resultsDto);
        setPlayersData(resultsDto);
        return resultsDto;
    }

    private void setPlayersData(ResultsDto resultsDto) {
        resultsDto.setPlayersSize(players.values().size());
        resultsDto.setPlayersNames(players.values().stream()
                .map(Participant::getName)
                .collect(Collectors.toList()));
        resultsDto.setPlayersProfits(players.values().stream()
                .map(player -> player.calculateProfitFromState(dealer))
                .collect(Collectors.toList()));
    }

    private void setDealerData(ResultsDto resultsDto) {
        resultsDto.setDealerName(dealer.getName());
        resultsDto.setDealerProfit(calculateDealerProfit());
    }

    private double calculateDealerProfit() {
        double dealerProfit = 0;
        for (Player player : players.values()) {
            dealerProfit -= player.calculateProfitFromState(dealer);
        }
        return dealerProfit;
    }
}
