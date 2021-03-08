package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardsTest {
    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
        cards.addCard(new Card(CardPattern.SPADE, CardNumber.JACK));
    }

    @Test
    @DisplayName("새로운 카드 생성 확인")
    void addNewCard() {
        assertEquals(1, cards.getCards().size());
    }

    @Test
    @DisplayName("전체 포인트 계산 테스트")
    void addAllPoint() {
        assertEquals(10, cards.calculateJudgingPoint());
    }

    @Test
    @DisplayName("에이스 존재할 때 전체 포인트 계산 테스트")
    void addAllPointWithAce() {
        cards.addCard(new Card(CardPattern.SPADE, CardNumber.FIVE));
        cards.addCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        assertEquals(16, cards.addAcePoint());
    }

    @Test
    @DisplayName("에이스기 11이어야 할 때 전체 포인트 계산 테스트")
    void addAllPointWithAceEleven() {
        cards.addCard(new Card(CardPattern.HEART, CardNumber.QUEEN));
        cards.addCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        assertEquals(21, cards.calculateJudgingPoint());
    }
}
