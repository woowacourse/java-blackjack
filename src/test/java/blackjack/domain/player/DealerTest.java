package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.cardgame.WinningStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.CLOVER;
import static blackjack.domain.card.CardShape.DIAMOND;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardShape.SPADE;
import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DealerTest {
    @Test
    void 합계가_16이하라면_카드가_더_필요하다() {
        Dealer dealer = dealer(
                new Card(KING, SPADE),
                new Card(SIX, SPADE));

        assertThat(dealer.isMoreCardNeeded()).isTrue();
    }

    @Test
    void 합계가_16보다_크다면_카드가_더_필요하다() {
        Dealer dealer = dealer(
                new Card(KING, SPADE),
                new Card(SEVEN, SPADE));

        assertThat(dealer.isMoreCardNeeded()).isFalse();
    }

    @Test
    void 딜러에게_카드가_있는_경우에_첫_카드를_요청하면_정상적으로_돌려준다() {
        Dealer dealer = dealer(new Card(ACE, SPADE));

        assertThat(dealer.getFirstCard()).isNotNull();
    }

    @Test
    void 딜러에게_카드가_없는_경우에_첫_카드를_요청하면_예외가_발생한다() {
        Dealer dealer = dealer();

        assertThatThrownBy(dealer::getFirstCard)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 딜러가 카드를 갖고 있지 않습니다.");
    }

    @Test
    void 딜러와_플레이어_둘다_21을_초과할_경우에_플레이어가_패배한다() {
        Player player = player(
                new Card(KING, CLOVER),
                new Card(KING, HEART),
                new Card(KING, SPADE));
        Dealer dealer = dealer(
                new Card(QUEEN, CLOVER),
                new Card(QUEEN, HEART),
                new Card(QUEEN, SPADE));

        var totalResult = dealer.judgeWithPlayers(List.of(player))
                .totalResult();

        assertThat(totalResult.get(player)).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러와_여러_플레이어의_숫자가_21_이하인_경우_숫자가_큰_사람이_이긴다() {
        Player player = player(new Card(KING, SPADE));
        Dealer dealer = dealer(new Card(TWO, SPADE));

        var result = dealer.judgeWithPlayers(List.of(player))
                .totalResult();

        assertThat(result.get(player)).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 카드_합계가_딜러는_21_이하_플레이어는_21_초과인_경우_플레이어가_패배한다() {
        Player player = player(
                new Card(KING, CLOVER),
                new Card(KING, HEART),
                new Card(KING, DIAMOND));
        Dealer dealer = dealer(new Card(TWO, HEART));

        var result = dealer.judgeWithPlayers(List.of(player))
                .totalResult();

        assertThat(result.get(player)).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 카드_합계가_딜러는_21_초과_플레이어는_21_이하인_경우_플레이어가_승리한다() {
        Player player = player(new Card(TWO, HEART));
        Dealer dealer = dealer(
                new Card(KING, CLOVER),
                new Card(KING, HEART),
                new Card(KING, SPADE));

        var result = dealer.judgeWithPlayers(List.of(player))
                .totalResult();

        assertThat(result.get(player)).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 카드_합계가_딜러와_플레이어_모두_21_이하인_경우_숫자가_큰_사람이_승리한다() {
        Player player = player(new Card(ACE, HEART));
        Dealer dealer = dealer(new Card(KING, SPADE));

        var result = dealer.judgeWithPlayers(List.of(player))
                .totalResult();

        assertThat(result.get(player)).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 카드_합계가_딜러와_플레이어_모두_21_이하이고_동일한_경우_무승부다() {
        Player player = player(new Card(ACE, HEART));
        Dealer dealer = dealer(new Card(ACE, HEART));

        var result = dealer.judgeWithPlayers(List.of(player))
                .totalResult();

        assertThat(result.get(player)).isEqualTo(WinningStatus.PUSH);
    }

    @Test
    void 게임_결과에서_플레이어가_순서를_유지하고_있다() {
        Player playerA = player(new Card(TWO, HEART));
        Player playerB = player(new Card(TWO, SPADE));
        Player playerC = player(new Card(TWO, CLOVER));
        Player playerD = player(new Card(TWO, DIAMOND));
        Dealer dealer = dealer(new Card(ACE, HEART));

        var result = dealer.judgeWithPlayers(List.of(playerA, playerB, playerC, playerD))
                .totalResult();

        assertThat(result.keySet()).containsExactly(playerA, playerB, playerC, playerD);
    }
}
