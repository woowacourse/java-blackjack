package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.InputUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private Gamers gamers = InputUtils.splitAsDelimiter("pobi")
            .stream()
            .map(name -> new Player(name, "50"))
            .collect(collectingAndThen(toList(), players -> new Gamers(players, new Dealer())));

    @ParameterizedTest
    @MethodSource("generateWinResult")
    @DisplayName("플레이어가 승리한 경우 테스트")
    void resultWinTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
        gamers.getPlayers().get(0).addCard(cards);
        gamers.getDealer().addCard(dealerCards);
        GameResult gameResult = new GameResult(gamers);
        assertThat(gameResult.getGameResult().get(gamers.getPlayers().get(0))).isEqualTo(expected);
    }

    static Stream<Arguments> generateWinResult() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.EIGHT),
                        new Card(CardSuit.CLOVER, CardNumber.TEN)),
                        Arrays.asList(
                                new Card(CardSuit.CLOVER, CardNumber.SEVEN),
                                new Card(CardSuit.CLOVER, CardNumber.TEN)), MatchResult.WIN),
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.EIGHT),
                        new Card(CardSuit.CLOVER, CardNumber.TEN)),
                        Arrays.asList(
                                new Card(CardSuit.CLOVER, CardNumber.EIGHT),
                                new Card(CardSuit.CLOVER, CardNumber.TEN),
                                new Card(CardSuit.CLOVER, CardNumber.FOUR)), MatchResult.WIN));
    }

    @ParameterizedTest
    @MethodSource("generateDrawResult")
    @DisplayName("플레이어와 딜러가 비긴 경우 테스트")
    void resultDrawTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
        gamers.getPlayers().get(0).addCard(cards);
        gamers.getDealer().addCard(dealerCards);
        GameResult gameResult = new GameResult(gamers);
        assertThat(gameResult.getGameResult().get(gamers.getPlayers().get(0))).isEqualTo(expected);

    }

    static Stream<Arguments> generateDrawResult() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.TEN),
                        new Card(CardSuit.HEART, CardNumber.KING)),
                        Arrays.asList(
                                new Card(CardSuit.DIAMOND, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.DRAW),
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.ACE),
                        new Card(CardSuit.HEART, CardNumber.KING)),
                        Arrays.asList(
                                new Card(CardSuit.SPADE, CardNumber.ACE),
                                new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.DRAW));
    }

    @ParameterizedTest
    @MethodSource("generateLoseResult")
    @DisplayName("플레이어가 패배한 경우 테스트")
    void resultLoseTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
        gamers.getPlayers().get(0).addCard(cards);
        gamers.getDealer().addCard(dealerCards);
        GameResult gameResult = new GameResult(gamers);

        assertThat(gameResult.getGameResult().get(gamers.getPlayers().get(0))).isEqualTo(expected);
    }

    static Stream<Arguments> generateLoseResult() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.EIGHT),
                        new Card(CardSuit.CLOVER, CardNumber.TEN)),
                        Arrays.asList(
                                new Card(CardSuit.DIAMOND, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.LOSE),
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.HEART, CardNumber.KING),
                        new Card(CardSuit.HEART, CardNumber.JACK),
                        new Card(CardSuit.CLOVER, CardNumber.ACE)),
                        Arrays.asList(
                                new Card(CardSuit.SPADE, CardNumber.KING),
                                new Card(CardSuit.SPADE, CardNumber.ACE)), MatchResult.LOSE));
    }

    @ParameterizedTest
    @MethodSource("generateBustResult")
    @DisplayName("플레이어가 버스트인 경우 테스트")
    void resultBustTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
        gamers.getPlayers().get(0).addCard(cards);
        gamers.getDealer().addCard(dealerCards);
        GameResult gameResult = new GameResult(gamers);
        assertThat(gameResult.getGameResult().get(gamers.getPlayers().get(0))).isEqualTo(expected);
    }

    static Stream<Arguments> generateBustResult() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.EIGHT),
                        new Card(CardSuit.CLOVER, CardNumber.TEN),
                        new Card(CardSuit.HEART, CardNumber.KING)),
                        Arrays.asList(
                                new Card(CardSuit.DIAMOND, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.BUST),
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.EIGHT),
                        new Card(CardSuit.CLOVER, CardNumber.TEN),
                        new Card(CardSuit.CLOVER, CardNumber.FOUR)),
                        Arrays.asList(
                                new Card(CardSuit.DIAMOND, CardNumber.EIGHT),
                                new Card(CardSuit.DIAMOND, CardNumber.TEN),
                                new Card(CardSuit.DIAMOND, CardNumber.FOUR)), MatchResult.BUST));
    }

    @ParameterizedTest
    @MethodSource("generateBlackJackResult")
    @DisplayName("플레이어가 블랙잭인 경우 테스트")
    void resultBlackJackTest(List<Card> cards, List<Card> dealerCards, MatchResult expected) {
        gamers.getPlayers().get(0).addCard(cards);
        gamers.getDealer().addCard(dealerCards);
        GameResult gameResult = new GameResult(gamers);

        assertThat(gameResult.getGameResult().get(gamers.getPlayers().get(0))).isEqualTo(expected);
    }

    static Stream<Arguments> generateBlackJackResult() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.ACE),
                        new Card(CardSuit.HEART, CardNumber.KING)),
                        Arrays.asList(
                                new Card(CardSuit.DIAMOND, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.KING)), MatchResult.BLACKJACK),
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.ACE),
                        new Card(CardSuit.HEART, CardNumber.KING)),
                        Arrays.asList(
                                new Card(CardSuit.SPADE, CardNumber.KING),
                                new Card(CardSuit.SPADE, CardNumber.JACK),
                                new Card(CardSuit.SPADE, CardNumber.ACE)), MatchResult.BLACKJACK));
    }
}
