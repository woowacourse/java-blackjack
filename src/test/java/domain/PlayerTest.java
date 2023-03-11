package domain;

import static domain.CardInfo.A;
import static domain.CardInfo.FOUR;
import static domain.CardInfo.THREE;
import static domain.Shape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void 본인의_카드뭉치의_총합을_반환한다() {
        Name name = getName();
        Cards cards = getCards();

        Player player = new Player(name, cards);

        assertThat(player.sumOfCards()).isEqualTo(cards.sumOfCards());
    }

    @Test
    void 카드를_받아_자신의_카드뭉치에_더할수_있다() {
        Name name = getName();
        Cards cards = getCards();

        Player player = new Player(name, cards);

        //중복카드면 false
        assertThat(player.addCard(new Card(HEART, A))).isFalse();
        //중복카드가 아니면 true
        assertThat(player.addCard(new Card(HEART, FOUR))).isTrue();
    }

    @Test
    void 자신의_카드뭉치가_블랙잭임을_확인한다() {
        Name name = getName();
        Cards cards = getCards();

        Player player = new Player(name, cards);

        assertThat(player.isBlackJack()).isFalse();
    }

    private static Cards getCards() {
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card(HEART, A));
        cardsByCardBox.add(new Card(HEART, THREE));
        return new Cards(cardsByCardBox);
    }

    private static Name getName() {
        return new Name("hamad");
    }
}
