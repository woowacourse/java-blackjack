package blackjack.domain;

import java.util.List;

public class BlackJackController {

    private final GameManager gameManager;

    public BlackJackController() {
        gameManager = new GameManager(new CardManager());
    }

    public void run() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        gameManager.registerPlayers(nicknames);
        gameManager.distributeCards();

        List<Player> players = gameManager.getPlayers();
        for(Player player : players) {
            while (!gameManager.isBurstPlayer(player)) {
                // TODO 뷰 입력
                gameManager.drawAdditionalCard(player);
            }
        }

        // TODO 딜러 카드 자동 뽑기
    }

}
