package domain.generator;

import static org.assertj.core.api.Assertions.*;

import domain.card.Card;
import domain.card.CardNumberType;
import domain.card.CardType;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("모든 참여자에게 기본적으로 2장씩 배분한다.")
    @Test
    void test33() {
        //given
        CardGiver cardGiver = new CardGiver(new TestRandomGenerator());
        Dealer dealer = Dealer.createEmpty();
        Player player = new Player("mimi", Hand.createEmpty());

        List<Participant> participants = List.of(dealer, player);

        //when
        cardGiver.giveDefaultTo(participants);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealer.getCards()).isEqualTo(new Hand(List.of(
                    new Card(CardNumberType.FIVE, CardType.SPACE),
                    new Card(CardNumberType.SIX, CardType.CLOVER)
            )));
            softly.assertThat(player.getCards()).isEqualTo(new Hand(List.of(
                    new Card(CardNumberType.SEVEN, CardType.CLOVER),
                    new Card(CardNumberType.THREE, CardType.CLOVER)
            )));
        });
    }

    @DisplayName("추가 카드를 받는다.")
    @Test
    void test34() {
        //given
        Player player = new Player("mimi", Hand.createEmpty());
        CardGiver cardGiver = new CardGiver(new TestRandomGenerator());

        //when
        cardGiver.giveAdditionalCard(player);

        //then
        assertThat(player.getCards()).isEqualTo(new Hand(List.of(
                new Card(CardNumberType.FIVE, CardType.SPACE)
        )));
    }
}
