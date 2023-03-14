package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantsTest {

    @DisplayName("중복된 이름은 사용할 수 없다")
    @Test
    void player_duplicate_throw() {
        Player player1 = new Player("maco", 10000);
        Player player2 = new Player("maco", 20000);
        Dealer dealer = new Dealer("딜러");
        assertThatThrownBy(() -> new Participants(dealer, List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복이 존재하지 않으면 참여자가 생성된다")
    @Test
    void player_notDuplicate_notThrow() {
        Player player1 = new Player("maco", 10000);
        Player player2 = new Player("maco2", 20000);
        Dealer dealer = new Dealer("딜러");

        assertThatCode(() -> new Participants(dealer, List.of(player1, player2)))
                .doesNotThrowAnyException();
    }

}
