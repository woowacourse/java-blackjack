package blackjack.domain;

import static blackjack.domain.HandTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {

    @DisplayName("dealer 생성자 테스트")
    @Test
    void dealerValidConstructorTest() {
        assertThat(new Dealer()).isNotNull();
        assertThat(new Dealer().getName()).isEqualTo("딜러");
    }

    @DisplayName("needsMoreCard() 메서드 테스트")
    @ParameterizedTest
    @MethodSource("needsMoreCardParameters")
    void needsMoreCardTest(List<Card> cards, boolean expected) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.draw(card);
        }
        assertThat(dealer.needsMoreCard()).isEqualTo(expected);
    }

    static Stream<Arguments> needsMoreCardParameters() {
        return Stream.of(
            Arguments.of(CARDS_EIGHT, true),
            Arguments.of(CARDS_ACE_AS_ONE, false)
        );
    }

}
