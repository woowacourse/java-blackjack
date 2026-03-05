import domain.Card;
import domain.Dealer;
import domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {



    public void printHand(User user) {
        String cards = user.getHand().stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
        System.out.println(user.getName() + "카드: " + cards);
    }
}
