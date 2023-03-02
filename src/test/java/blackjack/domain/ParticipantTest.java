package blackjack.domain;

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
        Rank rank1 = Rank.FIVE;
        Rank rank2 = Rank.EIGHT;
        Rank rank3 = Rank.SEVEN;
        int givenSum = rank1.getValue() + rank2.getValue() + rank3.getValue();

        Card card1 = new Card(rank1, Suit.CLOVER);
        Card card2 = new Card(rank2, Suit.SPADE);
        Card card3 = new Card(rank3, Suit.SPADE);

        participant.receiveCard(card1);
        participant.receiveCard(card2);
        participant.receiveCard(card3);

        // when & then
        assertThat(participant.calculateSumOfRank()).isEqualTo(givenSum);
    }
}
