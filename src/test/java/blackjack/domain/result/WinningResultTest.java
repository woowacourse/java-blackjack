package blackjack.domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        assertThat(WinningResult.of(player,dealer)).isEqualTo(WinningResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 버스트일 시 플레이어 패배")
    void getDrawResult2() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.CLOVER, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.SIX),
            new Card(Suit.SPADE, Denomination.NINE),
            new Card(Suit.CLOVER, Denomination.EIGHT));

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        assertThat(WinningResult.of(player,dealer)).isEqualTo(WinningResult.LOSE);
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

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        assertThat(WinningResult.of(player,dealer)).isEqualTo(WinningResult.WIN);
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

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        assertThat(WinningResult.of(player,dealer)).isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트가 아니고 플레이어가 버스트일 시 플레이어 패배")
    void getLoseResult1() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.SIX),
            new Card(Suit.SPADE, Denomination.TWO),
            new Card(Suit.CLOVER, Denomination.NINE));
        List<Card> playerCards = List.of( new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.CLOVER, Denomination.FIVE),
            new Card(Suit.DIAMOND, Denomination.TEN));

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        assertThat(WinningResult.of(player,dealer)).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘 다 버스트가 아니고 플레이어 점수가 낮을 시 패배")
    void getLoseResult2() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.SIX),
            new Card(Suit.SPADE, Denomination.TWO),
            new Card(Suit.CLOVER, Denomination.NINE));
        List<Card> playerCards = List.of( new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.CLOVER, Denomination.FOUR),
            new Card(Suit.DIAMOND, Denomination.TWO));

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        assertThat(WinningResult.of(player,dealer)).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘 다 블랙잭이면 무승부")
    void getBlackjackResult() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.TEN));
        List<Card> playerCards = List.of( new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.CLOVER, Denomination.ACE));

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        assertThat(WinningResult.of(player,dealer)).isEqualTo(WinningResult.DRAW);
    }

    @Test
    @DisplayName("플레이어 블랙잭, 딜러가 블랙잭이 아닐 시 플레이어는 블랙잭")
    void getPlayerBlackjackResult() {
        List<Card> dealerCards = List.of(new Card(Suit.HEART, Denomination.FIVE),
            new Card(Suit.SPADE, Denomination.TEN));
        List<Card> playerCards = List.of( new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.CLOVER, Denomination.ACE));

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        assertThat(WinningResult.of(player,dealer)).isEqualTo(WinningResult.BLACKJACK);
    }

    @Test
    @DisplayName("결과를 문자열로 반환한다.")
    void getWinningResult() {
        assertThat(WinningResult.WIN.getResult()).isEqualTo("승");
    }

}
