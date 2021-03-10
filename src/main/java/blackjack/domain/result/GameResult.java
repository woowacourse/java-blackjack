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
    private final List<PlayerResultDto> playersResults;

    public GameResult(List<Card> dealerCards, int dealerSum,
            DealerResult dealerResult,
            List<PlayerResultDto> playersResults) {
        this.dealerCards = dealerCards;
        this.dealerSum = dealerSum;
        this.dealerResult = dealerResult;
        this.playersResults = playersResults;
    }

    public static GameResult calculate(Dealer dealer, List<Player> players) {
        List<PlayerResultDto> playersResults = new ArrayList<>();
        DealerResult dealerMatchCount = new DealerResult();
        int dealerTotal = dealer.getHandTotal();

        for (Player player : players) {
            MatchResult matchResult = player.getMatchResult(dealerTotal);

            playersResults.add(PlayerResultDto.from(player, matchResult));

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

    public List<PlayerResultDto> getPlayersResults() {
        return playersResults;
    }
}
