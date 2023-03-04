package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerTest {

    @Test
    void 본인의_카드뭉치의_총합을_반환한다() {
        Name name = getName();
        Cards cards = getCards();

        Player player = new Player(name, cards);

        assertThat(player.sumOfPlayerCards()).isEqualTo(cards.sumOfCards());
    }

    @Test
    void 카드박스에서_카드를_뽑을_수_있다() {
        Name name = getName();
        Cards cards = getCards();

        Player player = new Player(name, cards);

        assertThat(player.selectToPickOtherCard(new CardBox(), () -> 0)).isTrue();
    }

    private static Cards getCards() {
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("A하트", 11));
        cardsByCardBox.add(new Card("3하트", 3));
        return new Cards(cardsByCardBox);
    }

    private static Name getName() {
        return new Name("hamad");
    }
}
