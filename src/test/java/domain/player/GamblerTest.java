package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.FixedDeck;
import domain.MatchResult;
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
        String max_range_name = "tobiisverygoob";
        String min_range_name = "h";
        //when & then
        assertThatThrownBy(() -> new Gambler(max_range_name))
                .isInstanceOf(BlackjackException.class);
        assertThatThrownBy(() -> new Gambler(min_range_name))
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

        FixedDeck fixedDeck = new FixedDeck(List.of(jack, eight, ten));
        dealer.deal(fixedDeck);
        tobi.deal(fixedDeck);
        quda.deal(fixedDeck);

        //when
        MatchResult tobiResult = tobi.getResult(dealer);
        MatchResult qudaResult = quda.getResult(dealer);

        //then
        assertThat(tobiResult).isEqualTo(MatchResult.LOSE);
        assertThat(qudaResult).isEqualTo(MatchResult.DRAW);
    }
}
