package player;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.CardPattern;
import dealer.Dealer;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        String name = "pola";

        player = Player.joinGame(name, new CardDeck().firstCardSettings(), 100);
    }

    @DisplayName("Player가 들고 있는 카드가 최고점을 넘을경우 true를 return한다.")
    @Test
    void isOverMaxGameScore() {
        Card kingCard = new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN);
        Card queenCard = new Card(CardNumber.QUEEN, CardPattern.CLOVER_PATTERN);
        Card jackCard = new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN);

        player.receiveCard(kingCard);
        player.receiveCard(queenCard);
        player.receiveCard(jackCard);

        Assertions.assertThat(player.isBust()).isTrue();
    }

    @DisplayName("Player가 들고 있는 카드가 최고점을 넘지 않을경우 false를 return한다.")
    @Test
    void isNotOverMaxGameScore() {
        Assertions.assertThat(player.isBust()).isFalse();
    }

    @DisplayName("Player가 딜러의 점수보다 높고 BUST가 아닐 경우 우승자다.")
    @Test
    void isWinnerPlayer() {
        Dealer dealer = new Dealer(List.of());

        Assertions.assertThat(player.isWinner(dealer)).isTrue();
    }

    @DisplayName("Player가 딜러의 점수보다 낮거나 같을 경우 우승하지 못한다.")
    @Test
    void isNotWinnerPlayer() {
        Dealer dealer = new Dealer(List.of(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN),
                new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN)));

        Assertions.assertThat(player.isWinner(dealer)).isFalse();
    }

    @DisplayName("Plyaer의 카드 스코어가 bust인 경우 점수가 더 높더라도 우승하지 못한다.")
    @Test
    void isBustPlayer() {
        Dealer dealer = new Dealer(List.of(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN),
                new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN)));

        player.receiveCard(new Card(CardNumber.JACK, CardPattern.DIA_PATTERN));
        player.receiveCard(new Card(CardNumber.QUEEN, CardPattern.DIA_PATTERN));

        Assertions.assertThat(player.isWinner(dealer)).isFalse();
    }

    @DisplayName("게임에 참가하는 경우, 카드 두장을 받고 시작한다.")
    @Test
    void getCardWhenPlay() {
        Assertions.assertThat(player.getCards().countCard()).isEqualTo(2);
    }
}
