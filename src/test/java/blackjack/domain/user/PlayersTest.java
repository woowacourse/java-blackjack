package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    @DisplayName("이름별로 참여자들을 생성한다.")
    @Test
    void createUsers() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = Players.of(names);

        assertThat(players).isInstanceOf(Players.class);
    }

    @DisplayName("Users 일급 컬렉션을 반환한다.")
    @Test
    void users() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = Players.of(names);

        int playerCount = players.getPlayers().size();

        assertThat(playerCount).isEqualTo(3);
    }

    @DisplayName("각 사용자들의 모든 카드를 보여준다.")
    @Test
    void showCardsByUsers() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = Players.of(names);
        Cards cards = new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        ));
        players.getPlayers()
                .forEach(player -> player.initializeCards(cards));

        List<Cards> cardsGroup = players.showCardsByUsers();
        boolean isAllRightShow = cardsGroup.stream()
                .allMatch(card -> card.getCards().size() == 2);

        assertThat(isAllRightShow).isTrue();
    }

    @Test
    @DisplayName("게임 진행 가능한 플레이어가 없을 때 현재 플레이어를 가져오려하면 예외가 발생한다.")
    void getCurrentPlayer() {
        List<String> names = Arrays.asList("amazzi","dani");
        Players players = Players.of(names);

        assertThatThrownBy(players::getCurrentPlayer)
                .isInstanceOf(NullPointerException.class);
    }
}
