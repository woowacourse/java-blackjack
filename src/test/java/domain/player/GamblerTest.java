package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.MatchResult;
import domain.StubDeck;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import expcetion.BlackjackException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {
    @Test
    @DisplayName("이름이 숫자면 안된다.")
    void 이름이_숫자일_시() {
        //given
        String name = "121345";

        //when & then
        assertThatThrownBy(() -> new Gambler(name))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("이름은 두글자 이상 열글자 미만으로 한다.")
    void 이름이_열글자를_넘을_시() {
        //given
        String maxRangeName = "tobiisverygoob";
        String minRangeName = "h";
        //when & then
        assertThatThrownBy(() -> new Gambler(maxRangeName))
                .isInstanceOf(BlackjackException.class);
        assertThatThrownBy(() -> new Gambler(minRangeName))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("승리 정상 판정")
    void 승리_정상_판정() {
        //given
        Dealer dealer = new Dealer();
        Gambler tobi = new Gambler("tobi");
        Gambler quda = new Gambler("quda");

        Card jack = new Card(CardRank.JACK, CardSuit.CLOVER); // 딜러
        Card eight = new Card(CardRank.EIGHT, CardSuit.DIAMOND); // tobi
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER); // quda

        StubDeck sd = new StubDeck(List.of(jack, eight, ten));
        dealer.deal(sd);
        tobi.deal(sd);
        quda.deal(sd);

        //when
        MatchResult tobiResult = tobi.getResult(dealer.score());
        MatchResult qudaResult = quda.getResult(dealer.score());

        //then
        assertThat(tobiResult).isEqualTo(MatchResult.LOSE);
        assertThat(qudaResult).isEqualTo(MatchResult.DRAW);
    }
}