package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    @BeforeEach
    void beforeEach() {
        List<PlayerName> usernames = Stream.of("a", "b", "c").map(PlayerName::new).toList();
        List<BettingMoney> bettingMonies = Stream.of(10000, 10000, 10000).map(BettingMoney::new).toList();

        game = Game.initialize(usernames, bettingMonies);
    }

    @Test
    @DisplayName("딜러에게 N장의 카드를 나눠주는 기능 테스트")
    void giveCardToDealerTest() {
        // when
        // then
        Cards dealerCards = game.getDealer().getCards();
        Assertions.assertThat(dealerCards.isInitialHands()).isTrue();
    }

    @Test
    @DisplayName("특정 플레이어에게 N장의 카드를 나눠주는 기능 테스트")
    void giveCardToPlayerTest() {
        // given
        PlayerName username = new PlayerName("a");
        // when
        // then
        Cards playerCards = game.getPlayerCards(username);
        assertTrue(playerCards.isInitialHands());
    }

    @Test
    @DisplayName("초기 참가자에게 카드가 2장씩 분배됐는지 확인합니다.")
    void distributeStartingHandsTest() {
        assertAll(() -> assertTrue(game.getDealer().getCards().isInitialHands()),
                () -> assertTrue(game.getPlayerCards(new PlayerName("a")).isInitialHands()),
                () -> assertTrue(game.getPlayerCards(new PlayerName("b")).isInitialHands()),
                () -> assertTrue(game.getPlayerCards(new PlayerName("c")).isInitialHands())
        );
    }
}
