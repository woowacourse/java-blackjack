package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("핸드를 생성한다")
    @Test
    public void createSuccess() {
        assertThatCode(Hand::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("패를 추가한다")
    @Test
    public void addCard() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());

        assertThat(hand.getCards()).hasSize(1);
    }


    @DisplayName("Jack 과 Ace 를 더한 값은 21이다")
    @Test
    public void calculate1() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());
        hand.put(CardFixture.cloverAce());

        assertThat(hand.calculate()).isEqualTo(21);
    }

    @DisplayName("Jack, 3, ACE 를 다 더하면 14이다")
    @Test
    public void calculate2() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());
        hand.put(CardFixture.diamond3());
        hand.put(CardFixture.cloverAce());

        assertThat(hand.calculate()).isEqualTo(14);
    }
}
