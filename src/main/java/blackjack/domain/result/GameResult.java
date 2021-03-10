package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private final List<Card> dealerCards;
    private final int dealerSum;
    private final DealerResult dealerResult;
    private final List<PlayerResult> playersResults;

    public GameResult(List<Card> dealerCards, int dealerSum,
            DealerResult dealerResult, List<PlayerResult> playersResults) {
        this.dealerCards = dealerCards;
        this.dealerSum = dealerSum;
        this.dealerResult = dealerResult;
        this.playersResults = playersResults;
    }

    public static GameResult calculate(Dealer dealer, List<Player> players) {
        int dealerTotal = dealer.getHandTotal();

        // 딜러 버스트
        if (dealer.isBust()) {
            return getGameResultWhenDealerBust(dealer, players, dealerTotal);
        }

        return getGameResult(dealer, players, dealerTotal);
    }

    private static GameResult getGameResultWhenDealerBust(Dealer dealer, List<Player> players, int dealerTotal) {
        List<PlayerResult> playersResults = new ArrayList<>();
        DealerResult dealerMatchCount = new DealerResult();
        for (Player player : players) {
            if (player.isBust()) {
                playersResults.add(PlayerResult.from(player, MatchResult.LOSE));
                dealerMatchCount.add(MatchResult.WIN);
                continue;
            }
            playersResults.add(PlayerResult.from(player, MatchResult.WIN));
            dealerMatchCount.add(MatchResult.LOSE);
        }
        return new GameResult(dealer.getCards(), dealerTotal, dealerMatchCount, playersResults);
    }

    private static GameResult getGameResult(Dealer dealer, List<Player> players, int dealerTotal) {
        List<PlayerResult> playersResults = new ArrayList<>();
        DealerResult dealerMatchCount = new DealerResult();

        for (Player player : players) {
            MatchResult matchResult = player.getMatchResult(dealerTotal);

            playersResults.add(PlayerResult.from(player, matchResult));

            MatchResult dealerMatch = matchResult.reverse();
            dealerMatchCount.add(dealerMatch);
        }

        return new GameResult(dealer.getCards(), dealerTotal, dealerMatchCount, playersResults);
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public int getDealerSum() {
        return dealerSum;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResult> getPlayersResults() {
        return playersResults;
    }
}
