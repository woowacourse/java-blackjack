package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exceptions.BlackjackArgumentException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

  @Nested
  @DisplayName("참가자 추가 테스트")
  class AddParticipants {

    @Test
    @DisplayName("딜러만 별도로 추가할 수 있다.")
    void test_addDealer() {
      //given
      final var participants = new Participants();

      //when
      participants.addDealer();
      //then
      final var dealer = participants.getDealer();
      assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    @DisplayName("딜러가 2명 이상이라면, 예외가 발생한다.")
    void error_addDealerWhenDealerDuplication() {
      //given
      final var participants = new Participants();

      //when
      participants.addDealer();
      //then
      assertThatThrownBy(participants::addDealer)
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("하나의 게임에 복수의 딜러는 존재할 수 없습니다.");
    }

    @Test
    @DisplayName("닉네임 명단을 받으면, 참가자를 추가한다")
    void test_add() {
      //given
      final var participants = new Participants();
      final var names = List.of("pobi", "bougi", "uga");
      //when
      participants.add(names);
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
      final var participants = new Participants();
      final var names = List.of("pobi", "pobi");
      //when&then
      assertThatThrownBy(() -> participants.add(names))
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("중복된 닉네임");
    }
  }
}
