package domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardBundleTest {

    private CardBundle cardBundle;

    @BeforeEach
    void beforeEach() {
        cardBundle = new CardBundle();
    }

    @DisplayName("일반 카드의 점수를 합산한다")
    @Test
    void 일반_카드의_점수를_합산한다() {
        cardBundle.addCard(new Card(Rank.KING, Suit.SPADE));  // 10
        cardBundle.addCard(new Card(Rank.FIVE, Suit.HEART));  // 5
        assertThat(cardBundle.calculateScore()).isEqualTo(15);
    }

    @DisplayName("ACE가 있고 버스트가 아니면 11로 계산한다")
    @Test
    void ACE가_있고_버스트가_아니면_11로_계산한다() {
        cardBundle.addCard(new Card(Rank.ACE, Suit.SPADE));   // 11
        cardBundle.addCard(new Card(Rank.KING, Suit.HEART));  // 10
        assertThat(cardBundle.calculateScore()).isEqualTo(21);
    }

    @DisplayName("ACE가 있고 버스트면 1로 계산한다")
    @Test
    void ACE가_있고_버스트면_1로_계산한다() {
        cardBundle.addCard(new Card(Rank.ACE, Suit.SPADE));    // 1
        cardBundle.addCard(new Card(Rank.KING, Suit.HEART));   // 10
        cardBundle.addCard(new Card(Rank.FIVE, Suit.DIAMOND)); // 5
        assertThat(cardBundle.calculateScore()).isEqualTo(16);
    }

    @DisplayName("ACE가 여러 장이면 하나만 11로 계산한다")
    @Test
    void ACE가_여러_장이면_하나만_11로_계산한다() {
        cardBundle.addCard(new Card(Rank.ACE, Suit.SPADE));  // 11
        cardBundle.addCard(new Card(Rank.ACE, Suit.HEART));  // 1
        assertThat(cardBundle.calculateScore()).isEqualTo(12);
    }
}
