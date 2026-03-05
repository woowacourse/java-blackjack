public class GameController {

    private GameManager manager;

    public GameController(GameManager manager) {
        this.manager = manager;
    }

    public void run() {

        // 플레이어 등록
        Players players = new Players();
        players.add(new Player("pobi"));
        players.add(new Player("cary"));

        // 카드 뿌리기
        Dealer dealer = new Dealer();
        manager.startGame();

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


    }
}
