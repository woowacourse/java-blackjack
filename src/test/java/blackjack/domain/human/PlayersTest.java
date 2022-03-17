package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.cards.CardDeck;
import blackjack.domain.human.humans.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어 모음 생성 기능 검사")
    public void createTest() {
        Player player = Player.from("test");
        Players players = Players.from(List.of(player));
        assertThat(players.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어 모음 생성 기능 예외처리 검사")
    public void createErrorTest() {
        assertAll(
                () -> assertThatThrownBy( () -> Players.fromNames(List.of("test","test")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이름은 중복될 수 없습니다.") ,
                () -> assertThatThrownBy( () -> Players.from(List.of(Player.from("test"),Player.from("test"))))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이름은 중복될 수 없습니다.")
        );
    }

    @Test
    @DisplayName("복사 후 반환기능 검사")
    public void getTest() {
        // given
        Player player1 = Player.from("pobi");
        Player player2 = Player.from("jason");
        Players players = Players.from(List.of(player1, player2));
        CardDeck cardDeck = CardDeck.newInstance();

        // when
        player2 = Player.from("제이슨");
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);

        // then
        assertThat(players.get())
                .extracting("name")
                .contains("pobi", "jason");
    }

    @Test
    @DisplayName("플레이어들 카드 배분 기능 검사")
    public void giveCardTest() {
        // given
        Player player1 = Player.from("pobi");
        Player player2 = Player.from("jason");
        Players players = Players.from(List.of(player1, player2));
        CardDeck cardDeck = CardDeck.newInstance();

        // when
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);

        // then
        assertThat(player1.getCards().size()).isEqualTo(2);
        assertThat(player2.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어들 이름 반환 기능 검사")
    public void getPlayerNamesTest() {
        // given
        Player player1 = Player.from("pobi");
        Player player2 = Player.from("jason");
        Players players = Players.from(List.of(player1, player2));
        CardDeck cardDeck = CardDeck.newInstance();

        // when
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);

        // then
        assertThat(players.getPlayerNames()).contains("pobi", "jason");
    }
}
