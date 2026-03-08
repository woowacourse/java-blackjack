package model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.Card;
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
        Card card1 = Card.of("스페이드", 3);
        Card card2 = Card.of("스페이드", 4);
        player.draw(card1);
        player.draw(card2);

        // when
        player.open();
        List<String> opened = player.open();

        // then
        assertThat(opened).contains(card1.toString(), card2.toString());
        assertThat(opened).containsAll(List.of(card1.toString(), card2.toString()));
        assertThat(opened).hasSize(2);
    }
}
