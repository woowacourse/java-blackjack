package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.StubDeck;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import expcetion.BlackjackException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("이름이 중복되면 안된다.")
    void 이름이_중복될_시() {
        // given
        List<Gambler> gamblers = List.of(
                new Gambler("tobi", 1000),
                new Gambler("tobi", 2000)
        );

        // when & then
        assertThatThrownBy(() -> new Gamblers(gamblers))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("도박사의 이름 목록을 반환한다")
    void 이름_목록_반환() {
        // given
        Gambler gambler1 = new Gambler("tobi", 10000);
        Gambler gambler2 = new Gambler("quda", 20000);

        Gamblers gamblers = new Gamblers(List.of(gambler1, gambler2));

        // when
        List<String> names = gamblers.getNames();

        // then
        assertThat(names).containsExactly("tobi", "quda");
    }

    @Test
    @DisplayName("모든 도박사에게 카드를 배분한다")
    void 모든_도박사에게_카드_배분() {
        // given
        Gambler gambler1 = new Gambler("tobi", 10000);
        Gambler gambler2 = new Gambler("quda", 20000);

        Gamblers gamblers = new Gamblers(List.of(gambler1, gambler2));

        Card card1 = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card card2 = new Card(CardRank.NINE, CardSuit.DIAMOND);

        StubDeck deck = new StubDeck(List.of(card1, card2));

        // when
        gamblers.dealAll(deck);

        // then
        assertThat(gambler1.cards()).hasSize(1);
        assertThat(gambler2.cards()).hasSize(1);
    }

}
