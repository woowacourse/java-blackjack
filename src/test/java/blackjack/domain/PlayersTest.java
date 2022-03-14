package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Players;
import blackjack.dto.CurrentCardsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("7개 이상의 이름을 입력한 경우 예외가 발생한다.")
    void tooManyPlayers() {
        assertThatThrownBy(() -> new Players(new String[]{"a", "b", "c", "d", "e", "f", "g", "h"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최대")
                .hasMessageContaining("명까지 플레이 가능합니다.");
    }

    @Test
    @DisplayName("중복되는 이름을 입력한 경우 예외가 발생한다.")
    void duplicateNames() {
        assertThatThrownBy(() -> new Players(new String[]{"a", "b", "c", "c"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복되는 이름은 허용되지 않습니다.");
    }

    @Test
    @DisplayName("입력한 이름 개수만큼 플레이어를 생성한다.")
    void createPlayers() {
        Players players = new Players(new String[]{"a", "b", "c"});
        assertThat(players.getPlayers().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("모든 플레이어에게 카드를 추가하면 모든 플레이어의 카드 개수가 1개 증가한다.")
    void addForAllPlayers() {
        Players players = new Players(new String[]{"a", "b", "c"});
        players.addForAllPlayers(new Deck());

        for (CurrentCardsDto currentCardsDTO : players.generateCurrentCardsDTO()) {
            assertThat(currentCardsDTO.getCards().size()).isEqualTo(1);
        }
    }
}
