package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @ParameterizedTest(name = "[{index}] {0}인 경우 총합은 {1}")
    @MethodSource("provideParameters")
    @DisplayName("단일 카드 총합 구하기")
    void sum(Denomination denomination, int expect) {
        Cards cards = new Cards(Collections.singletonList(Card.valueOf(denomination, Suit.CLOVER)));
        assertThat(cards.sum()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments(Denomination.ACE, 11),
                Arguments.arguments(Denomination.TWO, 2),
                Arguments.arguments(Denomination.THREE, 3),
                Arguments.arguments(Denomination.FOUR, 4),
                Arguments.arguments(Denomination.FIVE, 5),
                Arguments.arguments(Denomination.SIX, 6),
                Arguments.arguments(Denomination.SEVEN, 7),
                Arguments.arguments(Denomination.EIGHT, 8),
                Arguments.arguments(Denomination.NINE, 9),
                Arguments.arguments(Denomination.TEN, 10),
                Arguments.arguments(Denomination.JACK, 10),
                Arguments.arguments(Denomination.QUEEN, 10),
                Arguments.arguments(Denomination.KING, 10)
        );
    }

    @Test
    @DisplayName("ACE 2개인 경우")
    void sum2() {
        Cards cards = new Cards(Arrays.asList(Card.valueOf(Denomination.ACE, Suit.CLOVER)
                , Card.valueOf(Denomination.ACE, Suit.HEART)));
        assertThat(cards.sum()).isEqualTo(12);
    }

    @Test
    @DisplayName("카드 추가하기")
    void add() {
        List<Card> list = new ArrayList<>();
        list.add(Card.valueOf(Denomination.ACE, Suit.CLOVER));
        Cards cards = new Cards(list);

        cards.add(Card.valueOf(Denomination.JACK, Suit.HEART));

        List<Card> cardList = cards.getValue();
        assertThat(cardList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드가 2장이고 합이 21이면 블랙잭이다.")
    void is_blackjack() {
        List<Card> list = new ArrayList<>();
        list.add(Card.valueOf(Denomination.ACE, Suit.CLOVER));
        list.add(Card.valueOf(Denomination.JACK, Suit.HEART));
        Cards cards = new Cards(list);

        assertThat(cards.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("합이 21이어도 카드가 2장 초과이면 블랙잭이 아니다.")
    void is_not_blackjack() {
        List<Card> list = new ArrayList<>();
        list.add(Card.valueOf(Denomination.ACE, Suit.CLOVER));
        list.add(Card.valueOf(Denomination.NINE, Suit.HEART));
        list.add(Card.valueOf(Denomination.TWO, Suit.HEART));
        Cards cards = new Cards(list);

        assertThat(cards.isBlackJack()).isFalse();
    }

    @Test
    @DisplayName("카드 합이 21이 넘어가면 Bust이다.")
    void is_bust() {
        List<Card> list = new ArrayList<>();
        list.add(Card.valueOf(Denomination.QUEEN, Suit.CLOVER));
        list.add(Card.valueOf(Denomination.JACK, Suit.HEART));
        list.add(Card.valueOf(Denomination.TWO, Suit.HEART));
        Cards cards = new Cards(list);

        assertThat(cards.isBust()).isTrue();
    }
}
