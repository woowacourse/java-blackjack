package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.HandCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandCardTest {

    @Test
    @DisplayName("손에 있는 카드 계산")
    void 보유카드_정상_계산() {
        //given
        Card aceClover = new Card(CardRank.ACE, CardSuit.CLOVER); // A
        Card aceHeart = new Card(CardRank.ACE, CardSuit.HEART); // A
        Card four = new Card(CardRank.FOUR, CardSuit.CLOVER); // 4
        Card two = new Card(CardRank.TWO, CardSuit.CLOVER); // 2
        Card three = new Card(CardRank.THREE, CardSuit.CLOVER); // 3
        HandCard handCard = new HandCard();
        handCard.addCard(two);
        handCard.addCard(three);
        handCard.addCard(aceClover);
        handCard.addCard(aceHeart);
        handCard.addCard(four);

        //when
        int result = handCard.cardCalculate();

        //then
        assertThat(result).isEqualTo(21);
    }
}
