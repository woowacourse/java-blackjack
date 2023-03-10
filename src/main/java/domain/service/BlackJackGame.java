package domain.service;

import domain.model.Dealer;
import domain.model.Participant;
import domain.model.Player;
import domain.model.Players;
import domain.model.Profit;
import domain.vo.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackJackGame {

    private final CardDistributor cardDistributor;
    private final BlackJackResultMaker blackJackResultMaker;
    private final ProfitCalculator profitCalculator;

    public BlackJackGame(final CardDistributor cardDistributor, final BlackJackResultMaker blackJackResultMaker,
        final ProfitCalculator profitCalculator) {
        this.cardDistributor = cardDistributor;
        this.blackJackResultMaker = blackJackResultMaker;
        this.profitCalculator = profitCalculator;
    }

    public void giveCard(final Participant participant) {
        cardDistributor.giveCard(participant);
    }

    public void giveInitCards(final Dealer dealer, final Players players) {
        cardDistributor.giveInitCards(dealer);
        cardDistributor.giveInitCards(players);
    }

    public Map<Player, Result> makePlayersResult(final Dealer dealer, final Players players) {
        return blackJackResultMaker.makePlayersResult(dealer, players);
    }

    public Result makeDealerResult(final Dealer dealer, final Players players) {
        return blackJackResultMaker.makeDealerResult(dealer, players);
    }

    public List<Profit> calculatePlayersProfit(final Players players, final Dealer dealer) {
        return IntStream.range(0, players.count())
            .mapToObj(index -> profitCalculator.calculatePlayerProfit(players.get(index), dealer))
            .collect(Collectors.toList());
    }
}
