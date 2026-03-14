package view;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialDeal(Players players, Dealer dealer) {
        List<String> names = players.getPlayers().stream().map(Player::getName).toList();
        System.out.println("\n" + "딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
        System.out.println("딜러: " + dealer.reveal().getDisplayName());
        for(Player player : players.getPlayers()) {
            printHand(player);
        }
        System.out.println();
    }

    public void printHand(Player player) {
        String cards = player.getHand().stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
        System.out.println(player.getName() + "카드: " + cards);
    }

    public void printBlackjackWin(Players players) {
        for(Player player : players.getPlayers()) {
            if(player.isBlackjack()) {
                System.out.println(player.getName() + "는 블랙잭입니다!!");
            }
        }
    }

    public void printDealerHit(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardResult(Players players, Dealer dealer) {
        System.out.println();
        System.out.println(formatHandResult("딜러", dealer));
        players.getPlayers().forEach(player ->
                System.out.println(formatHandResult(player.getName(), player))
        );
    }

    private String formatHandResult(String name, Participant participant) {
        String cards = participant.getHand().stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
        return  name + "카드: " + cards + " - 결과: " + participant.calculateScore();
    }

    public void printGameRecord(Players players) {
        System.out.println('\n' + "## 최종 수익");
        int dealerProfit = players.getPlayers().stream()
                .mapToInt(Player::calculateProfit)
                .sum() * -1;
        System.out.println("딜러: " + dealerProfit);
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + ": " + player.calculateProfit());
        }
    }
}
