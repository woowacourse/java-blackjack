package model.blackjackgame;

import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.ONE;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.SIX;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 2장 지급")
    @Test
    void testDistributeCardsForSetting() {
        Dealer dealer = new Dealer();
        Players players = Players.from(List.of("lily", "jojo"));

        Cards cards = new Cards(
            List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(ONE, HEART),
                new Card(SEVEN, CLOVER), new Card(SIX, DIAMOND), new Card(ONE, SPADE))
        );

        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);
        blackjackGame.distributeCardsForSetting(cards);

        Dealer updatedDealer = blackjackGame.getDealer();
        Players updatedPlayers = blackjackGame.getPlayers();

        assertThat(updatedDealer.cardsSize()).isEqualTo(2);
        updatedPlayers.getPlayers()
            .forEach(player -> assertThat(player.cardsSize()).isEqualTo(2));
    }
}
