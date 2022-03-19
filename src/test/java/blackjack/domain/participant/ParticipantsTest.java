package blackjack.domain.participant;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import blackjack.domain.hand.CardHand;
import blackjack.domain.hand.OneCard;
import blackjack.fixture.CardSupplierStub;
import blackjack.strategy.CardSupplier;
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

    @DisplayName("1명의 딜러와 플레이어명 리스트 크기 만큼의 플레이어가 2장의 패를 가진 상태로 생성된다.")
    @Test
    void init_initializeDealerAndPlayers() {
        CardSupplier cards = CardSupplierStub.of(
                CLOVER2, CLOVER10, CLOVER2, CLOVER10, CLOVER2, CLOVER10);
        Participants participants = Participants.of(List.of("a", "b"), cards);

        CardHand expectedCardHand = new OneCard(CLOVER2).hit(CLOVER10);
        Dealer expectedDealer = new Dealer(expectedCardHand);
        List<Player> expectedPlayers = List.of(
                new Player("a", expectedCardHand),
                new Player("b", expectedCardHand)
        );

        assertThat(participants.getDealer()).isEqualTo(expectedDealer);
        assertThat(participants.getPlayers()).containsAll(expectedPlayers);
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
