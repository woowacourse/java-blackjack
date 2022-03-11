package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardFactoryTest {

    @Test
    @DisplayName("객체가 생성되면 52장의 카드를 갖는다.")
    void initDeck() {
        // give
        final CardFactory cardFactory = CardFactory.create();

        // when
        final int actual = cardFactory.getRemainAmount();

        // then
        assertThat(actual).isEqualTo(52);
    }

    @Test
    @DisplayName("카드를 한 장 만들어서 반환한다.")
    void createCard() {
        // give
        final Card card = new Card(CardSymbol.DIAMOND, CardNumber.ACE);
        final CardFactory cardFactory = CardFactory.createBy(List.of(card));

        // when
        final Card actual = cardFactory.drawCard();

        // then
        assertThat(actual).isEqualTo(card);
    }
}
