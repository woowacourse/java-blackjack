package view;

import domain.Card;
import domain.Dealer;
import domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialDeal(List<Player> players, Dealer dealer) {
        String userNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", userNames);
        System.out.println("딜러카드: " + dealer.reveal().getDisplayName());
        for(Player player : players) {
            String cards = player.getHand().stream()
                    .map(Card::getDisplayName)
                    .collect(Collectors.joining(", "));
            System.out.println(player.getName()+"카드: " + cards);
        }
        System.out.println();
    }

    public void printHand(Player player) {
        String cards = player.getHand().stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
        System.out.println(player.getName() + "카드: " + cards);
    }

    public void printBlackjackWin(Player player) {
        System.out.println(player.getName() + "는 블랙잭입니다!!");
    }

    public void printDealerBlackjack() {
        System.out.println("딜러가 블랙잭이 나왔습니다!!");
    }

    public void printBlackjackDraw(Player player) {
        System.out.println(player.getName() + "와 딜러가 블랙잭입니다!!");
    }

    public void printDealerHit(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerStand(){
        System.out.println("딜러는 17이상이라 카드를 더 뽑지 않습니다.");
    }

    public void printCardResult(List<Player> players, Dealer dealer) {
        System.out.println();
        System.out.println("딜러카드: " + dealer.getHand().stream().map(Card::getDisplayName)
                .collect(Collectors.joining(", "))+ "- 결과: " + dealer.getHand().stream().mapToInt(Card::getValue).sum());
        for(Player player : players) {
            System.out.println(player.getName() + "카드: " + player.getHand().stream().map(Card::getDisplayName)
                    .collect(Collectors.joining(", "))+ " - 결과: " + player.getHand().stream().mapToInt(Card::getValue).sum());
        }
    }

    public void printGameRecord(List<Player> players, Dealer dealer) {
        System.out.println('\n' + "## 최종 수익");
        int dealerProfit = players.stream()
                .mapToInt(Player::calculateProfit)
                .sum() * -1;
        System.out.println("딜러: " + dealerProfit);
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.calculateProfit());
        }
    }
}
