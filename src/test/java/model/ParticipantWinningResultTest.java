package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantWinningResultTest {

    @Test
    @DisplayName("딜러가 버스트되고 플레이어의 경우 버스트가 아닌 경우 플레이어가 승리한다")
    void decideDealerWinning() {
        Dealer dealer = new Dealer();
        Player player = new Player("moda");
        Players players = new Players(List.of(player));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));

        player.receiveCard(new Card(CardRank.FIVE, CardSuit.DIAMOND));
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        GameResult result = participantWinningResult.getResult().get(player);
        GameResult expect =GameResult.WIN;
        assertEquals(expect, result);
    }
}