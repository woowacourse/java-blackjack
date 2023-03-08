package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantsTest extends AbstractTestFixture {
    @Test
    @DisplayName("딜러와 유저를 가진다")
    void test_has_dealer_and_user() {
        var dealer = new Dealer();
        var user = new User("조이");
        var user2 = new User("땡칠");
        var participants = createParticipantsFrom(dealer, user, user2);

        assertThat(participants.getUsers()).containsOnly(user, user2);
        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    @DisplayName("카드를 한 장씩 돌린다")
    void test_draw_from_deck() {
        var dealer = new Dealer();
        var user = new User("조이");
        var user2 = new User("땡칠");
        var participants = createParticipantsFrom(dealer, user, user2);

        participants.dealFrom(new Deck());

        assertThat(user.hand().cards()).hasSize(1);
        assertThat(user2.hand().cards()).hasSize(1);
        assertThat(dealer.hand().cards()).hasSize(1);
    }

    static Stream<Arguments> test_find_if_player_is_participant() {
        var dealer = new Dealer();
        var notParticipant = new User("조이");
        var participant = new User("땡칠");
        var participants = createParticipantsFrom(dealer, participant);
        return Stream.of(
                Arguments.of(participants, participant, true),
                Arguments.of(participants, dealer, true),
                Arguments.of(participants, notParticipant, false),
                Arguments.of(participants, new Dealer(), false)
        );
    }

    @DisplayName("플레이어가 참가자인지 알 수 있다")
    @ParameterizedTest(name = "플레이어가 참가자인지 알 수 있다")
    @MethodSource
    void test_find_if_player_is_participant(Participants participants, Player player, boolean doesExist) {
        assertThat(participants.has(player)).isEqualTo(doesExist);
    }
}
