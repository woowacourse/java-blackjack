package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Deck;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private static final Player PLAYER1 = Player.from("1", 1000);
    private static final Player PLAYER2 = Player.from("2", 2000);
    private static final Player PLAYER3 = Player.from("3", 3000);
    private static final Player PLAYER4 = Player.from("4", 4000);
    private static final Player PLAYER5 = Player.from("5", 5000);

    @DisplayName("최소 한 명 이상의 플레이어가 존재해야 한다.")
    @Test
    void validateTest_countOfPlayersIsZero_throwException() {
        assertThatThrownBy(() -> new Players(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 한 명의 플레이어가 있어야 합니다.");
    }

    @DisplayName("4명 이상의 플레이어를 가지면 예외가 발생한다.")
    @Test
    void validateTest_tooManyPlayers_throwException() {
        List<Player> manyPlayers = List.of(PLAYER1, PLAYER2, PLAYER3, PLAYER4, PLAYER5);

        assertThatThrownBy(() -> new Players(manyPlayers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 4명의 플레이어만 참여 가능합니다.");
    }

    @DisplayName("중복된 이름을 사용하면, 예외가 발생한다.")
    @Test
    void validateTest_whenNameIsDuplicated_throwException() {
        List<Player> duplicatedPlayers = List.of(PLAYER1, PLAYER1, PLAYER2);

        assertThatThrownBy(() -> new Players(duplicatedPlayers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 사용할 수 없습니다.");
    }

    @DisplayName("게임을 시작할 때 모든 플레이어들은 카드를 두 장 뽑는다.")
    @Test
    void drawStartCardsTest() {
        Players players = new Players(List.of(PLAYER1, PLAYER2));
        Deck deck = Deck.createShuffledDeck();

        players.drawStartCards(deck);

        assertAll(
                () -> assertThat(PLAYER1.getCards()).hasSize(2),
                () -> assertThat(PLAYER2.getCards()).hasSize(2)
        );
    }
}
