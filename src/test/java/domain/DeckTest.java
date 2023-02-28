package domain;


import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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

}
