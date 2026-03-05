package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.StubDeck;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import dto.BlackjackResult;
import expcetion.BlackjackException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("이름이 중복되면 안된다.")
    void 이름이_중복될_시(){
        //given
        List<String> names = new ArrayList<>(List.of("tobi","tobi"));

        //when & then
        assertThatThrownBy(()-> new Gamblers(names))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("딜러 및 사용자 승패 결과 도출")
    void 딜러와_사용자_승패결과_도출(){
        //given
        int dealerScore = 17;
        Gamblers gamblers = new Gamblers(List.of("tobi","quda")); // 사용자 두명

        Card eight = new Card(CardRank.EIGHT, CardSuit.DIAMOND); // 8
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER); // 10

        Card seven = new Card(CardRank.SEVEN, CardSuit.CLOVER); // 7
        Card nine= new Card(CardRank.NINE, CardSuit.DIAMOND); // 9

        StubDeck sd = new StubDeck(List.of(eight,seven,ten,nine)); // 8 7 10 9
        gamblers.dealAll(sd);
        gamblers.dealAll(sd);

        //when
        BlackjackResult result = gamblers.getResult(dealerScore);

        //then
        assertThat(result.winCount()).isEqualTo(1);
        assertThat(result.lossCount()).isEqualTo(1);
        assertThat(result.drawCount()).isEqualTo(0);
        assertThat(result.logs().get(0)).isEqualTo("tobi:승");
        assertThat(result.logs().get(1)).isEqualTo("quda:패");
    }
}