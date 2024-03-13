package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Result;
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
    Card spadeA, spadeK, heart2;

    @BeforeEach
    void init() {
        Name name = new Name("pobi");
        pobi = new Player(name);

        dealer = new Dealer();

        spadeA = new Card(Symbol.SPADE, Rank.BIG_ACE);
        spadeK = new Card(Symbol.SPADE, Rank.KING);
        heart2 = new Card(Symbol.HEART, Rank.TWO);
    }

    @DisplayName("딜러가 버스트가 아니고, 블랙잭이 아닐 경우에 플레이어의 플레이어 우승 결과를 구한다.")
    @Test
    void getDealerIsNotBustCaseTest() {
        // given
        pobi.hit(spadeA);

        dealer.hit(spadeK);
        dealer.hit(spadeK);

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 버스트 일 때 플레이어의 플레이어 우승 결과를 구한다.")
    @Test
    void getDealerIsBustCaseTest() {
        // given
        pobi.hit(spadeA);

        dealer.hit(heart2);
        dealer.hit(spadeK);
        dealer.hit(spadeK);

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.WIN);
    }

    @DisplayName("둘 다 버스트 일 때 플레이어 우승 결과를 구한다.")
    @Test
    void getAllBustCaseTest() {
        // given
        pobi.hit(spadeK);
        pobi.hit(spadeK);
        pobi.hit(spadeK);

        dealer.hit(heart2);
        dealer.hit(spadeK);
        dealer.hit(spadeK);

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.LOSE);
    }


    @DisplayName("딜러가 블랙잭 일 때 플레이어의 플레이어 우승 결과를 구한다.")
    @Test
    void getDealerIsBlackJackCaseTest() {
        // given
        pobi.hit(spadeK);
        pobi.hit(spadeK);
        pobi.hit(spadeA);

        dealer.hit(spadeK);
        dealer.hit(spadeA);

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("둘 다 버스트, 블랙잭이 아닐 때 점수로 비교하여 플레이어 우승 결과를 구한다.")
    @Test
    void compareScoreWithTest() {
        // given
        pobi.hit(spadeK);
        pobi.hit(spadeK);

        dealer.hit(spadeK);

        // when
        Result pobiResult = Result.getPlayerResultWith(pobi, dealer);

        // then
        assertThat(pobiResult).isEqualTo(Result.WIN);
    }
}
