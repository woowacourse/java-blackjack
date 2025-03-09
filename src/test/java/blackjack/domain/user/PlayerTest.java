package blackjack.domain.user;

import static blackjack.fixture.CardFixture.make;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    Player player = new Player(new Nickname("플레이어"));

    @Test
    @DisplayName("초기 카드를 추가할 수 있다.")
    void canAddInitialCards() {
        List<Card> initialCards = List.of(
                new Card(CardShape.HEART, CardValue.EIGHT),
                new Card(CardShape.CLOVER, CardValue.EIGHT));

        player.addInitialCards(initialCards);

        assertThat(player.getHand()).hasSize(2);
    }

    @Test
    @DisplayName("히트를 할 수 있다.")
    void canHit() {
        Card newCard = new Card(CardShape.HEART, CardValue.EIGHT);

        player.hit(newCard);

        assertThat(player.getHand()).hasSize(1);
        assertThat(player.getHand().getFirst()).isEqualTo(newCard);
    }

    @ParameterizedTest
    @DisplayName("히트 가능 여부를 확인할 수 있다.")
    @MethodSource()
    void canCalculateTotalPoint(List<Card> cards, boolean expectedIsHitPossible) {
        player.addInitialCards(cards);

        boolean actualIsHitPossible = player.checkHitPossibility();

        assertThat(actualIsHitPossible).isEqualTo(expectedIsHitPossible);
    }

    static Stream<Arguments> canCalculateTotalPoint() {
        return Stream.of(
                Arguments.of(List.of(make(CardValue.EIGHT), make(CardValue.EIGHT)), true),
                Arguments.of(List.of(make(CardValue.EIGHT), make(CardValue.EIGHT), make(CardValue.EIGHT)), false),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.QUEEN)), false),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.QUEEN), make(CardValue.KING)), false),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.ACE)), true),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.ACE), make(CardValue.ACE)), true),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.ACE), make(CardValue.QUEEN),
                        make(CardValue.KING)), false)
        );
    }
}