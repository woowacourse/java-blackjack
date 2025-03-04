package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardTest {

    @DisplayName("정상: 카드 객체 생성 확인")
    @Test
    void testCardGenerate() {
        Card card = new Card("종류", "번호");

        assertThat(card).isNotNull();
    }

    @DisplayName("카드가 숫자 랭크일 떄 스코어를 반환한다.")
    @Test
    void testCardScore() {
        Card card = new Card("스페이드", "1");

        List<Integer> score = card.checkScore();

        assertThat(score).isEqualTo(List.of(1));
    }

    @DisplayName("카드가 페이스카드 랭크일 때 스코어를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"J", "Q", "K"})
    void testCardScore_Face(String rank) {
        Card card = new Card("스페이드", rank);

        List<Integer> score = card.checkScore();

        assertThat(score).isEqualTo(List.of(10));
    }

    @DisplayName("카드가 에이스 랭크일 때 스코어를 반환한다.")
    @Test
    void testCardScore_Ace() {
        Card card = new Card("스페이드", "A");

        List<Integer> score = card.checkScore();

        assertThat(score).isEqualTo(List.of(1, 11));
    }
}
