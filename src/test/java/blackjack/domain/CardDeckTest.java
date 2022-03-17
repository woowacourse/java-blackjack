package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Player;
import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 덱에서 카드를 뽑는다.")
    void getCard() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.getCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 덱에서 카드를 모두 뽑고 추가로 뽑는 경우 테스트")
    void getCardsInExcess() {
        CardDeck cardDeck = new CardDeck();

        assertThatThrownBy(() -> {
            for (int i = 0; i < 53; i++) {
                cardDeck.getCard();
            }
        }).isInstanceOf(NoSuchElementException.class)
                .hasMessage("[ERROR] 카드가 존재하지 않습니다.");
    }
}
