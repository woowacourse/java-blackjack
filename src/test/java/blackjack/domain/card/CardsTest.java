package blackjack.domain.card;

import static blackjack.Fixtures.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("에이스가 없는 경우의 점수를 계산한다.")
    @Test
    public void testCalculateDefaultCondition() {
        //given
        List<Card> initCards = List.of(CLOVER_FIVE, HEART_KING);

        Cards cards = createCards(initCards);

        //when
        int score = cards.calculateScore();

        //then
        Assertions.assertThat(score).isEqualTo(15);
    }

    @DisplayName("에이스가 포함된 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithAce() {
        //given
        List<Card> initCards = List.of(CLOVER_FIVE, DIAMOND_ACE);

        Cards cards = createCards(initCards);

        //when
        int score = cards.calculateScore();

        //then
        Assertions.assertThat(score).isEqualTo(16);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce() {
        //given
        List<Card> initCards = List.of(HEART_ACE, CLOVER_ACE, SPADE_ACE, DIAMOND_ACE, CLOVER_KING);

        Cards cards = createCards(initCards);

        //when
        int score = cards.calculateScore();

        //then
        Assertions.assertThat(score).isEqualTo(14);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce2() {
        //given
        List<Card> initCards = List.of(SPADE_ACE, DIAMOND_ACE, CLOVER_NINE);

        Cards cards = createCards(initCards);

        //when
        int score = cards.calculateScore();

        //then
        Assertions.assertThat(score).isEqualTo(21);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce3() {
        //given
        List<Card> initCards = List.of(SPADE_FIVE, DIAMOND_FIVE, CLOVER_EIGHT, SPADE_ACE, DIAMOND_ACE, CLOVER_ACE);

        Cards cards = createCards(initCards);

        //when
        int score = cards.calculateScore();

        //then
        Assertions.assertThat(score).isEqualTo(21);
    }

    @DisplayName("블랙잭 넘버와 같은지 여부를 판단하는 메소드 테스트")
    @Test
    public void testIsSameBlackJackNumber() {
        //given
        List<Card> initCards = List.of(SPADE_ACE, DIAMOND_TEN);

        //when
        Cards cards = createCards(initCards);

        int score = cards.calculateScore();

        // then
        Assertions.assertThat(cards.isSameBlackJackScore()).isTrue();
    }

    private Cards createCards(List<Card> initCards) {
        Cards cards = new Cards();

        for (Card initCard : initCards) {
            cards.add(initCard);
        }

        return cards;
    }
}
