package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    @DisplayName("카드 생성")
    void create_cards() {
        assertThat(Card.from(Suits.DIAMOND, Denominations.ACE))
            .isSameAs(Card.from(Suits.DIAMOND, Denominations.ACE));
    }

    @Test
    @DisplayName("카드 생성2 - 생성자에 type 순서 다르게 입력")
    void create_cards3() {
        assertThat(Card.from(Suits.DIAMOND, Denominations.ACE))
            .isSameAs(Card.from(Denominations.ACE, Suits.DIAMOND));
    }

    @Test
    @DisplayName("카드 점수 확인")
    void getScore() {
        assertThat(Card.from(Suits.DIAMOND, Denominations.ACE).getScore()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 Ace인지 확인")
    void isAce() {
        assertThat(Card.from(Suits.DIAMOND, Denominations.ACE).isAce()).isTrue();
    }

    @Test
    @DisplayName("카드 Ace 아닌지 확인")
    void isAce_not() {
        assertThat(Card.from(Suits.DIAMOND, Denominations.TWO).isAce()).isFalse();
    }

    @Test
    @DisplayName("카드이름 확인")
    void getCardName() {
        assertThat(Card.from(Suits.DIAMOND, Denominations.ACE).getCardName()).isEqualTo("A다이아몬드");
    }

}
