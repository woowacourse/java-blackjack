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
    public void Cards_Instance_create_with_CardList() {
        List<Card> cards = List.of(new Card(CardValue.EIGHT, CardSymbol.DIAMOND),
                new Card(CardValue.JACK, CardSymbol.CLOVER));

        assertThatCode(() -> new Cards(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 목록의 숫자 합을 계산 한다. (케이스 1)")
    public void Cards_Sum_of_cards_case1() {
        List<CardValue> cardValues = List.of(CardValue.EIGHT, CardValue.JACK);
        var sut = CardFixture.카드_목록_생성(cardValues);

        var sum = sut.sum();

        assertThat(sum).isEqualTo(18);
    }

    @Test
    @DisplayName("카드 목록의 숫자 합을 계산 한다. (케이스 2)")
    public void Cards_Sum_of_cards_case2() {
        List<CardValue> cardValues = List.of(CardValue.FIVE, CardValue.EIGHT);
        var sut2 = CardFixture.카드_목록_생성(cardValues);

        var sum2 = sut2.sum();

        assertThat(sum2).isEqualTo(13);
    }

    @Test
    @DisplayName("카드 목록에 에이스가 포함되어 있으면 참을 반환한다.")
    public void Cards_True_if_contain_ACE_card() {
        List<CardValue> cardValues = List.of(CardValue.ACE, CardValue.FIVE);
        var sut = CardFixture.카드_목록_생성(cardValues);

        var result = sut.containAce();

        assertTrue(result);
    }

    @Test
    @DisplayName("카드 목록에 에이스가 포함되어 없으면 거짓을 반환한다.")
    public void Cards_False_if_not_contain_ACE_card() {
        List<CardValue> cardValues = List.of(CardValue.TWO, CardValue.FIVE);
        var sut = CardFixture.카드_목록_생성(cardValues);

        var result = sut.containAce();

        assertFalse(result);
    }

    @Test
    @DisplayName("카드 목록에 첫 번째 카드를 가져온다")
    public void Cards_Get_first_card() {
        List<Card> cards = List.of(new Card(CardValue.EIGHT, CardSymbol.DIAMOND),
                new Card(CardValue.JACK, CardSymbol.CLOVER));
        var sut = new Cards(cards);

        var result = sut.getFirstCard();

        assertThat(result).isEqualTo(List.of(cards.get(0)));
    }

    @Test
    @DisplayName("플레이어는 카드들 숫자 합 중 최대값을 결정한다.")
    public void GamePlayer_Determine_max_number_sum_of_cards() {
        var sut = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        var result = sut.calculateScore();

        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("숫자 합이 21이 넘지 않으면 플레이어는 에이스를 11로 결정한다.")
    public void GamePlayer_Determine_ace_is_11_if_not_exceed_21() {
        var sut = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.ACE));

        var result = sut.calculateScore();

        assertThat(result).isEqualTo(19);
    }

    @Test
    @DisplayName("숫자 합이 21이 넘으면 플레이어는 에이스를 1로 결정한다.")
    public void GamePlayer_Determine_ace_is_1_if_exceed_21() {
        var sut = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.TEN, CardValue.ACE));

        var result = sut.calculateScore();

        assertThat(result).isEqualTo(19);
    }

    @Test
    @DisplayName("숫자 합이 21을 넘으면 버스트다.")
    public void GamePlayer_Bust_if_exceed_21() {
        var sut = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.TEN, CardValue.SEVEN));

        var result = sut.isBust();

        assertTrue(result);
    }

    @Test
    @DisplayName("숫자 합이 21을 넘지 않으면 버스트가 아니다.")
    public void GamePlayer_Not_bust_if_under_21() {
        var sut = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.TEN));

        var result = sut.isBust();

        assertFalse(result);
    }
}
