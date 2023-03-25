package blackjack.domain.participant;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.JACK;
import static blackjack.domain.card.Number.KING;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Number.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void 이름을_확인한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(QUEEN, HEART)
        ));
        final Player player = new Player("dazzle", cards);

        assertThat(player.getName()).isEqualTo("dazzle");
    }

    @Test
    void 카드를_뽑을_수_있으면_true_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(QUEEN, HEART)
        ));
        final Player player = new Player("kokodak", cards);

        assertThat(player.isDrawable()).isTrue();
    }

    @Test
    void 카드를_뽑을_수_없으면_false_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(ACE, HEART)
        ));
        final Player player = new Player("kokodak", cards);

        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    void 카드를_받는다() {
        //given
        final List<Card> cardPack = new ArrayList<>(List.of(
                new Card(QUEEN, CLOVER),
                new Card(KING, HEART)
        ));
        final Cards cards = new Cards(cardPack);
        final Player player = new Player("dazzle", cards);

        //when
        player.drawCard(new Card(TWO, DIAMOND));

        //then
        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    void 점수를_확인한다() {
        final List<Card> cardPack = new ArrayList<>(List.of(
                new Card(QUEEN, CLOVER),
                new Card(KING, HEART)
        ));
        final Cards cards = new Cards(cardPack);
        final Player player = new Player("dazzle", cards);

        assertThat(player.getScore()).isEqualTo(20);
    }

    @Test
    void 카드를_더_받을_수_있는지의_여부_상태를_바꿀_수_있다() {
        final Player player = new Player("kokodak");

        player.changeDrawable();

        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    void 플레이어의_점수가_21_초과라면_딜러의_점수에_상관없이_플레이어가_진다() {
        final Cards cards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART),
                new Card(QUEEN, DIAMOND)
        ));
        final Player player = new Player("kokodak", cards);
        final Dealer dealer = new Dealer(cards);

        Result result = dealer.compareScoreTo(player).reverseResult();

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @Test
    void 플레이어의_점수가_21_이하이고_딜러의_점수가_21_초과이면_플레이어가_이긴다() {
        final Cards playerCards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART),
                new Card(ACE, HEART)
        ));
        final Cards dealerCards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART),
                new Card(QUEEN, HEART)
        ));
        final Player player = new Player("kokodak", playerCards);
        final Dealer dealer = new Dealer(dealerCards);

        Result result = dealer.compareScoreTo(player).reverseResult();

        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어와_딜러의_점수_모두_21_이하이고_플레이어의_점수가_딜러보다_작다면_플레이어가_진다() {
        final Cards playerCards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART)
        ));
        final Cards dealerCards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART),
                new Card(ACE, HEART)
        ));
        final Player player = new Player("kokodak", playerCards);
        final Dealer dealer = new Dealer(dealerCards);

        Result result = dealer.compareScoreTo(player).reverseResult();

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @Test
    void 플레이어와_딜러의_점수_모두_21_이하이고_플레이어의_점수가_딜러보다_크다면_플레이어가_이긴다() {
        final Cards playerCards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART),
                new Card(ACE, HEART)
        ));
        final Cards dealerCards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART)
        ));
        final Player player = new Player("kokodak", playerCards);
        final Dealer dealer = new Dealer(dealerCards);

        Result result = dealer.compareScoreTo(player).reverseResult();

        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어와_딜러의_점수_모두_21_이하이고_플레이어와_딜러의_점수가_같다면_무승부이다() {
        final Cards playerCards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART)
        ));
        final Cards dealerCards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART)
        ));
        final Player player = new Player("kokodak", playerCards);
        final Dealer dealer = new Dealer(dealerCards);

        Result result = dealer.compareScoreTo(player).reverseResult();

        assertThat(result).isEqualTo(Result.DRAW);
    }
}
