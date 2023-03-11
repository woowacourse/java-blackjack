package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    static class UserImplement extends User {

        @Override
        public boolean isHittable() {
            return true;
        }
    }

    @DisplayName("카드를 받아 자신의 카드 더미에 추가할 수 있다")
    @Test
    void hit() {
        User user = new UserImplement();
        Card card1 = Card.of(Denomination.TWO, Suits.HEART);
        Card card2 = Card.of(Denomination.THREE, Suits.DIAMOND);
        user.hit(card1);
        user.hit(card2);
        List<Card> cards = user.getCards();

        assertThat(cards).containsExactly(card1, card2);
    }
}
