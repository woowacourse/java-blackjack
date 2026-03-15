package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingAmount;
import blackjack.domain.deck.Card;
import blackjack.domain.deck.CardShape;
import blackjack.domain.deck.CardValue;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class UsersTest {

    @Test
    void 유저_이름_목록_반환() {
        // given
        Users users = new Users(List.of(new User("흑곰", new BettingAmount(new BigDecimal("10000"))), new User("밀란", new BettingAmount(new BigDecimal("10000")))));

        // when & then
        assertThat(users.getNames())
                .containsExactly("흑곰", "밀란");
    }

    @Test
    void 전원_버스트일_때_true_반환() {
        // given
        User userA = new User("흑곰", new BettingAmount(new BigDecimal("10000")));
        User userB = new User("밀란", new BettingAmount(new BigDecimal("10000")));
        userA.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        userA.add(new Card(CardValue.TEN, CardShape.HEART));
        userA.add(new Card(CardValue.TEN, CardShape.CLOVER));
        userB.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        userB.add(new Card(CardValue.TEN, CardShape.HEART));
        userB.add(new Card(CardValue.TEN, CardShape.CLOVER));
        Users users = new Users(List.of(userA, userB));

        // when & then
        assertThat(users.isAllBurst()).isTrue();
    }

    @Test
    void 한_명이라도_버스트_아니면_false_반환() {
        // given
        User userA = new User("흑곰", new BettingAmount(new BigDecimal("10000")));
        User userB = new User("밀란", new BettingAmount(new BigDecimal("10000")));
        userA.add(new Card(CardValue.TEN, CardShape.DIAMOND));
        userA.add(new Card(CardValue.TEN, CardShape.HEART));
        userA.add(new Card(CardValue.TEN, CardShape.CLOVER));
        userB.add(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        userB.add(new Card(CardValue.EIGHT, CardShape.HEART));
        Users users = new Users(List.of(userA, userB));

        // when & then
        assertThat(users.isAllBurst()).isFalse();
    }

    @Test
    void 아무도_버스트_아닐_때_false_반환() {
        // given
        Users users = new Users(List.of(new User("흑곰", new BettingAmount(new BigDecimal("10000"))), new User("밀란", new BettingAmount(new BigDecimal("10000")))));

        // when & then
        assertThat(users.isAllBurst()).isFalse();
    }
}
