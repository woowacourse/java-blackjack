package blackjack.domain.cards;

import static blackjack.domain.CardsTestDataGenerator.generateCards;
import static blackjack.domain.Denomination.*;
import static blackjack.domain.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class CardsTest {

    @DisplayName("카드 리스트를 활용하여 Cards 를 생성한다.")
    @Test
    void 카드들_생성() {
        assertDoesNotThrow(() -> new Cards(generateCards()));
    }

    @DisplayName("카드의 총점을 계산한다.")
    @Test
    void 카드_총점_계산() {
        List<Card> cardValues = List.of(Card.of(JACK, CLOVER), Card.of(ACE, DIAMOND));

        Cards cards = new Cards(cardValues);

        assertThat(cards.calculateTotalScore()).isEqualTo(21);
    }

    @DisplayName("카드 List에 카드를 추가한다.")
    @Test
    void 카드_추가() {
        Cards cards = new Cards(generateCards());

        cards.add(Card.of(KING, DIAMOND));

        assertThat(cards.getValue().size()).isEqualTo(3);
    }

    @DisplayName("Cards가 주어지면 점수를 계산하면 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(CardsArgumentsProvider.class)
    void 카드_점수_계산(Cards cards, int totalScore) {
        assertThat(cards.calculateTotalScore()).isEqualTo(totalScore);
    }

    @DisplayName("Cards가 주어지면 1과 11 둘 중 하나를 사용하여 블랙잭 점수에 가깝게 계산하여 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(AceIncludeCardsArgumentsProvider.class)
    void ACE_카드를_포함한_점수_계산(Cards cards, int totalScore) {
        assertThat(cards.calculateTotalScore()).isEqualTo(totalScore);
    }
}
