import domain.BlackjackGame;
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

        for (String name : names) {
            String result = inputView.readHitStand(name);
            while(result.equals("y")){
                // Blakcjack 게임에 플레이어 히트 스탠드 여부 전달하고, 블랙잭 게임에서 플레이어 카드 처리
            }
        }



    }

}
