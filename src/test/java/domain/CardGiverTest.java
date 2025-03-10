package domain;

import static org.assertj.core.api.Assertions.*;
import static util.ExceptionConstants.*;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.AnswerType;

public class CardGiverTest {

    static class TestRandomGenerator implements RandomGenerator<Card> {
        Card expectedCard1 = new Card(CardNumberType.FIVE, CardType.SPACE);
        Card expectedCard3 = new Card(CardNumberType.SIX, CardType.CLOVER);
        Card expectedCard4 = new Card(CardNumberType.SEVEN, CardType.CLOVER);
        Card expectedCard5 = new Card(CardNumberType.THREE, CardType.CLOVER);
        List<Card> testCards = List.of(expectedCard1, expectedCard3, expectedCard4, expectedCard5);
        int index = 0;

        @Override
        public Card generate() {
            return testCards.get(index++);
        }
    }

//    @DisplayName("카드를 2장 배분한다")
//    @Test
//    void test1() {
//        // given
//        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
//        CardGiver cardGiver = new CardGiver(randomGenerator);
//
//        Card expectedCard1 = new Card(CardNumberType.FIVE, CardType.SPACE);
//        Card expectedCard2 = new Card(CardNumberType.SIX, CardType.CLOVER);
//
//        //when
//        Cards cards = cardGiver.giveDefault();
//
//        //then
//        assertThat(cards.getCards()).containsExactly(expectedCard1, expectedCard2);
//    }


    @DisplayName("모든 참여자에게 기본적으로 2장씩 배분한다.")
    @Test
    void test33() {
        //given
        CardGiver cardGiver = new CardGiver(new TestRandomGenerator());
        Dealer dealer = Dealer.createEmpty();
        Player player = new Player("mimi", Cards.createEmpty());

        List<Participant> participants = List.of(dealer, player);
        //when
        cardGiver.giveDefaultTo(participants);

        //then
        SoftAssertions.assertSoftly(softly ->{
            softly.assertThat(dealer.cards).isEqualTo(new Cards(List.of(
                    new Card(CardNumberType.FIVE, CardType.SPACE),
                    new Card(CardNumberType.SIX, CardType.CLOVER)
            )));
            softly.assertThat(player.cards).isEqualTo(new Cards(List.of(
                    new Card(CardNumberType.SEVEN, CardType.CLOVER),
                    new Card(CardNumberType.THREE, CardType.CLOVER)
            )));
        });
    }

    @DisplayName("사용자 답변이 YES이면 추가 카드를 받는다.")
    @Test
    void test34() {
        //given
        Player player = new Player("mimi", Cards.createEmpty());
        AnswerType answerType = AnswerType.YES;

        CardGiver cardGiver = new CardGiver(new TestRandomGenerator());
        //when
        cardGiver.giveAdditionalCard(player, answerType);
        //then
        assertThat(player.cards).isEqualTo(new Cards(List.of(
                new Card(CardNumberType.FIVE, CardType.SPACE)
        )));
    }

    @DisplayName("사용자 답변이 NO이면 추가 카드를 받지 않는다.")
    @Test
    void test35() {
        //given
        Player player = new Player("mimi", Cards.createEmpty());
        AnswerType answerType = AnswerType.NO;

        CardGiver cardGiver = new CardGiver(new TestRandomGenerator());
        //when
        cardGiver.giveAdditionalCard(player, answerType);
        //then
        assertThat(player.cards).isEqualTo(Cards.createEmpty());
    }
}
