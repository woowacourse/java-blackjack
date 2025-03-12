package blackjack.model.participant;

import static blackjack.model.card.CardFixtures.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.CardValue;
import blackjack.model.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들 테스트")
class GamePlayersTest {

    @DisplayName("플레이어 이름은 중복될 수 없다.")
    @Test
    void shouldThrowException_WhenDuplicatePlayerName() {
        // given
        Name belloName = new Name("벨로");
        Player bello1 = new Player(belloName);
        Player bello2 = new Player(belloName);

        // when, then
        assertThatCode(() -> GamePlayers.createForNewGame(List.of(bello1, bello2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("참가할 플레이어가 없는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenNoPlayer() {
        // when, then
        assertThatCode(() -> GamePlayers.createForNewGame(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 1명 이상의 플레이어가 있어야 합니다.");
    }

    @DisplayName("참가자가 이미 패를 가지고 있는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenHasHand() {
        // given
        Player bello = new Player(new Name("벨로"));
        bello.receiveHand(createCard(Suit.SPADES, CardValue.TEN));

        // when, then
        assertThatCode(() -> GamePlayers.createForNewGame(List.of(bello)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 패를 가진 플레이어가 존재합니다.");
    }

    @DisplayName("참가한 플레이어 수를 알 수 있다.")
    @Test
    void participantCountTest() {
        // given
        Player bello = new Player(new Name("벨로"));
        Player pobi = new Player(new Name("포비"));

        // when
        GamePlayers gamePlayers = GamePlayers.createForNewGame(List.of(bello, pobi));

        //then
        assertThat(gamePlayers.participantCount())
            .isSameAs(2);
    }

    @DisplayName("게임에 참가한 플레이어 목록을 조회할 때 반환 결과를 수정할 수 없다.")
    @Test
    void getGamePlayersImmutableTest() {
        // given
        Player bello = new Player(new Name("벨로"));
        Player pobi = new Player(new Name("포비"));
        GamePlayers gamePlayers = GamePlayers.createForNewGame(List.of(bello, pobi));

        // when
        List<Player> players = gamePlayers.getPlayers();

        //then
        assertThatCode(() -> players.add(new Player(new Name("네오"))))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
