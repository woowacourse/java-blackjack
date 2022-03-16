package blackjack.domain;

import static blackjack.domain.testutil.CardDeckFixtureGenerator.createCardDeck;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    Dealer dealer = new Dealer();
    Gambler gambler = new Gambler("돌범", new BetMoney(1000));
    CardDeck cardDeck;


    @DisplayName("버스트가 없는 경우에 한하여")
    @Nested
    class GameResultWithoutBurstTest {
        @BeforeEach
        void setUp() {
            //given
            final PlayingCard card1 = new PlayingCard(Suit.CLUBS, Denomination.FIVE);
            final PlayingCard card2 = new PlayingCard(Suit.CLUBS, Denomination.SIX);
            Deque<PlayingCard> rawCardDeck = new ArrayDeque<>();
            rawCardDeck.push(card1);
            rawCardDeck.push(card2);
            cardDeck = new CardDeck(() -> rawCardDeck);
        }

        @DisplayName("카드 점수에 따라 승에 해당하는 객체를 반환하는지 확인한다.")
        @Test
        void win() {
            //given
            cardDeck.drawTo(dealer); // six to dealer
            cardDeck.drawTo(gambler); // five to gambler

            //when
            final GameResult result = GameResult.of(dealer, gambler);

            //then
            assertThat(result).isEqualTo(GameResult.WIN);
        }

        @DisplayName("카드 점수에 따라 패에 해당하는 객체를 반환하는지 확인한다.")
        @Test
        void lose() {
            //given
            cardDeck.drawTo(gambler); // six to gambler
            cardDeck.drawTo(dealer); // five to dealer

            //when
            final GameResult result = GameResult.of(dealer, gambler);

            //then
            assertThat(result).isEqualTo(GameResult.LOSE);
        }

        @DisplayName("카드 점수에 따라 무승부에 해당하는 객체를 반환하는지 확인한다.")
        @Test
        void draw() {
            //given
            PlayingCard card3 = new PlayingCard(Suit.CLUBS, Denomination.SEVEN);
            PlayingCard card4 = new PlayingCard(Suit.HEARTS, Denomination.SEVEN);
            Deque<PlayingCard> rawCardDeck = new ArrayDeque<>();
            rawCardDeck.push(card3);
            rawCardDeck.push(card4);
            cardDeck = new CardDeck(() -> rawCardDeck);

            cardDeck.drawTo(gambler);
            cardDeck.drawTo(dealer);

            //when
            final GameResult result = GameResult.of(dealer, gambler);

            //then
            assertThat(result).isEqualTo(GameResult.DRAW);
        }
    }


    @DisplayName("딜러가 버스트인 경우, 패배 결과 객체를 반환하는지 확인한다.")
    @Test
    void burst_dealer_lose() {
        //given
        cardDeck = createCardDeck(
            new PlayingCard(Suit.CLUBS, Denomination.KING),
            new PlayingCard(Suit.HEARTS, Denomination.KING),
            new PlayingCard(Suit.HEARTS, Denomination.JACK),
            new PlayingCard(Suit.HEARTS, Denomination.QUEEN)
        );
        this.cardDeck.drawTo(dealer);
        this.cardDeck.drawTo(dealer);
        this.cardDeck.drawTo(dealer);

        this.cardDeck.drawTo(gambler);

        //when
        final GameResult result = GameResult.of(dealer, gambler);

        //then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 버스트이면서 겜블러가 블랙잭인 경우, 단순 패배가 아니라 블랙잭으로 인한 패배 결과 객체를 반환하는지 확인한다.")
    @Test
    void burst_dealer_and_gambler_blackjack_lose() {
        //given
        cardDeck = createCardDeck(
            new PlayingCard(Suit.CLUBS, Denomination.ACE),
            new PlayingCard(Suit.CLUBS, Denomination.KING),
            new PlayingCard(Suit.HEARTS, Denomination.KING),
            new PlayingCard(Suit.HEARTS, Denomination.JACK),
            new PlayingCard(Suit.HEARTS, Denomination.QUEEN)
        );
        this.cardDeck.drawTo(dealer);
        this.cardDeck.drawTo(dealer);
        this.cardDeck.drawTo(dealer);

        this.cardDeck.drawTo(gambler);
        this.cardDeck.drawTo(gambler);

        //when
        final GameResult result = GameResult.of(dealer, gambler);

        //then
        assertThat(result).isEqualTo(GameResult.BLACKJACK);
    }

    @DisplayName("겜블러가 버스트인 경우, 승리 결과 객체를 반환하는지 확인한다.")
    @Test
    void burst_gambler_lose() {
        //given
        cardDeck = createCardDeck(
            new PlayingCard(Suit.CLUBS, Denomination.KING),
            new PlayingCard(Suit.HEARTS, Denomination.KING),
            new PlayingCard(Suit.HEARTS, Denomination.JACK),
            new PlayingCard(Suit.HEARTS, Denomination.QUEEN)
        );

        cardDeck.drawTo(gambler);
        cardDeck.drawTo(gambler);
        cardDeck.drawTo(gambler);

        cardDeck.drawTo(dealer);

        //when
        final GameResult result = GameResult.of(dealer, gambler);

        //then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @DisplayName("둘다 버스트인 경우, 무승부 결과 객체를 반환하는지 확인한다.")
    @Test
    void burst_draw() {
        //given
        cardDeck = createCardDeck(
            new PlayingCard(Suit.CLUBS, Denomination.KING),
            new PlayingCard(Suit.CLUBS, Denomination.JACK),
            new PlayingCard(Suit.CLUBS, Denomination.QUEEN),
            new PlayingCard(Suit.HEARTS, Denomination.KING),
            new PlayingCard(Suit.HEARTS, Denomination.JACK),
            new PlayingCard(Suit.HEARTS, Denomination.QUEEN)
        );

        cardDeck.drawTo(gambler);
        cardDeck.drawTo(gambler);
        cardDeck.drawTo(gambler);

        cardDeck.drawTo(dealer);
        cardDeck.drawTo(dealer);
        cardDeck.drawTo(dealer);

        //when
        final GameResult result = GameResult.of(dealer, gambler);

        //then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("겜블러가 블랙잭인 경우, 딜러는 블랙잭으로 인한 패배를 반환 받는다.")
    @Test
    void blackjack() {
        //given
        cardDeck = createCardDeck(
            new PlayingCard(Suit.HEARTS, Denomination.JACK),
            new PlayingCard(Suit.HEARTS, Denomination.QUEEN),
            new PlayingCard(Suit.CLUBS, Denomination.ACE),
            new PlayingCard(Suit.CLUBS, Denomination.KING)
        );

        cardDeck.drawTo(gambler);
        cardDeck.drawTo(gambler);

        cardDeck.drawTo(dealer);
        cardDeck.drawTo(dealer);

        //when
        final GameResult result = GameResult.of(dealer, gambler);

        //then
        assertThat(result).isEqualTo(GameResult.BLACKJACK);
    }

    @DisplayName("겜블러와 딜러가 모두 블랙잭인 경우에도 딜러는 gambler의 블랙잭으로 인한 패배를 반환 받는다.")
    @Test
    void blackjack_both() {
        //given
        cardDeck = createCardDeck(
            new PlayingCard(Suit.HEARTS, Denomination.ACE),
            new PlayingCard(Suit.HEARTS, Denomination.QUEEN),
            new PlayingCard(Suit.CLUBS, Denomination.ACE),
            new PlayingCard(Suit.CLUBS, Denomination.KING)
        );

        cardDeck.drawTo(gambler);
        cardDeck.drawTo(gambler);

        cardDeck.drawTo(dealer);
        cardDeck.drawTo(dealer);

        //when
        final GameResult result = GameResult.of(dealer, gambler);

        //then
        assertThat(result).isEqualTo(GameResult.BLACKJACK);
        System.out.println();
    }
}
