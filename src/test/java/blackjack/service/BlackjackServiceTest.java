package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.participant.Players;
import blackjack.model.GameState;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackServiceTest {

    BlackjackService blackjackService = new BlackjackService();

    PickStrategy mustPickFive = cards -> Card.createOpenedCard(Rank.FIVE, Suit.CLOVER);
    PickStrategy mustPickTen = cards -> Card.createOpenedCard(Rank.TEN, Suit.CLOVER);

    @DisplayName("딜러와 플레이어들에게 카드를 2 장씩 분배한다.")
    @Test
    void distributeInitialCards() {
        //given
        GameState gameState = blackjackService.setUp(
                List.of("player1", "player2", "player3"),
                List.of(1000, 1000, 1000),
                mustPickFive
        );

        //when
        Players distributedPlayers = blackjackService.distributeInitialCards(gameState);

        //then
        GameState distributed = gameState.updatePlayers(distributedPlayers);

        Players players = distributed.players();
        players.perform(player ->
                assertThat(player.getAllCard().size())
                        .isEqualTo(2)
        );

        assertThat(distributed.dealer().getAllCard().size())
                .isEqualTo(2);
    }


    @DisplayName("각 플레이어들에 대해 consumer를 수행한다.")
    @Test
    void forEachNotBustPlayers() {
        //given
        GameState gameState = blackjackService.setUp(
                List.of("player1"),
                List.of(1000),
                mustPickTen
        );
        Players distributedPlayers = blackjackService.distributeInitialCards(gameState);    //10 + 10 = 20

        CardDeck mustPickFive = CardDeck.of(this.mustPickFive);
        AtomicInteger pickedCount = new AtomicInteger();

        //when
        blackjackService.forEach(
                distributedPlayers,
                player -> {
                    pickedCount.getAndIncrement();
                    player.pickAdditionalCard(mustPickFive);
                }
        );  //20 + 5 = 25

        //then
        assertThat(pickedCount.getAndIncrement()).isEqualTo(1);
    }

    @DisplayName("딜러가 추가로 카드를 받응 횟수를 반환한다.")
    @Test
    void drawDealerCardUntilSeventeen() {
        //given
        GameState gameState = blackjackService.setUp(
                List.of("player1", "player2", "player3"),
                List.of(1000, 1000, 1000),
                mustPickFive
        );

        blackjackService.distributeInitialCards(gameState);

        //when
        assertThat(blackjackService.drawDealerCardUntilSeventeen(gameState))
                .isEqualTo(2);
    }
}