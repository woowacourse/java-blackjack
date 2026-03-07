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

        // 밍구) 이 부분 조금 걸림.. 컨트롤러가 생성하는 게 맞는가
        List<Player> players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        resultView.printParticipantsCards(players, dealer);

        for (Player player : players) {
            String result = null;
            while((result = inputView.readHitStand(player.getName())).equals("y")){
                blackjackGame.giveCard(player);
                resultView.printCards(player);
            }
        }

        resultView.printDealerHitStand(blackjackGame.dealerHitsStand());

        resultView.printCardsWithResult(players, dealer);

        resultView.printResultStatistics(players, dealer);
    }
}
