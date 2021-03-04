package blackjack.domain;

import java.util.Map;

public class GameResult { //TODO: getCards하면 card들만 나오고 여기서 처리
    public static void getPlayersCardsAndResult(Dealer dealer, Players players) {
        System.out.println(dealer.getName() + ": " + dealer.getCards() + " - 결과: " + dealer.calculateMaximumPoint());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + "카드: " + player.getCards()
                    + " - 결과: " + player.calculateMaximumPoint());
            WinnerFlag.calculateResult(dealer, player);
        }
    }

    public static void getResult(Players players) {
        WinnerCount winnerCount = new WinnerCount();
        Map<WinnerFlag, Integer> result = winnerCount.calculateTotalWinnings(players);
        System.out.println("딜러: " + result.get(WinnerFlag.LOSE) + WinnerFlag.WIN.getFlagOutput()
                + result.get(WinnerFlag.DRAW) + WinnerFlag.DRAW.getFlagOutput()
                + result.get(WinnerFlag.WIN) + WinnerFlag.LOSE.getFlagOutput());
        players.makeEachPlayerResult();
    }
}
