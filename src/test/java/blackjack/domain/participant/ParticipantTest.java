package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParticipantTest {

    private Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant();
    }

    @DisplayName("참가자는 카드를 받으면 가지고 있는 카드의 개수가 1증가한다.")
    @Test
    void increaseNumberOfCardWhenGivenCard() {
        // given
        Card card = new Card(Rank.ACE, Suit.DIAMOND);

        // when
        participant.receiveCard(card);

        // then
        assertThat(participant.getCards().size()).isEqualTo(1);
    }

    @DisplayName("참가자는 자신이 가진 모든 카드의 Rank합을 반환한다.")
    @Test
    void returnsSumOfRank() {
        // given
        Rank five = Rank.FIVE;
        Rank eight = Rank.EIGHT;
        Rank seven = Rank.SEVEN;
        int givenSum = five.getValue() + eight.getValue() + seven.getValue();

        Card fiveClover = new Card(five, Suit.CLOVER);
        Card eightSpade = new Card(eight, Suit.SPADE);
        Card sevenSpade = new Card(seven, Suit.SPADE);

        participant.receiveCard(fiveClover);
        participant.receiveCard(eightSpade);
        participant.receiveCard(sevenSpade);

        // when & then
        assertThat(participant.calculateSumOfRank()).isEqualTo(givenSum);
    }

    @DisplayName("참가자가 가진 카드 중에 에이스가 2장있다면 1장만 11로 계산한다.")
    @Test
    void calculateAceToElevenAtOnce() {
        // given
        Rank ace = Rank.ACE;

        Card aceClover = new Card(ace, Suit.CLOVER);
        Card aceSpade = new Card(ace, Suit.SPADE);
        Card aceHeart = new Card(ace, Suit.HEART);

        participant.receiveCard(aceClover);
        participant.receiveCard(aceSpade);
        participant.receiveCard(aceHeart);

        // when & then
        assertThat(participant.calculateSumOfRank()).isEqualTo(13);
    }

    @DisplayName("참가자마다 정해진 경계와 카드 숫자의 합을 비교해 boolean값을 반환한다.")
    @Test
    void returnsBooleanCompareSumOfCardsAndParticipantBoundary() {
        // given
        int sumOfCardByPlayer = 21;

        Card tenDiamond = new Card(Rank.TEN, Suit.DIAMOND);
        Card jackDiamond = new Card(Rank.JACK, Suit.DIAMOND);
        participant.receiveCard(tenDiamond);
        participant.receiveCard(jackDiamond);

        // when
        boolean isUnderThanBoundary = participant.isUnderThanBoundary(sumOfCardByPlayer);

        // then
        assertThat(isUnderThanBoundary).isTrue();
    }
}
