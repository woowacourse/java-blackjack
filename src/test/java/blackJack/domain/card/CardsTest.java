package blackJack.domain.card;

import blackJack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardsTest {
    private final Cards cards = new Cards();

    @Test
    @DisplayName("Cards 생성 테스트")
    void createValidCards() {
        assertThat(new Cards()).isNotNull();
    }

    @Test
    @DisplayName("중복된 카드를 받는 경우 예외 발생 테스트")
    void receiveDuplicatedCard() {
        Card card1 = new Card(Symbol.CLOVER, Denomination.ACE);
        Card card2 = new Card(Symbol.CLOVER, Denomination.ACE);
        cards.receiveCard(card1);

        assertThatThrownBy(() -> cards.receiveCard(card2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 받을 수 없습니다.");
    }

    @Test
    @DisplayName("블랙잭이 가능한 카드 개수 체크 확인 테스트")
    void isBlackJackPossibleCount() {
        cards.receiveCard(new Card(Symbol.CLOVER, Denomination.ACE));
        cards.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));

        assertThat(cards.isBlackJackPossibleCount()).isTrue();
    }

    @Test
    @DisplayName("ACE 카드 보유 여부 테스트")
    void hasAce() {
        cards.receiveCard(new Card(Symbol.CLOVER, Denomination.ACE));

        assertThat(cards.hasAce()).isTrue();
    }

    @Test
    @DisplayName("카드 합계 계산 테스트")
    void calculateScore() {
        Player player = new Player("k");
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(player.calculateFinalScore()).isEqualTo(8);
    }
}
