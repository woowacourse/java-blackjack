package domain;

import static org.assertj.core.api.Assertions.*;
import static util.ExceptionConstants.*;
import static view.AnswerType.NO;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ExceptionConstants;
import view.AnswerType;

public class CardGiverTest {

    static class TestRandomGenerator implements RandomGenerator<Card> {
        Card expectedCard1 = new Card(CardNumberType.FIVE, CardType.SPACE);
        Card expectedCard2 = new Card(CardNumberType.FIVE, CardType.SPACE);
        Card expectedCard3 = new Card(CardNumberType.SIX, CardType.CLOVER);
        Card expectedCard4 = new Card(CardNumberType.SEVEN, CardType.CLOVER);
        Card expectedCard5 = new Card(CardNumberType.THREE, CardType.CLOVER);
        List<Card> testCards = List.of(expectedCard1, expectedCard2, expectedCard3, expectedCard4, expectedCard5);
        int index = 0;

        @Override
        public Card generate() {
            return testCards.get(index++);
        }
    }

    @DisplayName("카드를 2장 배분한다")
    @Test
    void test1() {
        // given
        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
        CardGiver cardGiver = new CardGiver(randomGenerator, GivenCards.createEmpty());

        Card expectedCard1 = new Card(CardNumberType.FIVE, CardType.SPACE);
        Card expectedCard2 = new Card(CardNumberType.SIX, CardType.CLOVER);

        //when
        Cards cards = cardGiver.giveDefault();

        //then
        assertThat(cards.getCards()).containsExactly(expectedCard1, expectedCard2);
    }

    @DisplayName("배분할 카드가 2장 미만일 시 예외가 발생한다")
    @Test
    void test19() {
        // given
        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 51; i++) {
            cards.add(new Card(CardNumberType.ACE, CardType.DIAMOND));
        }
        GivenCards givenCards = GivenCards.create(cards);
        CardGiver cardGiver = new CardGiver(randomGenerator, givenCards);
        //when & then
        assertThatThrownBy(
                cardGiver::giveDefault
        ).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(ERROR_HEADER);
    }

    @DisplayName("카드를 랜덤으로 1장 생성하여 배분한다")
    @Test
    void test4() {
        //given
        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
        GivenCards testGivenCards = GivenCards.createEmpty();
        testGivenCards.addUnique(new Card(CardNumberType.FIVE, CardType.SPACE));
        CardGiver cardGiver = new CardGiver(randomGenerator, testGivenCards);
        //when
        Card card = cardGiver.giveOne();
        //then
        assertThat(card).isEqualTo(new Card(CardNumberType.SIX, CardType.CLOVER));
    }

    @DisplayName("모든 참여자에게 기본적으로 2장씩 배분한다.")
    @Test
    void test33() {
        //given
        CardGiver cardGiver = new CardGiver(new TestRandomGenerator(), GivenCards.createEmpty());
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

        CardGiver cardGiver = new CardGiver(new TestRandomGenerator(), GivenCards.createEmpty());
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

        CardGiver cardGiver = new CardGiver(new TestRandomGenerator(), GivenCards.createEmpty());
        //when
        cardGiver.giveAdditionalCard(player, answerType);
        //then
        assertThat(player.cards).isEqualTo(Cards.createEmpty());
    }
}
