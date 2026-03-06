import domain.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import view.InputView;
import view.ResultView;

public class BlackjackController {
    private final InputView inputView;
    private final ResultView resultView;
    private final BlackjackGame blackjackGame;

    public BlackjackController(InputView inputView, ResultView resultView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.blackjackGame = blackjackGame;
    }

    public void run(){
        List<String> names = inputView.readPlayerNames();

        blackjackGame.registPlayers(names);

        blackjackGame.giveHand();

        List<Player> players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        resultView.printParticipantsCards(players, dealer);

        for (Player player : players) {
            String result = null;
            while((result = inputView.readHitStand(player.getName())).equals("y")){
                blackjackGame.addPlayerCard(player);
                resultView.printPlayerCards(player);
            }
        }

        resultView.printDealerHitStand(blackjackGame.dealerHitsStand());

        resultView.printResult(players, dealer);

        resultView.printFinalResult(players, dealer);
    }

}
