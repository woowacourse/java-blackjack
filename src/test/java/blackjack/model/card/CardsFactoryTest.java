package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("카드 뭉치 팩터리 테스트")
class CardsFactoryTest {

    @DisplayName("스탠다드 52개의 중복되지 않는 카드들을 생성한다.")
    @Test
    void createStandardDeckCardsTest() {
        // when
        List<Card> standardDeckCards = CardsFactory.createStandardDeckCards();

        // then
        assertThat(standardDeckCards)
                .hasSize(52);
        assertThat(standardDeckCards)
                .doesNotHaveDuplicates();
    }
}
