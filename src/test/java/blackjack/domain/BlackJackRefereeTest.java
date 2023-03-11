package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.BlackJackReferee;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Amount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackJackRefereeTest {

    @Test
    @DisplayName("딜러가 처음의 받은 2장의 카드가 블랙잭일 Win반환")
    void judgmentPlayerBlackjack() {
        //given
        BlackJackReferee referee = new BlackJackReferee();
        Dealer dealer = new Dealer();
        Player player = new Player(new ParticipantName("비버"), new Amount("1000"));
        List<Card> dealerCards = List.of(
                new Card(CardNumber.QUEEN, CardSuit.CLUB),
                new Card(CardNumber.ACE, CardSuit.SPADE)
        );
        List<Card> playerCards = List.of(
                new Card(CardNumber.FIVE, CardSuit.CLUB),
                new Card(CardNumber.ACE, CardSuit.SPADE)
        );
        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        //when
        referee.createResult(dealer, player);
        Map<Player, WinningResult> result = referee.getPlayerWinningResult();

        //then
        assertThat(result.get(player)).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 처음의 받은 2장의 카드가 블랙잭일 Lose반환")
    void judgmentDealerBlackjack() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new ParticipantName("비버"), new Amount("1000"));
        BlackJackReferee referee = new BlackJackReferee();
        List<Card> dealerCards = List.of(
                new Card(CardNumber.FIVE, CardSuit.CLUB),
                new Card(CardNumber.ACE, CardSuit.SPADE)
        );
        List<Card> playerCards = List.of(
                new Card(CardNumber.QUEEN, CardSuit.CLUB),
                new Card(CardNumber.ACE, CardSuit.SPADE)

        );
        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        //when
        referee.createResult(dealer, player);
        Map<Player,WinningResult> result = referee.getPlayerWinningResult();

        //then
        assertThat(result.get(player)).isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러가 처음의 받은 2장의 카드가 블랙잭일 Push 반환")
    void judgmentAllBlackjack() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new ParticipantName("비버"), new Amount("1000"));
        BlackJackReferee referee = new BlackJackReferee();
        List<Card> dealerCards = List.of(
                new Card(CardNumber.JACK, CardSuit.CLUB),
                new Card(CardNumber.ACE, CardSuit.SPADE)
        );
        List<Card> playerCards = List.of(
                new Card(CardNumber.QUEEN, CardSuit.CLUB),
                new Card(CardNumber.ACE, CardSuit.SPADE)

        );
        for (Card card : dealerCards) {
            dealer.hit(card);
        }
        for (Card card : playerCards) {
            player.hit(card);
        }
        //when
        referee.createResult(dealer, player);
        Map<Player,WinningResult> result = referee.getPlayerWinningResult();

        //then
        assertThat(result.get(player)).isEqualTo(WinningResult.PUSH);
    }

    @Test
    @DisplayName("딜러가 플레이어를 이길 시 WIN 리턴")
    void judgeWinner() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new ParticipantName("홍실"), new Amount("1000"));
        BlackJackReferee referee = new BlackJackReferee();
        dealer.hit(new Card(CardNumber.TEN, CardSuit.DIAMOND));
        dealer.hit(new Card(CardNumber.TEN, CardSuit.DIAMOND));
        player.hit(new Card(CardNumber.TWO, CardSuit.SPADE));
        player.hit(new Card(CardNumber.TWO, CardSuit.SPADE));

        //when
        referee.createResult(dealer, player);
        Map<Player,WinningResult> result = referee.getPlayerWinningResult();

        //then
        assertThat(result.get(player)).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 플레이어에게 졌을 시 LOSE 리턴")
    void judgeWinner2() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new ParticipantName("홍실"), new Amount("1000"));
        BlackJackReferee referee = new BlackJackReferee();
        dealer.hit(new Card(CardNumber.TWO, CardSuit.SPADE));
        dealer.hit(new Card(CardNumber.TWO, CardSuit.SPADE));
        player.hit(new Card(CardNumber.TEN, CardSuit.DIAMOND));
        player.hit(new Card(CardNumber.TEN, CardSuit.DIAMOND));

        //when
        referee.createResult(dealer, player);
        Map<Player,WinningResult> result = referee.getPlayerWinningResult();

        //then
        assertThat(result.get(player)).isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러가 플레이어와 같은 값을 가질 시 PUSH 리턴")
    void judgeWinner3() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new ParticipantName("홍실"), new Amount("1000"));
        BlackJackReferee referee = new BlackJackReferee();
        dealer.hit(new Card(CardNumber.NINE, CardSuit.SPADE));
        dealer.hit(new Card(CardNumber.EIGHT, CardSuit.SPADE));
        player.hit(new Card(CardNumber.ACE, CardSuit.DIAMOND));
        player.hit(new Card(CardNumber.SIX, CardSuit.DIAMOND));

        //when
        referee.createResult(dealer, player);
        Map<Player,WinningResult> result = referee.getPlayerWinningResult();

        //then
        assertThat(result.get(player)).isEqualTo(WinningResult.PUSH);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 블랙잭일 때 PUSH 리턴")
    void judgeWinner4() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new ParticipantName("홍실"), new Amount("1000"));
        BlackJackReferee referee = new BlackJackReferee();
        dealer.hit(new Card(CardNumber.ACE, CardSuit.SPADE));
        dealer.hit(new Card(CardNumber.TEN, CardSuit.SPADE));
        player.hit(new Card(CardNumber.ACE, CardSuit.DIAMOND));
        player.hit(new Card(CardNumber.QUEEN, CardSuit.DIAMOND));

        //when
        referee.createResult(dealer, player);
        Map<Player,WinningResult> result = referee.getPlayerWinningResult();

        //then
        assertThat(result.get(player)).isEqualTo(WinningResult.PUSH);
    }

}