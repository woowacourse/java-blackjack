package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private static final int ONE_HUNDRED_THOUSAND = 100_000;

    @Test
    @DisplayName("플레이어 참가 제한에 맞아야 한다.")
    public void 플레이어_수가_제한_범위_이내라면_성공() {
        // given
        final List<Player> players = new ArrayList<>();
        for (int i = 1; i <= Participants.MAXIMUM_BOUND; i++) {
            players.add(new Player(new Name(String.format("포비%d", i)), ONE_HUNDRED_THOUSAND));
        }

        // then
        assertThat(new Participants(players))
                .isInstanceOf(Participants.class);
    }

    @Test
    @DisplayName("플레이어 참가 수는 범위가 제한되어 있다.")
    public void 플레이어_수가_제한_범위를_초과하면_실패() {
        // given
        final List<Player> players = new ArrayList<>();
        for (int i = 1; i <= Participants.MAXIMUM_BOUND + 1; i++) {
            players.add(new Player(new Name(String.format("포비%d", i)), ONE_HUNDRED_THOUSAND));
        }

        // then
        assertThatThrownBy(() -> new Participants(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 이름은 서로 중복될 수 없다.")
    public void 중복된_플레이어_이름이_있으면_실패() {
        // given
        final String pobi = "포비";
        final List<Player> duplicatedNamePlayers = List.of(
                new Player(new Name(pobi), ONE_HUNDRED_THOUSAND),
                new Player(new Name(pobi), ONE_HUNDRED_THOUSAND)
        );

        // then
        assertThatThrownBy(() -> new Participants(duplicatedNamePlayers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 이름은 딜러 이름과 겹칠 수 없다.")
    public void 플레이어_이름이_딜러_이름과_같으면_실패() {
        // given
        final List<Player> playerWithDealerName = List.of(
                new Player(new Name(Dealer.DEALER_NAME), ONE_HUNDRED_THOUSAND));

        // then
        assertThatThrownBy(() -> new Participants(playerWithDealerName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
