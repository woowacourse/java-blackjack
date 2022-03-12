package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackCardsGeneratorTest {

    @Test
    @DisplayName("블랙잭 게임에는 서로 다른 52장의 카드를 생성한다.")
    void generateBlackJackCards() {
        // given
        BlackJackCardsGenerator generator = new BlackJackCardsGenerator();

        // when
        List<Card> cards = generator.generate();

        // then
        assertThat(cards.stream().distinct().count()).isEqualTo(52);
    }
}