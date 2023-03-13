package domain;

import static domain.card.CardInfo.A;
import static domain.card.Shape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @Test
    void 카드이름과_점수가_같은_객체는_같은_객체이다() {
        Card card1 = new Card(HEART, A);
        Card card2 = new Card(HEART, A);

        assertThat(card1).isEqualTo(card2);
    }
}
