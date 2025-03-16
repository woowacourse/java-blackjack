package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.participants.BettingAmount;
import domain.participants.PlayerName;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    @BeforeEach
    void beforeEach() {
        Map<PlayerName, BettingAmount> players = Map.of(
                new PlayerName("a"),new BettingAmount(10000),
                new PlayerName("b"),new BettingAmount(10000),
                new PlayerName("c"),new BettingAmount(10000)
        );
        game = new Game(players);
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
        PlayerName username = new PlayerName("a");
        // when
        game.giveCardToPlayer(username, 2);
        // then
        List<Card> playerCards = game.getPlayerCards(username);
        assertThat(playerCards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 참가자에게 카드가 2장씩 분배됐는지 확인합니다.")
    void distributeStartingHandsTest() {
        // when
        game.distributeStartingHands();
        // then
        assertThat(game.getDealer().getCards().size()).isEqualTo(2);
        assertThat(game.getPlayerCards(new PlayerName("a")).size()).isEqualTo(2);
        assertThat(game.getPlayerCards(new PlayerName("b")).size()).isEqualTo(2);
        assertThat(game.getPlayerCards(new PlayerName("c")).size()).isEqualTo(2);
    }
}