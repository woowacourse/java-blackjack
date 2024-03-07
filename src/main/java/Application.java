import domain.Blackjack;
import domain.BlackjackResultDTO;
import domain.Player;
import domain.Players;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {

        String[] names = InputView.inputPlayers();

        Players players = Players.from(names);
        Blackjack blackjack = new Blackjack(players);
        OutputView.printPlayersStatus(blackjack.getDealer(), blackjack.getPlayers().getPlayers());

        // 참가자 게임진행
        for (var player : blackjack.getPlayers().getPlayers()) {
            drawCardDuringPlayerTurn(player, blackjack);
        }

        Player dealer = blackjack.getDealer();

        if (dealer.calculateScore() < 17) {
            blackjack.dealCard(dealer);
            OutputView.printDealerStatus();
        }

        // 결과 출력
        List<Player> players1 = blackjack.getPlayers().getPlayers();
        Player dealer2 = blackjack.getDealer();
        OutputView.printPlayerStatus(dealer);
        players1.add(dealer2);
        OutputView.printResults(players1);

        // 승패 출력 맨~
        BlackjackResultDTO blackjackResult = blackjack.finishGame();
        OutputView.printGameResults(blackjackResult);
    }

    private static void drawCardDuringPlayerTurn(final Player player, final Blackjack blackjack) {
        while (player.alive() && InputView.tryPoll(player.getName())) {
            blackjack.dealCard(player);
        }
        OutputView.printPlayerStatus(player);
    }
}
