package blackjack.domain;

import static blackjack.domain.CardsTestDataGenerator.*;
import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.GameResult.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class PlayerTest {

    @DisplayName("이름과 카드 리스트가 주어지면 정상적으로 생성된다.")
    @Test
    void 플레이어_생성_정상() {
        assertDoesNotThrow(() -> new Player("mat", generateCards()));
    }

    @DisplayName("플레이어의 총 점수가 21점 이하인 경우 hit가 가능하다.")
    @Test
    void 플레이어_게임_지속_가능() {
        String name = "mat";
        List<Card> cards = generateTotalScoreNotMoreThan21Cards();
        Player player = new Player(name, cards);

        assertThat(player.isPlaying()).isTrue();
    }

    @DisplayName("플레이어의 총 점수가 21점을 초과하는 경우 hit가 불가능하다.")
    @Test
    void 플레이어_게임_지속_불가능() {
        String name = "sudal";
        List<Card> cards = generateTotalScoreGraterThan21Cards();
        Player player = new Player(name, cards);

        assertThat(player.isPlaying()).isFalse();
    }

    @DisplayName("카드를 받아서 합칠 수 있다.")
    @Test
    void 카드_합침() {
        String name = "mat";
        List<Card> cards = generateCards();
        Player player = new Player(name, cards);
        Card card = Card.of(FIVE, SPADE);

        player.combine(card);

        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @DisplayName("Cards가 주어지면 점수를 계산하면 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(CardsArgumentsProvider.class)
    void 플레이어_카드_점수_계산(Cards cards, int totalScore) {
        Dealer dealer = new Dealer(cards.getValue());

        assertThat(dealer.getTotalScore()).isEqualTo(totalScore);
    }

    @DisplayName("딜러가 버스트 일 경우 플레이어가 승리한다.")
    @Test
    void 플레이어_승패_여부_버스트_승() {
        List<Card> bustCards = generateTotalScoreGraterThan21Cards();
        List<Card> normalCards = generateTotalScoreNotMoreThan21Cards();

        Dealer dealer = new Dealer(bustCards);
        Player player = new Player("sudal", normalCards);

        GameResult gameResult = player.decideResult(dealer.getTotalScore());

        assertThat(gameResult).isEqualTo(WIN);
    }

    @DisplayName("플레이어와 딜러 모두 버스트일 경우 패배한다.")
    @Test
    void 플레이어_승패_여부_둘다_버스트_패() {
        List<Card> dealerByBustValue = generateTotalScoreGraterThan21Cards();

        List<Card> playerByBustValue = generateTotalScoreGraterThan21Cards();

        Dealer dealer = new Dealer(dealerByBustValue);
        Player player = new Player("sudal", playerByBustValue);

        GameResult gameResult = player.decideResult(dealer.getTotalScore());

        assertThat(gameResult).isEqualTo(LOSE);
    }

    @DisplayName("플레이어만 버스트이면 패배한다.")
    @Test
    void 플레이어_승패_여부_버스트_패() {
        List<Card> minValueCards = generateTotalScoreNotMoreThan21Cards();
        //22점
        List<Card> maxValueCards = generateTotalScoreGraterThan21Cards();

        Dealer dealer = new Dealer(minValueCards);
        Player player = new Player("sudal", maxValueCards);

        GameResult gameResult = player.decideResult(dealer.getTotalScore());

        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("플레이어가 딜러보다 점수가 높으면 승리한다.")
    @Test
    void 플레이어_승패_여부_점수_승() {
        //12점
        List<Card> minValueCards = generateTotalScoreNotMoreThan16Cards();
        //18점
        List<Card> maxValueCards = generateTotalScoreGraterThan17Cards();

        Dealer dealer = new Dealer(minValueCards);
        Player player = new Player("sudal", maxValueCards);

        GameResult gameResult = player.decideResult(dealer.getTotalScore());

        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어가 딜러보다 점수가 낮으면 패배.")
    @Test
    void 플레이어_승패_여부_점수_패() {
        //12점
        List<Card> minValueCards = generateTotalScoreNotMoreThan16Cards();
        //18점
        List<Card> maxValueCards = generateTotalScoreGraterThan17Cards();

        Dealer dealer = new Dealer(maxValueCards);
        Player player = new Player("sudal", minValueCards);

        GameResult gameResult = player.decideResult(dealer.getTotalScore());

        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("플레이어와 딜러가 점수가 같으면 무.")
    @Test
    void 플레이어_승패_여부_점수_무() {
        //12점
        List<Card> tieValueByPlayer = generateCards();
        //12점
        List<Card> tieValueByDealer = generateCards();

        Dealer dealer = new Dealer(tieValueByDealer);
        Player player = new Player("sudal", tieValueByPlayer);

        GameResult gameResult = player.decideResult(dealer.getTotalScore());

        assertThat(gameResult).isEqualTo(GameResult.TIE);
    }
}
