package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardFactoryTest {

    @Test
    @DisplayName("서로 다른 52장의 블랙잭 게임 카드를 생성한다.")
    void generateBlackJackCards() {
        // when
        List<Card> cards = CardFactory.createBlackJackCards();

        // then
        assertThat(cards.stream().distinct().count()).isEqualTo(52);
    }
}
