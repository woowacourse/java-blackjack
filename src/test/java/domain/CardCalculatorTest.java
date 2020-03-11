package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardCalculatorTest {
    @DisplayName("딜러가 가지고 있는 카드에 추가로 카드를 넣을지 판단하는 메서드")
    @Test
    void addCardToDealerTest() {
        Player dealer = new Dealer(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.TEN, CardSuitSymbol.CLUB));

        Assertions.assertThat(CardCalculator.isUnderSixteen(dealer)).isFalse();
    }

    @DisplayName("블랙잭(총 합이 21) 인지 판단하는 메서드 테스트")
    @Test
    void isBlackjackTest() {

    }
}
