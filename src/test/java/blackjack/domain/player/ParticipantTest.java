package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.card.state.Ready;
import blackjack.fixture.ParticipantImpl;
import blackjack.fixture.PlayerFixture;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParticipantTest {

    @Test
    @DisplayName("이름과 카드 일급 컬렉션을 통해서 플레이어를 생성 한다.")
    void create_with_name_and_cards() {
        final Cards cards = new Cards(List.of(new Card(CardValue.EIGHT, CardSymbol.CLOVER),
                new Card(CardValue.ACE, CardSymbol.DIAMOND)));

        assertThatCode(() -> new ParticipantImpl(cards))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 정보를 통해서 플레이어를 생성 한다.")
    void create_with_player_info() {
        final var sut = new ParticipantImpl();

        assertThat(sut.state).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("플레이어는 카드들 숫자 합 중 최대값을 결정한다.")
    void determine_max_number_sum_of_cards() {
        final var sut = PlayerFixture.참가자_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        final var result = sut.calculateScore();

        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("숫자 합이 21을 넘으면 버스트다.")
    void GamePlayer_Bust_if_exceed_21() {
        final var sut = PlayerFixture.참가자_생성(
                List.of(CardValue.EIGHT, CardValue.TEN, CardValue.SEVEN));

        final var result = sut.isBust();

        assertTrue(result);
    }

    @Test
    @DisplayName("숫자 합이 21을 넘지 않으면 버스트가 아니다.")
    void GamePlayer_Not_bust_if_under_21() {
        final var sut = PlayerFixture.참가자_생성(
                List.of(CardValue.EIGHT, CardValue.SEVEN));

        final var result = sut.isBust();

        assertFalse(result);
    }
}
