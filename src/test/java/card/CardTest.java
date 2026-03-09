package card;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void 카드를_생성한다() {
        //given
        Card card = new Card(Rank.FIVE, Pattern.CLOVER);

        //when
        int score = card.getCardScore();
        String shape = card.getCardShape();

        //then
        assertThat(score).isEqualTo(5);
        assertThat(shape).isEqualTo("클로버");
    }

    @Test
    void 숫자_카드는_자신의_숫자_값을_가진다() {
        //given
        Card card = new Card(Rank.FIVE, Pattern.CLOVER);

        //when
        int score = card.getCardScore();

        //then
        assertThat(score).isEqualTo(5);
    }

    @Test
    void 에이스를_제외한_문양_카드는_10의_값을_가진다() {
        //given
        Card card = new Card(Rank.JACK, Pattern.CLOVER);

        //when
        int score = card.getCardScore();

        //then
        assertThat(score).isEqualTo(10);
    }
}
