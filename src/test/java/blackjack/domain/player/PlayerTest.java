package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {

    @ParameterizedTest
    @DisplayName("참가자의 이름은 공백이 될수 없다.")
    @ValueSource(strings = {"\n", " ", ""})
    void givenBlankName_thenFail(String name) {
        //then
        assertThatThrownBy(() -> Participant.from(name, BetAmount.from("10000")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 이름을 입력해 주세요");
    }

    @Test
    @DisplayName("참가자의 이름이 10자 초과하면 예외가 발생한다.")
    void givenTenOverLengthName_thenFail() {
        //then
        assertThatThrownBy(() -> Participant.from("01234567891", BetAmount.from("10000")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("10자 이하의 이름만 입력해 주세요");
    }

    @Test
    @DisplayName("참가자 카드를 출력한다.")
    void createPlayerAndCards_thenDisplayCardNames() {
        //given
        Player player = Participant.from("power", BetAmount.from("10000"));
        player.takeCard(Card.of(Shape.DIAMOND, Number.FIVE));
        player.takeCard(Card.of(Shape.SPADE, Number.TWO));
        player.takeCard(Card.of(Shape.CLUBS, Number.SIX));

        //when
        List<String> cards = player.getCardNames();

        //then
        assertThat(cards)
                .isEqualTo(List.of("5다이아몬드", "2스페이드", "6클로버"));
    }

    @Test
    @DisplayName("참가자 Bust 상태")
    void givenPlayerAndCards_thenBust() {
        //given
        Player player = Participant.from("power", BetAmount.from("10000"));
        player.takeCard(Card.of(Shape.DIAMOND, Number.TEN));
        player.takeCard(Card.of(Shape.SPADE, Number.TEN));
        player.takeCard(Card.of(Shape.CLUBS, Number.TWO));

        //then
        assertThat(player.isBust())
                .isEqualTo(true);
    }

    @Test
    @DisplayName("참가자 플레이중인 상태")
    void givenParticipantAndCards_thenInPlaying() {
        //given
        Player player = Participant.from("power", BetAmount.from("10000"));
        player.takeCard(Card.of(Shape.DIAMOND, Number.TEN));
        player.takeCard(Card.of(Shape.SPADE, Number.TEN));

        //then
        assertThat(player.isInPlaying())
                .isEqualTo(true);
    }

    @Test
    @DisplayName("딜러 플레이중인 상태")
    void givenDealerAndCards_thenInPlaying() {
        //given
        Player player = Dealer.create();
        player.takeCard(Card.of(Shape.DIAMOND, Number.TEN));
        player.takeCard(Card.of(Shape.SPADE, Number.SIX));

        //then
        assertThat(player.isInPlaying())
                .isEqualTo(true);
    }

    @Test
    @DisplayName("참가자 블랙잭 상태")
    void givenPlayerAndCards_thenBlackJack() {
        //given
        Player player = Participant.from("power", BetAmount.from("10000"));
        player.takeCard(Card.of(Shape.DIAMOND, Number.TEN));
        player.takeCard(Card.of(Shape.SPADE, Number.ACE));

        //then
        assertThat(player.isBlackJack())
                .isEqualTo(true);
    }
}
