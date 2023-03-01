package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    static class UserImplement extends User {

        @Override
        boolean isReceivable() {
            return true;
        }
    }

    @DisplayName("카드를 받아 자신의 카드 더미에 추가할 수 있다")
    @Test
    void receiveCard() {
        User user = new UserImplement();
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        user.receiveCard(card1);
        user.receiveCard(card2);
        List<Card> cards = user.getCards();

        assertThat(cards).containsExactly(card1, card2);
    }
}
