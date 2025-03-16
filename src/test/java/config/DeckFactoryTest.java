package config;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckFactoryTest {
    @Test
    @DisplayName("카드 덱 생성 테스트")
    void createCardDeckTest(){
        //given
        DeckFactory deckFactory = new DeckFactory();

        assertThat(deckFactory.create()).isInstanceOf(Deck.class);
    }
}
