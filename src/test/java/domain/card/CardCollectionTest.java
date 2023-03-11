package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardCollectionTest {
    
    @Test
    @DisplayName("카드를 추가하면 카드의 개수가 1 증가한다.")
    void addCard() {
        CardCollection cardCollection = new CardCollection();
        CardCollection collection = cardCollection.add(new Card(CardNumber.ACE, CardShape.SPADE));
        CardCollection collection2 = collection.add(new Card(CardNumber.ACE, CardShape.SPADE));
        CardCollection collection3 = collection2.add(new Card(CardNumber.ACE, CardShape.SPADE));
        assertThat(collection3.size()).isEqualTo(3);
    }
    
    @Test
    @DisplayName("카드컬렉션은 카드의 점수를 계산한다.")
    void calculateScore() {
        CardCollection cardCollection = new CardCollection();
        CardCollection collection = cardCollection.add(new Card(CardNumber.KING, CardShape.SPADE));
        CardCollection collection2 = collection.add(new Card(CardNumber.KING, CardShape.SPADE));
        CardCollection collection3 = collection2.add(new Card(CardNumber.KING, CardShape.SPADE));
        assertThat(collection3.calculateScore()).isEqualTo(30);
    }
    
    @Test
    @DisplayName("카드컬렉션을 순회할 수 있다.")
    void iterator() {
        CardCollection cardCollection = new CardCollection();
        CardCollection collection = cardCollection.add(new Card(CardNumber.KING, CardShape.SPADE));
        CardCollection collection2 = collection.add(new Card(CardNumber.KING, CardShape.SPADE));
        CardCollection collection3 = collection2.add(new Card(CardNumber.KING, CardShape.SPADE));
        int count = 0;
        for (Card card : collection3) {
            count++;
        }
        assertThat(count).isEqualTo(3);
    }
}