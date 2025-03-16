package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.value.Nickname;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameUserTest {

    static final Nickname NICKNAME = new Nickname("게임유저");
    GameUser gameUser = new GameUser(NICKNAME);

    @Test
    @DisplayName("카드들을 추가할 수 있다.")
    void canAddInitialCards() {
        Card card = new Card(CardShape.HEART, CardValue.EIGHT);

        gameUser.addCardInHand(card);

        assertAll(
                () -> assertThat(gameUser.getHand()).hasSize(1),
                () -> assertThat(gameUser.getHand().getFirst()).isEqualTo(card));
    }

    @Test
    @DisplayName("카드를 추가할 수 있다.")
    void canAddCards() {
        Card firstCard = new Card(CardShape.HEART, CardValue.EIGHT);
        Card secondCard = new Card(CardShape.HEART, CardValue.EIGHT);
        List<Card> cards = List.of(firstCard, secondCard);

        gameUser.addCardInHand(cards);

        assertAll(
                () -> assertThat(gameUser.getHand()).hasSize(2),
                () -> assertThat(gameUser.getHand().getFirst()).isEqualTo(firstCard),
                () -> assertThat(gameUser.getHand().getLast()).isEqualTo(secondCard));
    }
}