package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.exception.UserNameEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    private List<Card> cards;
    private CardDeck cardDeck;
    private User user;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.CLOVER, Type.EIGHT),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.HEART, Type.TEN)
                )
        );
        cardDeck = new CardDeck(cards);
        user = new User("pobi");
    }

    @DisplayName("user 생성 시 빈 문자열일 경우 예외 발생 확인")
    @Test
    void emptyStringTest() {
        assertThatThrownBy(() -> {
            new User("");
        }).isInstanceOf(UserNameEmptyException.class)
                .hasMessage("유저의 이름은 공백일 수 없습니다.");
    }

    @DisplayName("카드 덱에서 뽑았을 때 유저가 가지고 있는 카드 수와 덱의 카드 수가 동시에 변하는지 확인")
    @Test
    void addCardTest() {
        for (int i = 0; i < 2; i++) {
            user.addCard(cardDeck.pop());
        }
        assertThat(user.getCardsSize()).isEqualTo(2);
        assertThat(cardDeck.size()).isEqualTo(4);
    }

    @DisplayName("현재 보유 중인 카드의 총 점수 계산")
    @Test
    void calculateScoreTest() {
        for (int i = 0; i < 2; i++) {
            user.addCard(cardDeck.pop());
        }
        int score = user.calculateScore();
        assertThat(score).isEqualTo(19);
    }

    @DisplayName("유저의 이름을 반환하는지 확인")
    @Test
    void getNameTest() {
        Player user = new User("pobi");
        assertThat(user.getName()).isEqualTo("pobi");
    }

    @DisplayName("유저의 초기 카드 오픈상태 확인")
    @Test
    void getInitialCardsTest() {
        for (int i = 0; i < 2; i++) {
            user.addCard(cardDeck.pop());
        }
        assertThat(user.getInitialCards()).containsExactly(
                new Card(Symbol.CLOVER, Type.EIGHT), new Card(Symbol.CLOVER, Type.ACE));
    }

}
