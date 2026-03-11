package participant;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    Hand dummyHand;
    PlayerName name;

    @BeforeEach
    void init() {
        dummyHand = new Hand();
        name = new PlayerName("제발");
    }

    @Test
    @DisplayName("플레이어는 카드를 뽑아 핸드에 저장할 수 있다")
    void keepCard_AddsCardToHand() {
        Player player = new Player(name, dummyHand);
        int beforeSize = player.handSize();

        player.keepCard(new Card(Rank.EIGHT, Pattern.CLOVER));
        int afterSize = player.handSize();

        assertThat(beforeSize + 1).isEqualTo(afterSize);
    }

    @Test
    @DisplayName("플레이어는 자신의 핸드 카드의 총합을 계산할 수 있다.")
    void getTotalCardScore_CalculatesTotalCardScore() {
        Player player = new Player(name, dummyHand);
        Card card1 = new Card(Rank.FOUR, Pattern.CLOVER);
        Card card2 = new Card(Rank.SIX, Pattern.CLOVER);

        player.keepCard(card1);
        player.keepCard(card2);
        int expectedScore = card1.getCardScore() + card2.getCardScore();
        int playerScore = player.getTotalCardScore();

        assertThat(playerScore).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("플레이어 핸드의 카드 총합이 21초과면 bust이다.")
    void isBust_ReturnsTrue_WhenTotalScoreExceeds21() {
        Card card1 = new Card(Rank.JACK, Pattern.CLOVER);
        Card card2 = new Card(Rank.QUEEN, Pattern.CLOVER);
        Card card3 = new Card(Rank.KING, Pattern.CLOVER);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);
        dummyHand.addCard(card3);

        boolean result = new Player(name, dummyHand).isBust();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어 핸드의 카드 총합이 21이하면 bust가 아니다.")
    void isBust_ReturnsFalse_WhenTotalScoreLessThan21() {
        Card card1 = new Card(Rank.JACK, Pattern.CLOVER);
        Card card2 = new Card(Rank.TWO, Pattern.CLOVER);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);

        boolean result = new Player(name, dummyHand).isBust();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어의 블랙잭 여부를 판단할 수 있다")
    void isBlackjack_returnsTrue_whenPlayerHasBlackjack(){
        Player player = new Player(name, dummyHand);
        player.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        player.keepCard(new Card(Rank.JACK, Pattern.CLOVER));

        Player player2 = new Player(new PlayerName("user2"), new Hand());
        player2.keepCard(new Card(Rank.ACE, Pattern.HEART));
        player2.keepCard(new Card(Rank.EIGHT, Pattern.HEART));

        boolean success = player.isBlackjack();
        boolean fail = player2.isBlackjack();

        assertThat(success).isTrue();
        assertThat(fail).isFalse();
    }
}
