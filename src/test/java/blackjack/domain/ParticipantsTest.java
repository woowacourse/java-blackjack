package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("Player만 반환할 수 있어야 한다.")
    void getPlayers_success() {
        // given
        Participants participants = new Participants(new Dealer(), List.of(new Player("glen"), new Player("encho")));

        // when
        List<Person> players = participants.getPlayers();

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
        Person dealer = participants.getDealer();

        // then
        assertThat(dealer)
                .isOfAnyClassIn(Dealer.class);
    }
}
