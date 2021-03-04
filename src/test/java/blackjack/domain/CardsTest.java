package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
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
        assertEquals(cards.getCards().size(), 1);
    }

    @Test
    @DisplayName("전체 포인트 계산 테스트")
    void addAllPoint() {
        assertEquals(cards.calculateJudgingPoint(), 10);
    }

    @Test
    @DisplayName("에이스 존재할 때 전체 포인트 계산 테스트")
    void addAllPointWithAce() {
        cards.addCard(new Card(CardPattern.SPADE, CardNumber.FIVE));
        cards.addCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        assertEquals(cards.calculateMaximumPoint(), 16);
    }

    @Test
    @DisplayName("에이스기 11이어야 할 때 전체 포인트 계산 테스트")
    void addAllPointWithAceEleven() {
        cards.addCard(new Card(CardPattern.HEART, CardNumber.QUEEN));
        cards.addCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        assertEquals(cards.calculateJudgingPoint(), 21);
    }
}
