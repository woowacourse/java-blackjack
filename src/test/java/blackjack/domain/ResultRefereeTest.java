package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Name;
import blackjack.domain.gameplayer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultRefereeTest {

    private static final Card ACE_HEARTS = new Card(CardNumber.ACE, CardSymbol.HEARTS);
    private static final Card TWO_HEARTS = new Card(CardNumber.TWO, CardSymbol.HEARTS);
    private static final Card NINE_HEARTS = new Card(CardNumber.NINE, CardSymbol.HEARTS);
    private static final Card JACK_HEARTS = new Card(CardNumber.JACK, CardSymbol.HEARTS);
    private static final Card QUEEN_HEARTS = new Card(CardNumber.QUEEN, CardSymbol.HEARTS);
    private static final Card KING_HEARTS = new Card(CardNumber.KING, CardSymbol.HEARTS);
    private static final Card ACE_DIAMONDS = new Card(CardNumber.ACE, CardSymbol.DIAMONDS);

    private static final Card JACK_DIAMONDS = new Card(CardNumber.JACK, CardSymbol.DIAMONDS);
    private static final Card QUEEN_DIAMONDS = new Card(CardNumber.QUEEN, CardSymbol.DIAMONDS);
    private static final Card KING_DIAMONDS = new Card(CardNumber.KING, CardSymbol.DIAMONDS);

    Player kong;
    Dealer dealer;

    @BeforeEach
    void init() {
        kong = new Player(new Name("tori"));
        dealer = new Dealer();
    }

    @DisplayName("플레이어가 버스트인 경우에 플레이어는 무조건 진다.")
    @Test
    void Should_PlayerLose_When_PlayerBurst() {
        kong.addCard(JACK_HEARTS);
        kong.addCard(QUEEN_HEARTS);
        kong.addCard(KING_HEARTS);

        dealer.addCard(JACK_DIAMONDS);
        dealer.addCard(TWO_HEARTS);
        dealer.addCard(QUEEN_DIAMONDS);

        // when, then
        assertThat(ResultReferee.getPlayerResult(kong, dealer)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭이 아닌 경우에는 플레이어는 블랙잭이다.")
    @Test
    void Should_PlayerBlackJack_When_PlayerBlackjack() {
        kong.addCard(JACK_HEARTS);
        kong.addCard(ACE_HEARTS);

        dealer.addCard(JACK_DIAMONDS);
        dealer.addCard(QUEEN_DIAMONDS);

        // when, then
        assertThat(ResultReferee.getPlayerResult(kong, dealer)).isEqualTo(Result.BLACKJACK);
    }

    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭인 경우에는 플레이어와 딜러는 무승부이다.")
    @Test
    void Should_PlayerDraw_When_PlayerDealerBlackjack() {
        kong.addCard(JACK_HEARTS);
        kong.addCard(ACE_HEARTS);

        dealer.addCard(JACK_DIAMONDS);
        dealer.addCard(ACE_DIAMONDS);

        // when, then
        assertThat(ResultReferee.getPlayerResult(kong, dealer)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어가 버스트도 아니고 블랙잭도 아닐 때 딜러의 점수보다 높다면 플레이어는 승리한다.")
    @Test
    void Should_PlayerWin_When_PlayerScoreIsGreaterThanDealerScore() {
        kong.addCard(JACK_HEARTS);
        kong.addCard(QUEEN_HEARTS);

        dealer.addCard(JACK_DIAMONDS);
        dealer.addCard(NINE_HEARTS);

        // when, then
        assertThat(ResultReferee.getPlayerResult(kong, dealer)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어가 버스트도 아니고 블랙잭도 아닐 때 딜러의 점수와 같다면 플레이어는 무승부이다.")
    @Test
    void Should_PlayerDraw_When_PlayerScoreIsEqualToDealerScore() {
        kong.addCard(JACK_HEARTS);
        kong.addCard(QUEEN_HEARTS);

        dealer.addCard(JACK_DIAMONDS);
        dealer.addCard(KING_DIAMONDS);

        // when, then
        assertThat(ResultReferee.getPlayerResult(kong, dealer)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어가 버스트도 아니고 블랙잭도 아니고, 딜러도 버스트가 아닐 때 딜러의 점수의 점수보다 낮다면 플레이어는 패배한다.")
    @Test
    void Should_PlayerLose_When_PlayerScoreIsLessThanDealerScore() {
        kong.addCard(JACK_HEARTS);
        kong.addCard(NINE_HEARTS);

        dealer.addCard(JACK_DIAMONDS);
        dealer.addCard(KING_DIAMONDS);

        // when, then
        assertThat(ResultReferee.getPlayerResult(kong, dealer)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 버스트도 아니고 블랙잭도 아니고, 딜러도 버스트일 때는 플레이어가 승리한다.")
    @Test
    void Should_PlayerWin_When_DealerBurst() {
        kong.addCard(JACK_HEARTS);
        kong.addCard(NINE_HEARTS);

        dealer.addCard(JACK_DIAMONDS);
        dealer.addCard(TWO_HEARTS);
        dealer.addCard(QUEEN_DIAMONDS);

        // when, then
        assertThat(ResultReferee.getPlayerResult(kong, dealer)).isEqualTo(Result.WIN);
    }
}