package view;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialDeal(BlackjackGame blackjackGame) {
        List<String> names = blackjackGame.getPlayers().stream().map(Player::getName).toList();
        System.out.println("\n" + "딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
        System.out.println("딜러: " + blackjackGame.getDealer().reveal().getDisplayName());
        for(Player player : blackjackGame.getPlayers()) {
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

    public void printBlackjacks(List<Player> blackjackPlayers) {
        for (Player player : blackjackPlayers) {
            System.out.println(player.getName() + "는 블랙잭입니다!!");
        }
    }

    public void printDealerHit(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardResult(BlackjackGame blackjackGame) {
        System.out.println();
        System.out.println(formatHandResult("딜러", blackjackGame.getDealer()));
        blackjackGame.getPlayers().forEach(player ->
                System.out.println(formatHandResult(player.getName(), player))
        );
    }

    private String formatHandResult(String name, Participant participant) {
        String cards = participant.getHand().stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
        return  name + "카드: " + cards + " - 결과: " + participant.calculateScore();
    }

    public void printGameRecord(BlackjackGame blackjackGame) {
        System.out.println('\n' + "## 최종 수익");
        int dealerProfit = blackjackGame.getPlayers().stream()
                .mapToInt(Player::calculateProfit)
                .sum() * -1;
        System.out.println("딜러: " + dealerProfit);
        for (Player player : blackjackGame.getPlayers()) {
            System.out.println(player.getName() + ": " + player.calculateProfit());
        }
    }
}
