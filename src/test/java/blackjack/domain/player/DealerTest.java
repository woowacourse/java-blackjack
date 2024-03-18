package blackjack.domain.player;

import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.cardgame.BettingAmount;
import blackjack.domain.cardgame.BlackjackGame;
import blackjack.domain.cardgame.Profit;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void 딜러는_핸드의_숫자_합계가_16_이하이면_추가_카드를_필요로한다() {
        // given
        Dealer dealer = dealer(new Card(KING, HEART));

        // when
        boolean moreCardNeeded = dealer.isMoreCardNeeded();

        // then
        assertThat(moreCardNeeded).isTrue();
    }

    @Test
    void 딜러의_핸드가_비어있을_때_첫번째_카드를_요청하면_예외가_발생한다() {
        // given
        Dealer dealer = dealer();

        // when & then
        assertThatCode(dealer::getFirstCard)
                .isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void 딜러가_핸드에_카드를_가지고_있을_때_첫번째_카드를_요청하면_정상적으로_반환한다() {
        // given
        Card card = new Card(KING, HEART);
        Dealer dealer = dealer(card);

        // when
        Card firstCard = dealer.getFirstCard();

        // then
        assertThat(card).isEqualTo(firstCard);
    }

    @Test
    void 플레이어가_버스트_된_경우_플레이어는_베팅_금액을_모두_잃는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        Player player = player(
                new Card(Denomination.JACK, Suit.CLOVER),
                new Card(Denomination.QUEEN, Suit.CLOVER),
                new Card(Denomination.KING, Suit.CLOVER));

        // when
        BettingAmount bettingAmount = new BettingAmount(10000);
        blackjackGame.bet(player, bettingAmount);
        Map<Player, Profit> playerProfits = blackjackGame.findPlayerProfits(dealer);

        // then
        assertThat(playerProfits.get(player).getValue()).isEqualTo(-bettingAmount.getValue());
    }

    @Test
    void 플레이어가_버스트_되지_않았고_딜러가_버스트_된_경우_플레이어는_베팅_금액의_1배를_받는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.CLOVER));
        dealer.addCard(new Card(Denomination.QUEEN, Suit.CLOVER));
        dealer.addCard(new Card(Denomination.KING, Suit.CLOVER));

        Player player = player(new Card(Denomination.JACK, Suit.CLOVER));

        // when
        BettingAmount bettingAmount = new BettingAmount(10000);
        blackjackGame.bet(player, bettingAmount);
        Map<Player, Profit> playerProfits = blackjackGame.findPlayerProfits(dealer);

        // then
        assertThat(playerProfits.get(player).getValue()).isEqualTo(10000);
    }

    @Test
    void 플레이어와_딜러_모두가_블랙잭인_경우_플레이어는_베팅한_금액을_돌려받는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.CLOVER));
        dealer.addCard(new Card(Denomination.ACE, Suit.CLOVER));

        Player player = player(
                new Card(Denomination.JACK, Suit.CLOVER),
                new Card(Denomination.ACE, Suit.CLOVER));

        // when
        BettingAmount bettingAmount = new BettingAmount(10000);
        blackjackGame.bet(player, bettingAmount);
        Map<Player, Profit> playerProfits = blackjackGame.findPlayerProfits(dealer);

        // then
        assertThat(playerProfits.get(player).getValue()).isEqualTo(0);
    }

    @Test
    void 플레이어가_블랙잭이고_딜러는_블랙잭이_아닌_경우_플레이어는_베팅한_금액의_150퍼센트를_받는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.CLOVER));

        Player player = player(
                new Card(Denomination.JACK, Suit.CLOVER),
                new Card(Denomination.ACE, Suit.CLOVER));

        // when
        BettingAmount bettingAmount = new BettingAmount(10000);
        blackjackGame.bet(player, bettingAmount);
        Map<Player, Profit> playerProfits = blackjackGame.findPlayerProfits(dealer);

        // then
        assertThat(playerProfits.get(player).getValue()).isEqualTo(15000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_더_큰_경우_플레이어는_베팅_금액의_1배를_받는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.NINE, Suit.CLOVER));

        Player player = player(new Card(Denomination.TEN, Suit.CLOVER));

        // when
        BettingAmount bettingAmount = new BettingAmount(10000);
        blackjackGame.bet(player, bettingAmount);
        Map<Player, Profit> playerProfits = blackjackGame.findPlayerProfits(dealer);

        // then
        assertThat(playerProfits.get(player).getValue()).isEqualTo(10000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_더_작은_경우_플레이어는_베팅_금액을_모두_잃는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLOVER));

        Player player = player(new Card(Denomination.NINE, Suit.CLOVER));

        // when
        BettingAmount bettingAmount = new BettingAmount(10000);
        blackjackGame.bet(player, bettingAmount);
        Map<Player, Profit> playerProfits = blackjackGame.findPlayerProfits(dealer);

        // then
        assertThat(playerProfits.get(player).getValue()).isEqualTo(-10000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_딜러와_같은_경우_플레이어는_베팅_금액을_돌려받는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLOVER));

        Player player = player(new Card(Denomination.TEN, Suit.CLOVER));

        // when
        BettingAmount bettingAmount = new BettingAmount(10000);
        blackjackGame.bet(player, bettingAmount);
        Map<Player, Profit> playerProfits = blackjackGame.findPlayerProfits(dealer);

        // then
        assertThat(playerProfits.get(player).getValue()).isEqualTo(0);
    }

    // TODO: 테스트 흐름을 한 눈에 파악하기 어려움
    @Test
    void 딜러의_수익은_플레이어의_수익의_합을_negation한_것과_같다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLOVER));

        Player player1 = player(new Card(Denomination.TWO, Suit.CLOVER));
        Player player2 = player(new Card(Denomination.TWO, Suit.CLOVER));

        BettingAmount bettingAmount = new BettingAmount(10000);
        blackjackGame.bet(player1, bettingAmount);
        blackjackGame.bet(player2, bettingAmount);
        Map<Player, Profit> playerProfits = blackjackGame.findPlayerProfits(dealer);

        // when
        int playerProfit = playerProfits.values()
                .stream()
                .mapToInt(Profit::getValue)
                .sum();

        int dealerProfit = blackjackGame.findDealerProfit(dealer);

        // then
        assertThat(dealerProfit).isEqualTo(-playerProfit);
    }
}
