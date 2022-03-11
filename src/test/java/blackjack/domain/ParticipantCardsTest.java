package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.card.Suit;

public class ParticipantCardsTest {

    @Test
    @DisplayName("참가자 카드 생성")
    void createParticipantCards() {
        List<Card> cards = List.of(new Card(Suit.DIAMOND, Denomination.ACE)
            , new Card(Suit.DIAMOND, Denomination.JACK));

        ParticipantCards participantCards = new ParticipantCards(cards);

        assertThat(participantCards.getCards().containsAll(cards)).isTrue();
    }

    @Test
    @DisplayName("에이스 없는 경우 점수 계산")
    void calculateScoreNotContainAce() {
        List<Card> cards = List.of(new Card(Suit.DIAMOND, Denomination.FIVE)
            , new Card(Suit.DIAMOND, Denomination.JACK));
        ParticipantCards participantCards = new ParticipantCards(cards);

        assertThat(participantCards.calculateScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("에이스 있는 경우 점수 계산")
    void calculateScoreContainAce() {
        List<Card> cards = List.of(new Card(Suit.DIAMOND, Denomination.ACE)
            , new Card(Suit.DIAMOND, Denomination.JACK));

        ParticipantCards participantCards = new ParticipantCards(cards);

        assertThat(participantCards.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("에이스 2개 있는 경우 점수 계산")
    void calculateScoreContainAce2() {
        List<Card> cards = List.of(new Card(Suit.DIAMOND, Denomination.ACE)
            , new Card(Suit.CLOVER, Denomination.ACE));

        ParticipantCards participantCards = new ParticipantCards(cards);

        assertThat(participantCards.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 3개 있는 경우 점수 계산")
    void calculateScoreContainAce3() {
        List<Card> cards = List.of(new Card(Suit.DIAMOND, Denomination.ACE)
            , new Card(Suit.CLOVER, Denomination.ACE)
            , new Card(Suit.HEART, Denomination.ACE));

        ParticipantCards participantCards = new ParticipantCards(cards);

        assertThat(participantCards.calculateScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("숫자 5와 에이스 2개 있는 경우 점수 계산")
    void calculateScoreContainAce4() {
        List<Card> cards = List.of(new Card(Suit.DIAMOND, Denomination.FIVE)
            , new Card(Suit.CLOVER, Denomination.ACE)
            , new Card(Suit.HEART, Denomination.ACE));

        ParticipantCards participantCards = new ParticipantCards(cards);

        assertThat(participantCards.calculateScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("숫자 10 두개와 에이스 1개 있는 경우 점수 계산")
    void calculateScoreContainAce5() {
        List<Card> cards = List.of(new Card(Suit.DIAMOND, Denomination.TEN)
            , new Card(Suit.CLOVER, Denomination.TEN)
            , new Card(Suit.HEART, Denomination.ACE));

        ParticipantCards participantCards = new ParticipantCards(cards);

        assertThat(participantCards.calculateScore()).isEqualTo(21);
    }
}
