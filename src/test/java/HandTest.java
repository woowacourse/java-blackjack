import domain.Card;
import domain.Hand;
import domain.Pattern;
import domain.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HandTest {
    @Test
    void 핸드는_카드를_받을_수_있다() {
        Hand hand = new Hand();
        Integer size = hand.getHandSize();
        hand.addCard(new Card(Rank.FIVE, Pattern.DIAMOND));
        assertThat(hand.getHandSize()).isEqualTo(size + 1);
    }

    @Test
    void 에이스를_제외한_핸드의_총합을_계산할_수_있다() {
        Hand hand = new Hand();
        Card card1 = new Card(Rank.FIVE, Pattern.DIAMOND);
        Card card2 = new Card(Rank.SEVEN, Pattern.DIAMOND);
        hand.addCard(card1);
        hand.addCard(card2);

        Integer expectedScore = card1.getCardScore() + card2.getCardScore();
        Integer totalScore = hand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @Test
    void 핸드의_총합을_에이스를_11로_간주하여_계산할_수_있다() {
        Hand hand = new Hand();
        Card card1 = new Card(Rank.ACE, Pattern.DIAMOND);
        Card card2 = new Card(Rank.SEVEN, Pattern.DIAMOND);
        hand.addCard(card1);
        hand.addCard(card2);

        Integer expectedScore = card1.getCardScore() + card2.getCardScore(); //18
        Integer totalScore = hand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @Test
    void 핸드의_총합을_에이스를_1로_간주하여_계산할_수_있다() {
        Hand hand = new Hand();
        Card card1 = new Card(Rank.ACE, Pattern.DIAMOND);
        Card card2 = new Card(Rank.SEVEN, Pattern.DIAMOND);
        Card card3 = new Card(Rank.TEN, Pattern.DIAMOND);
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);

        Integer expectedScore = 1 + card2.getCardScore() + card3.getCardScore(); //18
        Integer totalScore = hand.calculateTotalScore();

        assertThat(totalScore).isEqualTo(expectedScore);
    }
}
