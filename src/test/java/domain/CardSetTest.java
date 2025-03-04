package domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardSetTest {

    @Test
    void 카드셋_생성_테스트() {
        //given
        List<Card> cards = new ArrayList<>();

        //when
        CardSet cardSet = new CardSet(cards);

        //then
        Assertions.assertThat(cardSet).isInstanceOf(CardSet.class);
    }
}
