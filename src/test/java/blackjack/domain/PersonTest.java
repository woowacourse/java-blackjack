package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    @DisplayName("상대의 점수가 내 점수보다 낮으면 승리해야 한다.")
    void matchGame_win() {
        // given
        Person person1 = new Person("glen");
        Person person2 = new Person("encho");
        person1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person2.addCard(new Card(Suit.DIAMOND, Rank.FIVE));

        // when
        GameResult gameResult = person1.matchGame(person2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("상대의 점수가 내 점수보다 높으면 패배해야 한다.")
    void matchGame_lose() {
        // given
        Person person1 = new Person("glen");
        Person person2 = new Person("encho");
        person1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person2.addCard(new Card(Suit.DIAMOND, Rank.ACE));

        // when
        GameResult gameResult = person1.matchGame(person2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("상대의 점수가 나와 같으면 무승부여야 한다.")
    void matchGame_draw() {
        // given
        Person person1 = new Person("glen");
        Person person2 = new Person("encho");
        person1.addCard(new Card(Suit.DIAMOND, Rank.FIVE));
        person2.addCard(new Card(Suit.DIAMOND, Rank.FIVE));

        // when
        GameResult gameResult = person1.matchGame(person2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("상대의 점수와 나의 점수가 21점을 넘으면 무승부여야 한다.")
    void matchGame_sameOverScore() {
        // given
        Person person1 = new Person("glen");
        Person person2 = new Person("encho");
        person1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person2.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person2.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person2.addCard(new Card(Suit.DIAMOND, Rank.KING));

        // when
        GameResult gameResult = person1.matchGame(person2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("상대의 점수가 21점을 넘고 내가 넘지 않으면 승리해야 한다.")
    void matchGame_otherOverScore() {
        // given
        Person person1 = new Person("glen");
        Person person2 = new Person("encho");
        person1.addCard(new Card(Suit.DIAMOND, Rank.TWO));
        person2.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person2.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person2.addCard(new Card(Suit.DIAMOND, Rank.KING));

        // when
        GameResult gameResult = person1.matchGame(person2);

        // then
        assertThat(gameResult)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("점수가 21점을 넘을 때 카드를 뽑으면 예외가 발생해야 한다.")
    void addCard_overScore() {
        // given
        Person person = new Person("encho");
        person.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person.addCard(new Card(Suit.DIAMOND, Rank.KING));
        person.addCard(new Card(Suit.DIAMOND, Rank.KING));

        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            person.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        }).withMessage("[ERROR] 점수가 21점을 넘으면 카드를 더 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("person은 플레이어여야 한다.")
    void isPlayer_true() {
        // given
        Person person = new Person("glen");

        // expect
        assertThat(person.isPlayer())
                .isTrue();
    }
}
