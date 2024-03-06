package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("이름과 카드 일급 컬렉션을 통해서 플레이어를 생성 한다.")
    public void Player_Instance_create_with_name_and_cards() {
        Name name = new Name("초롱");
        Cards cards = new Cards(List.of(new Card(CardValue.EIGHT, CardSymbol.CLOVER),
                new Card(CardValue.ACE, CardSymbol.DIAMOND)));

        assertThatCode(() -> new Player(name, cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어는 카드들 숫자 합 중 최대값을 결정한다.")
    public void Player_Determine_max_number_sum_of_cards() {
        var sut = 플레이어_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        var result = sut.determineMaxSum();

        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("숫자 합이 21이 넘지 않으면 플레이어는 에이스를 11로 결정한다.")
    public void Player_Determine_ace_is_11_if_not_exceed_21() {
        var sut = 플레이어_생성(List.of(CardValue.EIGHT, CardValue.ACE));

        var result = sut.determineMaxSum();

        assertThat(result).isEqualTo(19);
    }

    @Test
    @DisplayName("숫자 합이 21이 넘으면 플레이어는 에이스를 1로 결정한다.")
    public void Player_Determine_ace_is_1_if_exceed_21() {
        var sut = 플레이어_생성(List.of(CardValue.EIGHT, CardValue.ACE, CardValue.SEVEN));

        var result = sut.determineMaxSum();

        assertThat(result).isEqualTo(16);
    }

    @Test
    @DisplayName("플레이어는 숫자의 합이 21이 넘지 않을 때까지 추가 카드를 받을 수 있다.")
    public void Player_Can_receive_additional_card() {
        var sut = 플레이어_생성(List.of(CardValue.EIGHT, CardValue.ACE));

        var result = sut.isReceivable();

        assertTrue(result);
    }

    @Test
    @DisplayName("플레이어는 숫자의 합이 21이 넘어 가면 추가 카드를 받을 수 없다.")
    public void Player_Can_not_receive_additional_card() {
        var sut = 플레이어_생성(List.of(CardValue.EIGHT, CardValue.JACK, CardValue.KING));

        var result = sut.isReceivable();

        assertFalse(result);
    }

    private Cards 카드_목록_생성(List<CardValue> cardValues) {
        return new Cards(cardValues.stream()
                                   .map(cardValue -> new Card(cardValue, CardSymbol.DIAMOND))
                                   .toList());
    }

    private Player 플레이어_생성(List<CardValue> cardValues) {
        Name name = new Name("초롱");
        return new Player(name, 카드_목록_생성(cardValues));
    }
}
