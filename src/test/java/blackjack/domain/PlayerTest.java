package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    void 카드_합이_21이_넘는_경우_카드를_더_받지_못한다() {
        //given
        Player player = new Player("pobi", new Cards(
                Arrays.asList(
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.TWO)
                ),
                scoreCalculator
        ));

        Card card = new Card(Suit.HEART, Rank.TWO);

        //when & then
        assertThatThrownBy(() -> player.send(card))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
    }

    @DisplayName("")
    @Test
    void 플레이어는_추가로_카드를_받을_수_있다() {
        //given
        Player player = new Player(
                "pobi",
                new Cards(
                        new ArrayList<>(Arrays.asList(
                                new Card(Suit.DIAMOND, Rank.ACE),
                                new Card(Suit.DIAMOND, Rank.TWO))),
                        scoreCalculator));

        Card card = new Card(Suit.HEART, Rank.THREE);

        //when
        player.send(card);

        //then
        assertThat(player.getCards()).hasSize(3);
        assertThat(player.getCards()).isEqualTo(
                Arrays.asList(
                        new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.TWO),
                        new Card(Suit.HEART, Rank.THREE))
        );
    }
}
