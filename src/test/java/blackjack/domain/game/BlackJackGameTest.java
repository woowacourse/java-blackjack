package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.value.Nickname;
import blackjack.mock.GameInputOutputMock;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    static final List<Nickname> NICKNAMES = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
    CardDeck cardDeck = makeCardDeck();
    GameInputOutputMock gameInputOutputMock = new GameInputOutputMock();
    BlackJackGame blackJackGame = new BlackJackGame(NICKNAMES, cardDeck, gameInputOutputMock);

    @Test
    @DisplayName("입력된 닉네임만으로 플레이어를 등록할 수 있다.")
    void canRegisterPlayer() {
        blackJackGame.runGame();

        Map<String, List<Card>> initialPlayerHands = gameInputOutputMock.getInitialPlayerHands();
        assertThat(initialPlayerHands.keySet())
                .containsExactlyInAnyOrder("쿠키", "빙봉");
    }

    @Test
    @DisplayName("플레이어에게 초기카드를 분배할 수 있다.")
    void canDistributeInitialCardToPlayer() {
        blackJackGame.runGame();

        Map<String, List<Card>> initialPlayerHands = gameInputOutputMock.getInitialPlayerHands();
        assertThat(initialPlayerHands.keySet())
                .containsExactlyInAnyOrder("쿠키", "빙봉");
        assertThat(initialPlayerHands.values())
                .allMatch(hand -> hand.size() == GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("딜러에게 초기카드를 분배할 수 있다.")
    void canDistributeInitialCardToDealer() {
        blackJackGame.runGame();

        List<Card> initialDealerHand = gameInputOutputMock.getInitialDealerHand();
        assertThat(initialDealerHand).hasSize(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("플레이어 턴을 진행할 수 있다.")
    void canProcessPlayerTurn() {
        blackJackGame.runGame();

        Map<String, List<Card>> finalPlayerHands = gameInputOutputMock.getFinalPlayerHands();
        assertThat(finalPlayerHands.keySet())
                .containsExactlyInAnyOrder("쿠키", "빙봉");
        assertThat(finalPlayerHands.values())
                .allMatch(hand -> hand.size() == 3);
    }

    @Test
    @DisplayName("딜러의 턴을 진행할 수 있다.")
    void canProcessDealerTurn() {
        blackJackGame.runGame();

        List<Card> finalDealerHand = gameInputOutputMock.getFinalDealerHand();
        assertThat(finalDealerHand).hasSize(3);
    }

    @Test
    @DisplayName("수익을 계산할 수 있다.")
    void canCalculateProfit() {
        blackJackGame.runGame();

        List<PlayerProfit> profits = gameInputOutputMock.getPlayerProfits();
        assertThat(profits.getFirst().getProfit())
                .isEqualTo(0);
        assertThat(profits.getLast().getProfit())
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