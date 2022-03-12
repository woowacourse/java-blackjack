package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class WinningResultTest {

    @Test
    @DisplayName("무승부 결과 도출(플레이어와 딜러 점수 둘다 버스트가 아니면서 무승부)")
    void getDrawResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.THREE),
            new Card(Suit.SPADE, Denomination.NINE),
            new Card(Suit.CLOVER, Denomination.EIGHT));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(WinningResult.of(player, dealer)).isEqualTo(WinningResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 버스트일 시 점수에 상관없이 무승부")
    void getDrawResult2() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.CLOVER, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.SIX),
            new Card(Suit.SPADE, Denomination.NINE),
            new Card(Suit.CLOVER, Denomination.EIGHT));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(WinningResult.of(player, dealer)).isEqualTo(WinningResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘 다 버스트가 아니면서 플레이어 우승")
    void getWinResult1() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.CLOVER, Denomination.FOUR),
            new Card(Suit.DIAMOND, Denomination.FIVE));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.NINE),
            new Card(Suit.SPADE, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.EIGHT));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(WinningResult.of(player, dealer)).isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트고 플레이어가 버스트가 아닐 시 플레이어 우승")
    void getWinResult2() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.CLOVER, Denomination.FIVE),
            new Card(Suit.DIAMOND, Denomination.TEN));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.SIX),
            new Card(Suit.SPADE, Denomination.TWO),
            new Card(Suit.CLOVER, Denomination.THREE));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(WinningResult.of(player, dealer)).isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트가 아니고 플레이어가 버스트일 시 플레이어 패배")
    void getLoseResult1() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.SIX),
            new Card(Suit.SPADE, Denomination.TWO),
            new Card(Suit.CLOVER, Denomination.NINE));
        List<Card> playerCards = List.of(new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.CLOVER, Denomination.FIVE),
            new Card(Suit.DIAMOND, Denomination.TEN));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(WinningResult.of(player, dealer)).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘 다 버스트가 아니고 플레이어 점수가 낮을 시 패배")
    void getLoseResult2() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.SIX),
            new Card(Suit.SPADE, Denomination.TWO),
            new Card(Suit.CLOVER, Denomination.NINE));
        List<Card> playerCards = List.of(new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.CLOVER, Denomination.FOUR),
            new Card(Suit.DIAMOND, Denomination.TWO));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        assertThat(WinningResult.of(player, dealer)).isEqualTo(WinningResult.LOSE);
    }
}
