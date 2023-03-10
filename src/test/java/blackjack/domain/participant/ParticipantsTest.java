package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.Bet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("Player만 반환할 수 있어야 한다.")
    void getPlayers_success() {
        // given
        Dealer dealer = new Dealer(Collections.emptyList());
        List<Player> players = List.of(
                new Player("glen", Collections.emptyList(), Bet.of(1000)),
                new Player("encho", Collections.emptyList(), Bet.of(1000))
        );
        Participants participants = new Participants(dealer, players);

        // when
        List<Player> foundPlayers = participants.getPlayers();

        // then
        assertThat(foundPlayers)
                .hasSize(2)
                .isNotOfAnyClassIn(Dealer.class);
    }

    @Test
    @DisplayName("Dealer만 반환할 수 있어야 한다.")
    void getDealer_success() {
        // given
        Dealer dealer = new Dealer(Collections.emptyList());
        List<Player> players = List.of(
                new Player("glen", Collections.emptyList(), Bet.of(1000)),
                new Player("encho", Collections.emptyList(), Bet.of(1000))
        );
        Participants participants = new Participants(dealer, players);

        // when
        Participant foundDealer = participants.getDealer();

        // then
        assertThat(foundDealer)
                .isOfAnyClassIn(Dealer.class);
    }

    @Test
    @DisplayName("중복된 이름이 있으면 예외가 발생해야 한다.")
    void create_duplicateName() {
        // given
        List<String> names = List.of("glen", "pobi", "glen");
        List<Player> players = names.stream()
                .map(name -> new Player(name, Collections.emptyList(), Bet.of(1000)))
                .collect(Collectors.toList());

        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Participants(new Dealer(Collections.emptyList()), players);
        }).withMessage("[ERROR] 중복된 이름이 있습니다.");
    }
}
