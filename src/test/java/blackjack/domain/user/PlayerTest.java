package blackjack.domain.user;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private final Money defaultMoney = Money.of(0);

    @DisplayName("플레이어는 이름을 받아 생성된다.")
    @Test
    void playerCreationTest() {
        Player player = new Player(Name.of("youngE"), defaultMoney);
        assertThat(player).isInstanceOf(Player.class);
    }

    @DisplayName("플레이어 이름에 공백이 입력되는 경우 예외를 발생시킨다.")
    @Test
    void playerCreationErrorTest() {
        assertThatThrownBy(() -> {
            new Player(Name.of(" "), defaultMoney);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어는 카드를 받을 수 있다.")
    @Test
    void drawCard() {
        Player player = new Player(Name.of("Player"), defaultMoney);
        CardDeck cardDeck = CardDeck.createDeck();
        player.draw(cardDeck.drawCard());

        assertThat(player.getCards().cards()).hasSize(1);
    }

    @DisplayName("isBust:true - 플레이어 카드의 총합이 21을 초과하는 경우 자동 중단")
    @Test
    void isBustTrueWhenOver21Score() {
        Player player = new Player(Name.of("Player"), defaultMoney);

        player.draw(jack);
        player.draw(seven);
        player.draw(six);

        assertTrue(player.isBust());
    }

    private static Stream<Arguments> cardsProvider() {
        return Stream.of(
                Arguments.of(false, new Card[]{king, queen, two}),  // Bust
                Arguments.of(true, new Card[]{king, two}),          // Alive
                Arguments.of(false, new Card[]{ace, king})          // BlackJack
        );
    }

    @DisplayName("canContinue - 블랙잭이거나 버스트가 난 경우 False, 그 외는 True를 리턴")
    @ParameterizedTest
    @MethodSource("cardsProvider")
    void isCanContinueTest(boolean result, Card... cardArray) {
        Player player = new Player(Name.of("PlayerBust"), defaultMoney);
        for (Card card : cardArray) {
            player.draw(card);
        }

        assertThat(player.canContinue()).isEqualTo(result);
    }
}