package blackjack.domain.user;

import blackjack.domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {

    private static final double money = 10000;

    @DisplayName("빈 이름 입력시 예외 발생")
    @Test
    void new_emptyName_ExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new Player("", money, makeCards(), 21)
        );
    }

    @DisplayName("카드 추가 테스트")
    @Test
    void draw_additionalCard() {
        Player player = new Player("pobi", money, makeCards(), 21);
        Deck deck = new Deck(CardGenerator.makeShuffledNewDeck());

        player.draw(deck);
        assertThat(player.getCards()).hasSize(3);
    }

    @DisplayName("player의 HandStatus.STAY 로 번경")
    @Test
    void convertToStay() {
        Player player = new Player("pobi", money, makeCards(), 21);

        player.convertToStay();
        assertFalse(player.isBlackjack());
        assertFalse(player.isHit());
        assertFalse(player.isBust());

    }

    @DisplayName("플레이어 이름 가져오기")
    @Test
    void getName() {
        User player = new Player("pobi", money, makeCards(), 21);
        assertThat(player.getName()).isEqualTo("pobi");
    }

    private List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));

        return cards;
    }

}
