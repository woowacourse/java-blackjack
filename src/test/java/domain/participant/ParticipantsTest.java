package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.AbstractTestFixture;
import domain.card.Deck;

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

        assertThat(user.getHand().getCards()).hasSize(1);
        assertThat(user2.getHand().getCards()).hasSize(1);
        assertThat(dealer.getHand().getCards()).hasSize(1);
    }

    static Stream<Arguments> test_find_if_player_is_participant() {
        var dealer = new Dealer();
        var participant = new User("땡칠");
        return Stream.of(
                Arguments.of(createParticipantsFrom(dealer, participant), participant),
                Arguments.of(createParticipantsFrom(dealer, participant), dealer)
        );
    }

    @DisplayName("플레이어를 찾을 수 있다")
    @ParameterizedTest(name = "플레이어를 찾을 수 있다")
    @MethodSource
    void test_find_if_player_is_participant(Participants participants, Player player) {
        assertThat(participants.find(player)).isEqualTo(player);
    }

    @Test
    @DisplayName("참가중이지 않은 플레이어를 찾으면 예외를 던진다")
    void test_dealing_card_to_not_playing_throws() {
        var participant = new User("조이", createCards("10", "2"));
        var notParticipant = new User("참가자아님");

        var participants = createParticipantsFrom(new Dealer(createCards("K")), participant);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> participants.find(notParticipant));
    }
}
