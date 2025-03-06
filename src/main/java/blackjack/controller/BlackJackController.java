package blackjack.controller;

import blackjack.domain.CardManager;
import blackjack.domain.GameManager;
import blackjack.domain.Nickname;
import blackjack.domain.Player;
import blackjack.dto.DrawnCardResult;
import blackjack.dto.PlayerWinningResult;
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
        // TODO 뷰 출력

        List<DrawnCardResult> drawnCardResults = gameManager.calculateDrawnCardResults();
        // TODO 뷰 출력

        List<PlayerWinningResult> playerWinningResults = gameManager.calculateGameResult();
        // TODO 뷰 출력
    }

}
