package model;

import exception.EmptyDeckException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    @Test
    void drawTest() {
        Deck deck = new Deck(CardFactory.createCardList());

        assertThatThrownBy(()->{
            for(int i=0;i<53;i++){
                deck.draw();
            }
        }).isInstanceOf(EmptyDeckException.class).hasMessageMatching("더 이상 draw 할 카드가 존재하지 않습니다.");

    }
}
