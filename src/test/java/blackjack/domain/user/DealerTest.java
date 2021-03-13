package blackjack.domain.user;

import blackjack.domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {

    @DisplayName("딜러 draw 메소드 테스트 : 16 이하일 때")
    @Test
    void draw_underStayLimit_addCard() {
        User dealer = new Dealer(underStayLimitCards());
        Deck deck = new Deck(CardGenerator.makeShuffledNewDeck());
        assertTrue(dealer.draw(deck));
    }

    @DisplayName("딜러 draw 메소드 테스트 : 16 이상일 때 ")
    @Test
    void draw_overStayLimit_noChange() {
        User dealer = new Dealer(overStayLimitCards());
        Deck deck = new Deck(CardGenerator.makeShuffledNewDeck());
        assertFalse(dealer.draw(deck));
    }

    private List<Card> underStayLimitCards() {
        return Stream.<Card>builder()
            .add(new Card(Denomination.EIGHT, Suit.HEARTS)).add(new Card(Denomination.TWO, Suit.HEARTS))
            .build()
            .collect(Collectors.toList());
    }

    private List<Card> overStayLimitCards() {
        return Stream.<Card>builder()
            .add(new Card(Denomination.EIGHT, Suit.HEARTS)).add(new Card(Denomination.NINE, Suit.HEARTS))
            .build()
            .collect(Collectors.toList());
    }
}