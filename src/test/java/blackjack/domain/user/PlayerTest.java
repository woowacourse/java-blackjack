package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {

    @DisplayName("빈 이름 입력시 예외 발생")
    @Test
    void new_emptyName_ExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new Player("")
        );
    }

    @DisplayName("카드 추가 테스트")
    @Test
    void draw_additionalCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Player player = new Player("pobi");
        player.initialHands(cards, 21);

        player.draw(new Card(Denomination.TWO, Suit.SPADES));
        assertThat(player.getCards()).hasSize(3);
    }

    @DisplayName("player의 HandStatus.STAY 로 번경")
    @Test
    void convertToStay() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Player player = new Player("pobi");
        player.initialHands(cards, 21);

        player.convertToStay();
        assertThat(player.getStatus()).isEqualTo(HandStatus.STAY);
    }

    @DisplayName("플레이어 이름 가져오기")
    @Test
    void getName() {
        User player = new Player("pobi");
        assertThat(player.getName()).isEqualTo("pobi");
    }
}