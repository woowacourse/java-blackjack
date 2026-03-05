package domain;

import domain.exception.DuplicatedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @DisplayName("카드 추가 시 중복 검사 예외 테스트")
    @Test
    void appendAndDuplicateTest_holdingTwoAndAppendTwo_ThrowDuplicatedException() {
        hand.appendCard(new Card("2", "하트"));

        Assertions.assertThatThrownBy(
                () -> hand.appendCard(new Card("2", "하트")))
                .isInstanceOf(DuplicatedException.class);
    }

    @DisplayName("카드 총합 계산 기능 테스트")
    @Test
    void calculateTest_holdTwoThree_ReturnTotalValue() {
        hand.appendCard(new Card("2", "하트"));
        hand.appendCard(new Card("3", "스페이드"));

        Assertions.assertThat(hand.calculateTotalValue())
                .isEqualTo(5);
    }
}
