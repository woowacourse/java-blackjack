package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printStartingCardsStatuses(Dealer dealer, Players players) {
        String names = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
        System.out.println(Formatter.formatDealerStartCardStatus(dealer));
        for (Player player : players.getPlayers()) {
            System.out.println(Formatter.formatPlayerCardStatus(player));
        }
    }

    public static void printMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(List<PlayerResult> playerResults, DealerResult dealerResult, Dealer dealer) {
        System.out.println(Formatter.formatDealerCardResult(dealer, dealerResult));

        for (PlayerResult playerResult : playerResults) {
            System.out.println(Formatter.formatPlayerCardResult(playerResult));
        }
    }

    public static void printCardResult(Player player) {
        System.out.println(Formatter.formatPlayerCardStatus(player));
    }

    public static void printBustedPlayer(Player player) {
        System.out.println(player.getName() + "는 버스트되어 더 이상 카드를 뽑을 수 없습니다!");
    }

    public static void printGameResult(DealerResult dealerResult,
                                       List<PlayerResult> playersResult) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %s%n", Formatter.formatDealerGameResult(dealerResult));
        System.out.println(Formatter.formatPlayerGameResult(playersResult));
    }

}
