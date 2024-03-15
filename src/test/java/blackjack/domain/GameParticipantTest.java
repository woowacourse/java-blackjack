package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.GameParticipant;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Result;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameParticipantTest {

    private GameParticipant gameParticipant;

    @BeforeEach
    void beforeEach() {
        gameParticipant = new Player(new Name("플레이어"));
        Hands newHands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO)
        ));
        gameParticipant.receiveHands(newHands);
    }

    @Test
    @DisplayName("카드 추가 테스트")
    void hit() {
        Card newCard = new Card(Shape.HEART, Rank.THREE);
        gameParticipant.hit(newCard);

        Assertions.assertThat(gameParticipant.getHands().getCards()).contains(newCard);
    }

    @Test
    @DisplayName("상대방과 비기는 테스트")
    void takeOnTieTest() {
        Hands heartBlackjack = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.JACK)
        ));
        gameParticipant.receiveHands(heartBlackjack);

        GameParticipant blackjackParticipant = new Dealer();
        Hands spadeBlackjack = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.JACK)
        ));
        blackjackParticipant.receiveHands(spadeBlackjack);
        Result result = blackjackParticipant.takeOn(gameParticipant);

        Assertions.assertThat(result).isEqualTo(Result.TIE);
    }

    @Test
    @DisplayName("상대방한테 지는 테스트")
    void isBustTrueTest() {
        Hands newHands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.FIVE),
                new Card(Shape.HEART, Rank.SEVEN)
        ));
        gameParticipant.receiveHands(newHands);

        Assertions.assertThat(gameParticipant.isBust()).isTrue();
    }

    @Test
    @DisplayName("상대방을 승리하는 테스트")
    void isBustFalseTest() {
        Hands newHands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.ACE)
        ));
        gameParticipant.receiveHands(newHands);

        Assertions.assertThat(gameParticipant.isBust()).isFalse();
    }
}
