package blackjack.domain;

import static blackjack.domain.TestCardFixture.*;
import static blackjack.domain.result.Result.LOSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameScoreBoard;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackGameTest {

    private BlackjackGame blackjackGame;
    private Deck deck;

    @BeforeEach
    void setUp() {
        blackjackGame = BlackjackGame.create("seung");
        deck = Deck.createShuffledCards();
    }

    @DisplayName("게임 실행 객체가 정상적으로 생성되었는지 확인")
    @Test
    void create() {
        List<Participant> players = blackjackGame.getPlayers();
        Participant participant = players.get(0);

        assertThat(participant.getName()).isEqualTo("seung");
    }

    @DisplayName("기본 카드 세팅이 정상적인지 확인")
    @Test
    void drawBaseCards() {
        blackjackGame.drawBaseCards(deck);
        Participant dealer = blackjackGame.getDealer();
        List<Participant> players = blackjackGame.getPlayers();
        Participant player = players.get(0);

        assertAll(
            () -> assertThat(dealer.getCards()).hasSize(2),
            () -> assertThat(player.getCards()).hasSize(2)
        );
    }

    @DisplayName("참가자들이 카드를 더 받을 수 있는지 없는지 확인")
    @ParameterizedTest
    @MethodSource("takeMoreCardByParticipantCase")
    void takeMoreCard(Participant participant, StubDeck stubDeck, boolean excepted) {
        participant.receiveCard(stubDeck.draw());
        participant.receiveCard(stubDeck.draw());

        assertThat(blackjackGame.takeMoreCard(participant, deck)).isEqualTo(excepted);
    }

    private static Stream<Arguments> takeMoreCardByParticipantCase() {
        return Stream.of(
            Arguments.of(new Dealer(), new StubDeck(List.of(aceCard, tenCard)), false),
            Arguments.of(new Player("seung"), new StubDeck(List.of(aceCard, tenCard)), false),
            Arguments.of(new Dealer(), new StubDeck(List.of(tenCard, sixCard)), true),
            Arguments.of(new Player("pobi"), new StubDeck(List.of(tenCard, sixCard)), true),
            Arguments.of(new Dealer(), new StubDeck(List.of(tenCard, sevenCard)), false),
            Arguments.of(new Player("heebong"), new StubDeck(List.of(tenCard, sevenCard)), true)
        );
    }

    @DisplayName("게임결과를 잘 계산하는지 확인")
    @Test
    void calculateGameScore() {
        StubDeck deck = new StubDeck(List.of(aceCard, tenCard, twoCard, threeCard));
        List<Participant> players = blackjackGame.getPlayers();
        Participant player = players.get(0);
        Participant dealer = blackjackGame.getDealer();

        player.receiveCard(deck.draw());
        player.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());

        GameScoreBoard gameScoreBoard = blackjackGame.calculateGameScore();
        Map<Result, Integer> dealerGameResult = gameScoreBoard.getDealerGameResult();
        for (Entry<Result, Integer> dealerResult : dealerGameResult.entrySet()) {
            assertThat(dealerResult.getKey()).isEqualTo(LOSE);
        }
    }
}
