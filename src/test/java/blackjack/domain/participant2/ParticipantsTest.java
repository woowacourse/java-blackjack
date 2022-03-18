package blackjack.domain.participant2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    private CardStack cardStack;

    @BeforeEach
    void setUp() {
        cardStack = new CardDeck();
    }

    @DisplayName("플레이어명의 개수(n)과 딜러 1명을 합한 n+1명의 참여자가 생성된다.")
    @Test
    void initializeDealerAndPlayers() {
        Participants participants = Participants.of(
                List.of("a", "b", "c"), cardStack::pop);

        int actual = participants.getValue().size();

        assertThat(actual).isEqualTo(4);
    }

    @DisplayName("모든 참여자들은 2장의 패를 가진 상태로 초기화된다.")
    @Test
    void initializeAllParticipantsWithTwoCards() {
        Participants participants = Participants.of(
                List.of("a", "b", "c"), cardStack::pop);

        for (Participant participant : participants.getValue()) {
            List<Card> cards = participant.getHand().getCardBundle().getCards();
            assertThat(cards.size()).isEqualTo(2);
        }
    }

    @DisplayName("플레이어명의 리스트가 비어있는 경우, 예외가 발생한다.")
    @Test
    void init_exceptionOnEmptyPlayerNamesList() {
        assertThatThrownBy(() -> Participants.of(List.of(), cardStack::pop))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어가 없는 게임은 존재할 수 없습니다.");
    }

    @DisplayName("플레이어명들에 중복이 존재하면 예외가 발생한다.")
    @Test
    void init_exceptionOnDuplicatePlayerNames() {
        assertThatThrownBy(() -> Participants.of(List.of("중복", "중복"), cardStack::pop))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어명은 중복될 수 없습니다.");
    }
}
