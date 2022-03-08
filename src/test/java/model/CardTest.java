package model;

import static model.CardFace.TEN;
import static model.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void duplicateCard() {
        List<Card> collect = Stream.of(new Card(SPADE, TEN), new Card(SPADE, TEN))
                .distinct()
                .collect(Collectors.toList());
        assertThat(collect.size()).isOne();
        assertThat(collect.get(0)).isEqualTo(new Card(SPADE, TEN));
    }
}
