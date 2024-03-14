package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.fixture.CardFixture;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("카드 목록을 포함한 일급 컬렉션을 생성 한다.")
    public void create_with_CardList() {
        List<Card> cards = List.of(new Card(CardValue.EIGHT, CardSymbol.DIAMOND),
                new Card(CardValue.JACK, CardSymbol.CLOVER));

        assertThatCode(() -> new Cards(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 목록의 숫자 합을 계산 한다. (케이스 1)")
    public void sum_of_cards_case1() {
        List<CardValue> cardValues = List.of(CardValue.EIGHT, CardValue.JACK);
        var sut = CardFixture.카드_목록_생성(cardValues);

        var sum = sut.calculate();

        assertThat(sum).isEqualTo(18);
    }

    @Test
    @DisplayName("카드 목록의 숫자 합을 계산 한다. (케이스 2)")
    public void sum_of_cards_case2() {
        List<CardValue> cardValues = List.of(CardValue.FIVE, CardValue.EIGHT);
        var sut2 = CardFixture.카드_목록_생성(cardValues);

        var sum2 = sut2.calculate();

        assertThat(sum2).isEqualTo(13);
    }

    @Test
    @DisplayName("A가 포함되어 있고 숫자합이 10이하라면 A는 11로 계산된다.")
    public void sum_of_cards_with_A_special_value() {
        final List<CardValue> cardValues = List.of(CardValue.FIVE, CardValue.ACE);
        var sut2 = CardFixture.카드_목록_생성(cardValues);

        var sum2 = sut2.calculate();

        assertThat(sum2).isEqualTo(16);
    }

    @Test
    @DisplayName("A가 포함되어 있고 숫자합이 11이상이면 A는 1로 계산된다.")
    void sum_of_cards_with_A_not_special_value() {
        final List<CardValue> cardValues = List.of(CardValue.JACK, CardValue.FOUR, CardValue.ACE);
        var sut2 = CardFixture.카드_목록_생성(cardValues);

        var sum2 = sut2.calculate();

        assertThat(sum2).isEqualTo(15);
    }

    @Test
    @DisplayName("A가 여러개일시 A는 한번만 11로 계산한다.")
    void ace_consider_special_value_only_once() {
        final List<CardValue> cardValues = List.of(CardValue.ACE, CardValue.ACE, CardValue.ACE);
        var sut2 = CardFixture.카드_목록_생성(cardValues);

        var sum2 = sut2.calculate();

        assertThat(sum2).isEqualTo(13);
    }

    @Test
    @DisplayName("카드 목록에 첫 번째 카드를 가져온다")
    public void get_first_card() {
        List<Card> cards = List.of(new Card(CardValue.EIGHT, CardSymbol.DIAMOND),
                new Card(CardValue.JACK, CardSymbol.CLOVER));
        var sut = new Cards(cards);

        var result = sut.getFirstCard();

        assertThat(result).isEqualTo(cards.get(0));
    }

}
