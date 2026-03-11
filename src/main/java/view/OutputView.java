package view;

import domain.Card;
import domain.Dealer;
import domain.DealerProfit;
import domain.User;
import domain.UserProfit;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialDeal(List<User> users, Dealer dealer) {
        String userNames = users.stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", userNames);
        System.out.println("딜러카드: " + dealer.reveal().getDisplayName());
        for(User user : users) {
            String cards = user.getHand().stream()
                    .map(Card::getDisplayName)
                    .collect(Collectors.joining(", "));
            System.out.println(user.getName()+"카드: " + cards);
        }
        System.out.println();
    }

    public void printHand(User user) {
        String cards = user.getHand().stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
        System.out.println(user.getName() + "카드: " + cards);
    }

    public void printDealerHit(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerStand(){
        System.out.println("딜러는 17이상이라 카드를 더 뽑지 않습니다.");
    }

    public void printCardResult(List<User> users, Dealer dealer) {
        System.out.println();
        System.out.println("딜러카드: " + dealer.getHand().stream().map(Card::getDisplayName)
                .collect(Collectors.joining(", "))+ "- 결과: " + dealer.getHand().stream().mapToInt(Card::getValue).sum());
        for(User user : users) {
            System.out.println(user.getName() + "카드: " + user.getHand().stream().map(Card::getDisplayName)
                    .collect(Collectors.joining(", "))+ " - 결과: " + user.getHand().stream().mapToInt(Card::getValue).sum());
        }
    }

    /**
     * 딜러: 1승 1패
     * pobi: 승
     * jason: 패
     */
    public void printGameRecord(List<UserProfit> userProfits, Dealer dealer) {
        System.out.println('\n' + "## 최종 승패");
        StringJoiner sj = new StringJoiner(" ");

        if (dealer.getWinRounds() > 0) sj.add(dealer.getWinRounds() + "승");
        if (dealer.getLoseRounds() > 0) sj.add(dealer.getLoseRounds() + "패");
        if (dealer.getDrawRounds() > 0) sj.add(dealer.getDrawRounds() + "무");

        if (sj.length() > 0) {
            System.out.println("딜러: " + sj.toString());
            for (UserProfit userProfit : userProfits) {
                System.out.println(userProfit.userName() + ": " + userProfit.gameResult().getName());
            }
        }
    }

    public void printTotalProfit(List<UserProfit> userProfits, DealerProfit dealerProfit) {
        System.out.println('\n' + "## 최종 수익");
        System.out.println("딜러: " + dealerProfit.profit());
        for (UserProfit userProfit : userProfits) {
            System.out.println(userProfit.userName() + ": " + userProfit.profit());
        }
    }
}
