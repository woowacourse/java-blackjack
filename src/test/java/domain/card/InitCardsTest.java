package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitCardsTest {

    Card card_A = new Card(Rank.RANK_A, Suit.HEART);
    Card card_6 = new Card(Rank.RANK_6, Suit.SPADE);
    Card card_Q = new Card(Rank.RANK_Q, Suit.DIAMOND);
    Card card_9 = new Card(Rank.RANK_9, Suit.SPADE);
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

    @Test
    @DisplayName("복수의 플레이어를 위한 초기화 카드들을 생성하는 기능")
    void generateInitCardsForPlayers() {
        List<List<Card>> initCardsForPlayers = InitCards.generateInitCardsForPlayers(deck, 2);
        assertThat(initCardsForPlayers.get(0)).containsExactly(card_A, card_6);
        assertThat(initCardsForPlayers.get(1)).containsExactly(card_Q, card_9);
    }
}
