package domain;

import static domain.FinalResult.DRAW;
import static domain.FinalResult.LOSE;
import static domain.FinalResult.WIN;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.gamer.Betting;
import domain.gamer.Dealer;
import domain.gamer.Nickname;
import domain.gamer.Player;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalResultTest {

    Player defaultPlayer;
    Player blackJackPlayer;
    Player bustPlayer;
    Dealer defaultDealer;
    Dealer blackJackDealer;
    Dealer bustDealer;

    Card cardQueen;
    Card cardKing;
    Card cardJack;
    Card cardAce;

    @BeforeEach
    void setUp() {

        defaultPlayer = new Player(new Nickname("기본"), new Betting(1000));
        blackJackPlayer = new Player(new Nickname("블랙잭"), new Betting(1000));
        bustPlayer = new Player(new Nickname("버스트"), new Betting(1000));

        defaultDealer = new Dealer();
        blackJackDealer = new Dealer();
        bustDealer = new Dealer();

        cardQueen = new Card(Rank.QUEEN, Shape.CLOVER);
        cardKing = new Card(Rank.KING, Shape.CLOVER);
        cardJack = new Card(Rank.JACK, Shape.CLOVER);
        cardAce = new Card(Rank.ACE, Shape.CLOVER);

        defaultPlayer.hit(cardJack);
        defaultDealer.hit(cardJack);
        blackJackPlayer.hit(cardJack);
        blackJackPlayer.hit(cardAce);

        blackJackDealer.hit(cardJack);
        blackJackDealer.hit(cardAce);

        bustPlayer.hit(cardQueen);
        bustPlayer.hit(cardJack);
        bustPlayer.hit(cardKing);
        bustDealer.hit(cardQueen);
        bustDealer.hit(cardJack);
        bustDealer.hit(cardKing);
    }

    @DisplayName("룰에 따라 승패를 결정한다.")
    @Test
    void 룰에_따라_승패를_결정한다() {

        // given

        // when

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(FinalResult.selectResult(defaultPlayer, defaultDealer)).isEqualTo(DRAW);
            softly.assertThat(FinalResult.selectResult(defaultPlayer, blackJackDealer)).isEqualTo(LOSE);
            softly.assertThat(FinalResult.selectResult(defaultPlayer, bustDealer)).isEqualTo(WIN);
            softly.assertThat(FinalResult.selectResult(blackJackPlayer, defaultDealer)).isEqualTo(WIN);
            softly.assertThat(FinalResult.selectResult(blackJackPlayer, blackJackDealer)).isEqualTo(DRAW);
            softly.assertThat(FinalResult.selectResult(blackJackPlayer, bustDealer)).isEqualTo(WIN);
            softly.assertThat(FinalResult.selectResult(bustPlayer, defaultDealer)).isEqualTo(LOSE);
            softly.assertThat(FinalResult.selectResult(bustPlayer, blackJackDealer)).isEqualTo(LOSE);
            softly.assertThat(FinalResult.selectResult(bustPlayer, bustDealer)).isEqualTo(LOSE);
        });
    }
}
