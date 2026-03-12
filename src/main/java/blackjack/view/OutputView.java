package blackjack.view;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.GameResult;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import java.util.List;

public class OutputView {
    public void printInitCards(List<Player> players, Dealer dealer) {
        System.out.println();
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", String.join(", ", names)));

        printDealerCard(dealer);
        for (Player player : players) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    private void printDealerCard(Dealer dealer) {
        Card card = dealer.cards().getFirst();
        System.out.println(String.format("딜러카드: %s%s", card.getRank().getName(), card.getSuit().getName()));
    }

    public void printPlayerCards(Player player) {
        List<String> formats = player.cards().stream()
                .map(card -> card.getRank().getName() + card.getSuit().getName())
                .toList();
        System.out.println(String.format("%s카드: %s", player.getName(), String.join(", ", formats)));
    }


    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printGameSummary(List<GameSummary> gameSummaries) {
        System.out.println();
        for (GameSummary gameSummary : gameSummaries) {
            List<String> cardFormats = gameSummary.cards().stream()
                    .map(card -> card.getRank().getName() + card.getSuit().getName())
                    .toList();
            System.out.println(String.format("%s카드: %s - 결과: %d", gameSummary.name(), String.join(", ", cardFormats),
                    gameSummary.score()));
        }
    }

    public void printGameResult(List<GameResult> gameResults) {
        System.out.println();
        System.out.println("## 최종 수익");
        for (GameResult gameResult : gameResults) {
            System.out.println(String.format("%s: %d", gameResult.name(), gameResult.profit()));
        }
    }

    public void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

}

