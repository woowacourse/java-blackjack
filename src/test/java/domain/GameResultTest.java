package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.result.GameResult;
import domain.result.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    private Participants participants;
    private Deck deck;
    private Participant leo;

    @BeforeEach
    void setUp() {
        participants = Participants.from(List.of("leo"));
        deck = Deck.from((orderedDeck) -> orderedDeck);
        leo = participants.findPlayers().get(0);
    }

    @ParameterizedTest
    @MethodSource("bustStatusCardProvider")
    @DisplayName("참가자의 카드 합이 21이 넘으면 버스트이다.")
    void getParticipantsBustStatus(Card card, boolean isBust) {
        leo.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        leo.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        leo.receiveCard(card);

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), participants.findDealer());
        final Map<Participant, Boolean> participantsBustStatus = gameResult.getParticipantsBustStatus();

        Assertions.assertThat(participantsBustStatus.get(leo)).isEqualTo(isBust);
    }

    @ParameterizedTest
    @MethodSource("dealerStatusCardProvider")
    @DisplayName("딜러와 참가자의 패 정보를 확인해 딜러의 승무패를 반환한다.")
    void getDealerStatus(Card card, Result result) {
        final Participant dealer = participants.findDealer();
        leo.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        dealer.receiveCard(card);

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        final Map<Result, Integer> dealerStatus = gameResult.getDealerStatus(gameResult.getPlayerStatus());

        assertThat(dealerStatus.get(result)).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어의 게임 결과를 반환한다.")
    void getPlayerResult() {
        participants.deal(deck);
        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(),
                participants.findDealer());
        Map<Participant, Result> playerResults = gameResult.getPlayerStatus();
        for (Map.Entry<Participant, Result> playerResult : playerResults.entrySet()) {
            assertThat(playerResult.getValue()).isEqualTo(Result.LOSE);
        }
        assertThat(playerResults.size()).isEqualTo(1);
    }

    private static Stream<Arguments> bustStatusCardProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.CLOVER, Rank.TWO), true),
                Arguments.of(new Card(Suit.HEART, Rank.ACE), false)
        );
    }

    private static Stream<Arguments> dealerStatusCardProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.CLOVER, Rank.TWO), Result.LOSE),
                Arguments.of(new Card(Suit.HEART, Rank.QUEEN), Result.TIE),
                Arguments.of(new Card(Suit.HEART, Rank.ACE), Result.WIN)
        );
    }
}
