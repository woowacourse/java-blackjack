package blackjack.domain.card;


import static blackjack.domain.Fixtures.ACE;
import static blackjack.domain.Fixtures.EIGHT;
import static blackjack.domain.Fixtures.NINE;
import static blackjack.domain.Fixtures.TEN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardsTest {
    private Cards cards;

    @BeforeEach
    void setup() {
        cards = new Cards();
        cards.add(ACE);
    }

    @Test
    @DisplayName("버스트 여부 확인 기능 true 테스트")
    public void isBustTest() {
        cards.add(TEN);
        cards.add(EIGHT);
        cards.add(NINE);

        assertThat(cards.isBust())
                .isTrue();
    }

    @Test
    @DisplayName("버스트 여부 확인 기능 False 테스트")
    public void isBust2Test() {
        cards.add(TEN);
        cards.add(EIGHT);

        assertThat(cards.isBust())
                .isFalse();
    }

    @Test
    @DisplayName("카드모음 생성되는지 테스트")
    public void equalSizeTest() {
        assertThat(cards.isSizeOf(1))
                .isTrue();
    }

    @Test
    @DisplayName("첫 카드 리턴 기능 테스트")
    void getFirstCardTest() {
        assertThat(cards.getFirstCard())
                .isEqualTo(ACE);
    }

    @Test
    @DisplayName("카드 리스트 리턴 기능 테스트")
    void get() {
        assertThat(cards.get())
                .isEqualTo(List.of(ACE));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE,FIVE,ACE,17",
            "JACK,FIVE,JACK,25",
            "ACE,ACE,ACE,13",
            "JACK,QUEEN,KING,30",
            "ACE,TEN,TEN,21",
    })

    @DisplayName("올바른 포인트 계산되는지 테스트")
    void setExpectedPointTest(Denomination d1, Denomination d2, Denomination d3, int expectedPoint) {
        Cards cards = new Cards();
        cards.add(Card.of(d1, Suit.SPADE));
        cards.add(Card.of(d2, Suit.SPADE));
        cards.add(Card.of(d3, Suit.SPADE));

        assertThat(cards.getPoint())
                .isEqualTo(expectedPoint);
    }
}
