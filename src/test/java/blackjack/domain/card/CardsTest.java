package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    private Cards cards;

    @BeforeEach
    void resetVariable() {
        cards = new Cards();
    }

    @DisplayName("같은 카드 추가시 예외 발생")
    @Test
    void add() {
        Card card = new Card(Type.DIAMOND, Symbol.ACE);
        cards.add(card);
        assertThatThrownBy(() -> cards.add(card)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("중복");
    }

    @DisplayName("카드 숫자 합계")
    @Test
    void getTotalSum() {
        cards.add(new Card(Type.DIAMOND, Symbol.ACE));
        cards.add(new Card(Type.SPADE, Symbol.ACE));
        cards.add(new Card(Type.HEART, Symbol.ACE));
        cards.add(new Card(Type.CLUB, Symbol.ACE));
        cards.add(new Card(Type.CLUB, Symbol.JACK));
        assertThat(cards.getTotalScore()).isEqualTo(14);

        cards = new Cards();
        cards.add(new Card(Type.DIAMOND, Symbol.ACE));
        cards.add(new Card(Type.CLUB, Symbol.ACE));
        cards.add(new Card(Type.CLUB, Symbol.TWO));
        assertThat(cards.getTotalScore()).isEqualTo(14);
    }

    @DisplayName("Bust시 0으로 초기화 확인")
    @Test
    void getTotalSumBust() {
        cards.add(new Card(Type.CLUB, Symbol.JACK));
        cards.add(new Card(Type.CLUB, Symbol.QUEEN));
        cards.add(new Card(Type.CLUB, Symbol.KING));
        assertThat(cards.getTotalScore()).isEqualTo(0);
    }

    @DisplayName("카드가 21을 넘는지 확인")
    @Test
    void isOverBlackJack() {
        cards.add(new Card(Type.DIAMOND, Symbol.TWO));
        cards.add(new Card(Type.DIAMOND, Symbol.THREE));
        cards.add(new Card(Type.DIAMOND, Symbol.JACK));
        assertThat(cards.isBust()).isFalse();

        cards.add(new Card(Type.CLUB, Symbol.JACK));
        assertThat(cards.isBust()).isTrue();
    }

    @DisplayName("카드가 21인지 확인")
    @Test
    void isBlackJack() {
        cards.add(new Card(Type.DIAMOND, Symbol.TWO));
        cards.add(new Card(Type.DIAMOND, Symbol.THREE));
        cards.add(new Card(Type.DIAMOND, Symbol.JACK));
        assertThat(cards.isBlackJack()).isFalse();

        cards.add(new Card(Type.CLUB, Symbol.SIX));
        assertThat(cards.isBlackJack()).isTrue();
    }

    @DisplayName("카드들 인포 출력 확인")
    @Test
    void checkCardsInfos() {
        Card cardDiamondEight = new Card(Type.DIAMOND, Symbol.EIGHT);
        Card cardDiamondTwo = new Card(Type.DIAMOND, Symbol.TWO);
        cards.add(cardDiamondEight);
        cards.add(cardDiamondTwo);
        List<String> infos = cards.getInfos();

        assertThat(infos.get(0)).isEqualTo("8다이아몬드");
        assertThat(infos.get(1)).isEqualTo("2다이아몬드");
    }
}