package domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("성공: 점수가 16점이면 카드를 받을 수 있다.")
    void receive_NoException_TotalScoreSixteen() {
        Participant dealer = new Dealer();
        dealer.receive(List.of(
            new Card(Rank.KING, Symbol.CLUB),
            new Card(Rank.SIX, Symbol.SPADE)
        ));
        assertThatCode(() -> dealer.receive(new Card(Rank.QUEEN, Symbol.DIAMOND)))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패: 점수가 17점이면 카드를 받을 수 없다.")
    void receive_Exception_TotalScoreSeventeen() {
        Participant dealer = new Dealer();
        dealer.receive(List.of(
            new Card(Rank.KING, Symbol.CLUB),
            new Card(Rank.SEVEN, Symbol.HEART)
        ));
        assertThatCode(() -> dealer.receive(new Card(Rank.QUEEN, Symbol.DIAMOND)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 카드를 받을 수 없는 상태입니다.");
    }
}
