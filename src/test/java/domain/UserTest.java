package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @DisplayName("카드를 받아 자신의 카드 더미에 추가할 수 있다")
    @Test
    void receiveCard() {
        User user = new User();
        Card card1 = new Card(Denomination.ACE, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        user.receiveCard(card1);
        user.receiveCard(card2);
        List<Card> cards = user.getCards();

        Assertions.assertThat(cards).containsExactly(card1, card2);
    }
}
