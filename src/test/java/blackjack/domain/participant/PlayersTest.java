package blackjack.domain.participant;

import static blackjack.domain.Judgement.*;
import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Pattern.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.Judgement;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;

public class PlayersTest {

    @Test
    @DisplayName("null 로 생성할 수 없다.")
    void createPlayersWithNull() {
        // then
        Assertions.assertThatThrownBy(() -> new Players(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("1명 이상이 Player 로 생성할 수 있다.")
    void createPlayersOverOnePlayer() {
        // then
        Assertions.assertThatThrownBy(() -> new Players(new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] Players 생성시 최소 한 명의 Player 가 필요합니다.");
    }

    @Test
    @DisplayName("중복된 이름을 가진 player 들로 생성할 수 없다.")
    void createPlayersWithDuplicatedName() {
        // given
        Name name1 = new Name("lala");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.TEN);
        Cards cards1 = new Cards(List.of(card1, card2));
        Player player1 = new Player(name1, cards1);

        Card card3 = new Card(Pattern.HEART, Denomination.TEN);
        Card card4 = new Card(Pattern.SPADE, Denomination.TEN);
        Cards cards2 = new Cards(List.of(card3, card4));
        Player player2 = new Player(name1, cards2);
        // then
        Assertions.assertThatThrownBy(() -> new Players(List.of(player1, player2)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] Player 이름은 중복될 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("provideResultForNotBust")
    @DisplayName("딜러와 플레이어 둘 다 버스트하지 않았을 경우 점수가 더 큰 쪽이 이긴다.")
    void bothNotBust(Dealer dealer, Map<Judgement, Integer> judgementMap, Judgement playerJudgement) {
        // given
        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, NINE);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Player player = new Player(new Name("pobi"), playerCards);
        Players players = new Players(List.of(player));

        // when
        Map<String, Judgement> playersResult = players.calculateJudgmentResult(dealer);

        // then
        assertThat(playersResult.get(player.getName())).isEqualTo(playerJudgement);
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
    void playerBust(Card dealerCard, Card playerCard) {
        // given
        Dealer dealer = createDealer(SIX);
        dealer.hit(dealerCard);

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, TEN);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Player player = new Player(new Name("pobi"), playerCards);
        player.hit(playerCard);
        Players players = new Players(List.of(player));

        // when
        Map<String, Judgement> playersResult = players.calculateJudgmentResult(dealer);

        // then
        assertThat(playersResult.get(player.getName())).isEqualTo(LOSE);
    }

    private static Stream<Arguments> provideForPlayerBust() {
        return Stream.of(
            Arguments.of(new Card(HEART, SIX), new Card(HEART, TWO)),
            Arguments.of(new Card(HEART, FIVE), new Card(HEART, TWO))
        );
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트면 플레이어가 이긴다")
    void dealerBust() {
        // given
        Dealer dealer = createDealer(SIX);
        dealer.hit(new Card(HEART, SIX));

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, TEN);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Player player = new Player(new Name("pobi"), playerCards);
        Players players = new Players(List.of(player));

        // when
        Map<String, Judgement> playersResult = players.calculateJudgmentResult(dealer);

        // then
        assertThat(playersResult.get(player.getName())).isEqualTo(WIN);
    }

    @Test
    @DisplayName("똑같이 21점이어도 블랙잭이 이긴다.")
    void blackJackDoesNotDefeat() {
        // given
        Dealer dealer = createDealer(ACE);

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, TEN);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Player player = new Player(new Name("pobi"), playerCards);
        player.hit(new Card(HEART, ACE));
        Players players = new Players(List.of(player));

        // when
        Map<String, Judgement> playersResult = players.calculateJudgmentResult(dealer);

        // then
        assertThat(playersResult.get(player.getName())).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("블랙잭 끼리는 비긴다")
    void blackJackDrawWithBlackJack() {
        // given
        Dealer dealer = createDealer(ACE);

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, ACE);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Player player = new Player(new Name("pobi"), playerCards);
        Players players = new Players(List.of(player));

        // when
        Map<String, Judgement> playersResult = players.calculateJudgmentResult(dealer);

        // then
        assertThat(playersResult.get(player.getName())).isEqualTo(DRAW);
    }

    private static Dealer createDealer(Denomination denomination2) {
        Card card1 = new Card(DIAMOND, TEN);
        Card card2 = new Card(CLOVER, denomination2);
        Cards cards = new Cards(List.of(card1, card2));
        return new Dealer(cards);
    }

    private static Map<Judgement, Integer> createJudgementMap(int win, int draw, int lose) {
        Map<Judgement, Integer> judgementMap = new EnumMap<>(Judgement.class);
        judgementMap.put(WIN, win);
        judgementMap.put(DRAW, draw);
        judgementMap.put(LOSE, lose);

        return judgementMap;
    }
}
