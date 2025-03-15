package model.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialDealResultTest {

    private static String name = "pobi";
    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Players players;

    @BeforeEach
    void setUp() {
        players = Players.from(List.of("moda", "pobi"));
        dealer = new Dealer();
        player1 = players.getPlayers().getFirst();
        player2 = players.getPlayers().getLast();
    }

    @Test
    @DisplayName("최초 카드 분배 후 플레이어만 블랙잭 경우 해당 플레이어는 승리한다..")
    void playerLoseWhenOnlyPlayerBurst() {
        //given
        player1.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        player1.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FIVE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));

        //when
        InitialDealResult initialDealResult = InitialDealResult.from(dealer, players);

        //then
        assertThat(initialDealResult.findWinnerPlayers()).containsExactly(player1);
    }

}