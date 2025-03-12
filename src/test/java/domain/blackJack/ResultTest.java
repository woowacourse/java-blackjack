package domain.blackJack;

import static domain.blackJack.MatchResult.BLACKJACK;
import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;
import static domain.card.Number.ACE;
import static domain.card.Number.FIVE;
import static domain.card.Number.FOUR;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Number.THREE;
import static domain.card.Number.TWO;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {
    @ParameterizedTest
    @DisplayName("결과 산출 테스트")
    @MethodSource("provideCardDeckForCalculateResultOfPlayer")
    void calculateResultOfPlayerTest(CardDeck cardDeck, MatchResult matchResult) {
        // given
        Result result = new Result();
        Player player = new Player("pobi", Money.from(1000));
        Dealer dealer = new Dealer();

        player.hitCard(cardDeck);
        player.hitCard(cardDeck);
        player.hitCard(cardDeck);

        dealer.hitCard(cardDeck);
        dealer.hitCard(cardDeck);
        dealer.hitCard(cardDeck);

        // when-then
        assertThat(result.calculateResultOfPlayer(player, dealer)).isEqualTo(matchResult);
    }

    private static Stream<Arguments> provideCardDeckForCalculateResultOfPlayer() {
        return Stream.of(
                Arguments.of(new CardDeck( // 25, 25, LOSE
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING), new Card(SPADE, FIVE),
                                new Card(HEART, JACK), new Card(HEART, KING), new Card(HEART, FIVE))), LOSE),
                Arguments.of(new CardDeck( // 25, 27, LOSE
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING), new Card(SPADE, FIVE),
                                new Card(HEART, JACK), new Card(HEART, KING), new Card(HEART, SEVEN))), LOSE),
                Arguments.of(new CardDeck( // 21, 23, LOSE
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING), new Card(SPADE, ACE),
                                new Card(HEART, JACK), new Card(HEART, KING), new Card(HEART, THREE))), LOSE),
                Arguments.of(new CardDeck( // 20, 10, LOSE
                        List.of(new Card(SPADE, JACK), new Card(SPADE, FOUR), new Card(SPADE, SIX),
                                new Card(HEART, TWO), new Card(HEART, THREE), new Card(HEART, FIVE))), LOSE),
                Arguments.of(new CardDeck( // 25, 20, WIN
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING), new Card(SPADE, FIVE),
                                new Card(HEART, TWO), new Card(HEART, THREE), new Card(HEART, FIVE))), WIN),
                Arguments.of(new CardDeck( // 10, 20, WIN
                        List.of(new Card(SPADE, TWO), new Card(SPADE, THREE), new Card(SPADE, FIVE),
                                new Card(HEART, JACK), new Card(HEART, FOUR), new Card(HEART, SIX))), WIN),
                Arguments.of(new CardDeck( // 20, 20, DRAW
                        List.of(new Card(SPADE, JACK), new Card(SPADE, FOUR), new Card(SPADE, SIX),
                                new Card(HEART, JACK), new Card(HEART, FOUR), new Card(HEART, SIX))), DRAW),
                Arguments.of(new CardDeck( // 21, 21, DRAW
                        List.of(new Card(SPADE, JACK), new Card(SPADE, FIVE), new Card(SPADE, SIX),
                                new Card(HEART, JACK), new Card(HEART, FIVE), new Card(HEART, SIX))), DRAW)
        );
    }

    @ParameterizedTest
    @DisplayName("블랙잭 테스트")
    @MethodSource("provideCardDeckForCalculateBlackJackOfPlayer")
    void calculateBlackJackOfPlayerTest(CardDeck cardDeck, MatchResult matchResult) {
        // given
        Result result = new Result();
        Player player = new Player("pobi", Money.from(1000));
        Dealer dealer = new Dealer();

        player.hitCard(cardDeck);
        player.hitCard(cardDeck);

        dealer.hitCard(cardDeck);
        dealer.hitCard(cardDeck);

        // when-then
        assertThat(result.calculateResultOfPlayer(player, dealer)).isEqualTo(matchResult);
    }

    private static Stream<Arguments> provideCardDeckForCalculateBlackJackOfPlayer() {
        return Stream.of(
                Arguments.of(new CardDeck( // 21, 20, BLACKJACK
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE),
                                new Card(HEART, JACK), new Card(HEART, KING))), BLACKJACK),
                Arguments.of(new CardDeck( // 21, 21, DRAW
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE),
                                new Card(HEART, JACK), new Card(HEART, ACE))), DRAW)
        );
    }
}
