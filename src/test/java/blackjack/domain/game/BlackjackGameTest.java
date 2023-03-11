package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackGameTest {

    @Test
    @DisplayName("플레이어는 두 장씩 카드를 받는다")
    void addTwoCardsTest() {
        List<String> names = List.of("jamie", "boxster");
        Participants participants = Participants.from(names);

        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.dealOutCard();

        Player player = participants.getPlayers().get(0);
        assertThat(player.getAllCards()).hasSize(2);
    }

    @MethodSource("blackjackGameCardSource")
    @ParameterizedTest
    @DisplayName("게임 진행 후 결과를 반환한다")
    void getGameResultTest(List<Card> cards, GameResult expect) {
        List<String> names = List.of("jamie");
        Participants participants = Participants.from(names);
        BlackjackGame blackjackGame = new BlackjackGame(participants, new CardDeck(new ArrayList<>(cards)));

        blackjackGame.dealOutCard();

        BlackjackResult result = blackjackGame.getResult();
        Player player = participants.getPlayers().get(0);

        assertThat(result.get(player)).isEqualTo(expect);
    }

    static Stream<Arguments> blackjackGameCardSource() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.HEART, CardNumber.ACE),
                                new Card(CardSuit.SPADE, CardNumber.SIX),
                                new Card(CardSuit.CLUB, CardNumber.SIX)
                        ), GameResult.WIN),
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.HEART, CardNumber.SEVEN),
                                new Card(CardSuit.SPADE, CardNumber.TEN),
                                new Card(CardSuit.DIAMOND, CardNumber.SEVEN)
                        ), GameResult.TIE),
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.ACE),
                                new Card(CardSuit.CLUB, CardNumber.TEN)
                        ), GameResult.LOSE)
        );
    }
}
