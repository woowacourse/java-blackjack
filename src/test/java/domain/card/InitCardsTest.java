package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitCardsTest {

    Card card_A = Card.getCard(Rank.RANK_A, Suit.HEART);
    Card card_6 = Card.getCard(Rank.RANK_6, Suit.SPADE);
    Card card_Q = Card.getCard(Rank.RANK_Q, Suit.DIAMOND);
    Card card_9 = Card.getCard(Rank.RANK_9, Suit.SPADE);
    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(List.of(card_A, card_6, card_Q, card_9));
    }

    @Test
    @DisplayName("덱에서 카드를 뽑아 초기화 카드를 만들고 반환하는 기능")
    void getInitCards() {
        InitCards initCards = new InitCards(deck);
        assertThat(initCards.getInitCards()).containsExactly(card_A, card_6);
    }
}
