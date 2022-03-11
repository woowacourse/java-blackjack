package blackjack.domain.game;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import blackjack.fixture.CardStackGenerator;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    private static final List<String> playerNames = List.of("hudi", "jeong");

    @DisplayName("생성자는 1명 이상의 플레이어명을 리스트로 받아 게임을 생성한다.")
    @Test
    void constructor_initsGameWithPlayerNames() {
        BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(), playerNames);

        List<Player> participants = blackjackGame.getPlayers();

        assertThat(participants.size()).isEqualTo(2);
        assertThat(participants.get(0).getName()).isEqualTo("hudi");
        assertThat(participants.get(1).getName()).isEqualTo("jeong");
    }

    @DisplayName("생성자 파라미터에 들어오는 플레이어명 리스트가 비어있으면 예외가 발생한다.")
    @Test
    void constructor_throwsExceptionOnNoPlayerNameInput() {
        assertThatThrownBy(() -> new BlackjackGame(new CardDeck(), List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어가 없는 게임은 존재할 수 없습니다.");
    }

    @DisplayName("딜러의 점수가 16이하인 경우 한 장의 카드를 더 할당한다.")
    @Test
    void giveCardToDealer_returnTrueIfDealerReceivedExtraCard() {
        CardStack cards = CardStackGenerator.ofReverse(
                CLOVER6, CLOVER10, CLOVER2, CLOVER3, CLOVER4, CLOVER5, CLOVER_KING);
        BlackjackGame blackjackGame = new BlackjackGame(cards, playerNames);

        boolean actual = blackjackGame.giveCardToDealer();

        assertThat(actual).isTrue();
    }

    @DisplayName("딜러의 점수가 17이상인 경우 한 장의 카드를 더 받지 않는다.")
    @Test
    void giveCardToDealer_returnFalseIfDealerDidNotReceiveExtraCard() {
        CardStack cards = CardStackGenerator.ofReverse(
                CLOVER7, CLOVER10, CLOVER2, CLOVER3, CLOVER4, CLOVER5, CLOVER_KING);
        BlackjackGame blackjackGame = new BlackjackGame(cards, playerNames);

        boolean actual = blackjackGame.giveCardToDealer();

        assertThat(actual).isFalse();
    }

    @DisplayName("CardDeck 에서 카드 한장을 뽑아서 반환한다.")
    @Test
    void popCard_returnPoppedCard() {
        CardStack cards = CardStackGenerator.ofReverse(
                CLOVER6, CLOVER10, CLOVER2, CLOVER3, CLOVER4, CLOVER5, CLOVER_KING);
        BlackjackGame blackjackGame = new BlackjackGame(cards, playerNames);

        Card actual = blackjackGame.popCard();

        assertThat(actual).isEqualTo(CLOVER_KING);
    }
}
