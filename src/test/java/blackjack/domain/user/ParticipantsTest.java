package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.IntStream;
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
                new Player(new PlayerName("a")),
                new Player(new PlayerName("b")),
                new Player(new PlayerName("c"))
            );

            assertThatCode(() -> new Participants(dealer, players)).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("중복된 이름이 없는 경우 참가자를 생성할 수 없다.")
        void createParticipantsWithDuplicate() {
            Dealer dealer = new Dealer();
            List<Player> players = List.of(
                new Player(new PlayerName("a")),
                new Player(new PlayerName("a")),
                new Player(new PlayerName("c"))
            );

            assertThatThrownBy(() -> new Participants(dealer, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 가진 플레이어가 있습니다.");
        }

        @Test
        @DisplayName("25명의 참가자는 게임을 진행할 수 없다.")
        void createParticipantsOver25() {
            Dealer dealer = new Dealer();
            List<Player> players = IntStream.rangeClosed('a', 'z')
                .mapToObj(c -> new Player(new PlayerName(String.valueOf((char) c))))
                .toList();

            assertThatThrownBy(() -> new Participants(dealer, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 25명까지만 참가 가능합니다.");
        }
    }
}
