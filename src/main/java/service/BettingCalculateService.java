package service;

import domain.betting.BettingResult;
import domain.participant.Players;
import domain.betting.Money;
import domain.betting.Profit;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.BettingResultDto;
import dto.PlayerProfitDto;

import java.util.ArrayList;
import java.util.List;

public class BettingCalculateService {
    private final Players players;
    private final Dealer dealer;

    public BettingCalculateService(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    private List<Profit> calculatePlayersProfit() {
        List<Profit> playersProfit = new ArrayList<>();
        for (Player player : players) {
            Money bettingMoney = player.getBettingMoney();
            BettingResult judge = BettingResult.judge(player, dealer);
            int earningsRate = judge.getEarningRate();
            playersProfit.add(new Profit(player.getName(), bettingMoney, earningsRate));
        }
        return playersProfit;
    }

    public BettingResultDto getBettingResult() {
        return new BettingResultDto(getPlayersProfit(), getDealerProfit());
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
            dealerProfit -= profit.calculateProfit();
        }
        return dealerProfit;
    }
}
