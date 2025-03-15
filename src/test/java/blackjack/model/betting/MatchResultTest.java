package blackjack.model.betting;

import static blackjack.TestFixtures.UNSHUFFLED_DECK;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import blackjack.model.card.Suit;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.view.BettingPlayerCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("승부 결과 테스트")
class MatchResultTest {

    private Player makePlayer(String name) {
        return Player.of(new BettingPlayerCreateDto(name, 1000));
    }

    @DisplayName("플레이어는 버스트인 경우 진다.")
    @Test
    void lose_WhenPlayerBust() {
        // given
        Player player = makePlayer("pobi");
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        player.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);

        // when
        MatchResult matchResult = MatchResult.calculatePlayerResult(dealer, player);

        // then
        assertThat(matchResult)
                .isSameAs(MatchResult.LOSE);
    }

    @DisplayName("플레이어는 버스트가 아니고 딜러가 버스트인 경우 이긴다.")
    @Test
    void win_WhenPlayerNoBustAndDealerBust() {
        // given
        Player player = makePlayer("pobi");
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.TEN));
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.TEN));

        // when
        MatchResult matchResult = MatchResult.calculatePlayerResult(dealer, player);

        // then
        assertThat(matchResult)
                .isSameAs(MatchResult.WIN);
    }

    @DisplayName("둘 다 블랙잭인 경우 무승부이다.")
    @Test
    void draw_WhenAllBlackjack() {
        // given
        Player player = makePlayer("pobi");
        player.receiveHand(new Card(Suit.SPADES, CardValue.ACE));
        player.receiveHand(new Card(Suit.SPADES, CardValue.KING));
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.ACE));
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.KING));

        // when
        MatchResult matchResult = MatchResult.calculatePlayerResult(dealer, player);

        // then
        assertThat(matchResult)
                .isSameAs(MatchResult.DRAW);
    }

    @DisplayName("플레이어만 블랙잭이면 이긴다.")
    @Test
    void win_WhenOnlyPlayerBlackjack() {
        // given
        Player player = makePlayer("pobi");
        player.receiveHand(new Card(Suit.SPADES, CardValue.ACE));
        player.receiveHand(new Card(Suit.SPADES, CardValue.KING));
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.KING));
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.KING));

        // when
        MatchResult matchResult = MatchResult.calculatePlayerResult(dealer, player);

        // then
        assertThat(matchResult)
                .isSameAs(MatchResult.BLACKJACK);
    }

    @DisplayName("플레이어는 블랙잭이 아니고 딜러가 블랙잭이면 진다.")
    @Test
    void lose_WhenOnlyDealerBlackjack() {
        // given
        Player player = makePlayer("pobi");
        player.receiveHand(new Card(Suit.SPADES, CardValue.KING));
        player.receiveHand(new Card(Suit.SPADES, CardValue.KING));
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.ACE));
        dealer.receiveHand(new Card(Suit.SPADES, CardValue.KING));

        // when
        MatchResult matchResult = MatchResult.calculatePlayerResult(dealer, player);

        // then
        assertThat(matchResult)
                .isSameAs(MatchResult.LOSE);
    }

    @DisplayName("모두 블랙잭이나 버스트가 아닌 경우 숫자로 승부를 낸다.")
    @ParameterizedTest
    @CsvSource({
            "KING, EIGHT, KING, KING, LOSE",
            "KING, NINE, KING, NINE, DRAW",
            "KING, KING, KING, EIGHT, WIN"
    })
    void calculatePlayerResultTest(CardValue playerCard1, CardValue playerCard2,
                                   CardValue dealerCard1, CardValue dealerCard2,
                                   MatchResult expected) {
        // given
        Player player = makePlayer("pobi");
        player.receiveHand(new Card(Suit.SPADES, playerCard1));
        player.receiveHand(new Card(Suit.SPADES, playerCard2));
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(new Card(Suit.SPADES, dealerCard1));
        dealer.receiveHand(new Card(Suit.SPADES, dealerCard2));

        // when
        MatchResult matchResult = MatchResult.calculatePlayerResult(dealer, player);

        // then
        assertThat(matchResult)
                .isSameAs(expected);
    }

    @DisplayName("승부의 반대를 알 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "WIN, LOSE",
            "LOSE, WIN",
            "DRAW, DRAW"
    })
    void reverseTest(MatchResult matchResult, MatchResult expected) {
        // when
        MatchResult reverseMatchResult = MatchResult.reverse(matchResult);

        // then
        assertThat(reverseMatchResult)
                .isSameAs(expected);
    }
}
