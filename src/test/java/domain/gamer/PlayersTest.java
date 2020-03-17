package domain.gamer;

import domain.card.Deck;
import domain.card.DeckFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    @Test
    @DisplayName("여러명의 Players를 생성한다.")
    void valueOf() {
        Deck deck = DeckFactory.create();
        Players players = Players.valueOf(deck, Arrays.asList("a", "b", "c"));
        assertThat(players).isNotNull();
    }

    @Test
    @DisplayName("Player 이름이 중복되면 예외를 생성한다.")
    void valueOfWithException() {
        Deck deck = DeckFactory.create();
        assertThatThrownBy(() -> Players.valueOf(deck, Arrays.asList("a", "a", "c")))
                .isInstanceOf(NameDuplicationException.class)
                .hasMessage("중복되는 이름이 존재합니다.");
    }
}
