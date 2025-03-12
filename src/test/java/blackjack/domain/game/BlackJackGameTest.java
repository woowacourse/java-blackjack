package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.user.GameUserStorage;
import blackjack.domain.user.Nickname;
import blackjack.domain.user.Player;
import blackjack.mock.GameInputOutputMock;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    GameUserStorage gameUserStorage = new GameUserStorage();
    CardDeck cardDeck = makeCardDeck();
    GameInputOutputMock gameInputOutput = new GameInputOutputMock();
    BlackJackGame blackJackGame = new BlackJackGame(gameUserStorage, cardDeck, gameInputOutput);

    @Test
    @DisplayName("입력된 닉네임만으로 플레이어를 등록할 수 있다.")
    void canRegisterPlayer() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        blackJackGame.runGame(nicknames);

        assertThat(gameUserStorage.getPlayers())
                .extracting(Player::getNickname)
                .containsExactlyInAnyOrder("쿠키", "빙봉");
    }

    @Test
    @DisplayName("플레이어에게 초기카드를 분배할 수 있다.")
    void canDistributeInitialCardToPlayer() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        blackJackGame.runGame(nicknames);

        Map<String, List<Card>> initialPlayerHands = gameInputOutput.getInitialPlayerHands();
        assertThat(initialPlayerHands.keySet())
                .containsExactlyInAnyOrder("쿠키", "빙봉");
        assertThat(initialPlayerHands.values())
                .allMatch(hand -> hand.size() == GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("딜러에게 초기카드를 분배할 수 있다.")
    void canDistributeInitialCardToDealer() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        blackJackGame.runGame(nicknames);

        List<Card> initialDealerHand = gameInputOutput.getInitialDealerHand();
        assertThat(initialDealerHand).hasSize(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("플레이어 턴을 진행할 수 있다.")
    void canProcessPlayerTurn() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        blackJackGame.runGame(nicknames);

        Map<String, List<Card>> finalPlayerHands = gameInputOutput.getFinalPlayerHands();
        assertThat(finalPlayerHands.keySet())
                .containsExactlyInAnyOrder("쿠키", "빙봉");
        assertThat(finalPlayerHands.values())
                .allMatch(hand -> hand.size() == 3);
    }

    @Test
    @DisplayName("딜러의 턴을 진행할 수 있다.")
    void canProcessDealerTurn() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        blackJackGame.runGame(nicknames);

        List<Card> finalDealerHand = gameInputOutput.getFinalDealerHand();
        assertThat(finalDealerHand).hasSize(3);
    }

    @Test
    @DisplayName("승패를 계산할 수 있다.")
    void canWinningState() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        blackJackGame.runGame(nicknames);

        GameResult gameResult = gameInputOutput.getGameResult();
        assertThat(gameResult.getDealerWinningState(WinningType.DRAW))
                .isEqualTo(2);
        assertThat(gameResult.getDealerWinningState(WinningType.WIN))
                .isEqualTo(0);
        assertThat(gameResult.getDealerWinningState(WinningType.LOSE))
                .isEqualTo(0);
    }

    public CardDeck makeCardDeck() {
        List<Card> cards = List.of(
                new Card(CardShape.HEART, CardValue.JACK),
                new Card(CardShape.HEART, CardValue.SIX),
                new Card(CardShape.HEART, CardValue.JACK),
                new Card(CardShape.HEART, CardValue.SIX),
                new Card(CardShape.HEART, CardValue.JACK),
                new Card(CardShape.HEART, CardValue.SIX),
                new Card(CardShape.HEART, CardValue.FIVE),
                new Card(CardShape.HEART, CardValue.FIVE),
                new Card(CardShape.HEART, CardValue.FIVE));
        return new CardDeck(cards);
    }
}