package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.hand.Hand;
import fixture.CardFixture;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultTest {

    @DisplayName("플레이어가 버스트면 플레이어는 배팅 금액을 모두 잃게 된다.")
    @Test
    void testPlayerBust() {
        // given
        Hand playerHand = HandFixture.createHandWithScoreTotal21();
        playerHand.append(CardFixture.createAHeart());
        Player player = new Player(new PlayerName("pobi"), playerHand);

        Dealer dealer = new Dealer();

        // when
        double multiplier = PlayerResult.determineMultiplier(player, dealer);

        // then
        assertThat(multiplier).isEqualTo(-1);
    }

    @DisplayName("플레이어가 블랙잭이고 딜러는 블랙잭이 아니면 플레이어는 배팅 금액의 1.5배를 딜러에게 받는다.")
    @Test
    void testOnlyPlayerBlackjack() {
        // given
        Hand playerHand = new Hand();
        playerHand.append(CardFixture.createAHeart());
        playerHand.append(CardFixture.createKHeart());
        Player player = new Player(new PlayerName("pobi"), playerHand);

        Dealer dealer = new Dealer();

        // when
        double multiplier = PlayerResult.determineMultiplier(player, dealer);

        // then
        assertThat(multiplier).isEqualTo(1.5);
    }

    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트면 플레이어는 가지고 있는 패에 상관 없이 승리해 배팅 금액을 받는다.")
    @Test
    void testDealerBust() {
        // given
        Player player = new Player(new PlayerName("pobi"), new Hand());

        Hand dealerHand = HandFixture.createHandWithScoreTotal21();
        dealerHand.append(CardFixture.createAHeart());
        Dealer dealer = new Dealer(dealerHand);

        // when
        double multiplier = PlayerResult.determineMultiplier(player, dealer);

        // then
        assertThat(multiplier).isEqualTo(1);
    }

    @DisplayName("플레이어와 딜러가 모두 버스트가 아니고 플레이어 핸드가 크면 플레이어는 배팅 금액을 얻게 된다.")
    @Test
    void testPlayerGreater() {
        // given
        Hand playerHand = new Hand();
        playerHand.append(CardFixture.createAHeart());
        Player player = new Player(new PlayerName("pobi"), playerHand);

        Hand dealerHand = new Hand();
        dealerHand.append(CardFixture.create2Heart());
        Dealer dealer = new Dealer(dealerHand);

        // when
        double multiplier = PlayerResult.determineMultiplier(player, dealer);

        // then
        assertThat(multiplier).isEqualTo(1);
    }

    @DisplayName("플레이어와 딜러가 모두 버스트가 아니고 플레이어 핸드가 작으면 플레이어는 배팅 금액을 잃게 된다.")
    @Test
    void testPlayerLess() {
        // given
        Hand playerHand = new Hand();
        playerHand.append(CardFixture.create2Heart());
        Player player = new Player(new PlayerName("pobi"), playerHand);

        Hand dealerHand = new Hand();
        dealerHand.append(CardFixture.create3Heart());
        Dealer dealer = new Dealer(dealerHand);

        // when
        double multiplier = PlayerResult.determineMultiplier(player, dealer);

        // then
        assertThat(multiplier).isEqualTo(-1);
    }

    @DisplayName("플레이어와 딜러가 모두 버스트가 아니고 둘의 핸드가 같으면 플레이어는 배팅 금액을 돌려받게 된다.")
    @Test
    void testPlayerEqual() {
        // given
        Hand playerHand = new Hand();
        playerHand.append(CardFixture.create2Heart());
        Player player = new Player(new PlayerName("pobi"), playerHand);

        Hand dealerHand = new Hand();
        dealerHand.append(CardFixture.create2Heart());
        Dealer dealer = new Dealer(dealerHand);

        // when
        double multiplier = PlayerResult.determineMultiplier(player, dealer);

        // then
        assertThat(multiplier).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 플레이어는 베팅한 금액을 돌려받는다.")
    @Test
    void testAllBlackjack() {
        // given
        Hand hand = new Hand();
        hand.append(CardFixture.createAHeart());
        hand.append(CardFixture.createKHeart());

        Player player = new Player(new PlayerName("pobi"), hand);
        Dealer dealer = new Dealer(hand);

        // when
        double multiplier = PlayerResult.determineMultiplier(player, dealer);

        // then
        assertThat(multiplier).isEqualTo(0);
    }
}
