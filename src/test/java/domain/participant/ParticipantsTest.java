package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    private final int betAmount = 1;

    @Test
    void 플레이어_수가_최솟값보다_작을_경우_예외가_발생한다() {
        List<Player> players = Collections.emptyList();

        assertThatThrownBy(() -> new Participants(players))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어_수가_최댓값보다_클_경우_예외가_발생한다() {
        List<Player> players = List.of(
                new Player("prin", betAmount),
                new Player("roro", betAmount),
                new Player("pobi", betAmount),
                new Player("crong", betAmount),
                new Player("tomi", betAmount),
                new Player("neo", betAmount),
                new Player("young", betAmount)
        );

        assertThatThrownBy(() -> new Participants(players))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 중복된_플레이어가_있을_경우_예외가_발생한다() {
        List<Player> players = List.of(
                new Player("prin", betAmount),
                new Player("prin", betAmount)
        );

        assertThatThrownBy(() -> new Participants(players))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 플레이어가 존재합니다");
    }

    @Test
    void 딜러를_리턴한다() {
        List<Player> players = List.of(
                new Player("prin", betAmount),
                new Player("roro", betAmount)
        );
        Participants participants = new Participants(players);

        assertThat(participants.getDealer()).isExactlyInstanceOf(Dealer.class);
    }

    @Test
    void 딜러를_포함하지_않은_플레이어_리스트를_리턴한다() {
        List<Player> players = List.of(
                new Player("prin", betAmount),
                new Player("roro", betAmount)
        );
        Participants participants = new Participants(players);

        assertThat(participants.getPlayers()).containsExactlyElementsOf(players);
    }
}
