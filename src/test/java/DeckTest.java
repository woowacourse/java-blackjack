import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    void 카드를_52장_생성한다() {
        Deck deck = new Deck();
        deck.init();

        int deckSize = deck.size();

        assertThat(deckSize).isEqualTo(52);
    }

    @Test
    void 카드는_숫자순에서_무늬순으로_생성된다() {
        Deck deck = new Deck();
        deck.init();
        int deckSize = deck.size();

        List<Card> result = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck.draw();
            result.add(card);
        }

        assertThat(result.toString()).contains(
                "[ACESPADE, TWOSPADE, THREESPADE, FOURSPADE, FIVESPADE, SIXSPADE, SEVENSPADE, EIGHTSPADE, " +
                        "NINESPADE, TENSPADE, JACKSPADE, QUEENSPADE, KINGSPADE, ACEHEART, TWOHEART, THREEHEART, " +
                        "FOURHEART, FIVEHEART, SIXHEART, SEVENHEART, EIGHTHEART, NINEHEART, TENHEART, JACKHEART," +
                        " QUEENHEART, KINGHEART, ACEDIAMOND, TWODIAMOND, THREEDIAMOND, FOURDIAMOND, FIVEDIAMOND," +
                        " SIXDIAMOND, SEVENDIAMOND, EIGHTDIAMOND, NINEDIAMOND, TENDIAMOND, JACKDIAMOND," +
                        " QUEENDIAMOND, KINGDIAMOND, ACECLUB, TWOCLUB, THREECLUB, FOURCLUB, FIVECLUB, SIXCLUB," +
                        " SEVENCLUB, EIGHTCLUB, NINECLUB, TENCLUB, JACKCLUB, QUEENCLUB, KINGCLUB]"
        );
    }
}
