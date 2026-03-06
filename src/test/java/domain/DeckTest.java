package domain;

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
                "[A스페이드, 2스페이드, 3스페이드, 4스페이드, 5스페이드, 6스페이드, 7스페이드, 8스페이드, 9스페이드, 10스페이드, " +
                        "J스페이드, Q스페이드, K스페이드, A하트, 2하트, 3하트, 4하트, 5하트, 6하트, 7하트, 8하트, 9하트, 10하트," +
                        " J하트, Q하트, K하트, A다이아몬드, 2다이아몬드, 3다이아몬드, 4다이아몬드, 5다이아몬드, 6다이아몬드, 7다이아몬드," +
                        " 8다이아몬드, 9다이아몬드, 10다이아몬드, J다이아몬드, Q다이아몬드, K다이아몬드, A클로버, 2클로버, 3클로버, 4클로버," +
                        " 5클로버, 6클로버, 7클로버, 8클로버, 9클로버, 10클로버, J클로버, Q클로버, K클로버]"
        );
    }

    @Test
    void 카드_셔플_테스트() {
        Deck deck1 = new Deck();
        deck1.init();
        deck1.shuffle();
        int deckSize = deck1.size();

        List<Card> result1 = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck1.draw();
            result1.add(card);
        }

        Deck deck2 = new Deck();
        deck2.init();

        List<Card> result2 = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck2.draw();
            result2.add(card);
        }

        assertThat(result1.size()).isEqualTo(result2.size());
        assertThat(result1).containsExactlyInAnyOrderElementsOf(result2);
    }
}
