package blackjack.domain;

import static blackjack.domain.CardsTestDataGenerator.*;
import static blackjack.domain.Denomination.*;
import static blackjack.domain.GameResult.*;
import static blackjack.domain.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class DealerTest {

    @DisplayName("카드 리스트가 주어지면 정상적으로 생성된다.")
    @Test
    void 딜러_생성_정상() {
        assertDoesNotThrow(() -> new Dealer(generateCards()));
    }

    @DisplayName("딜러의 점수가 16점 이하인 경우 카드를 받을 수 있다.")
    @Test
    void 카드_발급_가능() {
        List<Card> cards = generateTotalScoreNotMoreThan16Cards();
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 점수가 17점을 초과한 경우 카드를 받을 수 없다.")
    @Test
    void 카드_발급_불가능() {
        List<Card> cards = generateTotalScoreGraterThan17Cards();
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.canHit()).isFalse();
    }

    @DisplayName("카드를 받아서 합칠 수 있다.")
    @Test
    void 카드_합침() {
        Dealer dealer = new Dealer(generateCards());
        Card card = Card.of(FIVE, SPADE);

        dealer.add(card);

        assertThat(dealer.getCards().getValue().size()).isEqualTo(3);
    }

    @DisplayName("Cards가 주어지면 점수를 계산하면 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(CardsArgumentsProvider.class)
    void 딜러_카드_점수_계산(Cards cards, int totalScore) {
        Dealer dealer = new Dealer(cards.getValue());

        assertThat(dealer.getTotalScore()).isEqualTo(totalScore);
    }


    @DisplayName("딜러만 버스트 일 경우 패배한다.")
    @Test
    void 딜러_승패_여부_버스트_패() {
        List<Card> bustCards = generateTotalScoreGraterThan21Cards();
        List<Card> normalCards = generateTotalScoreNotMoreThan21Cards();

        Dealer dealer = new Dealer(bustCards);
        Player player = new Player("sudal", normalCards);

        GameResult gameResult = dealer.createResult(player);

        assertThat(gameResult).isEqualTo(LOSE);
    }

    @DisplayName("딜러, 플레이어 모두 버스트일 경우 승리한다.")
    @Test
    void 딜러_승패_여부_둘다_버스트_승() {
        List<Card> bustValueByDealer = generateTotalScoreGraterThan21Cards();
        List<Card> bustValueByPlayer = generateTotalScoreGraterThan21Cards();

        Dealer dealer = new Dealer(bustValueByDealer);
        Player player = new Player("sudal", bustValueByPlayer);

        GameResult gameResult = dealer.createResult(player);

        assertThat(gameResult).isEqualTo(WIN);
    }

    @DisplayName("플레이어만 버스트이면 승리한다.")
    @Test
    void 딜러_승패_여부_버스트_승() {
        List<Card> minValueCards = generateTotalScoreNotMoreThan16Cards();
        List<Card> maxValueCards = generateTotalScoreGraterThan21Cards();

        Dealer dealer = new Dealer(minValueCards);
        Player player = new Player("sudal", maxValueCards);

        GameResult gameResult = dealer.createResult(player);

        assertThat(gameResult).isEqualTo(WIN);
    }

    @DisplayName("딜러보다 플레이어의 점수가 높으면 패배한다.")
    @Test
    void 딜러_승패_여부_점수_패() {
        List<Card> minValueCards = generateTotalScoreNotMoreThan16Cards();
        List<Card> maxValueCards = generateTotalScoreGraterThan17Cards();

        Dealer dealer = new Dealer(minValueCards);
        Player player = new Player("sudal", maxValueCards);

        GameResult gameResult = dealer.createResult(player);

        assertThat(gameResult).isEqualTo(LOSE);
    }

    @DisplayName("딜러가 플레이어보다 점수가 높으면 승리한다.")
    @Test
    void 플레이어_승패_여부_점수_승() {
        List<Card> minValueCards = generateTotalScoreNotMoreThan16Cards();
        List<Card> maxValueCards = generateTotalScoreGraterThan17Cards();

        Dealer dealer = new Dealer(maxValueCards);
        Player player = new Player("sudal", minValueCards);

        GameResult gameResult = dealer.createResult(player);

        assertThat(gameResult).isEqualTo(WIN);
    }

    @DisplayName("딜러와 플레이어 점수가 같으면 무.")
    @Test
    void 딜러_승패_여부_점수_무() {
        List<Card> tieValueByPlayer = generateCards();
        List<Card> tieValueByDealer = generateCards();

        Dealer dealer = new Dealer(tieValueByDealer);
        Player player = new Player("sudal", tieValueByPlayer);

        GameResult gameResult = dealer.createResult(player);

        assertThat(gameResult).isEqualTo(TIE);
    }
}
