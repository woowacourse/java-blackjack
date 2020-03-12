package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.WinningResult;
import domain.deck.Card;
import domain.deck.Symbol;
import domain.deck.Type;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.appoint();
    }

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThat(Dealer.appoint()).isNotNull();
    }

    @Test
    @DisplayName("첫 카드 분배 결과")
    void getFirstDrawResult() {
        dealer.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        dealer.draw(new Card(Symbol.DIAMOND, Type.ACE));

        assertThat(dealer.getFirstDrawResult()).isEqualTo("딜러카드: 8클로버");
    }

    @ParameterizedTest
    @DisplayName("딜러 기준 드로우 가능한지 확인")
    @MethodSource("createOption")
    void isAvailableToDraw(Card card, boolean expected) {
        dealer.draw(new Card(Symbol.CLOVER, Type.TEN));
        dealer.draw(card);

        assertThat(dealer.isAvailableToDraw()).isEqualTo(expected);
    }

    private static Stream<Arguments> createOption() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.SIX), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.SEVEN), false),
                Arguments.of(new Card(Symbol.DIAMOND, Type.ACE), false)
        );
   }

    @Test
    @DisplayName("딜러 승패 확인 결과")
    void getWinningResult() {
        String expected = "딜러: 0승 0패 1무승부";

        dealer.applyWinningResult(WinningResult.DRAW);

        assertThat(dealer.getWinningResult()).isEqualTo(expected);
    }
}