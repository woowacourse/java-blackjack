package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    Player pobi;
    Dealer dealer;
    Card clover6, heart8, spadeA, heart6, diamond8, cloverA, spadeK, clover7, spade8, heart2, clover9, diamond3;

    @BeforeEach
    void init() {
        Name name = new Name("pobi");
        pobi = new Player(name);

        dealer = new Dealer();

        clover6 = new Card(Symbol.CLOVER, Rank.SIX);
        heart8 = new Card(Symbol.HEART, Rank.EIGHT);
        spadeA = new Card(Symbol.SPADE, Rank.BIG_ACE);
        heart6 = new Card(Symbol.HEART, Rank.SIX);
        diamond8 = new Card(Symbol.DIAMOND, Rank.EIGHT);
        cloverA = new Card(Symbol.CLOVER, Rank.BIG_ACE);
        spadeK = new Card(Symbol.SPADE, Rank.KING);
        clover7 = new Card(Symbol.CLOVER, Rank.SEVEN);
        spade8 = new Card(Symbol.SPADE, Rank.EIGHT);
        heart2 = new Card(Symbol.HEART, Rank.TWO);
        clover9 = new Card(Symbol.CLOVER, Rank.NINE);
        diamond3 = new Card(Symbol.DIAMOND, Rank.THREE);
    }

    @DisplayName("딜러가 버스트가 아니고, 블랙잭이 아닐 경우에 플레이어의 플레이어 우승 결과를 구한다.")
    @Test
    void getDealerIsNotBustCaseTest() {
        // given
        pobi.hit(diamond3);
        pobi.hit(clover9);
        pobi.hit(heart2); // pobi 점수 총합 : 14

        dealer.hit(clover9);
        dealer.hit(diamond8); // 딜러 점수 총합 : 17

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 버스트 일 때 플레이어의 플레이어 우승 결과를 구한다.")
    @Test
    void getDealerIsBustCaseTest() {
        // given
        pobi.hit(diamond3);
        pobi.hit(clover9);
        pobi.hit(heart2); // pobi 점수 총합 : 14

        dealer.hit(clover9);
        dealer.hit(heart6);
        dealer.hit(diamond8); // 딜러 점수 총합 : 23

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러가 블랙잭 일 때 플레이어의 플레이어 우승 결과를 구한다.")
    @Test
    void getDealerIsBlackJackCaseTest() {
        // given
        pobi.hit(diamond3);
        pobi.hit(clover9);
        pobi.hit(heart2);
        pobi.hit(clover7);// pobi 점수 총합 : 21

        dealer.hit(spadeK);
        dealer.hit(spadeA); // 딜러 점수 총합 : 21 (블랙잭)

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("둘 다 버스트, 블랙잭이 아닐 때 점수로 비교하여 플레이어 우승 결과를 구한다.")
    @Test
    void compareScoreWithTest() {
        // given
        pobi.hit(diamond3);
        pobi.hit(clover9);
        pobi.hit(heart2); // pobi 점수 총합 : 14

        dealer.hit(spadeK); // 딜러 점수 총합 : 10

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.WIN);
    }
}
