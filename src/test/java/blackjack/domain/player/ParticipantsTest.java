package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantsTest {

    @Test
    @DisplayName("플레이어 이름 리스트를 반환한다.")
    void drawCardTest() {
        //given
        final Participants participants = Participants.from(List.of(
                Participant.from("파워", BetAmount.from("10000")),
                Participant.from("준팍", BetAmount.from("10000")),
                Participant.from("서브웨이", BetAmount.from("10000"))
        ));

        //then
        assertThat(participants.getNames())
                .isEqualTo(List.of("파워", "준팍", "서브웨이"));
    }

    @Test
    @DisplayName("중복된 이름이 입력되면, 예외가 발생한다")
    void givenDuplicateName_thenFail() {
        //given
        List<Participant> participants = List.of(
                Participant.from("파워", BetAmount.from("10000")),
                Participant.from("파워", BetAmount.from("10000"))
        );

        //then
        assertThatThrownBy(() -> Participants.from(participants)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되지 않은 이름만 입력해주세요");
    }

    @Test
    @DisplayName("플레이어 이름은 딜러가 될수없다.")
    void givenParticipantsNameDealer_thenFail() {
        //given
        List<Participant> participants = List.of(Participant.from("딜러", BetAmount.from("10000")));

        //then
        assertThatThrownBy(() -> Participants.from(participants)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 딜러가 될 수 없습니다.");
    }
}
