package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.state.Blackjack;
import domain.participant.state.Bust;
import domain.participant.state.Hit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    @Test
    void sum() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.FIVE, Suit.CLUBS));
        player.drawCard(new Card(Denomination.FIVE, Suit.CLUBS));
        player.drawCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.score()).isEqualTo(21);
    }

    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    @Test
    void sum2() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.score()).isEqualTo(21);
    }

    @DisplayName("합계 점수가 21을 초과하면 버스트")
    @Test
    void bustState() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.JACK, Suit.CLUBS));
        player.drawCard(new Card(Denomination.QUEEN, Suit.CLUBS));

        assertThat(player.state).isInstanceOf(Bust.class);
    }

    @DisplayName("합계 점수가 21이 넘지 않으면 히트")
    @Test
    void hitState() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.JACK, Suit.CLUBS));

        assertThat(player.state).isInstanceOf(Hit.class);
    }

    @DisplayName("합계 점수가 21이면 블랙잭")
    @Test
    void blackjackState() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.state).isInstanceOf(Blackjack.class);
    }

    @DisplayName("플레이어는 [딜러] 이름을 사용할 수 없다.")
    @Test
    void playerIsNotDealer() {
        final Name name = new Name("딜러");

        assertThatThrownBy(() -> new Player(name, new BetAmount(100)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러 블랙잭, 플레이어 블랙잭: 수익률 0%")
    @Test
    void blackJackAndBlackjack() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.ACE, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.ACE, Suit.CLUBS));
        final int profit = pobi.profit(dealer);
        Assertions.assertThat(profit).isEqualTo(0);
    }

    @DisplayName("딜러 블랙잭, 플레이어 블랙잭 이외: 수익률 -100%")
    @Test
    void blackJackAndNotBlackjack() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));
        pobi.stand();

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.ACE, Suit.CLUBS));
        // when
        final int profit = pobi.profit(dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(-100);
    }

    @DisplayName("딜러 블랙잭 이외, 플레이어 블랙잭: 수익률 150%")
    @Test
    void notBlackJackAndBlackjack() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.ACE, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final int profit = pobi.profit(dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(150);
    }

    @DisplayName("딜러 버스트, 플레이어 버스트: 수익률 -100%")
    @Test
    void bustAndBust() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.QUEEN, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.QUEEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final int profit = pobi.profit(dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(-100);
    }

    @DisplayName("딜러 버스트, 플레이어 버스트X: 수익률 100%")
    @Test
    void bustAndNotBust() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));
        pobi.stand();

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.QUEEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final int profit = pobi.profit(dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(100);
    }

    @DisplayName("딜러 버스트X, 플레이어 버스트: 수익률 -100%")
    @Test
    void notBustAndBust() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.QUEEN, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.TEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final int profit = pobi.profit(dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(-100);
    }

    @DisplayName("딜러 버스트X, 플레이어 버스트X, 딜러승: 수익률 -100%")
    @Test
    void dealerWin() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.THREE, Suit.DIAMOND));
        pobi.stand();

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.TEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final int profit = pobi.profit(dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(-100);
    }

    @DisplayName("딜러 버스트X, 플레이어 버스트X, 플레이어승: 수익률 100%")
    @Test
    void playerWin() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.TEN, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));
        pobi.stand();

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.THREE, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final int profit = pobi.profit(dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(100);
    }

    @DisplayName("딜러 버스트X, 플레이어 버스트X, 동점: 수익률 0%")
    @Test
    void tie() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.TEN, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));
        pobi.stand();

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.TEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final int profit = pobi.profit(dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(0);
    }
}
