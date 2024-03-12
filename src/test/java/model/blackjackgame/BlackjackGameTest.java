package model.blackjackgame;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.SIX;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.card.Card;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 2장 지급")
    @Test
    void testInitHand() {
        Dealer dealer = new Dealer();
        Players players = Players.from(List.of("lily", "jojo"));
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);
        List<Card> cards = List.of(
            new Card(JACK, DIAMOND), new Card(FIVE, CLOVER),
            new Card(ACE, HEART), new Card(SEVEN, CLOVER),
            new Card(SIX, DIAMOND), new Card(ACE, SPADE)
        );

        blackjackGame.initHand(cards);

        Dealer updatedDealer = blackjackGame.getDealer();
        List<Player> updatedPlayers = blackjackGame.getPlayerGroup();

        assertAll(
            () -> assertEquals(2, updatedDealer.handSize()),
            () -> updatedPlayers.forEach(player -> assertEquals(2, player.handSize()))
        );
    }
}
