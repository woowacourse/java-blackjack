package blackjack.domain.participant;

import static blackjack.domain.CardsTestDataGenerator.generateBlackjack;
import static blackjack.domain.CardsTestDataGenerator.generateCards;
import static blackjack.domain.CardsTestDataGenerator.generateTotalScoreGraterThan17Cards;
import static blackjack.domain.CardsTestDataGenerator.generateTotalScoreNotMoreThan16Cards;
import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.GameResult.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.CardsArgumentsProvider;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
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

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("딜러의 점수가 17점을 초과한 경우 카드를 받을 수 없다.")
    @Test
    void 카드_발급_불가능() {
        List<Card> cards = generateTotalScoreGraterThan17Cards();
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("카드를 받아서 합칠 수 있다.")
    @Test
    void 카드_합침() {
        Dealer dealer = new Dealer(generateCards());
        Card card = Card.of(FIVE, SPADE);

        dealer.append(card);

        assertThat(dealer.getCards().size()).isEqualTo(3);
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
        Dealer dealer = new Dealer(generateBlackjack());
        dealer.append(Card.of(KING, SPADE));
        dealer.append(Card.of(JACK, HEART));

        Player player = new Player("sudal", generateBlackjack());

        GameResult gameResult = dealer.decideResult(player.getTotalScore());

        assertThat(gameResult).isEqualTo(LOSE);
    }

    @DisplayName("딜러, 플레이어 모두 버스트일 경우 승리한다.")
    @Test
    void 딜러_승패_여부_둘다_버스트_승() {
        Dealer dealer = new Dealer(generateBlackjack());
        dealer.append(Card.of(KING, SPADE));
        dealer.append(Card.of(JACK, HEART));

        Player player = new Player("sudal", generateBlackjack());
        player.append(Card.of(KING, HEART));
        player.append(Card.of(JACK, SPADE));

        GameResult gameResult = dealer.decideResult(player.getTotalScore());

        assertThat(gameResult).isEqualTo(WIN);
    }

    @DisplayName("플레이어만 버스트이면 승리한다.")
    @Test
    void 딜러_승패_여부_버스트_승() {
        Dealer dealer = new Dealer(generateTotalScoreNotMoreThan16Cards());
        Player player = new Player("sudal", generateBlackjack());
        player.append(Card.of(KING, SPADE));
        player.append(Card.of(JACK, SPADE));

        GameResult gameResult = dealer.decideResult(player.getTotalScore());

        assertThat(gameResult).isEqualTo(WIN);
    }

    @DisplayName("딜러보다 플레이어의 점수가 높으면 패배한다.")
    @Test
    void 딜러_승패_여부_점수_패() {
        Dealer dealer = new Dealer(generateTotalScoreNotMoreThan16Cards());
        Player player = new Player("sudal", generateTotalScoreGraterThan17Cards());

        GameResult gameResult = dealer.decideResult(player.getTotalScore());

        assertThat(gameResult).isEqualTo(LOSE);
    }

    @DisplayName("딜러가 플레이어보다 점수가 높으면 승리한다.")
    @Test
    void 플레이어_승패_여부_점수_승() {
        Dealer dealer = new Dealer(generateTotalScoreGraterThan17Cards());
        Player player = new Player("sudal", generateTotalScoreNotMoreThan16Cards());

        GameResult gameResult = dealer.decideResult(player.getTotalScore());

        assertThat(gameResult).isEqualTo(WIN);
    }

    @DisplayName("딜러와 플레이어 점수가 같으면 무.")
    @Test
    void 딜러_승패_여부_점수_무() {
        Dealer dealer = new Dealer(generateCards());
        Player player = new Player("sudal", generateCards());

        GameResult gameResult = dealer.decideResult(player.getTotalScore());

        assertThat(gameResult).isEqualTo(TIE);
    }
}
