package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("플레이어의 이름이 중복된 경우 테스트")
    void checkDuplicatePlayerName() {
        List<Player> players = List.of(new Player("rookie"), new Player("rookie"));

        assertThatThrownBy(() -> new Participants(new Dealer(), players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 수가 1-7명이 아닌 경우 테스트")
    void checkPlayerCount() {
        List<Player> players = List.of(
                new Player("k1"), new Player("k2"), new Player("k3"), new Player("k4"),
                new Player("k5"), new Player("k6"), new Player("k7"), new Player("k8"));

        assertThatThrownBy(() -> new Participants(new Dealer(), players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 인원수는 1명 이상 7명 이하여야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 카드 합계 계산 테스트")
    void calculateScore() {
        Participant participant = new Player("rookie");
        participant.hit(Card.from(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(participant.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 11로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceEleven() {
        Participant participant = new Player("rookie");
        participant.hit(Card.from(Symbol.CLOVER, Denomination.ACE));
        participant.hit(Card.from(Symbol.CLOVER, Denomination.JACK));

        assertThat(participant.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 1로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceOne() {
        Participant participant = new Player("rookie");
        participant.hit(Card.from(Symbol.CLOVER, Denomination.ACE));
        participant.hit(Card.from(Symbol.CLOVER, Denomination.JACK));
        participant.hit(Card.from(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(participant.getScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 여러개인 경우 계산 테스트")
    void calculateScoreWithAceCountThree() {
        Participant participant = new Player("rookie");
        participant.hit(Card.from(Symbol.CLOVER, Denomination.ACE));
        participant.hit(Card.from(Symbol.HEART, Denomination.ACE));
        participant.hit(Card.from(Symbol.DIAMOND, Denomination.ACE));
        participant.hit(Card.from(Symbol.SPADE, Denomination.EIGHT));

        assertThat(participant.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("중복된 카드를 받는 경우 예외 발생 테스트")
    void receiveDuplicatedCard() {
        Card card1 = Card.from(Symbol.CLOVER, Denomination.ACE);
        Card card2 = Card.from(Symbol.CLOVER, Denomination.ACE);
        Participant participant = new Player("rookie");
        participant.hit(card1);

        assertThatThrownBy(() -> participant.hit(card2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 받을 수 없습니다.");
    }
}
