package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {
            "Y,0,false",
            "Y,1,true",
            "N,0,false",
    })
    void Y_입력시_카드를_추가하고_N_입력시_카드를_추가하지_않는다(String input, int randomCardIndex, boolean expected) {
        Name name = new Name("hamad");
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("A하트", 11));
        cardsByCardBox.add(new Card("3하트", 3));
        Cards cards = new Cards(cardsByCardBox);

        Player player = new Player(name, cards);

        assertThat(player.selectToPickOtherCard(input, () -> randomCardIndex)).isEqualTo(expected);
    }

    @Test
    void 본인의_카드뭉치의_총합을_반환한다() {
        Name name = new Name("hamad");
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("A하트", 11));
        cardsByCardBox.add(new Card("3하트", 3));
        Cards cards = new Cards(cardsByCardBox);

        Player player = new Player(name, cards);

        assertThat(player.sumOfPlayerCards()).isEqualTo(cards.sumOfCards());
    }

}
