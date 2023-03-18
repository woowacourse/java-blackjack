package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suits;

public class UserTest {

    @DisplayName("카드를 받아 자신의 카드 더미에 추가할 수 있다")
    @Test
    void hit() {
        User user = new User("kiara", new Cards());
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        user.hit(card1);
        user.hit(card2);
        List<Card> cards = user.getCards();

        assertThat(cards).containsExactly(card1, card2);
    }
}
