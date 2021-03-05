package blackjack;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultCalculator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        initializeParticipants(dealer, players);

        OutputView.printPlayersHandStatus(dealer, players);

        progressPlayersTurn(dealer, players);
        progressDealerTurn(dealer);

        GameResult result = getResult(dealer, players);
        OutputView.printResult(result);
    }

    private static void initializeParticipants(Dealer dealer, List<Player> players) {
        dealer.setBaseCard();
        dealer.setPlayersBaseCard(players);
    }

    private static List<Player> getPlayers() {
        try {
            String playerNames = InputView.getPlayerName();
            return namesToPlayers(playerNames);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getPlayers();
        }
    }

    private static List<Player> namesToPlayers(String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(Player::from)
                .collect(Collectors.toList());
    }

    private static void progressPlayersTurn(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            turn(dealer, player);
        }
    }

    private static void turn(Dealer dealer, Player player) {
        while (!player.isBust() && InputView.wantsReceive(player.getName())) {
            dealer.deal(player);
            OutputView.printHandStatus(player);
        }
    }

    private static void progressDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerDrawMessage();
            dealer.pickAnotherCard();
        }
    }

    private static GameResult getResult(Dealer dealer, List<Player> players) {
        ResultCalculator resultCalculator = new ResultCalculator(dealer, players);
        return resultCalculator.getResult();
    }
}
