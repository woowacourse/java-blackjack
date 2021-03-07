package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        this.player = new Player(new Name("pobi"));
    }

    @Test
    @DisplayName("플레이어는 카드를 받는다.")
    void testReceiveCard() {
        Card card = Card.valueOf(Pattern.DIAMOND, Number.EIGHT);
        this.player.addCard(card);

        assertThat(this.player.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("플레이어 이름에 따른 비교")
    void testEqualsByName() {
        assertThat(this.player).isEqualTo(new Player(new Name("pobi")));
        assertThat(this.player).isNotEqualTo(new Player(new Name("jun")));
    }

    @Test
    @DisplayName("21점이 넘어 버스트되면 카드를 더 이상 받을 수 없는 상태가 된다.")
    void testPlayerHitable() {
        this.player.addCard(Card.valueOf(Pattern.DIAMOND, Number.JACK));
        assertThat(this.player.isHitable()).isTrue();
        this.player.addCard(Card.valueOf(Pattern.DIAMOND, Number.QUEEN));
        assertThat(this.player.isHitable()).isTrue();
        this.player.addCard(Card.valueOf(Pattern.DIAMOND, Number.KING));
        assertThat(this.player.isHitable()).isFalse();
    }
}
