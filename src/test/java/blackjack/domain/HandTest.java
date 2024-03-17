package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.fixture.CardFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("카드 손패")
public class HandTest {

    private final Card cardFourSpade = CardFixture.FOUR_SPADE_CARD.getCard();
    private final Card cardFiveSpade = CardFixture.FIVE_SPADE_CARD.getCard();
    private final Card cardAceClover = CardFixture.ACE_CLOVER_CARD.getCard();
    private final Card cardAceDiamond = CardFixture.ACE_DIAMOND_CARD.getCard();
    private final Card cardAceSpade = CardFixture.ACE_SPADE_CARD.getCard();
    private final Card cardKingSpade = CardFixture.KING_SPADE_CARD.getCard();
    private final Card cardThreeSpade = CardFixture.THREE_SPADE_CARD.getCard();

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @DisplayName("패에 카드를 추가한다.")
    @Test
    void addCard() {
        // when
        hand.add(cardFourSpade, cardFiveSpade);

        // then
        assertThat(hand.getCards())
                .contains(cardFourSpade, cardFiveSpade);
    }

    @DisplayName("패에 있는 카드들의 점수를 반환한다.")
    @Test
    void calculateScore() {
        // given
        hand.add(cardFourSpade, cardFiveSpade);

        // when
        long actual = hand.calculateScore();

        // then
        assertThat(actual).isEqualTo(9);
    }

    @DisplayName("패에 있는 첫 번째 카드를 반환한다.")
    @Test
    void getFirstHandCard() {
        // given
        hand.add(cardFourSpade, cardFiveSpade);

        // when
        Card actual = hand.getFirstCard();

        // then
        assertThat(actual).isEqualTo(new Card(Rank.FOUR, Suit.SPADE));
    }

    @DisplayName("패가 비어있으면 첫 번째 카드 반환 시도 시 예외가 발생한다.")
    @Test
    void getFirstHandCardException() {
        // when & then
        assertThatThrownBy(() -> hand.getFirstCard())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("에이스가 포함된 패의 점수를 반환한다.")
    @Test
    void calculateScoreWithAce() {
        // given
        Hand hand1 = new Hand();
        hand1.add(cardAceClover, cardAceDiamond);

        Hand hand2 = new Hand();
        hand2.add(cardAceClover, cardAceDiamond, cardAceSpade, cardFourSpade);

        Hand hand3 = new Hand();
        hand3.add(cardKingSpade, cardFourSpade, cardAceClover, cardAceDiamond, cardAceSpade);

        // when
        long actual1 = hand1.calculateScore();
        long actual2 = hand2.calculateScore();
        long actual3 = hand3.calculateScore();

        // then
        assertAll(
                () -> assertThat(actual1).isEqualTo(12),
                () -> assertThat(actual2).isEqualTo(17),
                () -> assertThat(actual3).isEqualTo(17)
        );
    }

    @DisplayName("카드 패의 버스트 여부를 반환한다.")
    @Test
    void isBust() {
        // given
        hand.add(cardThreeSpade, cardKingSpade, cardFourSpade, cardFiveSpade);

        // when & then
        assertThat(hand.isBust()).isTrue();
    }

    @DisplayName("카드 패의 블랙잭 여부를 반환한다.")
    @Test
    void isBlackjack() {
        // given
        hand.add(cardKingSpade, cardAceClover);

        // when & then
        assertThat(hand.isBlackjack()).isTrue();
    }
}
