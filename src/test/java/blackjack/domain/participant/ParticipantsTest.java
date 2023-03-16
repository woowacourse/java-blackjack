package blackjack.domain.participant;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ParticipantsTest {

    Dealer dealer;

    @BeforeEach
    void setting() {
        dealer = Dealer.from(new ArrayList<>());
    }

    @Test
    @DisplayName("참가자들이 존재하지 않을 때 예외를 던지는지 테스트")
    void throwExceptionWhenEmptyNames() {
        final List<String> names = List.of();

        Assertions.assertThatThrownBy(() -> Participants.of(dealer, names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자들이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("중복된 이름이 없을 경우 참여자들이 정상적으로 생성되는지 테스트")
    void nonDuplicateNameTest() {
        final List<String> names = List.of("pobi", "crong", "eddy");

        assertThatNoException().isThrownBy(() -> Participants.of(dealer, names));
    }

    @Test
    @DisplayName("중복된 이름이 존재하는 경우 예외를 던지는지 테스트")
    void throwExceptionWhenExistDuplicateName() {
        final List<String> names = List.of("pobi", "crong", "crong");

        Assertions.assertThatThrownBy(() -> Participants.of(dealer, names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 존재합니다.");
    }

    @Test
    @DisplayName("모든 참여자를 반환하는 테스트")
    void getAllTest() {
        final List<String> playerNames = List.of("pobi", "crong");
        final Participants participants = Participants.of(dealer, playerNames);
        final List<Participant> expected = List.of(dealer,
                Player.of(Name.from("pobi"), new ArrayList<>()),
                Player.of(Name.from("crong"), new ArrayList<>()));

        Assertions.assertThat(participants.getAll()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러를 반환하는 테스트")
    void getDealerTest() {
        final List<String> playerNames = List.of("pobi", "crong");
        final Participants participants = Participants.of(dealer, playerNames);

        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    @DisplayName("플레이어들을 반환하는 테스트")
    void getPlayersTest() {
        final List<String> playerNames = List.of("pobi", "crong");
        final Participants participants = Participants.of(dealer, playerNames);
        final Player expected = Player.of(Name.from("pobi"), new ArrayList<>());

        assertThat(participants.getPlayers().get(0).getClass()).isEqualTo(expected.getClass());
        assertThat(participants.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 이름을 반환하는 테스트")
    void getDealerNameTest() {
        // given
        final List<String> playerNames = List.of("pobi", "crong");
        final Participants participants = Participants.of(dealer, playerNames);
        final String expected = "딜러";

        // when
        final String actual = participants.getDealer().getName();

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("참여자들의 이름을 반환하는 테스트")
    void getNamesTest() {
        final List<String> playerNames = List.of("pobi", "crong");
        final Participants participants = Participants.of(dealer, playerNames);

        Assertions.assertThat(participants.getPlayerNames()).contains("딜러", "pobi", "crong");
    }
}
