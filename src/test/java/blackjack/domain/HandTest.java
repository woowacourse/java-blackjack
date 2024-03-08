package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("카드 손패")
public class HandTest {

    @DisplayName("패에 카드를 추가한다.")
    @Test
    void addCard() {
        //given
        Card cardFourDiamond = new Card(Rank.FOUR, Suit.DIAMOND);
        Card cardFiveDiamond = new Card(Rank.FIVE, Suit.DIAMOND);

        //when
        Hand hand = new Hand();
        hand.add(cardFourDiamond);
        hand.add(cardFiveDiamond);

        //then
        assertThat(hand.getCards())
                .contains(cardFourDiamond, cardFiveDiamond);
    }

    @DisplayName("패에 있는 카드들의 점수를 반환한다.")
    @Test
    void calculateScore() {
        //given
        Card cardFourDiamond = new Card(Rank.FOUR, Suit.DIAMOND);
        Card cardFiveDiamond = new Card(Rank.FIVE, Suit.DIAMOND);

        //when
        Hand hand = new Hand();
        hand.add(cardFourDiamond);
        hand.add(cardFiveDiamond);

        //then
        assertThat(hand.calculateScore())
                .isEqualTo(9);
    }

    @DisplayName("에이스가 포함된 패의 점수를 반환한다.")
    @Test
    void calculateScoreWithAce() {
        //given
        Card cardAceClover = new Card(Rank.ACE, Suit.CLOVER);
        Card cardAceDiamond = new Card(Rank.ACE, Suit.DIAMOND);
        Card cardAceSpade = new Card(Rank.ACE, Suit.SPADE);
        Card cardFourSpade = new Card(Rank.FOUR, Suit.SPADE);
        Card cardKingSpade = new Card(Rank.KING, Suit.SPADE);

        //when
        Hand hand1 = new Hand();
        hand1.add(cardAceClover, cardAceDiamond);

        Hand hand2 = new Hand();
        hand2.add(cardAceClover, cardAceDiamond, cardAceSpade, cardFourSpade);

        Hand hand3 = new Hand();
        hand3.add(cardKingSpade, cardFourSpade, cardAceClover, cardAceDiamond, cardAceSpade);

        //then
        assertThat(hand1.calculateScore())
                .isEqualTo(12);
        assertThat(hand2.calculateScore())
                .isEqualTo(17);
        assertThat(hand3.calculateScore())
                .isEqualTo(17);
    }
}
