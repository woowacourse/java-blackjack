package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantCardsTest {

    private List<Card> cards;

    @BeforeEach
    void init() {
        cards = List.of(
            new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.JACK)
        );
    }

    @Test
    @DisplayName("현재 가진 카드가 없을 때 참가자 초기 카드 생성")
    void createParticipantCardsEmpty() {
        ParticipantCards participantCards = new ParticipantCards();

        participantCards.addInitialCards(cards);
        assertThat(participantCards.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("현재 가진 카드가 있을 때 참가자 초기 카드 생성 시 오류")
    void createParticipantCardsNotEmpty() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.addInitialCards(cards);

        assertThatThrownBy(() ->
            participantCards.addInitialCards(cards)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드가 블랙잭인지 확인")
    void isBlackjack() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.addInitialCards(cards);

        assertThat(participantCards.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("참가자 카드 추가")
    void addParticipantCards() {
        ParticipantCards participantCards = new ParticipantCards();

        participantCards.addInitialCards(cards);
        participantCards.addCard(new Card(Suit.CLOVER, Denomination.TEN));
        assertThat(participantCards.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("카드가 버스트인지 확인")
    void isBust() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.addInitialCards(cards);

        participantCards.addCard(new Card(Suit.CLOVER, Denomination.TEN));
        participantCards.addCard(new Card(Suit.CLOVER, Denomination.FIVE));
        assertThat(participantCards.isBust()).isTrue();
    }

    @Test
    @DisplayName("에이스 있는 경우 점수 계산")
    void calculateScoreContainAce() {
        ParticipantCards participantCards = new ParticipantCards();

        participantCards.addInitialCards(cards);
        assertThat(participantCards.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("에이스 2개 있는 경우 점수 계산")
    void calculateScoreContainAce2() {
        ParticipantCards participantCards = new ParticipantCards();

        participantCards.addInitialCards(cards);
        participantCards.addCard(new Card(Suit.CLOVER, Denomination.ACE));
        assertThat(participantCards.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("소지 카드가 없을 때 오류 반환")
    void getFirstCardEmpty() {
        ParticipantCards participantCards = new ParticipantCards();

        assertThatThrownBy(() ->
            participantCards.getFirstCard()
        ).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
