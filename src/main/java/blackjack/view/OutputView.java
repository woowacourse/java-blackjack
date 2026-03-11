package blackjack.view;

import blackjack.model.Dealer;
import blackjack.model.GameResult;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import java.util.List;

public class OutputView {
    public void printInitCards(List<Player> players, Dealer dealer) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("딜러와 ");
        sb.append(String.join(", ", names));
        sb.append("에게 2장을 나누었습니다.");
        System.out.println(sb);

        printDealerCard(dealer);
        for (Player player : players) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    private void printDealerCard(Dealer dealer) {
        System.out.println(
                "딜러카드: " + dealer.cards().getFirst().getRank().getName() + dealer.cards().getFirst().getSuit()
                        .getName());
    }

    public void printPlayerCards(Player player) {
        List<String> formats = player.cards().stream()
                .map(card -> {
                    return card.getRank().getName() + card.getSuit().getName();
                })
                .toList();

        System.out.println(player.getName() + "카드: " + String.join(", ", formats));
    }


    public void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printCardStatus(GameSummary gameSummary) {

        StringBuilder sb = new StringBuilder();
        sb.append(gameSummary.name() + "카드: ");
        List<String> cardFormats = gameSummary.cards().stream()
                .map(card -> card.getSuit().getName() + card.getRank().getName()).toList();
        sb.append(String.join(", ", cardFormats));
        sb.append(" - 결과: " + gameSummary.score());

        System.out.println(sb);
    }

    public void printGameResult(List<GameResult> gameResults) {
        System.out.println();
        System.out.println("## 최종 수익");

        for (GameResult gameResult : gameResults) {
            System.out.println(gameResult.name() + ": " + gameResult.profit());
        }
    }

    public void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

}

