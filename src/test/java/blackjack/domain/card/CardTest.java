package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @DisplayName("정적 팩토리 메소드 of 로 새로운 카드를 생성한다.")
    @Test
    void of_createNewCard() {
        Card card = Card.of(CardRank.ACE, CardSymbol.CLOVER);

        assertThat(card).isNotNull();
    }

    @DisplayName("정적 팩토리 메소드 of 는 캐싱된 카드를 가져온다.")
    @Test
    void of_getCache() {
        Card card = Card.of(CardRank.ACE, CardSymbol.CLOVER);
        Card sameCard = Card.of(CardRank.ACE, CardSymbol.CLOVER);

        assertThat(card).isEqualTo(sameCard);
    }
}
