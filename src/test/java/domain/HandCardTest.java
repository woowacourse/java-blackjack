package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.HandCard;
import domain.deck.CardDeck;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandCardTest {
    private HandCard handCard;


    @BeforeEach
    void setup(){
        Card two = new Card(CardRank.TWO, CardSuit.CLOVER);
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
        assertThat(result).isEqualTo(21);
    }


    @Test
    @DisplayName("손에 있는 카드 버스트")
    void 보유카드_버스트(){
        //given
        Card aceClover = new Card(CardRank.ACE, CardSuit.CLOVER); // A
        Card aceHeart = new Card(CardRank.ACE, CardSuit.HEART); // A
        Card five = new Card(CardRank.FIVE, CardSuit.CLOVER); // 5
        Card jack = new Card(CardRank.JACK, CardSuit.CLOVER); //J
        handCard.addCard(aceClover);
        handCard.addCard(aceHeart);
        handCard.addCard(five);
        handCard.addCard(jack);

        //when
        int result = handCard.cardCalculator();

        //then
        assertThat(result).isEqualTo(0);
    }
    
    @Test
    @DisplayName("카드 합 정상 계산")
    void 카드_드로우_정상_계산(){
        //given
        Card aceHeart = new Card(CardRank.ACE, CardSuit.HEART); // A
        Card two = new Card(CardRank.TWO, CardSuit.CLOVER); // 2
        StubDeck sd = new StubDeck(List.of(aceHeart, two));
        handCard.addCard(sd.deal());
        handCard.addCard(sd.deal());
        
        //when
        int result = handCard.cardCalculator();

        //then
        assertThat(result).isEqualTo(18);
    }

    
}
