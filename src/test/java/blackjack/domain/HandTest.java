package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.TrumpCard;
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
        TrumpCard trumpCardFourDiamond = new TrumpCard(Rank.FOUR, Suit.DIAMOND);
        TrumpCard trumpCardFiveDiamond = new TrumpCard(Rank.FIVE, Suit.DIAMOND);

        //when
        Hand hand = new Hand();
        hand.add(trumpCardFourDiamond);
        hand.add(trumpCardFiveDiamond);

        //then
        assertThat(hand.getCards())
                .contains(trumpCardFourDiamond, trumpCardFiveDiamond);
    }

    @DisplayName("패에 있는 카드들의 점수를 반환한다.")
    @Test
    void calculateScore() {
        //given
        TrumpCard trumpCardFourDiamond = new TrumpCard(Rank.FOUR, Suit.DIAMOND);
        TrumpCard trumpCardFiveDiamond = new TrumpCard(Rank.FIVE, Suit.DIAMOND);

        //when
        Hand hand = new Hand();
        hand.add(trumpCardFourDiamond);
        hand.add(trumpCardFiveDiamond);

        //then
        assertThat(hand.calculateScore())
                .isEqualTo(9);
    }

    @DisplayName("에이스가 포함된 패의 점수를 반환한다.")
    @Test
    void calculateScoreWithAce() {
        //given
        TrumpCard trumpCardAceClover = new TrumpCard(Rank.ACE, Suit.CLOVER);
        TrumpCard trumpCardAceDiamond = new TrumpCard(Rank.ACE, Suit.DIAMOND);
        TrumpCard trumpCardAceSpade = new TrumpCard(Rank.ACE, Suit.SPADE);
        TrumpCard trumpCardFourSpade = new TrumpCard(Rank.FOUR, Suit.SPADE);
        TrumpCard trumpCardKingSpade = new TrumpCard(Rank.KING, Suit.SPADE);

        //when
        Hand hand1 = new Hand();
        hand1.add(trumpCardAceClover, trumpCardAceDiamond);

        Hand hand2 = new Hand();
        hand2.add(trumpCardAceClover, trumpCardAceDiamond, trumpCardAceSpade, trumpCardFourSpade);

        Hand hand3 = new Hand();
        hand3.add(trumpCardKingSpade, trumpCardFourSpade, trumpCardAceClover, trumpCardAceDiamond, trumpCardAceSpade);

        //then
        assertThat(hand1.calculateScore())
                .isEqualTo(12);
        assertThat(hand2.calculateScore())
                .isEqualTo(17);
        assertThat(hand3.calculateScore())
                .isEqualTo(17);
    }
}
