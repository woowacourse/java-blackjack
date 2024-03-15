package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.deck.Deck;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import strategy.SettedShuffleStrategy;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("승패")
class RefereeTest {

    SettedShuffleStrategy shuffleStrategy;
    Deck deck;
    Dealer dealer;
    Players players;
    Name pobi;
    Name neo;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        pobi = new Name("pobi");
        neo = new Name("neo");
        players = new Players(List.of("pobi", "neo"));
    }

    private void prepareCards(final Dealer dealer, final Players players) {
        dealer.receive(drawTwoCards(deck));
        for (final Player player : players.getPlayers()) {
            player.receive(drawTwoCards(deck));
        }
    }

    private List<Card> drawTwoCards(final Deck deck) {
        return Stream.generate(deck::draw)
                .limit(2)
                .toList();
    }

    @DisplayName("딜러가 버스트인 경우")
    @Nested
    class ifDealerBust {
        @BeforeEach
        void setDeckAndDealerBust() {
            Card card1 = Card.of(Symbol.CLOVER, Rank.KING);
            Card card2 = Card.of(Symbol.CLOVER, Rank.BIG_ACE);
            Card card3 = Card.of(Symbol.DIAMOND, Rank.EIGHT);
            Card card4 = Card.of(Symbol.SPADE, Rank.KING);
            Card card5 = Card.of(Symbol.CLOVER, Rank.SEVEN);
            Card card6 = Card.of(Symbol.SPADE, Rank.EIGHT);
            Card card7 = Card.of(Symbol.HEART, Rank.THREE);
            Card card8 = Card.of(Symbol.CLOVER, Rank.NINE);
            Card card9 = Card.of(Symbol.DIAMOND, Rank.SEVEN);

            List<Card> cards = List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9);
            shuffleStrategy = new SettedShuffleStrategy(cards);
            deck = new Deck(shuffleStrategy);
            prepareCards(dealer, players);
            dealer.hit(deck.draw());
        }

        @DisplayName("플레이어가 버스트가 아니라면 딜러가 진다.")
        @Test
        void playerNotBust() {
            // when
            Result result1 = Referee.judgeBasedOnDealer(dealer, findPobi(players));
            Result result2 = Referee.judgeBasedOnDealer(dealer, findNeo(players));

            // then
            assertAll(
                    () -> assertThat(result1).isEqualTo(Result.LOSE),
                    () -> assertThat(result2).isEqualTo(Result.LOSE)
            );
        }

        @DisplayName("플레이어(포비)가 버스트라면 딜러가 이긴다.")
        @Test
        void playerIsBust() {
            // given
            findPobi(players).hit(deck.draw());
            findPobi(players).hit(deck.draw());

            // when
            Result result1 = Referee.judgeBasedOnDealer(dealer, findPobi(players));
            Result result2 = Referee.judgeBasedOnDealer(dealer, findNeo(players));

            // then
            assertAll(
                    () -> assertThat(result1).isEqualTo(Result.WIN),
                    () -> assertThat(result2).isEqualTo(Result.LOSE)
            );
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class ifDealerBlackJack {

        @BeforeEach
        void setDeckAndDealerBlackJack() {
            Card card1 = Card.of(Symbol.CLOVER, Rank.KING);
            Card card2 = Card.of(Symbol.CLOVER, Rank.BIG_ACE);
            Card card3 = Card.of(Symbol.DIAMOND, Rank.FOUR);
            Card card4 = Card.of(Symbol.SPADE, Rank.KING);
            Card card5 = Card.of(Symbol.CLOVER, Rank.SEVEN);
            Card card6 = Card.of(Symbol.SPADE, Rank.BIG_ACE);
            Card card7 = Card.of(Symbol.HEART, Rank.TEN);
            Card card8 = Card.of(Symbol.CLOVER, Rank.BIG_ACE);
            Card card9 = Card.of(Symbol.DIAMOND, Rank.TEN);

            List<Card> cards = List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9);
            shuffleStrategy = new SettedShuffleStrategy(cards);
            deck = new Deck(shuffleStrategy);
            prepareCards(dealer, players);
        }

        @DisplayName("플레이어(포비)도 블랙잭이라면 무승부이다.")
        @Test
        void playerIsBlackJack() {
            // when
            Result result1 = Referee.judgeBasedOnDealer(dealer, findPobi(players));
            Result result2 = Referee.judgeBasedOnDealer(dealer, findNeo(players));

            // then
            assertAll(
                    () -> assertThat(result1).isEqualTo(Result.TIE),
                    () -> assertThat(result2).isEqualTo(Result.WIN)
            );
        }

        @DisplayName("플레이어(네오)가 블랙잭이 아니라면 딜러가 이긴다.")
        @Test
        void playerIsNotBlackJack() {
            // given
            findNeo(players).hit(deck.draw());

            // when
            Result result1 = Referee.judgeBasedOnDealer(dealer, findPobi(players));
            Result result2 = Referee.judgeBasedOnDealer(dealer, findNeo(players));

            // then
            assertAll(
                    () -> assertThat(result1).isEqualTo(Result.TIE),
                    () -> assertThat(result2).isEqualTo(Result.WIN)
            );
        }
    }

    @DisplayName("딜러가 일반 숫자(버스트도 아니고, 블랙잭도 아닌)인 경우")
    @Nested
    class ifDealerNormalNumber {

        @BeforeEach
        void setDeckAndDealerBust() {
            Card card1 = Card.of(Symbol.CLOVER, Rank.KING);
            Card card2 = Card.of(Symbol.CLOVER, Rank.TWO);
            Card card3 = Card.of(Symbol.DIAMOND, Rank.TWO);
            Card card4 = Card.of(Symbol.SPADE, Rank.KING);
            Card card5 = Card.of(Symbol.CLOVER, Rank.SEVEN);
            Card card6 = Card.of(Symbol.SPADE, Rank.BIG_ACE);
            Card card7 = Card.of(Symbol.HEART, Rank.TEN);
            Card card8 = Card.of(Symbol.CLOVER, Rank.TEN);
            Card card9 = Card.of(Symbol.DIAMOND, Rank.NINE);

            List<Card> cards = List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9);
            shuffleStrategy = new SettedShuffleStrategy(cards);
            deck = new Deck(shuffleStrategy);
            prepareCards(dealer, players);
        }

        @DisplayName("플레이어(포비)가 블랙잭이라면 딜러가 진다.")
        @Test
        void playerIsBlackJack() {
            // when
            Result result1 = Referee.judgeBasedOnDealer(dealer, findPobi(players));
            Result result2 = Referee.judgeBasedOnDealer(dealer, findNeo(players));

            // then
            assertAll(
                    () -> assertThat(result1).isEqualTo(Result.LOSE),
                    () -> assertThat(result2).isEqualTo(Result.WIN)
            );
        }

        @DisplayName("플레이어(네오)가 버스트라면 딜러가 이긴다.")
        @Test
        void playerIsBust() {
            // given
            findNeo(players).hit(deck.draw());
            findNeo(players).hit(deck.draw());
            findNeo(players).hit(deck.draw());

            // when
            Result result1 = Referee.judgeBasedOnDealer(dealer, findPobi(players));
            Result result2 = Referee.judgeBasedOnDealer(dealer, findNeo(players));

            // then
            assertAll(
                    () -> assertThat(result1).isEqualTo(Result.LOSE),
                    () -> assertThat(result2).isEqualTo(Result.WIN)
            );
        }

        @DisplayName("플레이어(네오)와 딜러 모두 일반 점수인 경우, 점수가 같다면 무승부이다.")
        @Test
        void playerIsSameNormalScoreAndSame() {
            // given
            findNeo(players).hit(deck.draw());

            // when
            Result result1 = Referee.judgeBasedOnDealer(dealer, findPobi(players));
            Result result2 = Referee.judgeBasedOnDealer(dealer, findNeo(players));

            // then
            assertAll(
                    () -> assertThat(result1).isEqualTo(Result.LOSE),
                    () -> assertThat(result2).isEqualTo(Result.TIE)
            );
        }

        @DisplayName("플레이어(네오)와 딜러 모두 일반 점수인 경우, 딜러의 점수가 더 낮으면 딜러가 진다.")
        @Test
        void playerIsSameNormalScoreAndSmaller() {
            // given
            findNeo(players).hit(deck.draw());
            findNeo(players).hit(deck.draw());

            // when
            Result result1 = Referee.judgeBasedOnDealer(dealer, findPobi(players));
            Result result2 = Referee.judgeBasedOnDealer(dealer, findNeo(players));

            // then
            assertAll(
                    () -> assertThat(result1).isEqualTo(Result.LOSE),
                    () -> assertThat(result2).isEqualTo(Result.LOSE)
            );
        }
    }

    private Player findPobi(final Players players) {
        return players.getPlayers().stream()
                .filter(player -> player.getName().equals(pobi))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 테스트용 이름"));
    }

    private Player findNeo(final Players players) {
        return players.getPlayers().stream()
                .filter(player -> player.getName().equals(neo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 테스트용 이름"));
    }
}
