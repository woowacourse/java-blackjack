package blackjack;

import blackjack.domain.*;
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

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.CLOVER, Type.EIGHT),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.HEART, Type.TEN)
                )
        );
        cardDeck = new CardDeck(cards);
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
        User user = new User("pobi");
        for (int i = 0; i < 2; i++) {
            user.addCard(cardDeck.getOneCard());
        }
        assertThat(user.getCardsSize()).isEqualTo(2);
        assertThat(cardDeck.size()).isEqualTo(4);
    }
}
