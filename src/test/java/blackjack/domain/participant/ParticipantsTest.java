package blackjack.domain.participant;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자들이 존재하지 않을 때 예외를 던지는지 테스트")
    void throwExceptionWhenEmptyNames() {
        List<String> names = List.of();

        Assertions.assertThatThrownBy(() -> new Participants(new Dealer(), names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자들이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("중복된 이름이 없을 경우 참여자들이 정상적으로 생성되는지 테스트")
    void nonDuplicateNameTest() {
        List<String> names = List.of("pobi", "crong", "eddy");

        assertThatNoException().isThrownBy(() -> new Participants(new Dealer(), names));
    }

    @Test
    @DisplayName("중복된 이름이 존재하는 경우 예외를 던지는지 테스트")
    void throwExceptionWhenExistDuplicateName() {
        List<String> names = List.of("pobi", "crong", "crong");

        Assertions.assertThatThrownBy(() -> new Participants(new Dealer(), names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 존재합니다.");
    }

    @Test
    @DisplayName("딜러를 반환하는 테스트")
    void getDealerTest() {
        Dealer dealer = new Dealer();
        List<String> playerNames = List.of("pobi", "crong");
        Participants participants = new Participants(dealer, playerNames);

        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    @DisplayName("플레이어들을 반환하는 테스트")
    void getPlayersTest() {
        Dealer dealer = new Dealer();
        List<String> playerNames = List.of("pobi", "crong");
        Participants participants = new Participants(dealer, playerNames);
        Player expected = new Player(new Name("pobi"));
        assertThat(participants.getPlayers().get(0).getClass()).isEqualTo(expected.getClass());
        assertThat(participants.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어들의 이름을 반환하는 테스트")
    void getPlayerNames() {
        Dealer dealer = new Dealer();
        List<String> playerNames = List.of("pobi", "crong");
        Participants participants = new Participants(dealer, playerNames);

        Assertions.assertThat(participants.getPlayerNames()).contains("pobi", "crong");
    }
}
