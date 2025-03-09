package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Nested
    @DisplayName("중복 이름 테스트")
    class CreatePlayerNameTest {

        @Test
        @DisplayName("중복된 이름이 없는 경우 참가자를 생성할 수 있다.")
        void createParticipantsWithNotDuplicate() {
            Dealer dealer = new Dealer();
            List<Player> players = List.of(
                new Player("a"),
                new Player("b"),
                new Player("c")
            );

            assertThatCode(() -> new Participants(dealer, players)).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("중복된 이름이 없는 경우 참가자를 생성할 수 없다.")
        void createParticipantsWithDuplicate() {
            Dealer dealer = new Dealer();
            List<Player> players = List.of(
                new Player("a"),
                new Player("a"),
                new Player("c")
            );

            assertThatThrownBy(() -> new Participants(dealer, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 가진 플레이어가 있습니다.");
        }
    }
}
