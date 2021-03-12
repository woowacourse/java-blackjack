package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.BlackjackManager;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("이름들을 입력받아서 플레이어들을 생성")
    void testCreatePlayers() {
        List<String> names = Arrays.asList("pobi", "brown", "jason");
        Players players = new Players(names);
        assertThat(players.toList()).hasSize(3);
    }

    @Test
    @DisplayName("중복된 이름 입력시 예외처리")
    void testDuplicateException() {
        List<String> names = Arrays.asList("pobi", "pobi", "jason");
        assertThatThrownBy(() -> new Players(names))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("NULL 혹은 공백을 입력했을 시 예외처리")
    void testNullOrBlankException() {
        List<String> blank1 = Collections.singletonList("");
        assertThatThrownBy(() -> new Players(blank1))
            .isInstanceOf(IllegalArgumentException.class);
        List<String> blank2 = Collections.singletonList(" ");
        assertThatThrownBy(() -> new Players(blank2))
            .isInstanceOf(IllegalArgumentException.class);
        List<String> nullList = null;
        assertThatThrownBy(() -> new Players(nullList))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        List<String> names = Arrays.asList("pobi", "jason");
        Dealer dealer = new Dealer();
        Players players = new Players(names);
        BlackjackManager blackjackManager = new BlackjackManager(dealer, players);
        blackjackManager.initDrawCards();

        assertThat(players.toList()
            .stream()
            .filter(player -> player.getCards().size() == 2)
            .count())
            .isEqualTo(2);
    }

    @Test
    @DisplayName("모든 플레이어가 플레이를 완료했는지 확인한다.")
    void testIsAllPlayerPlayedBlackJack() {
        List<String> names = Arrays.asList("미립", "현구막");
        Players players = new Players(names);
        players.initDraw(getCustomCardDeck());

        players.drawCurrentTurnPlayer(Card.valueOf(Pattern.SPADE, Number.JACK));
        players.passTurnToNextPlayer();
        players.drawCurrentTurnPlayer(Card.valueOf(Pattern.DIAMOND, Number.JACK));
        players.passTurnToNextPlayer();

        assertThat(players.isAllPlayerDone()).isTrue();
    }

    private CardDeck getCustomCardDeck() {
        Card firstCard = Card.valueOf(Pattern.HEART, Number.TEN);
        Card secondCard = Card.valueOf(Pattern.HEART, Number.KING);
        Card thirdCard = Card.valueOf(Pattern.HEART, Number.QUEEN);
        Card fourthCard = Card.valueOf(Pattern.HEART, Number.JACK);
        return CardDeck.customDeck(Arrays.asList(firstCard, secondCard, thirdCard, fourthCard));
    }

    @Test
    @DisplayName("현재 차례 플레이어 턴이 넘어갔는지 확인한다.")
    void testIsSuccessPassThePlayerTurn() {
        List<String> names = Arrays.asList("미립", "현구막");
        Players players = new Players(names);
        players.initDraw(getCustomCardDeck());

        assertThat(players.getCurrentPlayerName()).isEqualTo("미립");
        players.passTurnToNextPlayer();
        assertThat(players.getCurrentPlayerName()).isEqualTo("현구막");
    }

    @Test
    @DisplayName("현재 차례 플레이어가 카드를 뽑았는지 확인한다.")
    void testPlayerDrawCard() {
        List<String> name = Collections.singletonList("미립");
        Players players = new Players(name);
        players.initDraw(getDrawTestCardDeck());

        assertThat(players.getCurrentTurnPlayer().getScoreToInt()).isEqualTo(12);
        players.drawCurrentTurnPlayer(Card.valueOf(Pattern.DIAMOND, Number.THREE));
        assertThat(players.getCurrentTurnPlayer().getScoreToInt()).isEqualTo(15);
    }

    private CardDeck getDrawTestCardDeck() {
        Card firstCard = Card.valueOf(Pattern.HEART, Number.TWO);
        Card secondCard = Card.valueOf(Pattern.HEART, Number.KING);
        return CardDeck.customDeck(Arrays.asList(firstCard, secondCard));
    }

    @Test
    @DisplayName("Players의 커서(인덱스) 초기화 테스트")
    void testPlayerStateToStay() {
        List<String> names = Arrays.asList("미립", "현구막");
        Players players = new Players(names);

        assertThat(players.getCurrentPlayerName()).isEqualTo("미립");
        players.passTurnToNextPlayer();
        assertThat(players.getCurrentPlayerName()).isEqualTo("현구막");
        players.resetIndex();
        assertThat(players.getCurrentPlayerName()).isEqualTo("미립");
    }

    @Test
    @DisplayName("모든 플레이어가 베팅을 완료했는지 확인한다.")
    void testCurrentPlayerBet() {
        List<String> names = Arrays.asList("미립", "현구막");
        Players players = new Players(names);

        assertThat(players.isAllPlayerDone()).isFalse();

        players.betCurrentPlayer(3_000);
        players.passTurnToNextPlayer();

        assertThat(players.isAllPlayerDone()).isFalse();

        players.betCurrentPlayer(3_000);
        players.passTurnToNextPlayer();

        assertThat(players.isAllPlayerDone()).isTrue();
    }

    @Test
    @DisplayName("베팅을 진행하지 못한 플레이어들의 이름을 순서대로 가져온다.")
    void testYetBettingPlayerNames() {
        List<String> names = Arrays.asList("미립", "현구막");
        Players players = new Players(names);

        assertThat(players.getCurrentPlayerName()).isEqualTo("미립");
        players.betCurrentPlayer(3_000);
        players.passTurnToNextPlayer();
        assertThat(players.getCurrentPlayerName()).isEqualTo("현구막");
    }
}