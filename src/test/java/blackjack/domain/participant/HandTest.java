package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.fixture.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("핸드를 생성한다")
    @Test
    void createSuccess() {
        assertThatCode(Hand::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("패를 추가한다")
    @Test
    void addCard() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());

        assertThat(hand.getCards()).hasSize(1);
    }


    @DisplayName("Jack 과 Ace 를 더한 값은 21이다")
    @Test
    void calculate1() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());
        hand.put(CardFixture.cloverAce());

        assertThat(hand.calculate()).isEqualTo(21);
    }

    @DisplayName("Jack, 3, ACE 를 다 더하면 14이다")
    @Test
    void calculate2() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());
        hand.put(CardFixture.diamond3());
        hand.put(CardFixture.cloverAce());

        assertThat(hand.calculate()).isEqualTo(14);
    }

    @DisplayName("플레이어의 카드가 두 장이고 21점 이라면 블랙잭이다")
    @Test
    void testIsBlackJack1() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());
        hand.put(CardFixture.cloverAce());

        assertThat(hand.isBlackjack()).isTrue();
    }

    @DisplayName("플레이어의 카드가 두장도 21점도 아니라면 블랙잭이 아니다")
    @Test
    void testIsBlackJack2() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());
        hand.put(CardFixture.heartJack());
        hand.put(CardFixture.cloverAce());

        assertThat(hand.isBlackjack()).isFalse();
    }
}
