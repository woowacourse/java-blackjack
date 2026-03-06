package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.util.HashSet;
import java.util.Set;
import model.dto.Card;
import org.junit.jupiter.api.Test;

public class TestCards {

    @Test
    public void 카드_뽑기_기능_정상_작동() {

        //구현은 순차적으로 뽑는걸로 하되, 이걸 테스트까지 해야하느냐로..
        //given
        Cards cards = new Cards();

        //when
        Card card = cards.draw();

        //then

        //카드값의 유효성 검증.
        assertThat(card).isNotNull();

    }

    @Test
    public void 카드_뽑기_여러번_정상_작동() {
        //given
        Set<Card> deck = new HashSet<>();
        Cards cards = new Cards();

        //when
        for(int i = 0; i < 52; i++) {
            deck.add(cards.draw());
        }

        //then
        assertThat(deck.size()).isEqualTo(52);

        assertThatThrownBy(cards::draw)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NO_CARD_IN_DECK.getMessage());
    }

}
