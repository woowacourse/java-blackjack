package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Player;
import domain.player.PlayerName;
import domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void player가_카드를_뽑으면_cards_size가_1_증가한다() {
        //given
        Cards cards = new Cards();
        Player player = new Player(new PlayerName("judi"));
        //when
        int prevSize = cards.getCards().size();
        player.drawCard(new Card(Suit.HEART, Denomination.NINE));
        int nowSize = player.getCards().size();
        //then
        assertThat(nowSize).isEqualTo(prevSize + 1);
    }

    @Test
    @DisplayName("player getScore 메서드 테스트")
    void playerGetScoreTest() {
        //given
        Player player = new Player(new PlayerName("jude"));
        Card card = new Card(Suit.HEART, Denomination.NINE);
        //when
        player.drawCard(card);
        Score score = player.getScore();
        //then
        assertThat(score.getValue()).isEqualTo(9);
    }
}
