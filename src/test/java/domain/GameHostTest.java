package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.constants.Score;
import domain.constants.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameHostTest {
    @DisplayName("게이머들의 이름이 중복되었는지 확인한다.")
    @Test
    void checkDuplicatedNames() {
        List<String> names = List.of("pobi", "pobi");

        assertThatCode(() -> new GameHost(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 있습니다.");
    }

    @DisplayName("딜러와 게이머들에게 카드를 2장씩 나눠준다.")
    @Test
    void giveTwoCardsToDealerAndGamers() {
        GameHost gameHost = new GameHost(List.of("pobi"));
        gameHost.readyGame();

        Dealer dealer = gameHost.findPlayingDealer();
        Gamers gamers = gameHost.findPlayingGamers();
        List<Gamer> gamerList = gamers.listOf();

        assertThat(dealer.getHandSize()).isEqualTo(2);
        assertThat(gamerList.get(0).getHandSize()).isEqualTo(2);
    }

    @DisplayName("게이머가 카드를 한 장 드로우하게 한다.")
    @Test
    void drawOneCardToGamer() {
        GameHost gameHost = new GameHost(List.of("pobi"));
        gameHost.readyGame();

        Gamer gamer = gameHost.findPlayingGamers().listOf().get(0);
        int beforeDraw = gamer.getHandSize();

        gameHost.drawOneCardToGamer(gamer);

        assertThat(gamer.getHandSize()).isEqualTo(beforeDraw + 1);
    }

    @DisplayName("딜러가 카드를 추가로 드로우한 횟수를 반환한다.")
    @Test
    void cardDrawCountOfDealer() {
        Dealer dealer = new Dealer(underThresholdHand());
        GameHost gameHost = new GameHost(List.of("pobi", "jason"), dealer);

        int count = gameHost.cardDrawCountOfDealer();

        assertThat(count).isEqualTo(1);
    }

    @DisplayName("게이머들의 이름 정보를 반환한다.")
    @Test
    void getGamerNames() {
        GameHost gameHost = new GameHost(List.of("pobi", "jason"));

        assertThat(gameHost.gamerNames()).containsExactly("pobi", "jason");
    }

    @DisplayName("게이머들이 가지고 있는 카드 정보를 반환한다.")
    @Test
    void getGamerHands() {
        GameHost gameHost = new GameHost(List.of("pobi", "jason"));
        List<Hand> gamerHands = gameHost.gamerHands();

        assertThat(gamerHands).hasSize(2);
    }

    @DisplayName("딜러가 가지고 있는 카드 정보를 반환한다.")
    @Test
    void getDealerHand() {
        Dealer dealer = new Dealer(underThresholdHand());
        GameHost gameHost = new GameHost(List.of("pobi", "jason"), dealer);
        Hand dealerHand = gameHost.dealerHand();

        assertThat(dealerHand.getCards()).hasSize(2);
    }

    @DisplayName("딜러의 점수를 반환한다.")
    @Test
    void getDealerScore() {
        Dealer dealer = new Dealer(underThresholdHand());
        GameHost gameHost = new GameHost(List.of("pobi", "jason"), dealer);
        int dealerScore = gameHost.dealerScore();

        assertThat(dealerScore).isEqualTo(16);
    }

    @DisplayName("게이머들의 점수를 반환한다.")
    @Test
    void getGamerScores() {
        GameHost gameHost = new GameHost(List.of("pobi", "jason"));
        List<Integer> gamerScores = gameHost.gamerScores();

        assertThat(gamerScores).containsExactly(0, 0);
    }

    private Hand underThresholdHand() {
        Hand hand = new Hand();
        hand.saveCards(List.of(
                new Card(Score.KING, Shape.SPADE),
                new Card(Score.SIX, Shape.HEART)
        ));
        return hand;
    }
}
