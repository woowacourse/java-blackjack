package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("기존 카드의 합이 21이 넘는다면 예외를 발생시킨다")
    @Test
    void test1() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardType.CLOVER),
                new Card(CardNumberType.EIGHT, CardType.DIAMOND),
                new Card(CardNumberType.JACK, CardType.DIAMOND));
        Cards cards = new Cards(testCards);

        //when & then
        assertThatThrownBy(
                () -> cards.checkMaxSum()
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("새로 배분된 카드를 저장한다")
    @Test
    void test2() {
        //given
        Cards cards = Cards.createEmpty();
        Card testCard = new Card(CardNumberType.SIX, CardType.CLOVER);

        //when
        cards.add(testCard);

        //then
        assertThat(cards.getCards()).contains(testCard);
    }

    @DisplayName("카드의 합계가 16 이하이면 True를 반환한다")
    @Test
    void test3() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardType.CLOVER),
                new Card(CardNumberType.JACK, CardType.DIAMOND));
        Cards cards = new Cards(testCards);

        //when & then
        assertThat(cards.isUnderDrawLimit()).isTrue();
    }

    @DisplayName("카드의 합계가 16 초과이면 False를 반환한다")
    @Test
    void test4() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardType.CLOVER),
                new Card(CardNumberType.ACE, CardType.DIAMOND),
                new Card(CardNumberType.JACK, CardType.DIAMOND));
        Cards cards = new Cards(testCards);

        //when & then
        assertThat(cards.isUnderDrawLimit()).isFalse();

    }

    @DisplayName("카드의 합을 구한다")
    @Test
    void test5() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardType.CLOVER),
                new Card(CardNumberType.JACK, CardType.DIAMOND));
        Cards cards = new Cards(testCards);
        //when
        int sum = cards.calculateSumResult();
        //then
        assertThat(sum).isEqualTo(16);
    }

    @DisplayName("만약 ACE가 포함된 경우, 나머지 카드의 합이 11 이상이면 ACE를 1로 간주한다")
    @Test
    void test10() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.FIVE, CardType.CLOVER),
                new Card(CardNumberType.SIX, CardType.DIAMOND),
                new Card(CardNumberType.ACE, CardType.DIAMOND)
        );
        Cards cards = new Cards(testCards);
        //when
        int sum = cards.calculateSumResult();
        //then
        assertThat(sum).isEqualTo(12);
    }

    @DisplayName("만약 ACE가 포함된 경우, 나머지 카드의 합이 10 이하이면 ACE를 11으로 간주한다")
    @Test
    void test6() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.FIVE, CardType.CLOVER),
                new Card(CardNumberType.FIVE, CardType.DIAMOND),
                new Card(CardNumberType.ACE, CardType.DIAMOND)
                );
        Cards cards = new Cards(testCards);
        //when
        int sum = cards.calculateSumResult();
        //then
        assertThat(sum).isEqualTo(21);
    }

    @DisplayName("ACE가 여러 개인 경우, 각 ACE를 최적의 값으로 포함하여 총합을 계산한다")
    @Test
    void test7() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.ACE, CardType.CLOVER),
                new Card(CardNumberType.ACE, CardType.HEART),
                new Card(CardNumberType.ACE, CardType.DIAMOND)
        );
        Cards cards = new Cards(testCards);
        //when
        int sum = cards.calculateSumResult();
        //then
        assertThat(sum).isEqualTo(13);
    }
}
