package blackjack.domain.card;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.*;

class DeckTest {

    @Test
    @DisplayName("Deck을 생성하면 queue에 카드 52장을 채워넣다.")
    void createDeck_thenCardsCountFiftyTwo() {
        //given
        Deck deck = Deck.from(new RandomCardGenerator());

        //when & then
        assertThat(deck)
                .extracting("deck", InstanceOfAssertFactories.collection(ArrayDeque.class))
                .hasSize(52);
    }

    @Test
    @DisplayName("카드를 한장 뽑으면 deck의 size가 하나 줄어든다")
    void drawCard_thenDecreaseDeckSize() {
        //given
        Deck deck = Deck.from(new RandomCardGenerator());

        //when
        deck.distributeCard();

        //then
        assertThat(deck)
                .extracting("deck", InstanceOfAssertFactories.collection(ArrayDeque.class))
                .hasSize(51);
    }
}
