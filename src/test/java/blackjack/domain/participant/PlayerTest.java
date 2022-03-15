package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.utils.ParticipantsCreationUtils.createPlayerWithDenominations;
import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 정상적으로 생성되는지 확인")
    void create() {
        Player player = new Player("승팡");

        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받는지 확인")
    void receiveCard() {
        Player player = new Player("필즈");
        Card card = new Card(ACE, Suit.SPADE);

        player.receiveCard(card);

        assertThat(player.getCards()).containsExactly(card);
    }

    @ParameterizedTest
    @MethodSource("supplyPlayerHavingScoreMoreThan21")
    @DisplayName("플레이어는 점수가 21점 이상이면 카드를 더이상 받을 수 없다")
    void player_can_not_receive_card_where_more_than_21(Player player) {
        assertThat(player.canReceive()).isFalse();
    }

    private static Stream<Arguments> supplyPlayerHavingScoreMoreThan21() {
        return Stream.of(
                Arguments.of(createPlayerWithDenominations("user a", QUEEN, ACE)),
                Arguments.of(createPlayerWithDenominations("user a", SIX, SEVEN, EIGHT)),
                Arguments.of(createPlayerWithDenominations("user a", TWO, JACK, QUEEN))
        );
    }
}
