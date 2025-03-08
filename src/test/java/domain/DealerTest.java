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
    void 딜러의_카드_숫자_총합이_16초과면_true_아니면_false_반환한다() {
        Dealer exceedSixteenDealer = new Dealer(
            new Cards(List.of(new Card(Suit.CLOVER, Rank.EIGHT), new Card(Suit.HEART, Rank.JACK))));

        Dealer notExceedSixteenDealer = new Dealer(
            new Cards(List.of(new Card(Suit.CLOVER, Rank.TWO), new Card(Suit.HEART, Rank.JACK))));

        assertAll(
            () -> assertThat(exceedSixteenDealer.checkExceedSixteen()).isTrue(),
            () -> assertThat(notExceedSixteenDealer.checkExceedSixteen()).isFalse()
        );
    }

    @Test
    void 딜러가_카드를_뽑는다() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.CLOVER, Rank.JACK))));
        Card drawCard = new Card(Suit.HEART, Rank.FOUR);
        List<Card> providedCards = List.of(drawCard);

        Dealer newDealer = dealer.drawCard(providedCards);
        Dealer expectedDealer = new Dealer(new Cards(
            List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.CLOVER, Rank.JACK), drawCard)));
        assertThat(newDealer).isEqualTo(expectedDealer);
    }

    @Test
    void 딜러가_가진_카드리스트의_합계가_21초과이면_true_아니면_false를_반환한다() {
        Dealer exceedDealer = new Dealer(new Cards(
            List.of(
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.JACK),
                new Card(Suit.HEART, Rank.FOUR))));

        Dealer notExceedDealer = new Dealer(new Cards(
            List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.DIAMOND, Rank.JACK))));

        assertAll(
            () -> assertThat(exceedDealer.checkExceedTwentyOne()).isTrue(),
            () -> assertThat(notExceedDealer.checkExceedTwentyOne()).isFalse()
        );
    }

    @Test
    void 딜러가_가진_카드리스트의_총합을_반환한다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR))));

        assertThat(dealer.getTotalRankSum()).isEqualTo(22);
    }

    @Test
    void 딜러가_가진_첫번쨰_카드를_반환한다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(
                        new Card(Suit.HEART, Rank.EIGHT),
                        new Card(Suit.CLOVER, Rank.SEVEN)
                )));

        Card expected = new Card(Suit.HEART, Rank.EIGHT);

        assertThat(dealer.getInitialCard()).isEqualTo(expected);
    }
}
