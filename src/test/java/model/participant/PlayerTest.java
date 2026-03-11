package model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.card.Rank;
import model.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
    Participant player;

    @BeforeEach
    void setUp() {
        player = Player.of("pobi");
    }

    @Test
    void 플레이어_정상_생성_테스트() {
        // given
        // when

        // then
        assertThat(player.getName()).isEqualTo("pobi");
    }

    @Test
    void 플레이어의_카드_오픈_테스트() {
        // given
        Card card1 = Card.of(Suit.SPADE, Rank.THREE);
        Card card2 = Card.of(Suit.SPADE, Rank.FOUR);
        player.receive(card1);
        player.receive(card2);

        // when
        player.open();
        Cards opened = player.open();

        // then
        assertThat(opened.asList()).contains(card1, card2);
        assertThat(opened.asList()).containsAll(List.of(card1, card2));
        assertThat(opened.asList()).hasSize(2);
    }
}
