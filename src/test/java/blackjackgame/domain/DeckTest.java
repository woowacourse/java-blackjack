package blackjackgame.domain;

import static blackjackgame.domain.CardFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Deck;

class DeckTest {
    @DisplayName("덱에 넣은 순서대로 카드를 뽑는지 확인한다.")
    @Test
    void Should_RemoveFirstCard_When_PickOneCard() {
        List<Card> sampleCards = List.of(CLOVER_FIVE, CLOVER_NINE);
        Deck deck = new Deck(sampleCards);

        assertThat(deck.pickOne()).isEqualTo(CLOVER_FIVE);
        assertThat(deck.pickOne()).isEqualTo(CLOVER_NINE);
    }

    @DisplayName("카드를 전부 뽑으면 다시 카드를 생성하는지 확인한다.")
    @Test
    void Should_ReInitializeCards_When_pickAllCards() {
        Deck deck = new Deck();

        assertDoesNotThrow(() -> {
            for (int i = 0; i < 100; i++) {
                deck.firstPickCards();
                deck.pickOne();
            }
        });
    }
}
