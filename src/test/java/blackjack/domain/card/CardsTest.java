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

        var sum = sut.sum();

        assertThat(sum).isEqualTo(18);
    }

    @Test
    @DisplayName("카드 목록의 숫자 합을 계산 한다. (케이스 2)")
    public void sum_of_cards_case2() {
        List<CardValue> cardValues = List.of(CardValue.FIVE, CardValue.EIGHT);
        var sut2 = CardFixture.카드_목록_생성(cardValues);

        var sum2 = sut2.sum();

        assertThat(sum2).isEqualTo(13);
    }

    @Test
    @DisplayName("카드 목록에 에이스가 포함되어 있으면 참을 반환한다.")
    public void true_if_contain_ACE_card() {
        List<CardValue> cardValues = List.of(CardValue.ACE, CardValue.FIVE);
        var sut = CardFixture.카드_목록_생성(cardValues);

        var result = sut.containAce();

        assertTrue(result);
    }

    @Test
    @DisplayName("카드 목록에 에이스가 포함되어 없으면 거짓을 반환한다.")
    public void false_if_not_contain_ACE_card() {
        List<CardValue> cardValues = List.of(CardValue.TWO, CardValue.FIVE);
        var sut = CardFixture.카드_목록_생성(cardValues);

        var result = sut.containAce();

        assertFalse(result);
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
