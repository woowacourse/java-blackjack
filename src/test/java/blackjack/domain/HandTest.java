package blackjack.domain;

import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.TrumpCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.TrumpCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("카드 손패")
public class HandTest {

    private final TrumpCard trumpCardFourSpade = fourSpadeTrumpCard();
    private final TrumpCard trumpCardFiveSpade = fiveSpadeTrumpCard();

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @DisplayName("패에 카드를 추가한다.")
    @Test
    void addCard() {
        //when
        hand.add(trumpCardFourSpade, trumpCardFiveSpade);

        //then
        assertThat(hand.getCards())
                .contains(trumpCardFourSpade, trumpCardFiveSpade);
    }

    @DisplayName("패에 있는 카드들의 점수를 반환한다.")
    @Test
    void calculateScore() {
        //when
        hand.add(trumpCardFourSpade, trumpCardFiveSpade);

        //then
        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @DisplayName("패에 있는 첫 번째 카드를 반환한다.")
    @Test
    void getFirstHandCard() {
        //when
        hand.add(trumpCardFourSpade, trumpCardFiveSpade);

        //then
        assertThat(hand.getFirstCard()).isEqualTo(new TrumpCard(Rank.FOUR, Suit.SPADE));
    }

    @DisplayName("패가 비어있으면 첫 번째 카드 반환 시도 시 예외가 발생한다.")
    @Test
    void getFirstHandCardException() {
        //when & then
        assertThatThrownBy(() -> hand.getFirstCard())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("에이스가 포함된 패의 점수를 반환한다.")
    @Test
    void calculateScoreWithAce() {
        //given
        TrumpCard trumpCardAceClover = aceCloverTrumpCard();
        TrumpCard trumpCardAceDiamond = aceDiamondTrumpCard();
        TrumpCard trumpCardAceSpade = aceSpadeTrumpCard();
        TrumpCard trumpCardKingSpade = kingSpadeTrumpCard();

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

    @DisplayName("카드 패의 버스트 여부를 반환한다.")
    @Test
    void isBust() {
        //given
        hand.add(threeSpadeTrumpCard(), kingSpadeTrumpCard(), trumpCardFourSpade, fiveSpadeTrumpCard());

        //when & then
        assertThat(hand.isBust()).isTrue();
    }

    @DisplayName("카드 패의 블랙잭 여부를 반환한다.")
    @Test
    void isBlackjack() {
        //given
        hand.add(kingSpadeTrumpCard(), aceCloverTrumpCard());

        //when & then
        assertThat(hand.isBlackjack()).isTrue();
    }
}
