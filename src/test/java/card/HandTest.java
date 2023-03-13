package card;

import static testCards.TestCards.ACE_DIAMOND;
import static testCards.TestCards.EIGHT_DIAMOND;
import static testCards.TestCards.THREE_DIAMOND;
import static testCards.TestCards.TWO_DIAMOND;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {
    
    @DisplayName("카드를 가지지 않고 생성할 수 있다.")
    @Test
    public void create() {
        Hand hand = new Hand(null);
        Assertions.assertThat(hand.getCards()).isEmpty();
    }

    @DisplayName("가지고 있는 모든 카드를 반환한다.")
    @Test
    public void getCards() {
        Hand hand = new Hand(List.of(TWO_DIAMOND, EIGHT_DIAMOND));
        Assertions.assertThat(hand.getSize()).isEqualTo(2);
        Assertions.assertThat(hand.getCards()).containsExactly(TWO_DIAMOND, EIGHT_DIAMOND);
    }

    @DisplayName("카드가 추가되면 추가된 카드를 가진 새로운 Hand를 반환한다.")
    @Test
    void addCard() {
        Hand hand = new Hand(List.of(TWO_DIAMOND, EIGHT_DIAMOND));
        Hand newHand = hand.addCard(new Card(Denomination.NINE, Suit.DIAMOND));
        Assertions.assertThat(newHand.getPoint()).isEqualTo(19);
    }

    @DisplayName("들고있는 카드의 총 점수를 반환한다.")
    @Nested
    class GetPoint {

        @DisplayName("ACE가 없을 때는 기본 점수의 총합을 반환한다.")
        @Test
        void notContainsAce() {
            Hand hand = new Hand(List.of(TWO_DIAMOND, EIGHT_DIAMOND));
            Assertions.assertThat(hand.getPoint()).isEqualTo(10);
        }

        @DisplayName("ACE가 있고 보너스 점수를 적용할 수 있는 경우 보너스 점수를 합한 총 점수를 반환한다..")
        @Test
        void containsAceCanAddBonus() {
            Hand hand = new Hand(List.of(TWO_DIAMOND, EIGHT_DIAMOND, ACE_DIAMOND));
            Assertions.assertThat(hand.getPoint()).isEqualTo(21);
        }

        @DisplayName("ACE가 있고 보너스 점수를 적용할 수 없는 경우 보너스 점수를 더하지 않은 점수를 반환한다..")
        @Test
        void containsAceCanNotAddBonus() {
            Hand hand = new Hand(List.of(THREE_DIAMOND, EIGHT_DIAMOND, ACE_DIAMOND));
            Assertions.assertThat(hand.getPoint()).isEqualTo(12);
        }
    }
}
