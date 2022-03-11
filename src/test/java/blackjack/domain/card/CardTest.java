package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("정적 팩토리 메서드 of는 새로운 카드 인스턴스를 생성한다.")
    @Test
    void of_createNewCard() {
        Card card = Card.of(CardRank.ACE, CardSymbol.CLOVER);

        assertThat(card).isNotNull();
    }

    @DisplayName("정적 팩토리 메소드 of는 캐싱된 카드 인스턴스를 가져온다.")
    @Test
    void of_getCache() {
        Card card = Card.of(CardRank.ACE, CardSymbol.CLOVER);
        Card sameCard = Card.of(CardRank.ACE, CardSymbol.CLOVER);

        assertThat(card).isEqualTo(sameCard);
    }

    @DisplayName("getRankValue 메서드는 카드 랭크에 담긴 값을 가져온다.")
    @Test
    void getRankValue() {
        Card card = Card.of(CardRank.FIVE, CardSymbol.CLOVER);

        Score actual = card.getRankValue();
        Score expected = Score.valueOf(5);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("getName 메서드는 카드의 이름을 반환한다.")
    @Test
    void getName() {
        Card card = Card.of(CardRank.FIVE, CardSymbol.CLOVER);

        String actual = card.getName();
        String expected = "5클로버";

        assertThat(actual).isEqualTo(expected);
    }
}
