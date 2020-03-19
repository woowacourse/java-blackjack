package domain.user;

import domain.BettingMoney;
import domain.card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

@SuppressWarnings("NonAsciiCharacters")
public class BlackjackUserTest {

    @ParameterizedTest
    @EmptySource
    void 이름으로_빈값이_들어왔을때_예외처리_테스트(String input) {
        Assertions.assertThatThrownBy(() -> new Player(input, new BettingMoney("1000")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값이 입력되었습니다.");
    }

    @Test
    void 뽑은_카드가_존재할때_첫번째_드로우_시도시_예외발생_확인_테스트() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        dealer.draw(cardDeck);

        Assertions.assertThatThrownBy(() -> dealer.firstDraw(cardDeck))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 뽑아놓은 카드가 있습니다.");
    }
}
