package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import blackjack.domain.result.BetAmount;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.ParticipantResult;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FormatterTest {

    private static Stream<Arguments> testCasesForFormatCard() {
        return Stream.of(
                Arguments.of(new Card(CardSuit.CLUB, CardRank.ACE), "A클로버"),
                Arguments.of(new Card(CardSuit.DIAMOND, CardRank.TWO), "2다이아몬드"),
                Arguments.of(new Card(CardSuit.HEART, CardRank.JACK), "J하트"),
                Arguments.of(new Card(CardSuit.SPADE, CardRank.QUEEN), "Q스페이드")
        );
    }

    @ParameterizedTest
    @MethodSource("testCasesForFormatCard")
    void 카드의_이름을_포맷한다(Card card, String expected) {
        // when & then
        assertEquals(expected, Formatter.formatCard(card));
    }

    @Test
    void 챌린저의_게임_결과를_포맷한다() {
        // given
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        ParticipantResult participantResult = new ParticipantResult(player, GameResultType.LOSE, 1);

        // when
        String actual = Formatter.formatChallengerGameResult(List.of(participantResult));

        // then
        assertEquals("히로: 패", actual);
    }
}
