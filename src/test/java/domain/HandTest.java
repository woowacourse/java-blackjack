package domain;

import domain.card.Card;
import domain.exception.DuplicatedException;
import domain.member.Hand;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("Ace 처리 테스트")
    @ParameterizedTest
    @CsvSource({
            "5:6:A, 하트:하트:클로버, 12",
            "4:5:A, 하트:하트:하트, 20",
            "4:A:5:A, 하트:하트:하트:클로버, 21",
            "4:5:6, 하트:하트:하트, 15",
            "A:A, 하트:클로버, 12",
            "A:A:8, 하트:클로버:하트, 20",
            "A:A:A:A, 하트:클로버:스페이드:다이아몬드, 14",
            "10:10:A, 하트:클로버:하트, 21"
    })
    void aceTest_AceAmountOneAndSum11_return12(String numbers, String names, int result) {
        Queue<String> numberQueue = new LinkedList<>(List.of(numbers.split(":")));
        Queue<String> nameQueue = new LinkedList<>(List.of(names.split(":")));
        while (!numberQueue.isEmpty()) {
            hand.appendCard(new Card(numberQueue.poll(), nameQueue.poll()));
        }
        Assertions.assertThat(hand.calculateTotalValue()).isEqualTo(result);
    }

}
