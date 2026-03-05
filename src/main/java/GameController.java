import java.util.List;
import java.util.Scanner;

public class GameController {

    private GameManager manager;
    private InputView inputView;
    private OutputView outputView;

    public GameController(GameManager manager, InputView inputView, OutputView outputView) {
        this.manager = manager;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 플레이어 입력
        List<String> playerNames = inputView.readPlayerName();
        for (String playerName : playerNames) {
            manager.addPlayer(playerName);
        }

        // 카드를 2장씩 세팅
        manager.startGame();

        // 초기 정보 표출
        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();
        outputView.printInitialInfo(initialInfo);

        // 플레이어 턴 실행
        for (Player player : manager.getPlayerSequence()) {
            while (true) {
                if (player.isBust() || manager.isBlackjack(player)) {
                    break;
                }

                if (new Scanner(System.in).nextLine().equals("n")) {
                    break;
                }

                List<String> playerHand = manager.drawPlayerCard(player);
                System.out.println("playerHand = " + playerHand);
            }
        }

        // 딜러 턴 실행
        while (manager.isDealerTurn()) {
            manager.drawDealerCard();
        }

        // 점수 결과 출력
        System.out.println(manager.getScoreResults());

        // 최종 승패 출력
        System.out.println(manager.getFinalResult());
    }
}
