package blackjack.domain.card;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("에이스가 없는 경우의 점수를 계산한다.")
    @Test
    public void testCalculateDefaultCondition() {
        //given
        List<Card> initCards = List.of(
                new Card(CLOVER, FIVE),
                new Card(HEART, KING)
        );

        Cards cards = createCards(initCards);

        //when
        int score = cards.getScore();

        //then
        Assertions.assertThat(score).isEqualTo(15);
    }

    @DisplayName("에이스가 포함된 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithAce() {
        //given
        List<Card> initCards = List.of(
                new Card(CLOVER, FIVE),
                new Card(DIAMOND, ACE)
        );

        Cards cards = createCards(initCards);

        //when
        int score = cards.getScore();

        //then
        Assertions.assertThat(score).isEqualTo(16);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce() {
        //given
        List<Card> initCards = List.of(
                new Card(HEART, ACE),
                new Card(CLOVER, ACE),
                new Card(SPADE, ACE),
                new Card(DIAMOND, ACE),
                new Card(CLOVER, KING)
        );

        Cards cards = createCards(initCards);

        //when
        int score = cards.getScore();

        //then
        Assertions.assertThat(score).isEqualTo(14);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce2() {
        //given
        List<Card> initCards = List.of(
                new Card(SPADE, ACE),
                new Card(DIAMOND, ACE),
                new Card(CLOVER, NINE)
        );

        Cards cards = createCards(initCards);

        //when
        int score = cards.getScore();

        //then
        Assertions.assertThat(score).isEqualTo(21);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce3() {
        //given
        List<Card> initCards = List.of(
                new Card(SPADE, FIVE),
                new Card(DIAMOND, FIVE),
                new Card(CLOVER, EIGHT),
                new Card(SPADE, ACE),
                new Card(DIAMOND, ACE),
                new Card(CLOVER, ACE)
        );

        Cards cards = createCards(initCards);

        //when
        int score = cards.getScore();

        //then
        Assertions.assertThat(score).isEqualTo(21);
    }

    private Cards createCards(List<Card> initCards) {
        Cards cards = new Cards();

        for (Card initCard : initCards) {
            cards.add(initCard);
        }

        return cards;
    }
}
