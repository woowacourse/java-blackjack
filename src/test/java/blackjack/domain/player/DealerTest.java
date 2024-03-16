package blackjack.domain.player;

import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
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
        Dealer dealer = new Dealer();
        Player player = player(
                new Card(CardNumber.JACK, CardShape.CLOVER),
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.KING, CardShape.CLOVER));

        // when
        int bettingMoney = 10000;
        int bettingResult = dealer.findBettingResult(player, bettingMoney);

        // then
        assertThat(bettingResult).isEqualTo(-bettingMoney);
    }

    @Test
    void 플레이어가_버스트_되지_않았고_딜러가_버스트_된_경우_플레이어는_베팅_금액의_1배를_받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.JACK, CardShape.CLOVER));
        dealer.addCard(new Card(CardNumber.QUEEN, CardShape.CLOVER));
        dealer.addCard(new Card(CardNumber.KING, CardShape.CLOVER));

        Player player = player(new Card(CardNumber.JACK, CardShape.CLOVER));

        // when
        int bettingMoney = 10000;
        int bettingResult = dealer.findBettingResult(player, bettingMoney);

        // then
        assertThat(bettingResult).isEqualTo(bettingMoney);
    }

    @Test
    void 플레이어와_딜러_모두가_블랙잭인_경우_플레이어는_베팅한_금액을_돌려받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.JACK, CardShape.CLOVER));
        dealer.addCard(new Card(CardNumber.ACE, CardShape.CLOVER));

        Player player = player(
                new Card(CardNumber.JACK, CardShape.CLOVER),
                new Card(CardNumber.ACE, CardShape.CLOVER));

        // when
        int bettingMoney = 10000;
        int bettingResult = dealer.findBettingResult(player, bettingMoney);

        // then
        assertThat(bettingResult).isEqualTo(0);
    }

    @Test
    void 플레이어가_블랙잭이고_딜러는_블랙잭이_아닌_경우_플레이어는_베팅한_금액의_150퍼센트를_받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.JACK, CardShape.CLOVER));

        Player player = player(
                new Card(CardNumber.JACK, CardShape.CLOVER),
                new Card(CardNumber.ACE, CardShape.CLOVER));

        // when
        int bettingMoney = 10000;
        int bettingResult = dealer.findBettingResult(player, bettingMoney);

        // then
        assertThat(bettingResult).isEqualTo(15000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_더_큰_경우_플레이어는_베팅_금액의_1배를_받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.NINE, CardShape.CLOVER));

        Player player = player(new Card(CardNumber.TEN, CardShape.CLOVER));

        // when
        int bettingMoney = 10000;
        int bettingResult = dealer.findBettingResult(player, bettingMoney);

        // then
        assertThat(bettingResult).isEqualTo(10000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_더_작은_경우_플레이어는_베팅_금액을_모두_잃는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.TEN, CardShape.CLOVER));

        Player player = player(new Card(CardNumber.NINE, CardShape.CLOVER));

        // when
        int bettingMoney = 10000;
        int bettingResult = dealer.findBettingResult(player, bettingMoney);

        // then
        assertThat(bettingResult).isEqualTo(-10000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_딜러와_같은_경우_플레이어는_베팅_금액을_돌려받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.TEN, CardShape.CLOVER));

        Player player = player(new Card(CardNumber.TEN, CardShape.CLOVER));

        // when
        int bettingMoney = 10000;
        int bettingResult = dealer.findBettingResult(player, bettingMoney);

        // then
        assertThat(bettingResult).isEqualTo(0);
    }
}
