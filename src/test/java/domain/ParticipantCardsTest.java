package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;

class ParticipantCardsTest {
    private ParticipantCards cards;

    @BeforeEach
    void setUp() {
        cards = new ParticipantCards();
        cards.add(new Card(Symbol.EIGHT, Shape.DIAMOND));
        cards.add(new Card(Symbol.TWO, Shape.SPADE));
    }

    @Test
    @DisplayName("카드 점수 계산하는 기능 테스트 - Ace 미포함")
    void calculateScore() {
        assertThat(cards.calculateScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("카드 점수 계산하는 기능 테스트 - Ace 포함")
    void calculateScoreWithAce() {
        cards.add((new Card(Symbol.ACE, Shape.HEART)));
        assertThat(cards.calculateScore()).isEqualTo(21);

    }

}