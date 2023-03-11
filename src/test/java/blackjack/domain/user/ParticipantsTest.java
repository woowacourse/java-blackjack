package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.user.exception.DuplicatePlayerNameException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("이름들을 받아 딜러를 포함하여 players를 생성한다")
    void createPlayersTest() {
        List<String> names = List.of("jamie", "boxster");

        Participants participants = Participants.from(names);

        assertAll(
                () -> assertThat(participants.getPlayers())
                        .extracting(Player::getName).contains("jamie", "boxster"),
                () -> assertThat(participants.getDealer().getName()).isEqualTo("딜러")
        );
    }

    @Test
    @DisplayName("중복인 이름이 있는 경우 예외를 반환한다")
    void duplicateNamesTest() {
        List<String> names = List.of("jamie", "jamie");

        assertThatThrownBy(() -> Participants.from(names))
                .isInstanceOf(DuplicatePlayerNameException.class);
    }
}
