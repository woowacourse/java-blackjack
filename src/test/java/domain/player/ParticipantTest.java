package domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Test
    @DisplayName("Ace의 두 값 중 큰 값이 유리할 때는 큰 값을 쓴다")
    void sum() {
        final Participant participant = new Player(new Name("지쳐버린종"));

        participant.init(new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));

        Assertions.assertThat(participant.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace의 두 값 중 작은 값이 유리할 때는 작은 값을 쓴다")
    void sum2() {
        final Participant participant = new Player(new Name("지쳐버린종"));

        participant.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        participant.add(new Card(Rank.ACE, Suit.CLUBS));

        Assertions.assertThat(participant.getScore()).isEqualTo(21);
    }


    @Test
    @DisplayName("정해진 이름이 없다면 예외가 발생한다")
    void nameNotDetermined() {
        final Participant participant = new Dealer();

        assertThatThrownBy(participant::getName)
                .isInstanceOf(IllegalCallerException.class)
                .hasMessage("참여자의 이름이 정해지지 않았습니다");
    }
}
