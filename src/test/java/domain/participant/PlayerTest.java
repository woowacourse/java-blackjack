package domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import domain.Name;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("성공: 버스트 안 된 경우 카드 받기(경계값: 21점)")
    void receive_NoException_NotBusted() {
        Participant player = new Player(new Name("name"));
        player.receive(List.of(
            new Card(Rank.KING, Symbol.CLUB),
            new Card(Rank.NINE, Symbol.HEART),
            new Card(Rank.TWO, Symbol.SPADE)
        ));
        assertThatCode(() -> player.receive(new Card(Rank.QUEEN, Symbol.DIAMOND)))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패: 버스트 된 경우 카드 받기(경계값: 22점)")
    void receive_Exception_Busted() {
        Participant player = new Player(new Name("name"));
        player.receive(List.of(
            new Card(Rank.KING, Symbol.CLUB),
            new Card(Rank.TEN, Symbol.HEART),
            new Card(Rank.TWO, Symbol.SPADE)
        ));
        assertThatCode(() -> player.receive(new Card(Rank.QUEEN, Symbol.DIAMOND)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 카드를 받을 수 없는 상태입니다.");
    }
}
