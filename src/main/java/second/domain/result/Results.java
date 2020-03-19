package second.domain.result;

import second.domain.gamer.Dealer;
import second.domain.gamer.Money;
import second.domain.gamer.Player;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Results {
    private final List<Result> playerResults;
    private final Result dealerResult;

    private Results(final List<Player> players, final Dealer dealer) {
        this.playerResults = generatePlayerResults(players, dealer);
        this.dealerResult = generateDealerResult(this.playerResults, dealer);
    }

    public static Results of(final List<Player> players, final Dealer dealer) {
        return new Results(players, dealer);
    }

    private List<Result> generatePlayerResults(final List<Player> players, final Dealer dealer) {
        return players.stream()
                .map(player -> generateResult(dealer, player))
                .collect(Collectors.toList());
    }

    private Result generateResult(Dealer dealer, Player player) {
        return new Result(
                player.getName(),
                player.calculateProfit(calculateProfitMultipleValue(dealer, player))
        );
    }

    private double calculateProfitMultipleValue(Dealer dealer, Player player) {
        return ResultType.of(player, dealer).getProfitMultipleValue();
    }

    private Result generateDealerResult(final List<Result> playersResults, final Dealer dealer) {
        // Optional 써야하니까 reduce 사용 X 심지어 가독성도 이게 더 나은 것 같다.
        int dealerProfit = 0;
        for (Result result : playersResults) {
            dealerProfit += result.getMoney().getValue();
        }

        return new Result(dealer.getName(), new Money(-dealerProfit));
    }

    public List<Result> getPlayerResults() {
        return playerResults;
    }

    public Result getDealerResult() {
        return dealerResult;
    }
}
