package blackjack.domain.cardgame;

import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {
    @Test
    void 카드_한_장을_플레이어에게_지급한다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Player mangcho = player(0);

        // when
        blackjackGame.giveCard(mangcho);

        // then
        assertThat(mangcho.getCards().size()).isEqualTo(1);
    }

    @Test
    void 게임_시작시_딜러와_모든_플레이어에게_카드_2장을_지급한다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = dealer();
        Player mangcho = player(0);
        Player ddang = player(0);

        // when
        blackjackGame.initializeHand(dealer, List.of(mangcho, ddang));

        // then
        assertSoftly(softly -> {
            softly.assertThat(dealer.getCards().size()).isEqualTo(2);
            softly.assertThat(mangcho.getCards().size()).isEqualTo(2);
            softly.assertThat(ddang.getCards().size()).isEqualTo(2);
        });
    }


    @Test
    void 플레이어가_버스트_된_경우_플레이어는_베팅_금액을_모두_잃는다() {
        // given
        Dealer dealer = new Dealer();
        Player player = player(10000,
                new Card(Denomination.JACK, Suit.CLOVER),
                new Card(Denomination.QUEEN, Suit.CLOVER),
                new Card(Denomination.KING, Suit.CLOVER));

        // when
        Money money = new Money(10000);

        // then
        assertThat(money.findProfit(dealer.judgePlayerStatus(player)).getValue())
                .isEqualTo(-money.getValue());
    }

    @Test
    void 플레이어가_버스트_되지_않았고_딜러가_버스트_된_경우_플레이어는_베팅_금액의_1배를_받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.CLOVER));
        dealer.addCard(new Card(Denomination.QUEEN, Suit.CLOVER));
        dealer.addCard(new Card(Denomination.KING, Suit.CLOVER));

        Player player = player(10000, new Card(Denomination.JACK, Suit.CLOVER));

        // when
        Money money = new Money(10000);

        // then
        assertThat(money.findProfit(dealer.judgePlayerStatus(player)).getValue())
                .isEqualTo(10000);
    }

    @Test
    void 플레이어와_딜러_모두가_블랙잭인_경우_플레이어는_베팅한_금액을_돌려받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.CLOVER));
        dealer.addCard(new Card(Denomination.ACE, Suit.CLOVER));

        Player player = player(10000,
                new Card(Denomination.JACK, Suit.CLOVER),
                new Card(Denomination.ACE, Suit.CLOVER));

        // when
        Money money = new Money(10000);

        // then
        assertThat(money.findProfit(dealer.judgePlayerStatus(player)).getValue())
                .isEqualTo(0);
    }

    @Test
    void 플레이어가_블랙잭이고_딜러는_블랙잭이_아닌_경우_플레이어는_베팅한_금액의_150퍼센트를_받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.CLOVER));

        Player player = player(10000,
                new Card(Denomination.JACK, Suit.CLOVER),
                new Card(Denomination.ACE, Suit.CLOVER));

        // when
        Money money = new Money(10000);

        // then
        assertThat(money.findProfit(dealer.judgePlayerStatus(player)).getValue())
                .isEqualTo(15000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_더_큰_경우_플레이어는_베팅_금액의_1배를_받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.NINE, Suit.CLOVER));

        Player player = player(10000, new Card(Denomination.TEN, Suit.CLOVER));

        // when
        Money money = new Money(10000);

        // then
        assertThat(money.findProfit(dealer.judgePlayerStatus(player)).getValue())
                .isEqualTo(10000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_더_작은_경우_플레이어는_베팅_금액을_모두_잃는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLOVER));

        Player player = player(10000, new Card(Denomination.NINE, Suit.CLOVER));

        // when
        Money money = new Money(10000);

        // then
        assertThat(money.findProfit(dealer.judgePlayerStatus(player)).getValue())
                .isEqualTo(-10000);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트_되지_않았고_모두_블랙잭이_아닐_때_플레이어의_숫자가_딜러와_같은_경우_플레이어는_베팅_금액을_돌려받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLOVER));

        Player player = player(10000, new Card(Denomination.TEN, Suit.CLOVER));

        // when
        Money money = new Money(10000);

        // then
        assertThat(money.findProfit(dealer.judgePlayerStatus(player)).getValue())
                .isEqualTo(0);
    }

    // TODO: 테스트 흐름을 한 눈에 파악하기 어려움
    @Test
    void 딜러의_수익은_플레이어의_수익의_합을_negation한_것과_같다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLOVER));

        Player player1 = player(10000, new Card(Denomination.TWO, Suit.CLOVER));
        Player player2 = player(10000, new Card(Denomination.TWO, Suit.CLOVER));

        List<Player> players = List.of(player1, player2);
        Participants participants = new Participants(dealer, players);

        // when
        int playerProfit = players
                .stream()
                .mapToInt(player -> player.getBettingAmount()
                        .findProfit(dealer.judgePlayerStatus(player))
                        .getValue())
                .sum();

        int dealerProfit = participants.calculateDealerProfit();

        // then
        assertThat(dealerProfit).isEqualTo(-playerProfit);
    }
}
