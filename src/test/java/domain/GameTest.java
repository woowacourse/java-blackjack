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
        List<Card> dealerCards = game.getDealerCards();
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

        assertAll(() -> assertEquals(2, game.getDealerCards().size()),
                () -> assertEquals(2, game.getPlayerCards("a").size()),
                () -> assertEquals(2, game.getPlayerCards("b").size()),
                () -> assertEquals(2, game.getPlayerCards("c").size())
        );
    }

//    @Test
//    @DisplayName("특정 플레이어가 카드를 더 받을 수 있는지 테스트한다.")
//    void canGetMoreCardTest() {
//        // given
//        String username = "a";
//        // when
//        boolean canGetMoreCard = game.canGetMoreCard(username);
//        // then
//        assertThat(canGetMoreCard).isTrue();
//    }
}