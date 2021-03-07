package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class ResultCalculator {

    private final Dealer dealer;
    private final List<Player> players;

    public ResultCalculator(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public GameResultDto getResult() {
        List<PlayerResultDto> playersResults = new ArrayList<>();
        List<MatchResult> dealerResult = new ArrayList<>();

        int dealerCardSum = dealer.sumCard();

        for (Player player : players) {
            MatchResult winOrLose = MatchResult.match(player, dealerCardSum);

            PlayerResultDto playerResult = PlayerResultDto.from(player, winOrLose);

            playersResults.add(playerResult);
            dealerResult.add(winOrLose.reverse());
        }

        return new GameResultDto(dealer.getCards(), dealerCardSum, dealerResult, playersResults);
    }
}
