package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void player가_카드를_뽑으면_cards_size가_1_증가한다() {
        //given
        new Deck();
        Cards cards = new Cards(new ArrayList<>());
        Player player = new Player(new PlayerName("judi"), cards);

        //when
        int prevSize = cards.getCards().size();
        player.pickCard();
        int nowSize = player.getCards().size();

        //then
        assertThat(nowSize).isEqualTo(prevSize + 1);
    }

    @Test
    void 생성_테스트() {
        //given, when, then
        Assertions.assertDoesNotThrow(() ->  new Player(new PlayerName("pobi"), new Cards(new ArrayList<>())));
    }
}
