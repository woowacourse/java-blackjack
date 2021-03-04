package blackjack.view;

import blackjack.domain.Status;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {

    }

    public static void printPlayersGuideMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printShowUsersCardMessage(List<Player> players) {
        List<String> playerNames = players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
        System.out.println("딜러와 " + playerNames + "에게 2장의 카드를 나누었습니다.");
    }

    public static void showCards(Dealer dealer, List<Player> players) {
        System.out.println(dealer.getFirstCard());
        players
            .forEach(player -> System.out.println(player.getName() + "카드: " + player.getCards()));
    }

    public static void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsAndScore(Dealer dealer, List<Player> players) {
        System.out
            .println(dealer.getName() + " 카드: " + dealer.getCards() + "- 결과: " + dealer.getScore());
        players.forEach(player -> System.out.println(
            player.getName() + " 카드: " + player.getCards() + "- 결과: " + player.getScore()));
    }

    public static void printResult(Map<Player, Status> result) {
        System.out.println("## 최종 승패");
        List<Integer> winnings = new ArrayList<>();
        result.values()
            .forEach(status -> winnings.add(Collections.frequency(result.values(), status)));
        System.out.println("딜러 ");
    }

    public static void printHitGuideMessage(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)");
    }
}
