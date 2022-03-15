package blackjack.domain;

import static blackjack.domain.Judgement.*;
import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Pattern.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public class JudgementTest {

    @ParameterizedTest
    @MethodSource("provideResultForNotBust")
    @DisplayName("딜러와 플레이어 둘 다 버스트하지 않았을 경우 플레이어 점수가 더 크다면 이긴다.")
    void bothNotBust(Dealer dealer, Judgement playerJudgement) {
        // given
        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, NINE);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Money betMoney = new Money(1000);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);

        // when
        Judgement judgement = judgePlayer(player, dealer);

        // then
        assertThat(judgement).isEqualTo(playerJudgement);
    }

    private static Stream<Arguments> provideResultForNotBust() {
        return Stream.of(
            Arguments.of(createDealer(TEN), LOSE),
            Arguments.of(createDealer(NINE), DRAW),
            Arguments.of(createDealer(EIGHT), WIN)
        );
    }

    private static Dealer createDealer(Denomination denomination2) {
        Card card1 = new Card(DIAMOND, TEN);
        Card card2 = new Card(CLOVER, denomination2);
        Cards cards = new Cards(List.of(card1, card2));
        return new Dealer(cards);
    }

    @ParameterizedTest
    @MethodSource("provideForPlayerBust")
    @DisplayName("플레이어가 버스트일 경우 무조건 플레이어가 진다.")
    void playerBust(Card dealerCard, Card playerCard) {
        // given
        Dealer dealer = createDealer(SIX);
        dealer.hit(dealerCard);

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, TEN);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Money betMoney = new Money(1000);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);
        player.hit(playerCard);

        // when
        Judgement judgement = judgePlayer(player, dealer);

        // then
        assertThat(judgement).isEqualTo(LOSE);
    }

    private static Stream<Arguments> provideForPlayerBust() {
        return Stream.of(
            Arguments.of(new Card(HEART, SIX), new Card(HEART, TWO)),
            Arguments.of(new Card(HEART, FIVE), new Card(HEART, TWO))
        );
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트일 경우 플레이어가 이긴다.")
    void dealerBust() {
        // given
        Dealer dealer = createDealer(SIX);
        dealer.hit(new Card(HEART, SIX));

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, TEN);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Money betMoney = new Money(1000);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);

        // when
        Judgement judgement = judgePlayer(player, dealer);

        // then
        assertThat(judgement).isEqualTo(WIN);
    }

    @Test
    @DisplayName("딜러와 똑같이 21점이어도 2장의 카드만 가지고 있는 플레이어가 블랙잭이다.")
    void blackJackDoesNotDefeat() {
        // given
        Dealer dealer = createDealer(TEN);
        dealer.hit(new Card(DIAMOND, ACE));

        Card heartTen = new Card(HEART, TEN);
        Card spadeAce = new Card(SPADE, ACE);
        Cards playerCards = new Cards(List.of(heartTen, spadeAce));
        Money betMoney = new Money(1000);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);

        // when
        Judgement judgement = judgePlayer(player, dealer);

        // then
        assertThat(judgement).isEqualTo(BLACKJACK);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭인 경우 비긴다.")
    void blackJackDrawWithBlackJack() {
        // given
        Dealer dealer = createDealer(ACE);

        Card heartTen = new Card(HEART, TEN);
        Card spadeNine = new Card(SPADE, ACE);
        Cards playerCards = new Cards(List.of(heartTen, spadeNine));
        Money betMoney = new Money(1000);
        Player player = new Player(new Name("pobi"), playerCards, betMoney);

        // when
        Judgement judgement = judgePlayer(player, dealer);

        // then
        assertThat(judgement).isEqualTo(DRAW);
    }
}
