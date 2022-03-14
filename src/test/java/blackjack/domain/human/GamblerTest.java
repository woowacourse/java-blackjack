package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {

    @Test
    @DisplayName("플레이어 생성 테스트")
    public void createPlayer() {
        Gambler gambler = Gambler.of(Name.of("test"));
        assertThat(gambler.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("플레이어 카드 추가 테스트")
    public void addCard_Player() {
        Gambler gambler = Gambler.of(Name.of("test"));

        Card card5 = Card.of(Denomination.of("5"), Symbol.of("스페이드"));
        Card card6 = Card.of(Denomination.of("6"), Symbol.of("하트"));
        gambler.addCard(card5);
        gambler.addCard(card6);

        assertThat(gambler.getPoint())
                .isEqualTo(11);
    }
}
