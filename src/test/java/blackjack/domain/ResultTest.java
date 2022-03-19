package blackjack.domain;

import static blackjack.Fixtures.CLOVER_ACE;
import static blackjack.Fixtures.CLOVER_FIVE;
import static blackjack.Fixtures.DIAMOND_FIVE;
import static blackjack.Fixtures.DIAMOND_SIX;
import static blackjack.Fixtures.SPADE_ACE;
import static blackjack.Fixtures.SPADE_FIVE;
import static blackjack.Fixtures.SPADE_KING;
import static blackjack.Fixtures.SPADE_QUEEN;
import static blackjack.Fixtures.SPADE_SEVEN;
import static blackjack.Fixtures.SPADE_TWO;
import static blackjack.TestUtils.createPlayerByName;
import static blackjack.domain.Result.BLACKJACK;
import static blackjack.domain.Result.LOSS;
import static blackjack.domain.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    private static final int MINIMUM_BETTING_AMOUNT = 10;

    @DisplayName("게임 결과를 제대로 반환하는지 테스트")
    @Test
    public void testGetResult() {
        //given
        Deck deck = initDeck();

        List<Player> players = List.of(
                createPlayerByName("pobi"),
                createPlayerByName("jason")
        );

        for (Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
        }

        Dealer dealer = Dealer.create();
        dealer.drawCard(deck);
        dealer.drawCard(deck);

        //when
        Map<String, Integer> result = Result.calculateRevenue(players, dealer);

        //then
        assertThat(result.get(players.get(0).getName())).isEqualTo((int)(MINIMUM_BETTING_AMOUNT * LOSS.getRate()));
        assertThat(result.get(players.get(1).getName())).isEqualTo((int)(MINIMUM_BETTING_AMOUNT * BLACKJACK.getRate()));
    }

    @DisplayName("둘 다 버스트가 아니고 딜러가 작은 경우")
    @Test
    public void testWinPlayerWithNotBust() {
        //given
        Deck deck = new Deck(() -> new ArrayDeque<>(List.of(
                SPADE_ACE,
                SPADE_SEVEN,
                SPADE_TWO,
                SPADE_QUEEN
        )));

        List<Player> players = List.of(createPlayerByName("pobi"));

        for (Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
        }

        Dealer dealer = Dealer.create();
        dealer.drawCard(deck);
        dealer.drawCard(deck);

        //when
        Map<String, Integer> result = Result.calculateRevenue(players, dealer);

        //then
        assertThat(result.get(players.get(0).getName())).isEqualTo(MINIMUM_BETTING_AMOUNT);
    }

    @DisplayName("둘 다 버스트가 아니고 둘이 같은 경우")
    @Test
    public void testSameScoreWhenNotBust() {
        //given
        Deck deck = new Deck(() -> new ArrayDeque<>(List.of(
                SPADE_QUEEN,
                CLOVER_FIVE,
                SPADE_KING,
                SPADE_FIVE
        )));

        List<Player> players = List.of(createPlayerByName("pobi"));

        for (Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
        }

        Dealer dealer = Dealer.create();
        dealer.drawCard(deck);
        dealer.drawCard(deck);

        //when
        Map<String, Integer> result = Result.calculateRevenue(players, dealer);

        //then
        assertThat(result.get(players.get(0).getName())).isEqualTo((int)(MINIMUM_BETTING_AMOUNT * WIN.getRate()));
    }

    @DisplayName("딜러는 버스트가 아니고 플레이어만 버스트인 경우")
    @Test
    public void testPlayerBust() {
        //given
        Deck deck = new Deck(() -> new ArrayDeque<>(List.of(
                SPADE_QUEEN,
                SPADE_QUEEN,
                SPADE_TWO,
                SPADE_KING,
                SPADE_FIVE
        )));

        List<Player> players = List.of(createPlayerByName("pobi"));

        for (Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
            player.drawCard(deck);
        }

        Dealer dealer = Dealer.create();
        dealer.drawCard(deck);
        dealer.drawCard(deck);

        //when
        Map<String, Integer> result = Result.calculateRevenue(players, dealer);

        //then
        assertThat(result.get(players.get(0).getName())).isEqualTo((int)(MINIMUM_BETTING_AMOUNT * LOSS.getRate()));
    }

    private Deck initDeck() {
        return new Deck(() -> new ArrayDeque<>(List.of(
                CLOVER_ACE,
                DIAMOND_FIVE,   //16
                SPADE_KING,
                SPADE_ACE,  //21
                DIAMOND_SIX,
                SPADE_ACE   //17
        )));
    }
}