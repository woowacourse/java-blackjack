package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResultCalculator {

    private final Dealer dealer;
    private final List<Player> players;

    public ResultCalculator(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public GameResultDto getResult() {
        List<PlayerResultDto> playersResults = new ArrayList<>();
        Map<MatchResult, Integer> dealerMatchCount = new EnumMap<>(MatchResult.class);

        int dealerTotal = dealer.getHandTotal();

        for (Player player : players) {
            MatchResult matchResult = player.getMatchResult(dealerTotal);

            playersResults.add(PlayerResultDto.from(player, matchResult));

            MatchResult dealerMatch = matchResult.reverse();
            dealerMatchCount.put(dealerMatch, dealerMatchCount.getOrDefault(dealerMatch, 0) + 1);
        }

        return new GameResultDto(dealer.getCards(), dealerTotal, dealerMatchCount,
                playersResults);
    }
}
