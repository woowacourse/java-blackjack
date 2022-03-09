package blackjack.domain;

import static blackjack.domain.Judgement.DRAW;
import static blackjack.domain.Judgement.LOSE;
import static blackjack.domain.Judgement.WIN;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.TEN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    @ParameterizedTest
    @MethodSource("provideResultForNotBust")
    @DisplayName("딜러와 플레이어 둘 다 버스트하지 않았을 경우 점수가 더 큰 쪽이 이긴다.")
    void bothNotBust(Dealer dealer, Map<Judgement, Integer> judgementMap, Judgement playerJudgement) {
        // given
        Card heartTen = new Card(Pattern.HEART, TEN);
        Card spadeNine = new Card(Pattern.SPADE, NINE);
        List<Card> playerCards = List.of(heartTen, spadeNine);
        Player player = new Player("Dealer", playerCards);
        List<Player> players = List.of(player);

        // when
        Result result = new Result(dealer, players);
        Map<Judgement, Integer> dealerResult = result.getDealerResult();
        Map<String, Judgement> playersResult = result.getPlayersResult();

        // then
        assertAll(() -> judgementMap.keySet()
                        .forEach(key -> assertThat(dealerResult.get(key)).isEqualTo(judgementMap.get(key))),
                () -> assertThat(playersResult.get(player.getName())).isEqualTo(playerJudgement));
    }

    private static Stream<Arguments> provideResultForNotBust() {
        return Stream.of(
                Arguments.of(createDealer(TEN, TEN), createJudgementMap(1, 0, 0), LOSE),
                Arguments.of(createDealer(TEN, NINE), createJudgementMap(0, 1, 0), DRAW),
                Arguments.of(createDealer(TEN, EIGHT), createJudgementMap(0, 0, 1), WIN)
        );
    }

    private static Dealer createDealer(Denomination denomination1, Denomination denomination2) {
        Card card1 = new Card(Pattern.DIAMOND, denomination1);
        Card card2 = new Card(Pattern.CLOVER, denomination2);
        List<Card> dealerCards = List.of(card1, card2);
        return new Dealer("Dealer", dealerCards);
    }

    private static Map<Judgement, Integer> createJudgementMap(int win, int draw, int lose) {
        Map<Judgement, Integer> judgementMap = new EnumMap<>(Judgement.class);
        judgementMap.put(Judgement.WIN, win);
        judgementMap.put(Judgement.DRAW, draw);
        judgementMap.put(LOSE, lose);

        return judgementMap;
    }
}
