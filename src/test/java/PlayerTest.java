import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void 플레이어는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        String name = "abc";
        Player player = new Player(name);

        //when
        Hand actual = player.drawCardWhenStart(cardDeck);

        //then
        assertThat(actual.getCards()).hasSize(2);
    }

    @Test
    void 플레이어는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        String name = "abc";
        Player player = new Player(name);

        //when
        Hand actual = player.drawCard(cardDeck);

        //then
        assertThat(actual.getCards()).hasSize(1);
    }
}
