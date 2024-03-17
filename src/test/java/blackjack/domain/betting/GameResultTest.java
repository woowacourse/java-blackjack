package blackjack.domain.betting;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.Test;

import static blackjack.domain.betting.GameResult.LOSE;
import static blackjack.domain.betting.GameResult.PUSH;
import static blackjack.domain.betting.GameResult.WIN;
import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.CLOVER;
import static blackjack.domain.card.CardShape.DIAMOND;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardShape.SPADE;
import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private static final Name name = new Name("망쵸");

    @Test
    void 딜러와_플레이어_둘다_21을_초과할_경우에_플레이어가_패배한다() {
        Player player = player(
                name,
                new Card(KING, CLOVER),
                new Card(KING, HEART),
                new Card(KING, SPADE));
        Dealer dealer = dealer(
                new Card(QUEEN, CLOVER),
                new Card(QUEEN, HEART),
                new Card(QUEEN, SPADE));

        var result = GameResult.doesPlayerWin(dealer, player);

        assertThat(result).isEqualTo(LOSE);
    }

    @Test
    void 딜러와_플레이어_둘다_블랙잭인_경우에_무승부다() {
        Player player = player(
                name,
                new Card(KING, CLOVER),
                new Card(ACE, CLOVER));
        Dealer dealer = dealer(
                new Card(KING, HEART),
                new Card(ACE, HEART));

        var result = GameResult.doesPlayerWin(dealer, player);

        assertThat(result).isEqualTo(PUSH);
    }

    @Test
    void 딜러와_여러_플레이어의_숫자가_21_이하인_경우_숫자가_큰_사람이_이긴다() {
        Player player = player(name, new Card(KING, SPADE));
        Dealer dealer = dealer(new Card(TWO, SPADE));

        var result = GameResult.doesPlayerWin(dealer, player);

        assertThat(result).isEqualTo(WIN);
    }

    @Test
    void 카드_합계가_딜러는_21_이하_플레이어는_21_초과인_경우_플레이어가_패배한다() {
        Player player = player(
                name,
                new Card(KING, CLOVER),
                new Card(KING, HEART),
                new Card(KING, DIAMOND));
        Dealer dealer = dealer(new Card(TWO, HEART));

        var result = GameResult.doesPlayerWin(dealer, player);

        assertThat(result).isEqualTo(LOSE);
    }

    @Test
    void 카드_합계가_딜러는_21_초과_플레이어는_21_이하인_경우_플레이어가_승리한다() {
        Player player = player(name, new Card(TWO, HEART));
        Dealer dealer = dealer(
                new Card(KING, CLOVER),
                new Card(KING, HEART),
                new Card(KING, SPADE));

        var result = GameResult.doesPlayerWin(dealer, player);

        assertThat(result).isEqualTo(WIN);
    }

    @Test
    void 카드_합계가_딜러와_플레이어_모두_21_이하인_경우_숫자가_큰_사람이_승리한다() {
        Player player = player(name, new Card(ACE, HEART));
        Dealer dealer = dealer(new Card(KING, SPADE));

        var result = GameResult.doesPlayerWin(dealer, player);

        assertThat(result).isEqualTo(WIN);
    }

    @Test
    void 카드_합계가_딜러와_플레이어_모두_21_이하이고_동일한_경우_무승부다() {
        Player player = player(name, new Card(ACE, HEART));
        Dealer dealer = dealer(new Card(ACE, HEART));

        var result = GameResult.doesPlayerWin(dealer, player);

        assertThat(result).isEqualTo(PUSH);
    }
}
