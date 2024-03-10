package blackjack.domain;

import blackjack.domain.card.TrumpCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.TrumpCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카드 손패")
public class HandTest {

    private final TrumpCard trumpCardFourSpade = fourSpadeTrumpCard();
    private final TrumpCard trumpCardFiveSpade = fiveSpadeTrumpCard();

    @DisplayName("패에 카드를 추가한다.")
    @Test
    void addCard() {
        //given
        Hand hand = new Hand();

        //when
        hand.add(trumpCardFourSpade, trumpCardFiveSpade);

        //then
        assertThat(hand.getCards())
                .contains(trumpCardFourSpade, trumpCardFiveSpade);
    }

    @DisplayName("패에 있는 카드들의 점수를 반환한다.")
    @Test
    void calculateScore() {
        //given
        Hand hand = new Hand();

        //when
        hand.add(trumpCardFourSpade, trumpCardFiveSpade);

        //then
        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @DisplayName("에이스가 포함된 패의 점수를 반환한다.")
    @Test
    void calculateScoreWithAce() {
        //given
        TrumpCard trumpCardAceClover = aceCloverTrumpCard();
        TrumpCard trumpCardAceDiamond = aceDiamondTrumpCard();
        TrumpCard trumpCardAceSpade = aceSpadeTrumpCard();
        TrumpCard trumpCardKingSpade = fiveSpadeKingCard();

        //when
        Hand hand1 = new Hand();
        hand1.add(trumpCardAceClover, trumpCardAceDiamond);

        Hand hand2 = new Hand();
        hand2.add(trumpCardAceClover, trumpCardAceDiamond, trumpCardAceSpade, trumpCardFourSpade);

        Hand hand3 = new Hand();
        hand3.add(trumpCardKingSpade, trumpCardFourSpade, trumpCardAceClover, trumpCardAceDiamond, trumpCardAceSpade);

        //then
        assertThat(hand1.calculateScore()).isEqualTo(12);
        assertThat(hand2.calculateScore()).isEqualTo(17);
        assertThat(hand3.calculateScore()).isEqualTo(17);
    }
}
