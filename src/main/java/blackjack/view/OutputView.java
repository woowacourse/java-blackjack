package blackjack.view;

import blackjack.model.*;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPlayerCardStatus(Player player, List<Card> cards) {
        List<String> cardNames = cards.stream().map(Card::getCard)
                .toList();
        System.out.println(player.getName() + "카드: " + String.join(", ", cardNames));
    }

    public void printFirstCardStatus(Dealer dealer, Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .toList();
        System.out.println("\n딜러와 " + String.join(", ", playerNames) + "에게 " + "2장을 나누었습니다.");
        System.out.println("딜러카드: " + dealer.getCards().getFirst().getCard());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + ": " + String.join(", ", player.getCards().stream().map(Card::getCard).toList()));
        }
        System.out.println();
    }

    public void printDealerReceiveCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScoreResult(Dealer dealer, Players players) {
        System.out.println("\n딜러카드: " +
                String.join(", ", dealer.getCards().stream().map(Card::getCard).toList()) +
                " - 결과: " + dealer.getScore());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + "카드: " +
                    String.join(", ", player.getCards().stream().map(Card::getCard).toList())+
                    " - 결과: " + player.getScore());
        }
    }

    public void printGameResult(Map<Player, GameResult> gameResult) {
        System.out.println("\n## 최종 승패");
        int win = 0;
        int lose = 0;
        int draw = 0;
        for (GameResult result : gameResult.values()) {
            if (result.equals(GameResult.WIN)) {
                lose++;
            }
            if (result.equals(GameResult.LOSE)) {
                win++;
            }
            if (result.equals(GameResult.DRAW)) {
                draw++;
            }
        }
        System.out.print("딜러: ");
        if (win != 0) {
            System.out.print(win + "승 ");
        }
        if (lose != 0) {
            System.out.print(lose + "패");
        }
        if (draw != 0) {
            System.out.print(draw + "무 ");
        }
        System.out.println();
        for (Player player : gameResult.keySet()) {
            System.out.println(player.getName() + ": " + gameResult.get(player).getStatus());
        }
    }
}
