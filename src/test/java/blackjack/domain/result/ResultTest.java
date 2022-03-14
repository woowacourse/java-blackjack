package blackjack.domain.result;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.aceCard;
import static blackjack.domain.TestCardFixture.jackCard;
import static blackjack.domain.TestCardFixture.kingCard;
import static blackjack.domain.TestCardFixture.sevenCard;
import static blackjack.domain.TestCardFixture.tenCard;
import static blackjack.domain.TestCardFixture.threeCard;
import static blackjack.domain.TestCardFixture.twoCard;
import static blackjack.domain.result.CardScoreResult.*;
import static org.assertj.core.api.Assertions.*;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {

    @Test
    @DisplayName("결과의 값이 정확한지 확인")
    void equalsResultName() {
        assertThat(WIN.getName()).isEqualTo("승");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "WIN,패",
        "LOSE,승",
        "DRAW,무"
    })
    @DisplayName("결과가 정상적으로 반대로 뒤집어 지는지 확인")
    void reversResult(CardScoreResult result, String excepted) {
        CardScoreResult reverseResult = result.reverse();
        assertThat(reverseResult.getName()).isEqualTo(excepted);
    }

    @DisplayName("카드패의 승패 계산이 정확한지 확인")
    @ParameterizedTest
    @MethodSource("calculateCardHandResultTestCase")
    void calculateCardHandResult(Player player, Dealer dealer, CardScoreResult excepted) {
        assertThat(CardScoreResult.findCardScoreResult(player, dealer)).isEqualTo(excepted);
    }

    private static Stream<Arguments> calculateCardHandResultTestCase() {
        return Stream.of(
            Arguments.of(new Player(new Name("seung"), createCardHand(aceCard, tenCard)),
                new Dealer(createCardHand(aceCard, sevenCard)), WIN),
            Arguments.of(new Player(new Name("seung"), createCardHand(aceCard, tenCard)),
                new Dealer(createCardHand(aceCard, sevenCard, threeCard)), WIN),
            Arguments.of(new Player(new Name("seung"), createCardHand(aceCard, tenCard)),
                new Dealer(createCardHand(aceCard, kingCard)), DRAW),
            Arguments.of(new Player(new Name("seung"), createCardHand(sevenCard, tenCard)),
                new Dealer(createCardHand(sevenCard, kingCard)), DRAW),
            Arguments.of(
                new Player(new Name("seung"), createCardHand(aceCard, sevenCard, threeCard)),
                new Dealer(createCardHand(aceCard, jackCard)), LOSE),
            Arguments.of(new Player(new Name("seung"), createCardHand(twoCard, tenCard)),
                new Dealer(createCardHand(aceCard, sevenCard)), LOSE),
            Arguments.of(new Player(new Name("seung"), createCardHand(twoCard, tenCard, tenCard)),
                new Dealer(createCardHand(aceCard, sevenCard)), LOSE),
            Arguments.of(new Player(new Name("seung"), createCardHand(tenCard, tenCard, threeCard)),
                new Dealer(createCardHand(aceCard, sevenCard, tenCard)), LOSE)
        );
    }
}
