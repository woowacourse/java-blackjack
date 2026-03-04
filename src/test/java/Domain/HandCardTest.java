package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HandCardTest {
    private HandCard handCard;
    
    public class StebDeck {
        private final List<Card> deck;
        private int index;

        public StebDeck(List<Card> deck){
            this.deck = deck;
            this.index = 0;
        }

        public Card deal(){
            return deck.get(index++);
        }
    }

    @BeforeEach
    void setup(){
        Card two = new Card(CardRank.TWO,CardSuit.ClOVER);
        Card three = new Card(CardRank.THREE, CardSuit.CLOVER);
        handCard = new HandCard();
        handCard.addCard(two);
        handCard.addCard(three);
    }


    @Test
    @DisplayName("손에 있는 카드 계산")
    void 보유카드_정상_계산(){
        //given
        Card aceClover = new Card(CardRank.ACE, CardSuit.CLOVER); // A
        Card aceHeart = new Card(CardRank.ACE, CardSuit.HEART); // A
        Card four = new Card(CardRank.FOUR, CardSuit.CLOVER); // 4
        handCard.addCard(aceClover);
        handCard.addCard(aceHeart);
        handCard.addCard(four);

        //when
        int result = handCard.cardCalculator();

        //then
        assertThat(result).isEquals(21);
    }
    
    @Test
    @DisplayName("카드 합 정상 계산")
    void 카드_드로우_정상_계산(){
        //given
        Card aceHeart = new Card(CardRank.ACE, CardSuit.HEART); // A
        Card two = new Card(CardRank.TWO, CardSuit.CLOVER); // 2
        StebDeck sd = new StebDeck(List.of(aceHeart, two));
        handCard.addCard(sd.deal());
        handCard.addCard(sd.deal());
        
        //when
        int result = handCard.cardCalculator();

        //then
        assertThat(result).isEquals(18);        
    }
}
