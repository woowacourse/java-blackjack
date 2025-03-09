package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Rank;
import domain.card.Shape;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("덱에서 카드 한 장을 가져올 수 있어야 한다.")
    void should_create_cardDeck() {
        // given
        Card heartOne = new Card(Shape.HEART, Rank.ONE);
        CardDeck cardDeck = new CardDeck(List.of(heartOne));

        // when
        Card result = cardDeck.getAndRemoveFrontCard();

        // when
        assertThat(result).isEqualTo(heartOne);
    }

    @Test
    @DisplayName("덱에서 카드를 가져올 때, 기존 카드의 크기가 한 장 줄어들어야 한다.")
    void when_get_card_from_deck_then_cards_size_1_minus() {
        // given
        Card heartOne = new Card(Shape.HEART, Rank.ONE);
        CardDeck cardDeck = new CardDeck(List.of(heartOne));

        // when
        cardDeck.getAndRemoveFrontCard();

        // then
        Deque<Card> deck = cardDeck.getDeck();
        assertThat(deck.size()).isEqualTo(0);
    }
}
