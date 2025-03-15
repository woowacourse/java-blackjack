package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class CardTest {

    @DisplayName("정상: 카드 객체 생성 확인")
    @Test
    void testCardGenerate() {
        Card card = new Card(CardSuit.HEART, CardRank.TWO);

        assertThat(card).isNotNull();
    }

    @DisplayName("카드가 숫자 랭크일 떄 스코어를 반환한다.")
    @Test
    void testCardScore() {
        Card card = new Card(CardSuit.HEART, CardRank.TWO);

        Set<Integer> score = card.checkScore();

        assertThat(score).isEqualTo(Set.of(2));
    }

    @DisplayName("카드가 페이스 카드 랭크일 때 스코어를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = CardRank.class, names = {"JACK", "QUEEN", "KING"})
    void testCardScore_Face(CardRank rank) {
        Card card = new Card(CardSuit.DIAMOND, rank);

        Set<Integer> score = card.checkScore();

        assertThat(score).isEqualTo(Set.of(10));
    }

    @DisplayName("카드가 에이스 랭크일 때 스코어를 반환한다.")
    @Test
    void testCardScore_Ace() {
        Card card = new Card(CardSuit.CLUB, CardRank.ACE);

        Set<Integer> score = card.checkScore();

        assertThat(score).isEqualTo(Set.of(1, 11));
    }
}
