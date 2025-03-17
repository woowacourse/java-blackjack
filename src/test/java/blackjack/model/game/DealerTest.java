package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Players players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player1 = new Player("벡터", 1000);
        player2 = new Player("제프", 1000);
        players = new Players(List.of(player1, player2));
    }

    @Test
    void 참가자가_버스트면_딜러가_승리한다() {
        // given
        setBust(player1);

        // when
        PlayerResult result = getGameResult(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    void 딜러가_버스트면_참가자가_승리한다() {
        // given
        setBust(dealer);
        setHand(player1, CardType.KING, CardType.QUEEN); // 20점

        // when
        PlayerResult result = getGameResult(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.WIN);
    }

    @Test
    void 참가자의_점수가_딜러보다_높으면_승리한다() {
        // given
        setHand(dealer, CardType.KING, CardType.NORMAL_6); // 16점
        setHand(player1, CardType.KING, CardType.NORMAL_8); // 18점

        // when
        PlayerResult result = getGameResult(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.WIN);
    }

    @Test
    void 딜러의_점수가_참가자보다_높으면_딜러가_승리한다() {
        // given
        setHand(dealer, CardType.KING, CardType.NORMAL_8); // 18점
        setHand(player1, CardType.KING, CardType.NORMAL_6); // 16점

        // when
        PlayerResult result = getGameResult(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    void 딜러와_참가자의_점수가_같으면_무승부() {
        // given
        setHand(dealer, CardType.KING, CardType.NORMAL_8); // 18점
        setHand(player1, CardType.KING, CardType.NORMAL_8); // 18점

        // when
        PlayerResult result = getGameResult(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.DRAW);
    }

    @Test
    void 참가자가_블랙잭이면_승리한다() {
        // given
        setHand(dealer, CardType.KING, CardType.NORMAL_9); // 19점
        setBlackjack(player1);

        // when
        PlayerResult result = getGameResult(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.BLACKJACK);
    }

    @Test
    void 딜러가_블랙잭이면_딜러가_승리한다() {
        // given
        setBlackjack(dealer);
        setHand(player1, CardType.KING, CardType.NORMAL_9); // 19점

        // when
        PlayerResult result = getGameResult(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    void 딜러와_참가자가_블랙잭이면_무승부() {
        // given
        setBlackjack(dealer);
        setBlackjack(player1);

        // when
        PlayerResult result = getGameResult(player1);

        // then
        assertThat(result).isEqualTo(PlayerResult.DRAW);
    }


    private void setHand(Participant participant, CardType card1, CardType card2) {
        participant.putCard(new Card(CardShape.HEART, card1));
        participant.putCard(new Card(CardShape.SPADE, card2));
    }

    private void setBust(Participant participant) {
        participant.putCard(new Card(CardShape.HEART, CardType.KING));
        participant.putCard(new Card(CardShape.HEART, CardType.QUEEN));
        participant.putCard(new Card(CardShape.HEART, CardType.NORMAL_5)); // 25점, 버스트
    }

    private void setBlackjack(Participant participant) {
        participant.putCard(new Card(CardShape.HEART, CardType.ACE));
        participant.putCard(new Card(CardShape.SPADE, CardType.KING)); // 블랙잭
    }

    private PlayerResult getGameResult(Player player) {
        return dealer.calculateGameResult(players).get(player);
    }
}
