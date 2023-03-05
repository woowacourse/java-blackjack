package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("Player만 반환할 수 있어야 한다.")
    void getPlayers_success() {
        // given
        Participants participants = new Participants(new Dealer(), List.of(new Player("glen"), new Player("encho")));

        // when
        List<Player> players = participants.getPlayers();

        // then
        assertThat(players)
                .hasSize(2)
                .isNotOfAnyClassIn(Dealer.class);
    }

    @Test
    @DisplayName("Dealer만 반환할 수 있어야 한다.")
    void getDealer_success() {
        // given
        Participants participants = new Participants(new Dealer(), List.of(new Player("glen"), new Player("encho")));

        // when
        Participant dealer = participants.getDealer();

        // then
        assertThat(dealer)
                .isOfAnyClassIn(Dealer.class);
    }

    @Test
    @DisplayName("중복된 이름이 있으면 예외가 발생해야 한다.")
    void create_duplicateName() {
        // given
        List<String> names = List.of("glen", "pobi", "glen");
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());

        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Participants(new Dealer(), players);
        }).withMessage("[ERROR] 중복된 이름이 있습니다.");
    }
}
