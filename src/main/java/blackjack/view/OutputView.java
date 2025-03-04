package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import java.util.List;

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
//        pobi카드: 2하트, 8스페이드, A클로버
    }

    public void displayExtraDealerCardStatus() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void displayFinalCardStatus() {
//        딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
//        pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
//        jason카드: 7클로버, K스페이드 - 결과: 17
    }

    public void displayFinalResult() {
//     ## 최종 승패
//        딜러: 1승 1패
//        pobi: 승
//        jason: 패
    }
}
