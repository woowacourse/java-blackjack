package participant;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HandTest {

    Hand dummyHand;
    Card card1 = new Card(Rank.FIVE, Pattern.CLOVER);
    Card card2 = new Card(Rank.TWO, Pattern.SPADE);

    @BeforeEach
    void init() {
        dummyHand = new Hand();
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);
    }

    @Test
    void 핸드는_카드를_받을_수_있다() {
        int size = dummyHand.getHandSize();
        dummyHand.addCard(new Card(Rank.FOUR, Pattern.DIAMOND));
        assertThat(dummyHand.getHandSize()).isEqualTo(size + 1);
    }

    @Test
    void 에이스를_제외한_핸드의_총합을_계산할_수_있다() {
        int expectedScore = card1.getCardScore() + card2.getCardScore();
        int totalScore = dummyHand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @Test
    void 핸드의_총합을_에이스를_11로_간주하여_계산할_수_있다() {
        Card card = new Card(Rank.ACE, Pattern.DIAMOND);
        dummyHand.addCard(card);

        int expectedScore = card1.getCardScore() + card2.getCardScore() + card.getCardScore(); //18
        int totalScore = dummyHand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @Test
    void 핸드의_총합을_에이스를_1로_간주하여_계산할_수_있다() {
        Card card3 = new Card(Rank.ACE, Pattern.DIAMOND);
        Card card4 = new Card(Rank.TEN, Pattern.SPADE);
        dummyHand.addCard(card3);
        dummyHand.addCard(card4);

        int expectedScore = 1 + card1.getCardScore() + card2.getCardScore() + card4.getCardScore(); //18
        int totalScore = dummyHand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("에이스가 핸드에 여러 장 있어도 가장 유리하게 계산한다")
    void calculateTotalScore_WhenHandHasMultipleAces_UsesMostAdvantageousScore(){
        Card card3 = new Card(Rank.ACE, Pattern.DIAMOND);
        Card card4 = new Card(Rank.TEN, Pattern.SPADE);
        Card card5 = new Card(Rank.ACE, Pattern.HEART);
        dummyHand.addCard(card3);
        dummyHand.addCard(card4);
        dummyHand.addCard(card5);

        int expectedScore = 1 + 1 + card1.getCardScore() + card2.getCardScore() + card4.getCardScore(); //19
        int totalScore = dummyHand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }
}
