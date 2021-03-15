package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BlackjackManager;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("이름들을 입력받아서 플레이어들을 생성")
    void testCreatePlayers() {
        Names names = new Names(Arrays.asList("미립", "현구막", "포비"));
        Players players = getCustomPlayers(names);
        assertThat(players.toList()).hasSize(3);
    }

    @Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        Dealer dealer = new Dealer();
        Names names = new Names(Arrays.asList("미립", "현구막"));
        Players players = getCustomPlayers(names);
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
        Names names = new Names(Arrays.asList("미립", "현구막"));
        Players players = getCustomPlayers(names);
        players.initDraw(getCustomCardDeck());

        players.drawCurrentTurnPlayer(Card.valueOf(Pattern.SPADE, Number.JACK));
        players.passTurnToNextPlayer();
        players.drawCurrentTurnPlayer(Card.valueOf(Pattern.DIAMOND, Number.JACK));
        players.passTurnToNextPlayer();

        assertThat(players.isAllPlayerDone()).isTrue();
    }

    @Test
    @DisplayName("현재 차례 플레이어 턴이 넘어갔는지 확인한다.")
    void testIsSuccessPassThePlayerTurn() {
        Names names = new Names(Arrays.asList("미립", "현구막"));
        Players players = getCustomPlayers(names);
        players.initDraw(getCustomCardDeck());

        assertThat(players.getCurrentPlayerName()).isEqualTo("미립");
        players.passTurnToNextPlayer();
        assertThat(players.getCurrentPlayerName()).isEqualTo("현구막");
    }

    @Test
    @DisplayName("현재 차례 플레이어가 카드를 뽑았는지 확인한다.")
    void testPlayerDrawCard() {
        Players players = getCustomPlayers(new Names(Collections.singletonList("미립")));
        players.initDraw(getDrawTestCardDeck());

        assertThat(players.getCurrentTurnPlayer().getScoreToInt()).isEqualTo(12);
        players.drawCurrentTurnPlayer(Card.valueOf(Pattern.DIAMOND, Number.THREE));
        assertThat(players.getCurrentTurnPlayer().getScoreToInt()).isEqualTo(15);
    }

    @Test
    @DisplayName("플레이어들의 결과에 대한 배팅결과 리스트 반환")
    void testGetBetAmounts() {
        Dealer dealer = new Dealer();
        dealer.initDraw(getCustomCardDeck());
        Players players = getCustomPlayers(new Names(Arrays.asList("미립", "현구막")));
        players.initDraw(getCustomCardDeck());
        players.stayCurrentTurnPlayer();
        players.passTurnToNextPlayer();
        players.drawCurrentTurnPlayer(Card.valueOf(Pattern.DIAMOND, Number.FOUR));

        BetAmount firstAmount = players.getBetAmounts(dealer).get(0);
        assertThat(firstAmount.getValue()).isEqualTo(3000);
        BetAmount secondAmount = players.getBetAmounts(dealer).get(1);
        assertThat(secondAmount.getValue()).isEqualTo(-3000);

    }

    private Players getCustomPlayers(final Names names) {
        List<Player> players = new ArrayList<>();
        while (!names.isDoneAllPlayers()) {
            players.add(new Player(names.getCurrentTurnName(), BetAmount.betting(3000)));
            names.passTurnToNext();
        }
        return new Players(players);
    }

    private CardDeck getCustomCardDeck() {
        Card firstCard = Card.valueOf(Pattern.HEART, Number.TEN);
        Card secondCard = Card.valueOf(Pattern.HEART, Number.KING);
        Card thirdCard = Card.valueOf(Pattern.HEART, Number.QUEEN);
        Card fourthCard = Card.valueOf(Pattern.HEART, Number.JACK);
        return CardDeck.customDeck(Arrays.asList(firstCard, secondCard, thirdCard, fourthCard));
    }

    private CardDeck getDrawTestCardDeck() {
        Card firstCard = Card.valueOf(Pattern.HEART, Number.TWO);
        Card secondCard = Card.valueOf(Pattern.HEART, Number.KING);
        return CardDeck.customDeck(Arrays.asList(firstCard, secondCard));
    }
}