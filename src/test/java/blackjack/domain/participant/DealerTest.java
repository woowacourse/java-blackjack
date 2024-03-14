package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import blackjack.domain.fixture.CardsFixture;
import blackjack.domain.money.BetAmount;
import blackjack.domain.money.Profit;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @DisplayName("카드의 총 점수가 16을 넘지 않으면, 카드를 더 뽑을 수 있다")
    @Test
    void isDrawableTest_whenScoreIsUnderBound_returnTrue() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("카드의 총 점수가 17을 넘으면, 카드를 더 뽑을 수 없다")
    @Test
    void isDrawableTest_whenScoreIsOverBound_returnFalse() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("cardsAndScore")
    void calculateScoreTest(List<Card> cards, int expected) {
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.calculateScore()).isEqualTo(expected);
    }

    static Stream<Arguments> cardsAndScore() {
        return Stream.of(
                Arguments.of(CardsFixture.BLACKJACK, 21),
                Arguments.of(CardsFixture.TWO_ACE, 12),
                Arguments.of(CardsFixture.CARDS_SCORE_16, 16)
        );
    }

    @DisplayName("게임을 시작할 때는 카드를 두 장 뽑는다.")
    @Test
    void drawStartCardsTest() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createShuffledDeck();

        dealer.drawStartCards(deck);

        assertThat(dealer.getCards()).hasSize(2);
    }

    @DisplayName("이미 카드를 가지고 있는 경우, 시작 카드를 뽑을 수 없다.")
    @Test
    void drawStartCardsTest_whenAlreadyStarted_throwException() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);
        Deck deck = Deck.createShuffledDeck();

        assertThatThrownBy(() -> dealer.drawStartCards(deck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 시작 카드를 뽑았습니다.");
    }

    @DisplayName("카드의 총 점수가 16을 넘지 않으면, 카드를 한 장 뽑는다")
    @Test
    void addTest_whenScoreIsUnderBound() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_16);
        Card additionalCard = new Card(Value.ACE, Shape.HEART);

        dealer.add(additionalCard);

        assertThat(dealer.getCards())
                .containsAll(CardsFixture.CARDS_SCORE_16)
                .contains(additionalCard)
                .hasSize(3);
    }

    @DisplayName("카드의 총 점수가 16을 넘으면, 카드를 뽑을 때 예외가 발생한다.")
    @Test
    void addTest_whenScoreIsOverBound_throwException() {
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);
        Card card = new Card(Value.ACE, Shape.HEART);

        assertThatThrownBy(() -> dealer.add(card))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
    }

    @DisplayName("플레이어들의 이익을 계산할 수 있다.")
    @Test
    void calculatePlayersProfitTest() {
        Player blackjackPlayer = new Player(CardsFixture.BLACKJACK, new Name("black"), new BetAmount(1_000));
        Player winPlayer = new Player(CardsFixture.CARDS_SCORE_21, new Name("win"), new BetAmount(2_000));
        Player losePlayer = new Player(CardsFixture.CARDS_SCORE_16, new Name("lose"), new BetAmount(3_000));
        Players players = new Players(List.of(blackjackPlayer, winPlayer, losePlayer));
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);

        Map<Player, Profit> matchResult = dealer.calculatePlayersProfit(players);

        assertThat(matchResult).containsExactly(
                Map.entry(blackjackPlayer, new Profit(1_500)),
                Map.entry(winPlayer, new Profit(2_000)),
                Map.entry(losePlayer, new Profit(-3_000))
        );
    }

    @DisplayName("딜러의 이익을 계산할 수 있다.")
    @Test
    void calculateDealerProfitTest() {
        Player blackjackPlayer = new Player(CardsFixture.BLACKJACK, new Name("black"), new BetAmount(1_000));
        Player winPlayer = new Player(CardsFixture.CARDS_SCORE_21, new Name("win"), new BetAmount(2_000));
        Player losePlayer = new Player(CardsFixture.CARDS_SCORE_16, new Name("lose"), new BetAmount(3_000));
        Players players = new Players(List.of(blackjackPlayer, winPlayer, losePlayer));
        Dealer dealer = new Dealer(CardsFixture.CARDS_SCORE_17);
        int expected = -1_500 - 2_000 + 3_000;

        Profit dealerProfit = dealer.calculateDealerProfit(players);

        assertThat(dealerProfit.toInt()).isEqualTo(expected);
    }
}
