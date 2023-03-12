package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    @DisplayName("딜러가 블랙잭이고, 플레이어가 블랙잭인 경우 무승부다.")
    void dealerBlackJackAndPlayerBlackJack() {
        Player player = new Player(new Name("gray"));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Suit.SPADE, Denomination.TEN));
        player.addCard(new Card(Suit.SPADE, Denomination.ACE));

        dealer.addCard(new Card(Suit.HEART, Denomination.ACE));
        dealer.addCard(new Card(Suit.CLOVER, Denomination.TEN));

        Result result = Result.calculatePlayerResult(player, dealer);

        Assertions.assertThat(result).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("딜러가 히트이고, 플레이어가 블랙잭인 경우 블랙잭 승리이다.")
    void dealerHitAndPlayerBlackJack() {
        Player player = new Player(new Name("gray"));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Suit.SPADE, Denomination.TEN));
        player.addCard(new Card(Suit.SPADE, Denomination.ACE));

        dealer.addCard(new Card(Suit.HEART, Denomination.THREE));
        dealer.addCard(new Card(Suit.CLOVER, Denomination.TEN));

        Result result = Result.calculatePlayerResult(player, dealer);

        Assertions.assertThat(result).isEqualTo(Result.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어가 버스트인 경우 패배한다.")
    void playerBust() {
        Player player = new Player(new Name("gray"));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Suit.SPADE, Denomination.TEN));
        player.addCard(new Card(Suit.SPADE, Denomination.EIGHT));
        player.addCard(new Card(Suit.DIAMOND, Denomination.EIGHT));

        dealer.addCard(new Card(Suit.CLOVER, Denomination.TEN));
        dealer.addCard(new Card(Suit.SPADE, Denomination.EIGHT));

        Result result = Result.calculatePlayerResult(player, dealer);

        Assertions.assertThat(result).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트이고, 플레이어가 히트인 경우 승리한다.")
    void dealerBustAndPlayerHit() {
        Player player = new Player(new Name("gray"));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Suit.SPADE, Denomination.TEN));
        player.addCard(new Card(Suit.SPADE, Denomination.EIGHT));

        dealer.addCard(new Card(Suit.HEART, Denomination.FOUR));
        dealer.addCard(new Card(Suit.CLOVER, Denomination.TEN));
        dealer.addCard(new Card(Suit.SPADE, Denomination.EIGHT));

        Result result = Result.calculatePlayerResult(player, dealer);

        Assertions.assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 히트인 경우, 점수가 크면 승리한다.")
    void dealerHitAndPlayerHit() {
        Player player = new Player(new Name("gray"));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Suit.SPADE, Denomination.TEN));
        player.addCard(new Card(Suit.SPADE, Denomination.EIGHT));
        player.addCard(new Card(Suit.CLOVER, Denomination.ACE));

        dealer.addCard(new Card(Suit.HEART, Denomination.FOUR));
        dealer.addCard(new Card(Suit.CLOVER, Denomination.TEN));
        dealer.addCard(new Card(Suit.CLOVER, Denomination.TWO));

        Result result = Result.calculatePlayerResult(player, dealer);

        Assertions.assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 히트이고 점수가 같은 경우 무승부다.")
    void dealerHitAndPlayerHitDraw() {
        Player player = new Player(new Name("gray"));
        Dealer dealer = new Dealer();

        player.addCard(new Card(Suit.SPADE, Denomination.TWO));
        player.addCard(new Card(Suit.DIAMOND, Denomination.TEN));
        player.addCard(new Card(Suit.CLOVER, Denomination.FOUR));

        dealer.addCard(new Card(Suit.HEART, Denomination.FOUR));
        dealer.addCard(new Card(Suit.CLOVER, Denomination.TEN));
        dealer.addCard(new Card(Suit.CLOVER, Denomination.TWO));

        Result result = Result.calculatePlayerResult(player, dealer);

        Assertions.assertThat(result).isEqualTo(Result.DRAW);
    }
}
