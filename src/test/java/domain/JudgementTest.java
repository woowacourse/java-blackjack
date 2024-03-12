package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.deck.Deck;
import domain.deck.strategy.SettedDeckGenerator;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class JudgementTest {

    SettedDeckGenerator settedDecksGenerator;
    Deck deck;
    Dealer dealer;
    Players players;
    Player pobi;
    Player neo;
    BlackJackGame blackJackGame;

    @BeforeEach
    void init() {
        dealer = new Dealer();

        Name name1 = new Name("pobi");
        pobi = new Player(name1);
        Name name2 = new Name("neo");
        neo = new Player(name2);

        players = new Players(List.of(pobi, neo));

        Card card1 = new Card(Symbol.DIAMOND, Rank.EIGHT);
        Card card2 = new Card(Symbol.CLOVER, Rank.BIG_ACE);
        Card card3 = new Card(Symbol.SPADE, Rank.KING);
        Card card4 = new Card(Symbol.CLOVER, Rank.SEVEN);
        Card card5 = new Card(Symbol.SPADE, Rank.EIGHT);
        Card card6 = new Card(Symbol.HEART, Rank.TWO);
        Card card7 = new Card(Symbol.CLOVER, Rank.NINE);
        Card card8 = new Card(Symbol.DIAMOND, Rank.THREE);

        List<Card> cards = List.of(card1, card2, card3, card4, card5, card6, card7, card8);
        SettedDeckGenerator settedDecksGenerator = new SettedDeckGenerator(cards);
        deck = Deck.createByStrategy(settedDecksGenerator);
    }

    @DisplayName("딜러가 버스트인 경우")
    @Nested
    class ifDealerBust {
        @BeforeEach
        void setDeckAndDealerBust() {
            Card card1 = new Card(Symbol.CLOVER, Rank.KING);
            Card card2 = new Card(Symbol.CLOVER, Rank.BIG_ACE);
            Card card3 = new Card(Symbol.DIAMOND, Rank.EIGHT);
            Card card4 = new Card(Symbol.SPADE, Rank.KING);
            Card card5 = new Card(Symbol.CLOVER, Rank.SEVEN);
            Card card6 = new Card(Symbol.SPADE, Rank.EIGHT);
            Card card7 = new Card(Symbol.HEART, Rank.THREE);
            Card card8 = new Card(Symbol.CLOVER, Rank.NINE);
            Card card9 = new Card(Symbol.DIAMOND, Rank.SEVEN);

            List<Card> cards = List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9);
            settedDecksGenerator = new SettedDeckGenerator(cards);
            deck = Deck.createByStrategy(settedDecksGenerator);
            blackJackGame = new BlackJackGame(deck);
            blackJackGame.prepareCards(dealer, players);
            blackJackGame.takeTurn(dealer);
        }

        @DisplayName("플레이어가 버스트가 아니라면 플레이어가 이긴다.")
        @Test
        void playerNotBust() {
            // when
            Judgement judgement = Judgement.of(dealer, players);

            // then
            assertAll(
                    () -> assertThat(judgement.getDealerResult().getWinCount()).isEqualTo(0),
                    () -> assertThat(judgement.getDealerResult().getLoseCount()).isEqualTo(2),
                    () -> assertThat(judgement.getDealerResult().getTieCount()).isEqualTo(0),
                    () -> assertThat(judgement.getPlayerResults().getResults().get(pobi)).isEqualTo(Result.WIN),
                    () -> assertThat(judgement.getPlayerResults().getResults().get(neo)).isEqualTo(Result.WIN)
            );
        }

        @DisplayName("플레이어(포비)가 버스트라면 딜러가 이긴다.")
        @Test
        void playerIsBust() {
            // given
            blackJackGame.takeTurn(pobi);
            blackJackGame.takeTurn(pobi);

            // when
            Judgement judgement = Judgement.of(dealer, players);

            // then
            assertAll(
                    () -> assertThat(judgement.getDealerResult().getWinCount()).isEqualTo(1),
                    () -> assertThat(judgement.getDealerResult().getLoseCount()).isEqualTo(1),
                    () -> assertThat(judgement.getDealerResult().getTieCount()).isEqualTo(0),
                    () -> assertThat(judgement.getPlayerResults().getResults().get(pobi)).isEqualTo(Result.LOSE),
                    () -> assertThat(judgement.getPlayerResults().getResults().get(neo)).isEqualTo(Result.WIN)
            );
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class ifDealerBlackJack {
        @BeforeEach
        void setDeckAndDealerBust() {
            Card card1 = new Card(Symbol.CLOVER, Rank.KING);
            Card card2 = new Card(Symbol.CLOVER, Rank.BIG_ACE);
            Card card3 = new Card(Symbol.DIAMOND, Rank.FOUR);
            Card card4 = new Card(Symbol.SPADE, Rank.KING);
            Card card5 = new Card(Symbol.CLOVER, Rank.SEVEN);
            Card card6 = new Card(Symbol.SPADE, Rank.BIG_ACE);
            Card card7 = new Card(Symbol.HEART, Rank.TEN);
            Card card8 = new Card(Symbol.CLOVER, Rank.BIG_ACE);
            Card card9 = new Card(Symbol.DIAMOND, Rank.TEN);

            List<Card> cards = List.of(card1, card2, card3, card4, card5, card6, card7, card8, card9);
            settedDecksGenerator = new SettedDeckGenerator(cards);
            deck = Deck.createByStrategy(settedDecksGenerator);
            blackJackGame = new BlackJackGame(deck);
            blackJackGame.prepareCards(dealer, players);
        }

        @DisplayName("플레이어(포비)도 블랙잭이라면 무승부이다.")
        @Test
        void playerNotBust() {
            // when
            Judgement judgement = Judgement.of(dealer, players);

            // then
            assertAll(
                    () -> assertThat(judgement.getDealerResult().getWinCount()).isEqualTo(1),
                    () -> assertThat(judgement.getDealerResult().getLoseCount()).isEqualTo(0),
                    () -> assertThat(judgement.getDealerResult().getTieCount()).isEqualTo(1),
                    () -> assertThat(judgement.getPlayerResults().getResults().get(pobi)).isEqualTo(Result.TIE),
                    () -> assertThat(judgement.getPlayerResults().getResults().get(neo)).isEqualTo(Result.LOSE)
            );
        }

        @DisplayName("플레이어(네오)가 블랙잭이 아니라면 딜러가 이긴다.")
        @Test
        void playerIsBust() {
            // given
            blackJackGame.takeTurn(neo);

            // when
            Judgement judgement = Judgement.of(dealer, players);

            // then
            assertAll(
                    () -> assertThat(judgement.getDealerResult().getWinCount()).isEqualTo(1),
                    () -> assertThat(judgement.getDealerResult().getLoseCount()).isEqualTo(0),
                    () -> assertThat(judgement.getDealerResult().getTieCount()).isEqualTo(1),
                    () -> assertThat(judgement.getPlayerResults().getResults().get(pobi)).isEqualTo(Result.TIE),
                    () -> assertThat(judgement.getPlayerResults().getResults().get(neo)).isEqualTo(Result.LOSE)
            );
        }
    }

}
