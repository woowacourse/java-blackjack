package blackjack.domain;

import java.util.List;

public class BlackJackController {

    public void run() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        CardManager cardManager = new CardManager();
        GameManager gameManager = new GameManager(cardManager);
        gameManager.registerPlayers(nicknames);
        gameManager.distributeCards();

        List<Player> players = gameManager.getPlayers();
        for (Player player : players) {
            while (!gameManager.isBustPlayer(player)) {
                // TODO 뷰 입력
                gameManager.hit(player);
            }
        }

        int count = gameManager.drawDealerCards();
    }

}
