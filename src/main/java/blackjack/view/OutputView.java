package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void displayDistributedCardStatus(Dealer dealer, List<Player> players) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러와 ");
        String playerNames = String.join(", ", players.stream().map(player -> player.getName().trim()).toList());
        sb.append(playerNames);
        sb.append("에게 2장을 나누었습니다.\n\n");

        sb.append("딜러카드: " + dealer.getCardDeck().get(0).toString() + "\n");
        for (Player player : players) {

            sb.append(player.getName().trim() + "카드: ");
            sb.append(player.getCardDeck().get(0).toString() + ", ");
            sb.append(player.getCardDeck().get(1).toString() + "\n");
        }

        System.out.println(sb);
    }

    public void displayUpdatedPlayerCardStatus(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName().trim() + "카드: ");
        String playerCards = String.join(", ", player.getCardDeck().stream().map(Card::toString).toList());
        sb.append(playerCards);
        sb.append("\n");

        System.out.println(sb);
    }

    public void displayExtraDealerCardStatus() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void displayFinalCardStatus(Dealer dealer, List<Player> players) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러카드: ");
        sb.append(String.join(", ", dealer.getCardDeck().stream().map(Card::toString).toList()));
        sb.append(String.format("- 결과: %d\n", dealer.calculateTotalCardScore()));

        for (Player player : players) {
            sb.append(String.format("%s카드: ", player.getName()));
            sb.append(String.join(", ", player.getCardDeck().stream().map(Card::toString).toList()));
            sb.append(String.format("- 결과: %d\n", player.calculateTotalCardScore()));
        }

        System.out.println(sb);
    }

    public void displayDealerResult(Map<GameResult, Integer> dealerResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("## 최종 승패\n");

        sb.append("딜러: ");

        for (GameResult gameResult : GameResult.values()) {
            if (!dealerResult.containsKey(gameResult)) {
                continue;
            }
            sb.append(String.format("%d%s ", dealerResult.get(gameResult), gameResult.getStatus()));
        }

        System.out.println(sb);

    }

    public void displayPlayerResult(Player player, GameResult playerResult) {
        System.out.println(player.getName() + ": " + playerResult.getStatus());
    }
}
