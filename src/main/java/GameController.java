import java.util.List;
import java.util.Scanner;

public class GameController {

    private GameManager manager;

    public GameController(GameManager manager) {
        this.manager = manager;
    }

    public void run() {
        // 플레이어 입력
        manager.addPlayer("pobi");
        manager.addPlayer("cary");

        // 카드를 2장씩 세팅
        manager.startGame();

        // 초기 정보 표출
        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();

        // 플레이어 턴 실행
        for (Player player : manager.getPlayerSequence()) {
            while (true) {
                if (player.isBust() || manager.isBlackjack(player)) {
                    break;
                }

                if (new Scanner(System.in).nextLine().equals("n")) {
                    break;
                }

                List<String> playerHand = manager.drawCard(player);
                System.out.println("playerHand = " + playerHand);
            }
        }


    }
}
