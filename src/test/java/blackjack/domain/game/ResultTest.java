package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
import fixture.CardFixture;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("플레이어가 버스트면 플레이어는 패배다.")
    @Test
    void testPlayerBust() {
        // given
        Hand playerHand = HandFixture.createHandWithScoreTotal21();
        playerHand.append(CardFixture.createAHeart());
        Player player = new Player(new PlayerName("pobi"), playerHand);

        Dealer dealer = new Dealer();

        // when
        Result result = Result.determineResult(player, dealer);

        // then
        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 블랙잭이고 딜러는 블랙잭이 아니면 플레이어는 승리다.")
    @Test
    void testOnlyPlayerBlackjack() {
        // given
        Hand playerHand = new Hand();
        playerHand.append(CardFixture.createAHeart());
        playerHand.append(CardFixture.createKHeart());
        Player player = new Player(new PlayerName("pobi"), playerHand);

        Dealer dealer = new Dealer();

        // when
        Result result = Result.determineResult(player, dealer);

        // then
        assertThat(result).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 플레이어는 무승부다.")
    @Test
    void testAllBlackjack() {
        // given
        Hand hand = new Hand();
        hand.append(CardFixture.createAHeart());
        hand.append(CardFixture.createKHeart());

        Player player = new Player(new PlayerName("pobi"), hand);
        Dealer dealer = new Dealer(hand);

        // when
        Result result = Result.determineResult(player, dealer);

        // then
        assertThat(result).isEqualTo(Result.TIE);
    }

    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트면 플레이어는 승리다.")
    @Test
    void testDealerBust() {
        // given
        Player player = new Player(new PlayerName("pobi"), new Hand());

        Hand dealerHand = HandFixture.createHandWithScoreTotal21();
        dealerHand.append(CardFixture.createAHeart());
        Dealer dealer = new Dealer(dealerHand);

        // when
        Result result = Result.determineResult(player, dealer);

        // then
        assertThat(result).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러가 모두 버스트가 아니고 플레이어 핸드가 크면 플레이어는 승리다.")
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
        Result result = Result.determineResult(player, dealer);

        // then
        assertThat(result).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러가 모두 버스트가 아니고 플레이어 핸드가 작으면 플레이어는 패배다.")
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
        Result result = Result.determineResult(player, dealer);

        // then
        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러가 모두 버스트가 아니고 둘의 핸드가 같으면 플레이어는 무승부다.")
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
        Result result = Result.determineResult(player, dealer);

        // then
        assertThat(result).isEqualTo(Result.TIE);
    }
}
