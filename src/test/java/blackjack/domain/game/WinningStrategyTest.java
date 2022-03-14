package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.winningstrategy.BlackjackWinningStrategy;
import blackjack.domain.game.winningstrategy.FinalWinningStrategy;
import blackjack.domain.game.winningstrategy.PlayingWinningStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class WinningStrategyTest {

    @Test
    @DisplayName("딜러가 블랙잭인 경우 플레이어가 블랙잭이 아닌 이상 패배")
    void getLoseResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.CLOVER, Denomination.TEN));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TWO),
            new Card(Suit.SPADE, Denomination.EIGHT));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(new BlackjackWinningStrategy().getResult(dealer, player))
            .isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 블랙잭이면 무승부")
    void getDrawResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.CLOVER, Denomination.TEN));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.JACK));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(new BlackjackWinningStrategy().getResult(dealer, player))
            .isEqualTo(WinningResult.DRAW);
    }

    @Test
    @DisplayName("딜러가 블랙잭이 아니고 플레이어가 블랙잭이면 승리")
    void getWinResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.TEN));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.JACK));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(new BlackjackWinningStrategy().getResult(dealer, player))
            .isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트나면 패배")
    void getLoseResult2() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.TEN));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.JACK),
            new Card(Suit.SPADE, Denomination.QUEEN));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(new PlayingWinningStrategy().getResult(dealer, player))
            .isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트나면 승리")
    void getWinResult2() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.JACK),
            new Card(Suit.SPADE, Denomination.QUEEN));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.JACK));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(new FinalWinningStrategy().getResult(dealer, player))
            .isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러보다 점수가 높으면 승리")
    void getWinResult3() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.JACK));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(new FinalWinningStrategy().getResult(dealer, player))
            .isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러보다 점수가 낮으면 패배")
    void getLoseResult3() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.SEVEN));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(new FinalWinningStrategy().getResult(dealer, player))
            .isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 점수가 같으면 무승부")
    void getDrawResult2() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.EIGHT));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(new FinalWinningStrategy().getResult(dealer, player))
            .isEqualTo(WinningResult.DRAW);
    }
}
