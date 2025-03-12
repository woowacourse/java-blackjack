package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

  @Nested
  @DisplayName("참가자 추가 테스트")
  class AddParticipants {

    @Test
    @DisplayName("딜러가 없다, 예외가 발생한다.")
    void error_whenNoDealer() {
      //given
      final List<Participant> given = new ArrayList<>();

      //then
      assertThatThrownBy(() -> new Participants(given))
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("하나의 게임에 하나의 딜러가 존재해야 합니다.");
    }

    @Test
    @DisplayName("딜러가 2명 이상이라면, 예외가 발생한다.")
    void error_whenDealerDuplication() {
      //given
      final List<Participant> given = new ArrayList<>();
      given.add(new Dealer());
      given.add(new Dealer());

      //then
      assertThatThrownBy(() -> new Participants(given))
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("하나의 게임에 하나의 딜러가 존재해야 합니다.");
    }

    @Test
    @DisplayName("닉네임 명단을 받으면, 참가자를 추가한다")
    void test_generateFromNames() {
      //given
      final var names = List.of("pobi", "bougi", "uga");
      final var participants = Participants.from(names);

      //then
      final var players = participants.getParticipants();
      var actual = players.stream()
          .flatMap(player -> names.stream()
              .filter(name -> name.equals(player.getName())))
          .count();

      assertThat(actual).isEqualTo(names.size());
    }

    @Test
    @DisplayName("중복된 닉네임 명단을 받으면, 예외를 발생한다")
    void error_duplicationName() {
      //given
      final var names = List.of("pobi", "pobi");

      //when&then
      assertThatThrownBy(() -> Participants.from(names))
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("중복된 닉네임");
    }

    @Test
    @DisplayName("참가자가 없을 경우, 예외를 발생한다")
    void error_emptyParticipant() {
      //given
      final List<Participant> given = new ArrayList<>();
      given.add(new Dealer());
      final var participants = new Participants(given);
      //when&then
      assertThatThrownBy(participants::getPlayers)
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("게임 참가자가 없습니다! 게임 설정을 다시 진행해주세요.");
    }
  }
}
