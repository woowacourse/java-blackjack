package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.Player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerGameResultTest {
    @Test
    @DisplayName("플레이어와 딜러 모두 버스트가 아니라면, 딜러보다 21에 가까워야 이긴다.")
    void playerNumberWinTest() {
        // Given
        HandCards playerHands = new HandCards(
                List.of(new Card(CardNumber.ACE, CardShape.CLUB), new Card(CardNumber.JACK, CardShape.DIAMOND))
        );
        HandCards dealerHands = new HandCards(
                List.of(new Card(CardNumber.KING, CardShape.HEART), new Card(CardNumber.JACK, CardShape.SPADE))
        );

        Player player = new Player("플레이어 1", playerHands);
        Dealer dealer = new Dealer(dealerHands);

        // When
        PlayerGameResult playerResult = PlayerGameResult.from(player, dealer);

        // Then
        assertThat(playerResult).isEqualTo(PlayerGameResult.WIN);
    }


    @Test
    @DisplayName("플레이어가 버스트이고 딜러가 버스트가 아니라면 플레이어가 패배한다.")
    void playerBustLoseTest() {
        // Given
        HandCards playerHands = new HandCards(
                List.of(new Card(CardNumber.TWO, CardShape.CLUB), new Card(CardNumber.JACK, CardShape.DIAMOND), new Card(CardNumber.QUEEN, CardShape.CLUB))
        );
        HandCards dealerHands = new HandCards(
                List.of(new Card(CardNumber.KING, CardShape.HEART), new Card(CardNumber.JACK, CardShape.SPADE))
        );

        Player player = new Player("플레이어 1", playerHands);
        Dealer dealer = new Dealer(dealerHands);

        // When
        PlayerGameResult playerResult = PlayerGameResult.from(player, dealer);

        // Then
        assertThat(playerResult).isEqualTo(PlayerGameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 버스트라면 무승부이다.")
    void playerWinningTest() {
        // Given
        HandCards playerHands = new HandCards(
                List.of(new Card(CardNumber.TWO, CardShape.CLUB), new Card(CardNumber.JACK, CardShape.DIAMOND), new Card(CardNumber.QUEEN, CardShape.CLUB))
        );
        HandCards dealerHands = new HandCards(
                List.of(new Card(CardNumber.KING, CardShape.HEART), new Card(CardNumber.JACK, CardShape.SPADE), new Card(CardNumber.EIGHT, CardShape.CLUB))
        );

        Player player = new Player("플레이어 1", playerHands);
        Dealer dealer = new Dealer(dealerHands);

        // When
        PlayerGameResult playerResult = PlayerGameResult.from(player, dealer);

        // Then
        assertThat(playerResult).isEqualTo(PlayerGameResult.DRAW);
    }
}