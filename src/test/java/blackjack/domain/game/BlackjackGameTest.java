package blackjack.domain.game;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER3;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER8;
import static blackjack.domain.fixture.CardRepository.CLOVER9;
import static blackjack.domain.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.fixture.CardStackGenerator;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    private static final List<String> playerNames = List.of("hudi", "jeong");

    @DisplayName("addPlayer 메소드에 이름과 베팅금액을 전달하여 ParticipantGroup 의 Players 에 플레이어를 추가할 수 있다.")
    @Test
    void addPlayer() {
        // given
        CardStack cards = CardStackGenerator.ofReverse(
                CLOVER2, CLOVER3, CLOVER4, CLOVER5, CLOVER6, CLOVER7, CLOVER8);
        BlackjackGame blackjackGame = new BlackjackGame(cards);
        String playerName = "hudi";
        int money = 10000;

        // when
        blackjackGame.addPlayer(playerName, money);
        int actual = blackjackGame.getPlayers().getValue().size();
        int expected = 1;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("giveExtraCardToPlayer 는 전달받은 플레이어에 카드를 추가하고, 카드를 더 받을 수 있다면 true 를 반환한다.")
    @Test
    void giveExtraCardToPlayer_addCardToHandOfPlayerAndReturnTrueIfPlayerCanReceiveMore() {
        // given
        CardStack cardDeck = CardStackGenerator.ofReverse(
                CLOVER2, CLOVER3, CLOVER4, CLOVER5, CLOVER6, CLOVER7, CLOVER8);
        BlackjackGame blackjackGame = new BlackjackGame(cardDeck);
        blackjackGame.addPlayer("hudi", 10000);

        // when
        Player player = blackjackGame.getPlayers().getValue().get(0);
        boolean actual = blackjackGame.giveExtraCardToPlayer(player);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("giveExtraCardToPlayer 는 전달받은 플레이어에 카드를 추가하고, 카드를 더 받을 수 없다면 false 를 반환한다.")
    @Test
    void giveExtraCardToPlayer_addCardToHandOfPlayerAndReturnFalseIfPlayerCanNotReceiveMore() {
        // given
        CardStack cards = CardStackGenerator.ofReverse(
                CLOVER2, CLOVER3, CLOVER10, CLOVER5, CLOVER7);
        BlackjackGame blackjackGame = new BlackjackGame(cards);
        blackjackGame.addPlayer("hudi", 10000);

        // when
        Player player = blackjackGame.getPlayers().getValue().get(0);
        boolean actual = blackjackGame.giveExtraCardToPlayer(player);

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("딜러의 점수가 16이하인 점수가 17이상이 될 때까지 카드를 받는다.")
    @Test
    void giveExtraCardsToDealer_giveExtraCardsToDealerIfDealerScoreIsLessOrEqualThan16() {
        // given
        CardStack cards = CardStackGenerator.ofReverse(
                CLOVER2, CLOVER3, CLOVER7, CLOVER8, CLOVER9, CLOVER10, CLOVER4, CLOVER5, CLOVER6);
        BlackjackGame blackjackGame = new BlackjackGame(cards);
        blackjackGame.addPlayer("player1", 10000);
        blackjackGame.addPlayer("player2", 10000);

        // when
        int actual = blackjackGame.giveExtraCardsToDealer();
        int expected = 3;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("딜러의 점수가 17이상인 경우 한 장의 카드를 더 받지 않는다.")
    @Test
    void giveExtraCardsToDealer_doNotGiveExtraCardsToDealerIfDealerScoreIsGreaterThan16() {
        // given
        CardStack cards = CardStackGenerator.ofReverse(
                CLOVER_KING, CLOVER10, CLOVER7, CLOVER8, CLOVER9, CLOVER4, CLOVER5, CLOVER6);
        BlackjackGame blackjackGame = new BlackjackGame(cards);
        blackjackGame.addPlayer("player1", 10000);
        blackjackGame.addPlayer("player2", 10000);

        // when
        int actual = blackjackGame.giveExtraCardsToDealer();
        int expected = 0;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("CardDeck 에서 카드 한장을 뽑아서 반환한다.")
    @Test
    void popCard_returnPoppedCard() {
        CardStack cards = CardStackGenerator.ofReverse(
                CLOVER6, CLOVER10, CLOVER2, CLOVER3, CLOVER4, CLOVER5, CLOVER_KING);
        BlackjackGame blackjackGame = new BlackjackGame(cards);
        blackjackGame.addPlayer("player1", 10000);
        blackjackGame.addPlayer("player2", 10000);

        Card actual = blackjackGame.popCard();

        assertThat(actual).isEqualTo(CLOVER_KING);
    }


}
