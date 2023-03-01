package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/01
 */
public class DealerTest {

    @Test
    void 카드뭉치의_합이_16_이하인지_확인할_수_있다() {
        Name name = new Name("hamad");
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("A하트", 11));
        cardsByCardBox.add(new Card("3하트", 3));
        Cards cards = new Cards(cardsByCardBox);

        Dealer dealer = new Dealer(name, cards);

        assertThat(dealer.isSumUnderStandard()).isTrue();
    }

}
