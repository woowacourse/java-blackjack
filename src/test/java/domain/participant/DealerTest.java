package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DealerTest {

    @Test
    void 딜러가_카드를_뽑으면_가지고있는_카드리스트에_추가된다() {
        Cards cards = new Cards(
                List.of(new Card(Suit.HEART, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.NINE))
        );
        Dealer dealer = new Dealer(cards);
        dealer.drawCard(List.of(new Card(Suit.CLOVER, Rank.TWO)));

        List<Card> expected = List.of(new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLOVER, Rank.TWO));

        assertThat(dealer.getCards()).isEqualTo(expected);
    }

    @Test
    void 딜러가_가진_카드리스트의_합계가_21초과이면_true를_반환한다() {
        Cards exceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Dealer exceedDealer = new Dealer(exceedBlackjackScoreCards);

        assertThat(exceedDealer.isBust()).isTrue();
    }

    @Test
    void 딜러가_가진_카드리스트의_합계가_21이하이면_false를_반환한다() {
        Cards notExceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Dealer notExceedDealer = new Dealer(notExceedBlackjackScoreCards);

        assertThat(notExceedDealer.isBust()).isFalse();
    }

    @Test
    void 딜러가_가진_카드리스트의_총합을_반환한다() {
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.getTotalRankSum()).isEqualTo(22);
    }

    @Test
    void 딜러가_가진_카드리스트를_반환한다() {
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Dealer dealer = new Dealer(cards);

        List<Card> expected = List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.DIAMOND, Rank.JACK));

        assertThat(dealer.getCards()).isEqualTo(expected);
    }

    @Test
    void 딜러의_이름을_반환한다() {
        Cards cards = new Cards(List.of(new Card(Suit.DIAMOND, Rank.EIGHT)));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.getParticipantName()).isEqualTo("딜러");
    }

    @Test
    void 딜러가_가진_카드리스트의_크기가_두장이면서_합계가_21이면_true를_반환한다() {
        Cards equalToBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Dealer isBlackjackDealer = new Dealer(equalToBlackjackScoreCards);

        assertThat(isBlackjackDealer.isBlackjack()).isTrue();
    }

    @Test
    void 딜러가_가진_카드리스트의_크기가_두장이면서_합계가_21초과이면_false를_반환한다() {
        Cards exceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.HEART, Rank.ACE)));
        Dealer exceedDealer = new Dealer(exceedBlackjackScoreCards);

        assertThat(exceedDealer.isBlackjack()).isFalse();
    }

    @Test
    void 딜러가_가진_카드리스트의_크기가_두장이면서_합계가_21이하이면_false를_반환한다() {
        Cards notExceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.QUEEN),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Dealer notExceedDealer = new Dealer(notExceedBlackjackScoreCards);

        assertThat(notExceedDealer.isBlackjack()).isFalse();
    }

    @Test
    void 딜러가_가진_카드리스트의_크기가_두장이아니고_합계가_21이면_false를_반환한다() {
        Cards sizeNotTwoCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.THREE)));
        Dealer sizeNotTwoDealer = new Dealer(sizeNotTwoCards);

        assertThat(sizeNotTwoDealer.isBlackjack()).isFalse();
    }

    @Test
    void 딜러가_카드를_뽑아야하는_상황이면_true를_반환한다() {
        Cards exceedDealerDrawLimitCards = new Cards(
                List.of(new Card(Suit.CLOVER, Rank.SIX),
                        new Card(Suit.HEART, Rank.JACK)));
        Dealer shouldHitDealer = new Dealer(exceedDealerDrawLimitCards);

        assertThat(shouldHitDealer.shouldHit()).isTrue();
    }

    @Test
    void 딜러가_카드를_뽑지않아도_되는_상황이면_false를_반환한다() {
        Cards notExceedDealerDrawLimitCards = new Cards(
                List.of(new Card(Suit.CLOVER, Rank.SEVEN),
                        new Card(Suit.HEART, Rank.JACK)));
        Dealer shouldNotHitDealer = new Dealer(notExceedDealerDrawLimitCards);

        assertThat(shouldNotHitDealer.shouldHit()).isFalse();
    }
}
