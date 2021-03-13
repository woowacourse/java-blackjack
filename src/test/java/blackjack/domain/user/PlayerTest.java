package blackjack.domain.user;

import blackjack.domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {

    @DisplayName("빈 이름 입력시 예외 발생")
    @Test
    void new_emptyName_ExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new Player("", 10000, makeCards())
        );
    }

    @DisplayName("카드 추가 테스트")
    @Test
    void draw_additionalCard() {
        Player player = mockPlayer();
        Deck deck = new Deck(CardGenerator.makeShuffledNewDeck());

        player.draw(deck);
        assertThat(player.getCards()).hasSize(3);
    }

    @DisplayName("player의 HandStatus.STAY 로 번경")
    @Test
    void convertToStay() {
        Player player = mockPlayer();

        player.convertToStay();
        assertFalse(player.isBlackjack());
        assertFalse(player.isHit());
        assertFalse(player.isBust());

    }

    @DisplayName("플레이어 이름 가져오기")
    @Test
    void getName() {
        User player = mockPlayer();
        assertThat(player.getName()).isEqualTo("pobi");
    }

    private Player mockPlayer() {
        return new Player("pobi", 10000, makeCards());
    }

    private List<Card> makeCards() {
        return Stream.<Card>builder()
            .add(new Card(Denomination.FIVE, Suit.CLUBS))
            .add(new Card(Denomination.EIGHT, Suit.DIAMONDS))
            .build()
            .collect(Collectors.toList());
    }
}
