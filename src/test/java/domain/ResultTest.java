package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
    Player pobi, jason, solar, neo;
    Dealer dealer;
    Card clover6, heart8, spadeA, heart6, diamond8, cloverA, spadeK, clover7, spade8, heart2, clover9, diamond3;

    @BeforeEach
    void init() {
        Name name1 = new Name("pobi");
        Name name2 = new Name("jason");
        Name name3 = new Name("solar");
        Name name4 = new Name("neo");
        pobi = new Player(name1);
        jason = new Player(name2);
        solar = new Player(name3);
        neo = new Player(name4);

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

    @DisplayName("딜러가 버스트가 아니고, 블랙잭이 아닐 경우에 플레이어의 게임 결과를 구한다.")
    @Test
    void getDealerIsNotBustCaseTest() {
        // given
        pobi.hit(diamond3);
        pobi.hit(clover9);
        pobi.hit(heart2); // pobi 점수 총합 : 14

        jason.hit(spade8);
        jason.hit(clover7);
        jason.hit(spadeK); // jason 점수 총합 : 25 (버스트)

        solar.hit(cloverA);
        solar.hit(diamond8); // solar 점수 총합 : 19

        neo.hit(heart6);
        neo.hit(spadeA);
        neo.hit(heart8);
        neo.hit(clover6); // neo 점수 총합 : 21

        dealer.hit(clover9);
        dealer.hit(diamond8);
        dealer.hit(diamond3); // 딜러 점수 총합 : 20

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);
        Result jasonResult = Result.getPlayerResultWith(jason, dealer);
        Result solarResult = Result.getPlayerResultWith(solar, dealer);
        Result neoResult = Result.getPlayerResultWith(neo, dealer);

        // then
        assertAll(
                () -> assertThat(pobiResult).isEqualTo(Result.LOSE),
                () -> assertThat(jasonResult).isEqualTo(Result.LOSE),
                () -> assertThat(solarResult).isEqualTo(Result.LOSE),
                () -> assertThat(neoResult).isEqualTo(Result.WIN)
        );
    }

    @DisplayName("딜러가 버스트 일 때 플레이어의 게임 결과를 구한다.")
    @Test
    void getDealerIsBustCaseTest() {
        // given
        pobi.hit(diamond3);
        pobi.hit(clover9);
        pobi.hit(heart2); // pobi 점수 총합 : 14

        jason.hit(spade8);
        jason.hit(clover7);
        jason.hit(spadeK); // jason 점수 총합 : 25 (버스트)

        solar.hit(cloverA);
        solar.hit(diamond8); // solar 점수 총합 : 19

        neo.hit(heart6);
        neo.hit(spadeA);
        neo.hit(heart8);
        neo.hit(clover6); // neo 점수 총합 : 21

        dealer.hit(clover9);
        dealer.hit(diamond8);
        dealer.hit(heart2);
        dealer.hit(diamond3); // 딜러 점수 총합 : 22

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);
        Result jasonResult = Result.getPlayerResultWith(jason, dealer);
        Result solarResult = Result.getPlayerResultWith(solar, dealer);
        Result neoResult = Result.getPlayerResultWith(neo, dealer);

        // then
        assertAll(
                () -> assertThat(pobiResult).isEqualTo(Result.WIN),
                () -> assertThat(jasonResult).isEqualTo(Result.LOSE),
                () -> assertThat(solarResult).isEqualTo(Result.WIN),
                () -> assertThat(neoResult).isEqualTo(Result.WIN)
        );
    }

    @DisplayName("딜러가 블랙잭 일 때 플레이어의 게임 결과를 구한다.")
    @Test
    void getDealerIsBlackJackCaseTest() {
        // given
        pobi.hit(diamond3);
        pobi.hit(clover9);
        pobi.hit(heart2); // pobi 점수 총합 : 14

        jason.hit(spade8);
        jason.hit(clover7);
        jason.hit(spadeK); // jason 점수 총합 : 25 (버스트)

        solar.hit(cloverA);
        solar.hit(spadeK); // solar 점수 총합 : 21 (블랙잭)

        neo.hit(heart6);
        neo.hit(spadeA);
        neo.hit(heart8);
        neo.hit(clover6); // neo 점수 총합 : 21

        dealer.hit(spadeK);
        dealer.hit(spadeA); // 딜러 점수 총합 : 21 (블랙잭)

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);
        Result jasonResult = Result.getPlayerResultWith(jason, dealer);
        Result solarResult = Result.getPlayerResultWith(solar, dealer);
        Result neoResult = Result.getPlayerResultWith(neo, dealer);

        // then
        assertAll(
                () -> assertThat(pobiResult).isEqualTo(Result.LOSE),
                () -> assertThat(jasonResult).isEqualTo(Result.LOSE),
                () -> assertThat(solarResult).isEqualTo(Result.TIE),
                () -> assertThat(neoResult).isEqualTo(Result.LOSE)
        );
    }
}
