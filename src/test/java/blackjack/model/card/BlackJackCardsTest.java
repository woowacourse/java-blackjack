package blackjack.model.card;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackJackCardsTest {

    @Test
    void 카드_숫자의_합을_반환한다() {
        BlackJackCards blackJackCards = new BlackJackCards(
                List.of(
                        new BlackJackCard(CardType.CLOVER, CardNumber.TWO),
                        new BlackJackCard(CardType.CLOVER, CardNumber.TEN),
                        new BlackJackCard(CardType.CLOVER, CardNumber.ACE)
                )
        );
        List<Integer> expected = List.of(13, 23);

        assertThat(blackJackCards.calculatePossiblePoints()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @CsvSource(value = {"2,true", "1,false"})
    @ParameterizedTest
    void 숫자를_알려주면_카드_개수와_동일한지_알려준다(int size, boolean expected) {
        BlackJackCards blackJackCards = new BlackJackCards(
                List.of(
                        new BlackJackCard(CardType.CLOVER, CardNumber.TWO),
                        new BlackJackCard(CardType.CLOVER, CardNumber.TEN)
                )
        );

        assertThat(blackJackCards.hasSize(size)).isEqualTo(expected);
    }

    @Test
    void 두_개의_카트들을_합친다() {
        BlackJackCards blackJackCards = new BlackJackCards(List.of(new BlackJackCard(CardType.CLOVER, CardNumber.TEN)));
        BlackJackCards otherBlackJackCards = new BlackJackCards(
                List.of(new BlackJackCard(CardType.CLOVER, CardNumber.TEN)));

        blackJackCards.addAll(otherBlackJackCards);

        assertThat(blackJackCards.hasSize(2)).isTrue();
    }

    @Test
    void 카드를_N_개_뽑는다() {
        BlackJackCards blackJackCards = new BlackJackCards(
                List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThat(blackJackCards.pick(2)).hasSize(2);
        assertThat(blackJackCards.getValues()).isEmpty();
    }

    @Test
    void 존재하는_카드_개수보다_더_많은_카드를_뽑을_수_없다() {
        BlackJackCards blackJackCards = new BlackJackCards(
                List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> blackJackCards.pick(3));
    }

    @Test
    void 첫_번째_카드를_반환한다() {
        BlackJackCards blackJackCards = new BlackJackCards(
                List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThat(blackJackCards.getFirst()).isEqualTo(createCard(CardNumber.TEN));
    }

}
