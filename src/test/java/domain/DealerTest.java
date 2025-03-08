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
            new Cards(List.of(new Card(Symbol.CLOVER, Number.EIGHT), new Card(Symbol.HEART, Number.JACK))));

        Dealer notExceedSixteenDealer = new Dealer(
            new Cards(List.of(new Card(Symbol.CLOVER, Number.TWO), new Card(Symbol.HEART, Number.JACK))));

        assertAll(
            () -> assertThat(exceedSixteenDealer.checkDealerNeedsMoreCard()).isFalse(),
            () -> assertThat(notExceedSixteenDealer.checkDealerNeedsMoreCard()).isTrue()
        );
    }

    @Test
    void 딜러가_카드를_뽑는다() {
        Dealer dealer = new Dealer(new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.CLOVER, Number.JACK))));
        Card drawCard = new Card(Symbol.HEART, Number.FOUR);
        List<Card> providedCards = List.of(drawCard);

        Dealer newDealer = dealer.drawCard(providedCards);
        Dealer expectedDealer = new Dealer(new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.CLOVER, Number.JACK), drawCard)));
        assertThat(newDealer).isEqualTo(expectedDealer);
    }

    @Test
    void 딜러가_가진_카드리스트의_합계가_21초과이면_true_아니면_false를_반환한다() {
        Dealer exceedDealer = new Dealer(new Cards(
            List.of(
                new Card(Symbol.DIAMOND, Number.EIGHT),
                new Card(Symbol.DIAMOND, Number.JACK),
                new Card(Symbol.HEART, Number.FOUR))));

        Dealer notExceedDealer = new Dealer(new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.DIAMOND, Number.JACK))));

        assertAll(
            () -> assertThat(exceedDealer.checkExceedBurst()).isTrue(),
            () -> assertThat(notExceedDealer.checkExceedBurst()).isFalse()
        );
    }

    @Test
    void 딜러가_가진_카드리스트의_총합을_반환한다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(
                        new Card(Symbol.DIAMOND, Number.EIGHT),
                        new Card(Symbol.DIAMOND, Number.JACK),
                        new Card(Symbol.HEART, Number.FOUR))));

        assertThat(dealer.getTotalNumberSum()).isEqualTo(22);
    }

    @Test
    void 딜러가_가진_첫번쨰_카드를_반환한다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(
                        new Card(Symbol.HEART, Number.EIGHT),
                        new Card(Symbol.CLOVER, Number.SEVEN)
                )));

        Card expected = new Card(Symbol.HEART, Number.EIGHT);

        assertThat(dealer.getInitialCard()).isEqualTo(expected);
    }

    @Test
    void 카드리스트의_합계가_16초과이면_true_아니면_false를_반환한다() {
        Card card1 = new Card(Symbol.DIAMOND, Number.TWO);
        Card card2 = new Card(Symbol.CLOVER, Number.EIGHT);
        Card card3 = new Card(Symbol.DIAMOND, Number.SIX);
        Card card4 = new Card(Symbol.DIAMOND, Number.ACE);
        List<Card> cardList = List.of(card1, card2, card3);
        Dealer notExceedDealer = new Dealer(new Cards(cardList));

        List<Card> otherCardList = List.of(card1, card2, card3, card4);
        Dealer exceedDealer = new Dealer(new Cards(otherCardList));

        assertAll(
            () -> assertThat(notExceedDealer.checkDealerNeedsMoreCard()).isTrue(),
            () -> assertThat(exceedDealer.checkDealerNeedsMoreCard()).isFalse()
        );
    }
}
