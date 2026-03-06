import domain.Card;
import domain.Dealer;
import domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialDeal(List<User> users, Dealer dealer) {
        System.out.println('\n' + "딜러와 pobi, jason에게 2장을 나누었습니다.");
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
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }


}
