package blackjack;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        initializeParticipants(dealer, players);
        OutputView.printParticipantHands(dealer, players);

        progressPlayersTurn(dealer, players);
        progressDealerTurn(dealer);

        GameResultDto result = getResult(dealer, players);
        OutputView.printGameResult(result);
    }

    private static void initializeParticipants(Dealer dealer, List<Player> players) {
        dealer.drawBaseCard();
        dealer.drawBaseCardToPlayers(players);
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
            playerTurn(dealer, player);
        }
    }

    private static void playerTurn(Dealer dealer, Player player) {
        try {
            deal(dealer, player);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            playerTurn(dealer, player);
        }
    }

    private static void deal(Dealer dealer, Player player) {
        while (!player.isBust() && InputView.askDrawOrStay(player.getName())) {
            dealer.deal(player);
            OutputView.printHand(player);
        }
    }

    private static void progressDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerDrawMessage();
            dealer.selfDraw();
        }
    }

    private static GameResultDto getResult(Dealer dealer, List<Player> players) {
        return dealer.getGameResult(players);
    }
}
