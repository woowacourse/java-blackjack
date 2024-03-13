package model.view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.card.CardType;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;

public class OutputView {

    public OutputView() {
    }

    public void printDistributeCards(Dealer dealer, Players players) {
        System.out.print(dealer.getName() + "와 ");
        List<String> playerNames = players.getPlayers().stream().map(Player::getName).toList();
        System.out.print(String.join(", ", playerNames));
        System.out.println("에게 2장을 나누었습니다.");
    }

    public void printCardsStock(String name, List<CardType> cards) {
        System.out.println(name + "카드: " + String.join(", ", cards.stream().map(CardType::card).toList()));
    }

    public void printBustInfo(Player player) {
        System.out.println(player.getName() + "님 버스트하였습니다");
    }

    public void printDealerHitStatus(boolean isHit) {
        if (isHit) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public void printFinalCardStatus(String name, List<CardType> cards, int totalScore) {
        System.out.print(name + "카드: " + String.join(", ", cards.stream().map(CardType::card).toList()));
        System.out.println(" - 결과: " + totalScore);
    }

    public void printFinalResult(String dealerResult, Map<String, String> playerResult) {
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerResult);
        for (Entry<String, String> entrySet : playerResult.entrySet()) {
            System.out.println(String.join(": ", entrySet.getKey(), entrySet.getValue()));
        }
    }
}
