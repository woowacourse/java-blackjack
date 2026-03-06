import domain.BlackjackGame;
import domain.Dealer;
import domain.Player;
import java.util.List;
import javax.xml.transform.Result;
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

        for (String name : names) {
            Player player = new Player(name);
            blackjackGame.registPlayer(player);
        }

        // 카드 나눠주기
        blackjackGame.giveHand();


        // 카드 보여주기
        List<Player> players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        resultView.printparticipantsCards(players, dealer);

        for (Player player : players) {
            String result = null;
            while((result = inputView.readHitStand(player.getName())).equals("y")){
                // Blakcjack 게임에 플레이어 히트 스탠드 여부 전달하고, 블랙잭 게임에서 플레이어 카드 처리
                blackjackGame.addPlayerCard(player);
                resultView.printPlayerCards(player);
            }
        }

        // 모두 마쳤으면

        // 딜러 히트 스탠드
        boolean dealerHitStand = dealer.shouldGetMoreCard();
        if(dealerHitStand){
            blackjackGame.addDealerCard();
        }
        resultView.printDealerHitStand(dealerHitStand);

        // 결과 출력하기
        resultView.printResult(players, dealer);

        // 최종 승패 출력하기
        resultView.printFinalResult(players, dealer);
    }

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new InputView(), new ResultView(), new BlackjackGame());
        blackjackController.run();
    }

}
