package blackjack.domain.card;


import static blackjack.domain.Fixtures.ACE;
import static blackjack.domain.Fixtures.EIGHT;
import static blackjack.domain.Fixtures.NINE;
import static blackjack.domain.Fixtures.TEN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Cards;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;

    @BeforeEach
    void setup() {
        cards = new Cards();
        cards.add(ACE);
        cards.add(ACE);
    }

    @Test
    @DisplayName("버스트 여부 확인 기능 true 테스트")
    public void isBustTest() {
        cards.add(TEN);
        cards.add(EIGHT);
        cards.add(NINE);

        assertThat(cards.isBust())
                .isTrue();
    }

    @Test
    @DisplayName("버스트 여부 확인 기능 False 테스트")
    public void isBust2Test() {
        cards.add(TEN);
        cards.add(EIGHT);

        assertThat(cards.isBust())
                .isFalse();
    }

    @Test
    @DisplayName("카드모음 생성되는지 테스트")
    public void equalSizeTest() {
        assertThat(cards.size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("첫 카드 리턴 기능 테스트")
    void getFirstCardTest() {
        assertThat(cards.getFirstCard())
                .isEqualTo(ACE);
    }

    @Test
    @DisplayName("단순 포인트 리턴 기능 테스트")
    void getRawPointTest() {
        assertThat(cards.getRawPoint())
                .isEqualTo(22);
    }

    @Test
    @DisplayName("특정 카드 개수 리턴 기능 테스트")
    void getAceCountTest() {
        assertThat(cards.getAceCount())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("카드 리스트 리턴 기능 테스트")
    void get() {
        assertThat(cards.getCopy())
                .isEqualTo(List.of(ACE, ACE));
    }
}
