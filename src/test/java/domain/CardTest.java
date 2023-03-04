package domain;

import static domain.CardInfoFixture.에이스;
import static domain.ShapeFixture.하트;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CardTest {

    @Test
    void 카드이름과_점수가_같은_객체는_같은_객체이다() {
        Card card1 = new Card(하트, 에이스);
        Card card2 = new Card(하트, 에이스);

        assertThat(card1).isEqualTo(card2);
    }

    @Test
    void 카드는_임의의_값을_받아_자신의_값과_더해_반환한다() {
        Card card = new Card(하트, 에이스);

        assertThat(card.sum(new Card(하트, CardInfo.TWO))).isEqualTo(13);
    }
}
