package blackjack.domain.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantsTest {

    Participants participants = new Participants(new Dealer(), new Players(List.of("glen", "encho")), new BettingMoney(null));

    @Test
    @DisplayName("Player만 반환할 수 있어야 한다.")
    void getPlayers_success() {
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
        // when
        Person dealer = participants.getDealer();

        // then
        assertThat(dealer)
                .isOfAnyClassIn(Dealer.class);
    }

    @Test
    @DisplayName("파라미터로 전달받은 이름과 일치하는 이름을 가진 Person 객체를 반환할 수 있다.")
    void findByName_success() {
        // when
        Person encho = participants.findByName("encho");

        // then
        assertThat(encho.getName())
                .isEqualTo("encho");
    }
}
