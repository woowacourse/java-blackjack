package participant;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HandTest {

    @Test
    @DisplayName("핸드는 카드를 받을 수 있다")
    void addCard_addsCardToHand() {
        Hand dummyHand = new Hand();
        int beforeSize = dummyHand.getHandSize();

        dummyHand.addCard(new Card(Rank.ACE, Pattern.DIAMOND));

        assertThat(dummyHand.getHandSize()).isEqualTo(beforeSize + 1);
    }

    @Test
    @DisplayName("에이스를 제외한 핸드의 총합을 계산할 수 있다")
    void calculateTotalScore_calculatesTotalScoreWithoutAces() {
        Hand dummyHand = new Hand();
        Card card1 = new Card(Rank.QUEEN, Pattern.DIAMOND);
        Card card2 = new Card(Rank.TWO, Pattern.DIAMOND);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);
        int expectedScore = 12;

        int totalScore = dummyHand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("핸드의 총합을 에이스를 11로 간주하여 계산할 수 있다.")
    void calculateTotalScore_calculatesTotalScoreWithAcesAs11() {
        Hand dummyHand = new Hand();
        Card card1 = new Card(Rank.THREE, Pattern.DIAMOND);
        Card card2 = new Card(Rank.ACE, Pattern.DIAMOND);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);
        int expectedScore = 14;

        int totalScore = dummyHand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("핸드의 총합을 에이스를 1로 간주하여 계산할 수 있다.")
    void calculateTotalScore_calculatesTotalScoreWithAcesAs1() {
        Hand dummyHand = new Hand();
        Card card1 = new Card(Rank.ACE, Pattern.DIAMOND);
        Card card2 = new Card(Rank.TEN, Pattern.SPADE);
        Card card3 = new Card(Rank.EIGHT, Pattern.SPADE);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);
        dummyHand.addCard(card3);

        int expectedScore = 19;
        int totalScore = dummyHand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("에이스가 핸드에 여러 장 있어도 가장 유리하게 계산한다")
    void calculateTotalScore_WhenHandHasMultipleAces_UsesMostAdvantageousScore() {
        Hand dummyHand = new Hand();
        Card card1 = new Card(Rank.ACE, Pattern.DIAMOND);
        Card card2 = new Card(Rank.TEN, Pattern.SPADE);
        Card card3 = new Card(Rank.ACE, Pattern.HEART);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);
        dummyHand.addCard(card3);

        int expectedScore = 12;
        int totalScore = dummyHand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }
}
