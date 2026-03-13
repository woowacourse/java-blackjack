package service;

import domain.Players;
import domain.batting.Money;
import domain.batting.Profit;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.BattingResultDto;
import dto.PlayerProfitDto;

import java.util.ArrayList;
import java.util.List;

public class BattingCalculateService {
    private final Players players;
    private final Dealer dealer;

    public BattingCalculateService(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    private List<Profit> calculatePlayersProfit() {
        List<Profit> playersProfit = new ArrayList<>();
        for (Player player : players) {
            Money battingMoney = player.getBattingMoney();
            double earningsRate = player.getStatus().earningsRate(dealer.getStatus());
            playersProfit.add(new Profit(player.getName(), battingMoney, earningsRate));
        }
        return playersProfit;
    }

    public BattingResultDto getBattingResult() {
        return new BattingResultDto(getPlayersProfit(), getDealerProfit());
    }
    private List<PlayerProfitDto> getPlayersProfit() {
        List<Profit> profits = calculatePlayersProfit();
        List<PlayerProfitDto> playersProfit = new ArrayList<>();
        for (Profit profit : profits) {
            playersProfit.add(PlayerProfitDto.from(profit));
        }

        return playersProfit;
    }

    private long getDealerProfit() {
        List<Profit> profits = calculatePlayersProfit();
        long dealerProfit = 0L;
        for (Profit profit : profits) {
            dealerProfit -= (long) (profit.getMoney().getValue() * profit.getEarningRate());
        }
        return dealerProfit;
    }
}
