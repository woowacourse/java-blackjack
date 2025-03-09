package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    @BeforeEach
    void beforeEach() {
        List<PlayerName> usernames = Stream.of("a", "b", "c").map(PlayerName::new).toList();
        game = Game.initialize(usernames);
    }

    @Test
    @DisplayName("딜러에게 N장의 카드를 나눠주는 기능 테스트")
    void giveCardToDealerTest() {
        // when
        // then
        List<Card> dealerCards = game.getDealer().getCards();
        assertThat(dealerCards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 플레이어에게 N장의 카드를 나눠주는 기능 테스트")
    void giveCardToPlayerTest() {
        // given
        PlayerName username = new PlayerName("a");
        // when
        // then
        List<Card> playerCards = game.getPlayerCards(username);
        assertThat(playerCards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 참가자에게 카드가 2장씩 분배됐는지 확인합니다.")
    void distributeStartingHandsTest() {
        assertAll(() -> assertEquals(2, game.getDealer().getCards().size()),
                () -> assertEquals(2, game.getPlayerCards(new PlayerName("a")).size()),
                () -> assertEquals(2, game.getPlayerCards(new PlayerName("b")).size()),
                () -> assertEquals(2, game.getPlayerCards(new PlayerName("c")).size())
        );
    }
}