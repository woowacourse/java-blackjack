package fixture;

import domain.Card;
import domain.User;
import java.util.List;
import vo.Money;

public class UserFixture {
    public static User createDefaultUser() {
        return new User("라이", new Money(10000));
    }

    public static User createUserWithCards(List<Card> cards) {
        User user = createDefaultUser();
        for (Card card : cards) {
            user.receiveCard(card);
        }
        user.calculateScore();
        return user;
    }
}
