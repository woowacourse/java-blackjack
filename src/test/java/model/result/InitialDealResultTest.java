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
    @DisplayName("최초 카드 분배 후 플레이어만 블랙잭 경우 해당 플레이어는 승리한다.")
    void playerWinsAllWhenOnlyPlayerBlackjack() {
        //given
        player1.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        player1.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));
        player2.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        player2.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FIVE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));

        //when
        InitialDealResult initialDealResult = InitialDealResult.from(dealer, players);

        //then
        assertThat(initialDealResult.findWinnerPlayers()).containsExactly(player1);
    }

    @Test
    @DisplayName("최초 카드 분배 후 딜러만 블랙잭일 경우 딜러가 전체 승리한다.")
    void dealerWinsAllWhenOnlyDealerBlackjack() {
        //given
        player1.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player1.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));
        player2.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        player2.receiveCard(new Card(CardRank.FIVE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));

        //when
        InitialDealResult initialDealResult = InitialDealResult.from(dealer, players);

        //then
        assertThat(initialDealResult.findWinnerPlayers()).isEmpty();
        assertThat(initialDealResult.findLoserPlayers()).containsAll(List.of(player1, player2));
    }

    @Test
    @DisplayName("최초 카드 분배 후 딜러와 플레이어가 모두 블랙잭일 경우 블랙잭인 플레이어는 승리하고, 나머지 플레이어는 패배한다.")
    void blackjackPlayerWinsWhenPlayerAndDealerBlackjack() {
        //given
        player1.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        player1.receiveCard(new Card(CardRank.JACK, CardSuit.CLOVER));
        player2.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        player2.receiveCard(new Card(CardRank.FIVE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));

        //when
        InitialDealResult initialDealResult = InitialDealResult.from(dealer, players);

        //then
        assertThat(initialDealResult.findWinnerPlayers()).containsExactly(player1);
        assertThat(initialDealResult.findLoserPlayers()).containsExactly(player2);
    }
}