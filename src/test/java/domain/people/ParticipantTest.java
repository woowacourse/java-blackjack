package domain.people;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

class ParticipantTest {
    private Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant(
            new ArrayList<>(
                List.of(
                    Card.from(Suit.HEART, Rank.TWO),
                    Card.from(Suit.DIAMOND, Rank.KING))), "Leo");
    }

    @Test
    @DisplayName("참가자가 카드를 받는다.")
    void receiveCard() {
        participant.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
        assertThat(participant.fetchHand().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("참가자의 handValue를 계산한다.")
    void getHandValue() {
        assertThat(participant.fetchHandValue()).isEqualTo(12);
    }

    @Test
    @DisplayName("딜러는 유일하다.")
    void equalsTest() {
        assertThat(new Dealer().equals(new Dealer())).isTrue();
    }

    @Test
    @DisplayName("같은 이름의 참가자는 하나만 존재한다.")
    void eqaulsTestWhenDifferentHand() {
        assertThat(
            new Participant(
                new ArrayList<>(
                    List.of(Card.from(Suit.HEART, Rank.TWO),
                        Card.from(Suit.DIAMOND, Rank.KING))), "Leo")
                .equals(
                    new Participant(
                        new ArrayList<>(
                            List.of(Card.from(Suit.HEART, Rank.FOUR),
                                Card.from(Suit.DIAMOND, Rank.TWO))), "Leo")))
            .isTrue();
    }
}
