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

    public GameResult getResult() {
        List<PlayerResult> playersResults = new ArrayList<>();
        List<WinOrLose> dealerResult = new ArrayList<>();

        int dealerCardSum = dealer.sumCard();

        for (Player player : players) {
            WinOrLose winOrLose = WinOrLose.match(player, dealerCardSum);

            PlayerResult playerResult = PlayerResult.from(player, winOrLose);

            playersResults.add(playerResult);
            dealerResult.add(winOrLose.reverse());
        }

        return new GameResult(dealer.getCardHand(), dealerCardSum, dealerResult, playersResults);
    }
}
