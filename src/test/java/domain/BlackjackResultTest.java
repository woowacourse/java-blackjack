package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackResultTest {

    Dealer dealer;
    Gambler gambler;
    Gamblers gamblers;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        gambler = new Gambler("tobi", 10000);
        gamblers = new Gamblers(List.of(gambler));
    }

    @Test
    @DisplayName("딜러 및 사용자 승패 결과 도출")
    void 딜러와_사용자_승패결과_도출() {
        Gambler gambler2 = new Gambler("quda", 20000);
        gamblers = new Gamblers(List.of(gambler, gambler2));

        Card jack = new Card(CardRank.JACK, CardSuit.CLOVER);
        Card eight = new Card(CardRank.EIGHT, CardSuit.DIAMOND);
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card sevenDiamond = new Card(CardRank.SEVEN, CardSuit.DIAMOND);
        Card sevenClover = new Card(CardRank.SEVEN, CardSuit.CLOVER);
        Card nine = new Card(CardRank.NINE, CardSuit.DIAMOND);

        StubDeck sd = new StubDeck(List.of(jack, eight, ten, sevenDiamond, sevenClover, nine));

        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);

        BlackjackResult result = BlackjackResult.of(dealer, gamblers);

        assertThat(result.dealerProfit()).isEqualTo(-10000);
        assertThat(result.gamblerProfits().get("tobi")).isEqualTo(-10000);
        assertThat(result.gamblerProfits().get("quda")).isEqualTo(20000);
    }

    @Test
    @DisplayName("딜러와 사용자의 점수가 같으면 무승부")
    void 딜러와_사용자_무승부() {

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card nine = new Card(CardRank.NINE, CardSuit.DIAMOND);
        Card eight = new Card(CardRank.EIGHT, CardSuit.CLOVER);
        Card nine2 = new Card(CardRank.NINE, CardSuit.CLOVER);

        StubDeck sd = new StubDeck(List.of(ten, nine, eight, nine2));

        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);

        BlackjackResult result = BlackjackResult.of(dealer, gamblers);

        assertThat(result.dealerProfit()).isEqualTo(0);
        assertThat(result.gamblerProfits().get("tobi")).isEqualTo(0);
    }

    @Test
    @DisplayName("사용자가 bust면 딜러 승리")
    void 사용자_bust() {
        // given
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card nine = new Card(CardRank.NINE, CardSuit.DIAMOND);
        Card king = new Card(CardRank.KING, CardSuit.CLOVER);
        Card queen = new Card(CardRank.QUEEN, CardSuit.DIAMOND);

        StubDeck sd = new StubDeck(List.of(ten, nine, king, queen, ten, ten));

        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);

        // when
        BlackjackResult result = BlackjackResult.of(dealer, gamblers);

        // then
        assertThat(result.dealerProfit()).isEqualTo(10000);
        assertThat(result.gamblerProfits().get("tobi")).isEqualTo(-10000);
    }

    @Test
    @DisplayName("사용자가 블랙잭이면 1.5배 수익")
    void 사용자_블랙잭() {
        // given
        Card nine = new Card(CardRank.NINE, CardSuit.DIAMOND);
        Card king = new Card(CardRank.KING, CardSuit.HEART);
        Card eight = new Card(CardRank.EIGHT, CardSuit.CLOVER);
        Card ace = new Card(CardRank.ACE, CardSuit.SPADE);

        StubDeck sd = new StubDeck(List.of(nine, king, eight, ace));

        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);

        // when
        BlackjackResult result = BlackjackResult.of(dealer, gamblers);

        // then
        assertThat(result.gamblerProfits().get("tobi")).isEqualTo(15000);
        assertThat(result.dealerProfit()).isEqualTo(-15000);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 사용자가 블랙잭이면 무승부")
    void 딜러_블랙잭_사용자_블랙잭() {
        // given
        Card king = new Card(CardRank.KING, CardSuit.HEART);
        Card ace = new Card(CardRank.ACE, CardSuit.SPADE);

        StubDeck sd = new StubDeck(List.of(king, king, ace, ace));

        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);

        // when
        BlackjackResult result = BlackjackResult.of(dealer, gamblers);

        // then
        assertThat(result.gamblerProfits().get("tobi")).isEqualTo(0);
        assertThat(result.dealerProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 사용자가 21이면 사용자 패배")
    void 딜러_블랙잭_사용자_21() {
        // given
        Card king = new Card(CardRank.KING, CardSuit.HEART);
        Card ace = new Card(CardRank.ACE, CardSuit.SPADE);
        Card five = new Card(CardRank.FIVE, CardSuit.CLOVER);
        Card six = new Card(CardRank.SIX, CardSuit.CLOVER);

        StubDeck sd = new StubDeck(List.of(king, king, ace, five, six));

        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);
        gamblers.dealAll(sd);

        // when
        BlackjackResult result = BlackjackResult.of(dealer, gamblers);

        // then
        assertThat(result.gamblerProfits().get("tobi")).isEqualTo(-10000);
        assertThat(result.dealerProfit()).isEqualTo(10000);
    }
}