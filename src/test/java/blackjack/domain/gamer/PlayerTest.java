package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("생성")
    void create() {
        Participant player = new Player("john");

        assertThat(player.getName()).isEqualTo("john");
    }

    @Test
    @DisplayName("생성")
    void create2() {
        final Cards cards = new Cards(Collections.emptyList());

        Participant player = new Player("sarah", cards);
        assertThat(player.getName()).isEqualTo("sarah");
    }

    @Test
    @DisplayName("카드 22일 경우 받을 수 있는지 조건 확인")
    void isNotAbleToTake_true() {
        Participant player = new Player();
        player.takeCard(Card.from(Denominations.KING, Suits.CLOVER));
        player.takeCard(Card.from(Denominations.KING, Suits.CLOVER));
        player.takeCard(Card.from(Denominations.TWO, Suits.CLOVER));

        assertThat(player.isAbleToTake()).isFalse();
    }

    @Test
    @DisplayName("카드 21일 경우 받을 수 있는지 조건 확인")
    void isNotAbleToTake_false() {
        Participant player = new Player();
        player.takeCard(Card.from(Denominations.KING, Suits.CLOVER));
        player.takeCard(Card.from(Denominations.KING, Suits.CLOVER));
        player.takeCard(Card.from(Denominations.ACE, Suits.CLOVER));

        assertThat(player.isAbleToTake()).isTrue();
    }


}
