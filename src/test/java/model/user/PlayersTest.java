package model.user;

import exception.IllegalStringInputException;
import model.card.CardFactory;
import model.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    private Deck deck = new Deck(CardFactory.createCardList());

    @Test
    @DisplayName("입력으로 빈값이 들어올 때")
    void input_Empty() {
        assertThatThrownBy(() -> new Players(" ", deck))
                .isInstanceOf(IllegalStringInputException.class)
                .hasMessageMatching("입력은 한 글자 이상이어야 합니다.");
    }

    @Test
    @DisplayName("입력으로 null값이 들어올 때")
    void input_Null() {
        assertThatThrownBy(() -> new Players(null, deck))
                .isInstanceOf(IllegalStringInputException.class)
                .hasMessageMatching("Null 값은 입력하실 수 없습니다.");
    }

    @Test
    @DisplayName("쉼표만 있을 때")
    void split_test() {
        assertThatThrownBy(() -> new Players(", ,", deck))
                .isInstanceOf(IllegalStringInputException.class)
                .hasMessageMatching("입력은 한 글자 이상이어야 합니다.");
    }
}

