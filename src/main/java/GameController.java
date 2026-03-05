import java.util.List;

public class GameController {

    private GameManager manager;

    public GameController(GameManager manager) {
        this.manager = manager;
    }

    public void run() {

        List<String> playerName = List.of("pobi", "cary");
        // 카드를 2장씩 세팅
        manager.startGame();






        /*
        for (Player player : players.getPlayers()) {
            while (!player.isBust()) {
                int score = manager.calculateScore(player.getHand());
                manager.judgeBust(score, player);
                if (player.isBust()) {
                    continue;
                }
                manager.drawCard(player);
            }
        }

        for (Player player : players.getPlayers()) {
            System.out.println("player = " + player);
        }
        */


    }
}
