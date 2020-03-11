package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.deck.Card;
import domain.deck.DeckFactory;
import domain.deck.Symbol;
import domain.deck.Type;

class DealerTest {
    Dealer dealer;

    private static Stream<Arguments> createOption() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.SIX), 1),
                Arguments.of(new Card(Symbol.DIAMOND, Type.SEVEN), 0)
        );
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

    @BeforeEach
    void setUp() {
        dealer = Dealer.appoint();
    }

    @ParameterizedTest
    @DisplayName("딜러 기준 포인트에 따른 드로우")
    @MethodSource("createOption")
    void additionalDraw(Card card, int expected) {
        dealer.draw(new Card(Symbol.CLOVER, Type.TEN));
        dealer.draw(card);
        int initSize = dealer.cards.size();

        dealer.additionalDraw(DeckFactory.getDeck());

        assertThat(dealer.cards.size()).isEqualTo(initSize + expected);
    }
}