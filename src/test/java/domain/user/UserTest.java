package domain.user;

import domain.card.CardDeck;
import domain.card.CardFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class UserTest {

    @Test
    void 뽑은_카드가_존재할때_첫번째_드로우_시도시_예외발생_확인_테스트() {
        CardDeck cardDeck = CardFactory.createCardDeck();
        Dealer dealer = new Dealer();
        dealer.draw(cardDeck);

        Assertions.assertThatThrownBy(() -> dealer.firstDraw(cardDeck))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 뽑아놓은 카드가 있습니다.");
    }
}
