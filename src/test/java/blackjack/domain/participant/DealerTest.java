package blackjack.domain.participant;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.JACK;
import static blackjack.domain.card.Number.KING;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Number.SEVEN;
import static blackjack.domain.card.Number.SIX;
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
class DealerTest {

    @Test
    void 카드를_뽑을_수_있으면_true_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(SIX, HEART)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.isDrawable()).isTrue();
    }

    @Test
    void 카드를_뽑을_수_없으면_false_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(SEVEN, HEART)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    void 소지한_카드가_3장이라면_false_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(TWO, CLOVER),
                new Card(SIX, HEART),
                new Card(SEVEN, DIAMOND)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    void 카드를_받는다() {
        //given
        final List<Card> cardPack = new ArrayList<>(List.of(
                new Card(QUEEN, CLOVER),
                new Card(SIX, HEART)
        ));
        final Cards cards = new Cards(cardPack);
        final Dealer dealer = new Dealer(cards);

        //when
        dealer.drawCard(new Card(ACE, DIAMOND));

        //then
        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    void 점수를_확인한다() {
        final Cards cards = new Cards(List.of(
                new Card(TWO, CLOVER),
                new Card(SIX, HEART),
                new Card(SEVEN, DIAMOND)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.getScore()).isEqualTo(15);
    }

    @Test
    void 플레이어의_점수가_21_초과라면_딜러의_점수에_상관없이_딜러가_이긴다() {
        final Cards cards = new Cards(List.of(
                new Card(KING, CLOVER),
                new Card(JACK, HEART),
                new Card(QUEEN, DIAMOND)
        ));
        final Player player = new Player("kokodak", cards);
        final Dealer dealer = new Dealer(cards);

        Result result = dealer.compareScoreTo(player);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어의_점수가_21_이하이고_딜러의_점수가_21_초과이면_딜러가_진다() {
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

        Result result = dealer.compareScoreTo(player);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @Test
    void 플레이어와_딜러의_점수_모두_21_이하이고_플레이어의_점수가_딜러보다_작다면_딜러가_이긴다() {
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

        Result result = dealer.compareScoreTo(player);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어와_딜러의_점수_모두_21_이하이고_플레이어의_점수가_딜러보다_크다면_딜러가_진다() {
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

        Result result = dealer.compareScoreTo(player);

        assertThat(result).isEqualTo(Result.LOSE);
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

        Result result = dealer.compareScoreTo(player);

        assertThat(result).isEqualTo(Result.DRAW);
    }
}
