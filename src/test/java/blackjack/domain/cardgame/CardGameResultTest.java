package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.CLOVER;
import static blackjack.domain.card.CardShape.DIAMOND;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardShape.SPADE;
import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.WIN;
import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;

class CardGameResultTest {
    @Test
    void 딜러가_승패_횟수를_계산할_수_있다() {
        Map<Player, WinningStatus> result = new LinkedHashMap<>();
        result.put(player(), WIN);
        result.put(player(), WIN);
        result.put(player(), LOSE);

        CardGameResult cardGameResult = new CardGameResult(result);

        int dealerWinCount = cardGameResult.getDealerWinCount();
        int dealerLoseCount = cardGameResult.getDealerLoseCount();

        assertThat(dealerWinCount).isEqualTo(1);
        assertThat(dealerLoseCount).isEqualTo(2);
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

        var result = CardGameResult.of(dealer, List.of(player))
                .totalResult();

        assertThat(result.get(player)).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러와_여러_플레이어의_숫자가_21_이하인_경우_숫자가_큰_사람이_이긴다() {
        Player player = player(new Card(KING, SPADE));
        Dealer dealer = dealer(new Card(TWO, SPADE));

        var result = CardGameResult.of(dealer, List.of(player))
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

        var result = CardGameResult.of(dealer, List.of(player))
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

        var result = CardGameResult.of(dealer, List.of(player))
                .totalResult();

        assertThat(result.get(player)).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 카드_합계가_딜러와_플레이어_모두_21_이하인_경우_숫자가_큰_사람이_승리한다() {
        Player player = player(new Card(ACE, HEART));
        Dealer dealer = dealer(new Card(KING, SPADE));

        var result = CardGameResult.of(dealer, List.of(player))
                .totalResult();

        assertThat(result.get(player)).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 카드_합계가_딜러와_플레이어_모두_21_이하이고_동일한_경우_무승부다() {
        Player player = player(new Card(ACE, HEART));
        Dealer dealer = dealer(new Card(ACE, HEART));

        var result = CardGameResult.of(dealer, List.of(player))
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

        var result = CardGameResult.of(dealer, List.of(playerA, playerB, playerC, playerD))
                .totalResult();

        assertThat(result.keySet()).containsExactly(playerA, playerB, playerC, playerD);
    }
}
