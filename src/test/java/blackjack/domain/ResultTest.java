package blackjack.domain;

import static blackjack.domain.Judgement.DRAW;
import static blackjack.domain.Judgement.LOSE;
import static blackjack.domain.Judgement.WIN;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Pattern.CLOVER;
import static blackjack.domain.card.Pattern.DIAMOND;
import static blackjack.domain.card.Pattern.HEART;
import static blackjack.domain.card.Pattern.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    @ParameterizedTest
    @MethodSource("provideResultForNotBust")
    @DisplayName("딜러와 플레이어 둘 다 버스트하지 않았을 경우 점수가 더 큰 쪽이 이긴다.")
    void bothNotBust(Dealer dealer, Map<Judgement, Integer> judgementMap, Judgement playerJudgement) {
        // given
        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, NINE);
        List<Card> playerCards = List.of(heartTen, spadeNine);
        Player player = new Player("Dealer", playerCards);
        List<Player> players = List.of(player);

        // when
        Result result = new Result(dealer, players);
        Map<Judgement, Integer> dealerResult = result.getDealerResult();
        Map<String, Judgement> playersResult = result.getPlayersResult();

        // then
        assertAll(() -> assertThat(dealerResult).isEqualTo(judgementMap),
                () -> assertThat(playersResult.get(player.getName())).isEqualTo(playerJudgement));
    }

    private static Stream<Arguments> provideResultForNotBust() {
        return Stream.of(
                Arguments.of(createDealer(TEN), createJudgementMap(1, 0, 0), LOSE),
                Arguments.of(createDealer(NINE), createJudgementMap(0, 1, 0), DRAW),
                Arguments.of(createDealer(EIGHT), createJudgementMap(0, 0, 1), WIN)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForPlayerBust")
    @DisplayName("플레이어가 버스트면 무조건 딜러가 이긴다")
    void playerBust(CardDeck deck) {
        // given
        Dealer dealer = createDealer(SIX);
        dealer.play(deck);

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, TEN);
        List<Card> playerCards = List.of(heartTen, spadeNine);
        Player player = new Player("Dealer", playerCards);
        player.hit(deck);
        List<Player> players = List.of(player);

        // when
        Result result = new Result(dealer, players);
        Map<Judgement, Integer> dealerResult = result.getDealerResult();
        Map<String, Judgement> playersResult = result.getPlayersResult();
        Map<Judgement, Integer> judgementMap = createJudgementMap(1, 0, 0);

        // then
        assertAll(() -> assertThat(dealerResult).isEqualTo(judgementMap),
                () -> assertThat(playersResult.get(player.getName())).isEqualTo(LOSE));
    }

    private static Stream<Arguments> provideForPlayerBust() {
        return Stream.of(
                Arguments.of(new CardDeck(() -> new ArrayList<>(List.of(new Card(HEART, SIX), new Card(HEART, TWO))))),
                Arguments.of(new CardDeck(() -> new ArrayList<>(List.of(new Card(HEART, FIVE), new Card(HEART, TWO)))))
        );
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트면 플레이어가 이긴다")
    void dealerBust() {
        // given
        Dealer dealer = createDealer(SIX);
        dealer.play(new CardDeck(() -> new ArrayList<>(List.of(new Card(HEART, SIX)))));

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, TEN);
        List<Card> playerCards = List.of(heartTen, spadeNine);
        Player player = new Player("Dealer", playerCards);
        List<Player> players = List.of(player);

        // when
        Result result = new Result(dealer, players);
        Map<Judgement, Integer> dealerResult = result.getDealerResult();
        Map<String, Judgement> playersResult = result.getPlayersResult();
        Map<Judgement, Integer> judgementMap = createJudgementMap(0, 0, 1);

        // then
        assertAll(() -> assertThat(dealerResult).isEqualTo(judgementMap),
                () -> assertThat(playersResult.get(player.getName())).isEqualTo(WIN));
    }

    private static Dealer createDealer(Denomination denomination2) {
        Card card1 = new Card(DIAMOND, TEN);
        Card card2 = new Card(CLOVER, denomination2);
        List<Card> dealerCards = List.of(card1, card2);
        return new Dealer(dealerCards);
    }

    private static Map<Judgement, Integer> createJudgementMap(int win, int draw, int lose) {
        Map<Judgement, Integer> judgementMap = new EnumMap<>(Judgement.class);
        judgementMap.put(WIN, win);
        judgementMap.put(DRAW, draw);
        judgementMap.put(LOSE, lose);

        return judgementMap;
    }
}
