package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.fixture.ParticipantsFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Nested
    @DisplayName("생성자 예외 발생 테스트")
    class Constructor {
        @Test
        @DisplayName("참가자 수가 9명 이상이면 예외가 발생한다")
        void should_throw_exception_when_participant_size_is_over_9() {
            // given
            List<Participant> participantsSource = List.of(
                    new Dealer(),
                    new Player("1"),
                    new Player("2"),
                    new Player("3"),
                    new Player("4"),
                    new Player("5"),
                    new Player("6"),
                    new Player("7"),
                    new Player("8"),
                    new Player("9")
            );

            // when & then
            assertThatThrownBy(() -> new Participants(participantsSource))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("참가자 이름이 중복되면 예외가 발생한다")
        void should_throw_exception_when_participant_name_is_duplicated() {
            // given
            String samePlayerName = "a";
            List<Participant> participantsSource = List.of(
                    new Player(samePlayerName),
                    new Player(samePlayerName)
            );

            // when & then
            assertThatThrownBy(() -> new Participants(participantsSource))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    @DisplayName("모든 참가자 중 딜러를 제외한 플레이어 목록을 가져온다")
    void should_return_players_except_dealer() {
        // given
        List<String> playerNames = List.of("a", "b");
        Participants participants = ParticipantsFixture.createParticipants(playerNames);

        // when
        List<Participant> players = participants.getPlayerParticipants();

        // then
        assertThat(players).hasSize(2);
    }

    @Test
    @DisplayName("모든 참가자 중 딜러를 가져온다")
    void should_return_dealer() {
        // given
        List<String> playerNames = List.of("a", "b");
        Participants participants = ParticipantsFixture.createParticipants(playerNames);

        // when
        Participant dealer = participants.getDealer();

        // then
        assertThat(dealer.getClass()).isEqualTo(Dealer.class);
    }

    @Test
    @DisplayName("모든 참가자 중 딜러를 가져올 때 딜러가 없으면 예외가 발생한다")
    void should_throw_exception_when_dealer_is_not_exist() {
        // given
        List<Participant> participantsSource = List.of(
                new Player("a"),
                new Player("b")
        );
        Participants participants = new Participants(participantsSource);

        // when & then
        assertThatThrownBy(() -> participants.getDealer())
                .isInstanceOf(IllegalStateException.class);
    }
}

