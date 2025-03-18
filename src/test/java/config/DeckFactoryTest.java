package config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckFactoryTest {
    @Test
    @DisplayName("카드 덱 생성 테스트")
    void createCardDeckTest(){
        //given
        DeckFactory deckFactory = new DeckFactory();
        Deck deck = deckFactory.create();

        //when-then
        assertEquals(52, deck.getCards().size());
    }
}
