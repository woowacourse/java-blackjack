package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameUserStorage;
import blackjack.domain.user.Player;
import blackjack.domain.value.Nickname;
import blackjack.mock.GameInputOutputMock;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    static final List<Nickname> NICKNAMES = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
    GameUserStorage gameUserStorage = new GameUserStorage();
    CardDeck cardDeck = makeCardDeck();
    GameInputOutputMock gameInputOutputMock = new GameInputOutputMock();
    BlackJackGame blackJackGame = new BlackJackGame(gameUserStorage, cardDeck, gameInputOutputMock);

    @Test
    @DisplayName("입력된 닉네임만으로 플레이어를 등록할 수 있다.")
    void canRegisterPlayer() {
        blackJackGame.processPreparation(NICKNAMES);

        List<Player> players = gameUserStorage.getPlayers();
        assertThat(players)
                .extracting(Player::getNickname)
                .containsExactlyInAnyOrder("쿠키", "빙봉");
    }

    @Test
    @DisplayName("플레이어에게 초기카드를 분배할 수 있다.")
    void canDistributeInitialCardToPlayer() {
        blackJackGame.processPreparation(NICKNAMES);

        List<Player> players = gameUserStorage.getPlayers();
        assertThat(players)
                .extracting(Player::getHand)
                .allMatch(hand -> hand.size() == GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("딜러에게 초기카드를 분배할 수 있다.")
    void canDistributeInitialCardToDealer() {
        blackJackGame.processPreparation(NICKNAMES);

        Dealer dealer = gameUserStorage.getDealer();
        assertThat(dealer.getHand()).hasSize(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("플레이어 턴을 진행할 수 있다.")
    void canProcessPlayerTurn() {
        blackJackGame.processPreparation(NICKNAMES);
        blackJackGame.processPlayerTurns();

        List<Player> players = gameUserStorage.getPlayers();
        assertThat(players)
                .extracting(Player::getHand)
                .allMatch(hand -> hand.size() == 3);
    }

    @Test
    @DisplayName("딜러의 턴을 진행할 수 있다.")
    void canProcessDealerTurn() {
        blackJackGame.processPreparation(NICKNAMES);
        blackJackGame.processPlayerTurns();
        blackJackGame.processDealerTurns();

        Dealer dealer = gameUserStorage.getDealer();
        assertAll(
                () -> assertThat(dealer.getHand()).hasSize(3),
                () -> assertThat(dealer.getPoint()).isGreaterThan(GameRule.DEALER_DRAW_THRESHOLD.getValue()));
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