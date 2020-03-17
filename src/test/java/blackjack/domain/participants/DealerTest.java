package blackjack.domain.participants;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.participants.HandTest.CARDS_21_ACE_AS_ONE;
import static blackjack.domain.participants.HandTest.CARDS_8;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("dealer 생성자 테스트")
    @Test
    void dealerValidConstructorTest() {
        assertThat(new Dealer()).isNotNull();
        assertThat(new Dealer().getName()).isEqualTo("딜러");
    }

    static Stream<Arguments> needsMoreCardArguments() {
        return Stream.of(
                Arguments.of(CARDS_8, true),
                Arguments.of(CARDS_21_ACE_AS_ONE, false)
        );
    }

    @DisplayName("needsMoreCard()가 카드패 합이 16 이하일때 true를 반환하는지 테스트")
    @ParameterizedTest
    @MethodSource("needsMoreCardArguments")
    void needsMoreCardTest(List<Card> cards, boolean expected) {
        Dealer dealer = new Dealer();
        cards.forEach(dealer::draw);
        assertThat(dealer.needsMoreCard()).isEqualTo(expected);
    }
}
