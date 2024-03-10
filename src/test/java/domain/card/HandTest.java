package domain.card;

import static domain.FixtureCard.ACE_HEARTS;
import static domain.FixtureCard.SEVEN_HEARTS;
import static domain.FixtureCard.TEN_HEARTS;
import static domain.FixtureCard.TWO_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("손에 들고 있는 카드의 합계를 반환한다.")
    @Test
    void calculateSum() {
        List<Card> cards = List.of(SEVEN_HEARTS, TEN_HEARTS);

        Hand hand = new Hand(cards);

        assertThat(hand.calculateSum())
                .isEqualTo(SEVEN_HEARTS.getRankValue() + TEN_HEARTS.getRankValue());
    }

    @DisplayName("카드 한 장을 손 패로 가지고 온다.")
    @Test
    void add() {
        List<Card> cards = List.of(SEVEN_HEARTS, TEN_HEARTS);
        Hand hand = new Hand(cards);
        Card addCard = TWO_HEARTS;

        hand.add(addCard);

        assertThat(hand.getCards()).contains(addCard);
    }

    @DisplayName("손 패에 Ace카드가 몇개 있는지 반환한다.")
    @Test
    void containAce() {
        List<Card> hasAceCard = List.of(ACE_HEARTS, TEN_HEARTS);
        Hand hasAceHand = new Hand(hasAceCard);
        int actual1 = hasAceHand.countAceCard();

        assertThat(actual1).isEqualTo(1);
    }
}
