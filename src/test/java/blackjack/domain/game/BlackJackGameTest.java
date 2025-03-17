package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.GameUserStorage;
import blackjack.domain.user.Player;
import blackjack.domain.value.Nickname;
import blackjack.exception.ExceptionMessage;
import blackjack.mock.GameInputOutputMock;
import java.util.List;
import java.util.stream.IntStream;
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
    @DisplayName("플레이어의 수가 정해진 수보다 많을 경우 예외를 발생시킨다.")
    void canValidatePlayerCount() {
        List<Nickname> tooManyNicknames = IntStream.rangeClosed(0, GameRule.MAX_PLAYER_COUNT.getValue())
                .mapToObj(order -> new Nickname("플레이어" + order))
                .toList();

        assertThatThrownBy(() -> blackJackGame.processPreparation(tooManyNicknames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_PLAYER_COUNT.getContent());
    }

    @Test
    @DisplayName("플레이어가 중복될 경우 예외를 발생시킨다.")
    void canValidateDuplicatedPlayer() {
        List<Nickname> duplicatedNicknames = List.of(new Nickname("쿠키"), new Nickname("쿠키"));

        assertThatThrownBy(() -> blackJackGame.processPreparation(duplicatedNicknames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_ALLOWED_DUPLICATED_PLAYER.getContent());
    }

    @Test
    @DisplayName("플레이어의 배팅금액을 입력할 수 있다.")
    void canRegisterBettingAmount() {
        blackJackGame.processPreparation(NICKNAMES);

        List<Player> players = gameUserStorage.getPlayers();
        assertThat(players)
                .allMatch(player -> player.getBettingAmount() == GameInputOutputMock.BETTING_AMOUNT);
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
        blackJackGame.processPlaying();

        List<Player> players = gameUserStorage.getPlayers();
        assertThat(players)
                .extracting(Player::getHand)
                .allMatch(hand -> hand.size() == 3);
    }

    @Test
    @DisplayName("딜러의 턴을 진행할 수 있다.")
    void canProcessDealerTurn() {
        blackJackGame.processPreparation(NICKNAMES);
        blackJackGame.processPlaying();

        Dealer dealer = gameUserStorage.getDealer();
        assertAll(
                () -> assertThat(dealer.getHand()).hasSize(3),
                () -> assertThat(dealer.getPoint()).isGreaterThan(GameRule.DEALER_DRAW_THRESHOLD.getValue()));
    }

    @Test
    @DisplayName("준비 단계를 진행해야지 플레이 단계를 진행할 수 있다.")
    void canValidatePlayingStage() {
        assertThatThrownBy(() -> blackJackGame.processPlaying())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_GAME_STAGE.getContent());

        blackJackGame.processPreparation(NICKNAMES);
        assertThatCode(() -> blackJackGame.processPlaying())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이 단계를 진행해야지 결과 출력 단계를 진행할 수 있다.")
    void canValidateOutputResultStage() {
        blackJackGame.processPreparation(NICKNAMES);
        assertThatThrownBy(() -> blackJackGame.processOutputResult())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_GAME_STAGE.getContent());

        blackJackGame.processPlaying();
        assertThatCode(() -> blackJackGame.processOutputResult())
                .doesNotThrowAnyException();
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