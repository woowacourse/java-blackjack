package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    @BeforeEach
    void beforeEach() {
        List<String> usernames = List.of("a", "b", "c");
        game = new Game(usernames);
    }

    @Test
    @DisplayName("딜러에게 N장의 카드를 나눠주는 기능 테스트")
    void giveCardToDealerTest() {
        // when
        game.giveCardToDealer(2);
        // then
        List<Card> dealerCards = game.getDealer().getCards();
        assertThat(dealerCards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 플레이어에게 N장의 카드를 나눠주는 기능 테스트")
    void giveCardToPlayerTest() {
        // given
        String username = "a";
        // when
        game.giveCardToPlayer(username, 2);
        // then
        List<Card> playerCards = game.getPlayerCards(username);
        assertThat(playerCards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 참가자에게 카드가 2장씩 분배됐는지 확인합니다.")
    void distributeStartingHandsTest() {
        game.distributeStartingHands();

        assertAll(() -> assertEquals(2, game.getDealer().getCards().size()),
                () -> assertEquals(2, game.getPlayerCards("a").size()),
                () -> assertEquals(2, game.getPlayerCards("b").size()),
                () -> assertEquals(2, game.getPlayerCards("c").size())
        );
    }
}