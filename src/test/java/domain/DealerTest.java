package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("딜러 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DealerTest {

    @Test
    void 딜러가_카드를_뽑으면_가지고있는_카드리스트에_추가된다() {
        Cards cards = new Cards(
                List.of(new Card(Suit.HEART, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.NINE))
        );
        Participant dealer = new Dealer(cards);
        dealer.drawCard(List.of(new Card(Suit.CLOVER, Rank.TWO)));

        List<Card> expected = List.of(new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLOVER, Rank.TWO));

        assertThat(dealer.getCards()).isEqualTo(expected);
    }

    @Test
    void 딜러가_가진_카드리스트의_합계가_21초과이면_true_아니면_false를_반환한다() {
        Cards exceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Participant exceedDealer = new Dealer(exceedBlackjackScoreCards);

        Cards notExceedBlackjackScoreCards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Participant notExceedDealer = new Dealer(notExceedBlackjackScoreCards);

        assertAll(
                () -> assertThat(exceedDealer.isBurst()).isTrue(),
                () -> assertThat(notExceedDealer.isBurst()).isFalse()
        );
    }

    @Test
    void 딜러가_가진_카드리스트의_총합을_반환한다() {
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Participant dealer = new Dealer(cards);

        assertThat(dealer.getTotalRankSum()).isEqualTo(22);
    }

    @Test
    void 딜러가_가진_카드리스트의_합계가_16초과이면_true_아니면_false를_반환한다() {
        Cards exceedDealerDrawLimitCards = new Cards(
                List.of(new Card(Suit.CLOVER, Rank.SIX), new Card(Suit.HEART, Rank.JACK)));

        Participant shouldHitDealer = new Dealer(exceedDealerDrawLimitCards);

        Cards notExceedDealerDrawLimitCards = new Cards(
                List.of(new Card(Suit.CLOVER, Rank.SEVEN), new Card(Suit.HEART, Rank.JACK)));
        Participant shouldNotHitDealer = new Dealer(notExceedDealerDrawLimitCards);

        assertAll(
                () -> assertThat(shouldHitDealer.shouldHit()).isTrue(),
                () -> assertThat(shouldNotHitDealer.shouldHit()).isFalse()
        );
    }

    @Test
    void 딜러는_초기카드리스트를_한장만_반환한다() {
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK)));
        Participant dealer = new Dealer(cards);

        List<Card> expected = List.of(new Card(Suit.DIAMOND, Rank.EIGHT));

        assertThat(dealer.getInitialCards()).isEqualTo(expected);
    }

    @Test
    void 딜러의_이름을_반환한다() {
        Cards cards = new Cards(List.of(new Card(Suit.DIAMOND, Rank.EIGHT)));
        Participant dealer = new Dealer(cards);

        assertThat(dealer.getParticipantName()).isEqualTo("딜러");
    }
}
