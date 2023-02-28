package model;

import model.card.Card;
import model.card.Shape;
import model.card.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    private Result result;
    private Player bebe;
    private Player ethan;

    @BeforeEach
    void init() {
        bebe = new Player("bebe");
        ethan = new Player("ethan");
        result = new Result(new Players(List.of("bebe", "ethan")));
    }

    @Test
    @DisplayName("기존 Map에 Card를 추가한다.")
    void addCard() {
        // given
        Card card = new Card(Shape.CLOVER, Value.ACE);

        // when
        result.addCard(bebe, card);

        // then
        assertThat(result.getScoreBoards().get(bebe)).containsExactly(card);
    }
}
