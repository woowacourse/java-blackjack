package blackjack.domain;

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
import org.junit.jupiter.api.Test;

class GameResultTest {

    Dealer dealer;
    Gambler gambler;
    CardDeck cardDeck;
    PlayingCard card1;
    PlayingCard card2;

    @BeforeEach
    void setUp() {
        //given
        dealer = new Dealer();

        gambler = new Gambler("돌범");

        card1 = new PlayingCard(Suit.CLUBS, Denomination.FIVE);
        card2 = new PlayingCard(Suit.CLUBS, Denomination.SIX);
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

    @DisplayName("딜러가 버스트인 경우, 패배 결과 객체를 반환하는지 확인한다.")
    @Test
    void burst_dealer_lose() {
        //given
        PlayingCard card = new PlayingCard(Suit.CLUBS, Denomination.KING);
        PlayingCard burst1 = new PlayingCard(Suit.HEARTS, Denomination.KING);
        PlayingCard burst2 = new PlayingCard(Suit.HEARTS, Denomination.JACK);
        PlayingCard burst3 = new PlayingCard(Suit.HEARTS, Denomination.QUEEN);
        Deque<PlayingCard> rawCardDeck = new ArrayDeque<>();
        rawCardDeck.push(card);
        rawCardDeck.push(burst1);
        rawCardDeck.push(burst2);
        rawCardDeck.push(burst3);
        cardDeck = new CardDeck(() -> rawCardDeck);

        cardDeck.drawTo(dealer);
        cardDeck.drawTo(dealer);
        cardDeck.drawTo(dealer);
        cardDeck.drawTo(gambler);

        //when
        final GameResult result = GameResult.of(dealer, gambler);

        //then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("겜블러가 버스트인 경우, 승리 결과 객체를 반환하는지 확인한다.")
    @Test
    void burst_gambler_lose() {
        //given
        PlayingCard card = new PlayingCard(Suit.CLUBS, Denomination.KING);
        PlayingCard burst1 = new PlayingCard(Suit.HEARTS, Denomination.KING);
        PlayingCard burst2 = new PlayingCard(Suit.HEARTS, Denomination.JACK);
        PlayingCard burst3 = new PlayingCard(Suit.HEARTS, Denomination.QUEEN);
        Deque<PlayingCard> rawCardDeck = new ArrayDeque<>();
        rawCardDeck.push(card);
        rawCardDeck.push(burst1);
        rawCardDeck.push(burst2);
        rawCardDeck.push(burst3);
        cardDeck = new CardDeck(() -> rawCardDeck);

        cardDeck.drawTo(gambler);
        cardDeck.drawTo(gambler);
        cardDeck.drawTo(gambler);
        cardDeck.drawTo(dealer);

        //when
        final GameResult result = GameResult.of(dealer, gambler);

        //then
        assertThat(result).isEqualTo(GameResult.WIN);
    }
//
    @DisplayName("둘다 버스트인 경우, 무승부 결과 객체를 반환하는지 확인한다.")
    @Test
    void burst_draw() {
        //given
        PlayingCard burst1 = new PlayingCard(Suit.CLUBS, Denomination.KING);
        PlayingCard burst2 = new PlayingCard(Suit.CLUBS, Denomination.JACK);
        PlayingCard burst3 = new PlayingCard(Suit.CLUBS, Denomination.QUEEN);
        PlayingCard burst11 = new PlayingCard(Suit.HEARTS, Denomination.KING);
        PlayingCard burst22 = new PlayingCard(Suit.HEARTS, Denomination.JACK);
        PlayingCard burst33 = new PlayingCard(Suit.HEARTS, Denomination.QUEEN);
        Deque<PlayingCard> rawCardDeck = new ArrayDeque<>();
        rawCardDeck.push(burst1);
        rawCardDeck.push(burst2);
        rawCardDeck.push(burst3);
        rawCardDeck.push(burst11);
        rawCardDeck.push(burst22);
        rawCardDeck.push(burst33);
        cardDeck = new CardDeck(() -> rawCardDeck);

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
}
