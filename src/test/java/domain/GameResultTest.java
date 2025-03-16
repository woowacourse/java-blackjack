package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantName;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GameResultTest {

    @ParameterizedTest
    @CsvSource(value = {"WIN:10000", "LOSE:-10000", "PUSH:0", "BLACKJACK:15000"}, delimiterString = ":")
    void 게임결과_상태에따라_베팅금액을_반환한다(GameResult status, int expected) {
        assertThat(status.calculateProfit(10000)).isEqualTo(expected);
    }

    @Test
    void 플레이어가_카드를_추가로_뽑고_합이_21을_초과하면_플레이어는_베팅금액을_잃는다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.HEART, Rank.JACK),
                        new Card(Suit.SPADE, Rank.ACE))));

        Player player = new Player(new ParticipantName("drago"), new BettingAmount(10000),
                new Cards(List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.CLOVER, Rank.JACK),
                        new Card(Suit.HEART, Rank.TWO))));
        Players players = new Players(List.of(player));

        Map<Participant, Integer> expected = Map.of(player, -10000);

        assertThat(GameResult.calculateProfits(dealer, players)).isEqualTo(expected);
    }

    @Test
    void 플레이어가_가진_숫자들의_합이_21을_초과하지않고_딜러숫자의합이_21을_초과하면_플레이어는_베팅금액을_받는다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.HEART, Rank.JACK),
                        new Card(Suit.SPADE, Rank.TWO))));

        Player player = new Player(new ParticipantName("drago"), new BettingAmount(10000),
                new Cards(List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.CLOVER, Rank.NINE),
                        new Card(Suit.HEART, Rank.TWO))));
        Players players = new Players(List.of(player));

        Map<Participant, Integer> expected = Map.of(player, 10000);

        assertThat(GameResult.calculateProfits(dealer, players)).isEqualTo(expected);
    }

    @Test
    void 플레이어와_딜러가_가진_숫자들의_합이_21을_초과하지않는경우_21에가까운_플레이어는_베팅금액을_받는다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.HEART, Rank.EIGHT),
                        new Card(Suit.SPADE, Rank.TWO))));

        Player player = new Player(new ParticipantName("drago"), new BettingAmount(10000),
                new Cards(List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.CLOVER, Rank.NINE),
                        new Card(Suit.HEART, Rank.TWO))));
        Players players = new Players(List.of(player));

        Map<Participant, Integer> expected = Map.of(player, 10000);

        assertThat(GameResult.calculateProfits(dealer, players)).isEqualTo(expected);
    }

    @Test
    void 플레이어와_딜러가_가진_숫자들의_합이_21을_초과하지않고_동일하면_플레이어는_수익이_0이다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.HEART, Rank.NINE),
                        new Card(Suit.SPADE, Rank.TWO))));

        Player player = new Player(new ParticipantName("drago"), new BettingAmount(10000),
                new Cards(List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.CLOVER, Rank.NINE),
                        new Card(Suit.HEART, Rank.TWO))));
        Players players = new Players(List.of(player));

        Map<Participant, Integer> expected = Map.of(player, 0);

        assertThat(GameResult.calculateProfits(dealer, players)).isEqualTo(expected);
    }

    @Test
    void 플레이어의_처음_카드두장이_블랙잭이면_베팅금액의_쩜오배를_받는다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.HEART, Rank.NINE))));

        Player player = new Player(new ParticipantName("duei"), new BettingAmount(10000),
                new Cards(List.of(new Card(Suit.HEART, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.KING))));
        Players players = new Players(List.of(player));

        Map<Participant, Integer> expected = Map.of(player, 15000);

        assertThat(GameResult.calculateProfits(dealer, players)).isEqualTo(expected);
    }

    @Test
    void 딜러의_총수익을_반환한다() {
        Dealer dealer = new Dealer(new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.HEART, Rank.NINE))));

        Player player = new Player(new ParticipantName("duei"), new BettingAmount(10000),
                new Cards(List.of(new Card(Suit.HEART, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.KING))));
        Players players = new Players(List.of(player));

        int expected = -15000;

        assertThat(GameResult.calculateDealerProfits(dealer, players)).isEqualTo(expected);
    }
}
