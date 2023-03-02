package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

    @ParameterizedTest
    @DisplayName("참가자의 이름은 공백이 될수 없다.")
    @ValueSource(strings = {"\n", " ", ""})
    void givenBlankName_thenFail(String name) {
        //then
        assertThatThrownBy(() -> Participant.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 이름을 입력해 주세요");
    }

    @Test
    @DisplayName("참가자의 이름이 10자 초과하면 예외가 발생한다.")
    void givenTenOverLengthName_thenFail() {
        //then
        assertThatThrownBy(() -> Participant.from("01234567891"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("10자 이하의 이름만 입력해 주세요");
    }

    @Test
    @DisplayName("카드를 출력한다")
    void createParticipant_thenDisplayCards() {
        //given
        Participant participant = Participant.from("power");
        participant.takeCard(Card.of(Shape.DIAMOND, Number.FIVE));
        participant.takeCard(Card.of(Shape.SPADE, Number.TWO));
        participant.takeCard(Card.of(Shape.CLUBS, Number.SIX));

        //when
        List<Card> cards = participant.displayCards();

        //then
        assertThat(cards)
                .isEqualTo(List.of(Card.of(Shape.DIAMOND, Number.FIVE)
                        , Card.of(Shape.SPADE, Number.TWO)
                        , Card.of(Shape.CLUBS, Number.SIX)));
    }
}
