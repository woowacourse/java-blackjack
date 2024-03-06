package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {
    @Test
    @DisplayName("딜러와 참여자들에게 카드를 2장씩 나누어 준다")
    void dealTest() {
        // when
        Hand hand = new Hand(() -> 1);

        // then
        assertThat(hand.getCards()).hasSize(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 9, 10})
    @DisplayName("카드 합 계산은 카드 숫자를 기본으로 한다")
    void calculateCardsTotalTest(int index) {
        // given
        Hand hand = new Hand(() -> index);

        // when
        int actualTotal = hand.calculateCardsTotal();

        // then
        Denomination expectedDenomination = Denomination.findByIndex(index);
        int expectedTotal = expectedDenomination.getScore() + expectedDenomination.getScore();
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }

    @Test
    @DisplayName("BlackJack인지 확인한다")
    void checkBlackJackTest() {
        // given
        Hand hand = new Hand(new SequentialNumberGenerator(List.of(0, 11, 0, 12)));

        // when & then
        assertThat(hand.isBlackJack()).isTrue();
    }

    private static class SequentialNumberGenerator implements NumberGenerator {
        private final Iterator<Integer> numbers;

        public SequentialNumberGenerator(final List<Integer> numbers) {
            this.numbers = numbers.iterator();
        }

        @Override
        public int pick() {
            if (numbers.hasNext()) {
                return numbers.next();
            }
            throw new NoSuchElementException("이미 모든 numbers가 반환되었습니다.");
        }
    }

    @Test
    @DisplayName("카드를 뽑는다")
    void addCardTest() {
        // when
        Hand hand = new Hand(() -> 0);

        // when
        hand.addCard();

        // then
        assertThat(hand.getCards()).hasSize(3);
    }
}
