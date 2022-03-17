package blackjack.domain.participant;

import static blackjack.domain.CardsTestDataGenerator.generateBlackjack;
import static blackjack.domain.CardsTestDataGenerator.generateCards;
import static blackjack.domain.CardsTestDataGenerator.generateTotalScoreGraterThan17Cards;
import static blackjack.domain.CardsTestDataGenerator.generateTotalScoreNotMoreThan16Cards;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.CardsArgumentsProvider;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class ParticipantTest {

    @DisplayName("Cards가 주어지면 점수를 계산하면 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(CardsArgumentsProvider.class)
    void 플레이어_카드_점수_계산(Cards cards, int totalScore) {
        Participant participant = new Dealer(cards.getValue());

        assertThat(participant.getTotalScore()).isEqualTo(totalScore);
    }

    @DisplayName("카드를 받아서 합칠 수 있다.")
    @Test
    void 카드_합침() {
        String name = "mat";
        List<Card> cards = generateCards();
        Participant participant = new Player(name, cards, 10000);
        Card card = new Card(FIVE, SPADE);

        participant.append(card);

        assertThat(participant.getCards().size()).isEqualTo(3);
    }

    @DisplayName("카드가 블랙잭인 경우 true이다.")
    @Test
    void 플레이어_블랙잭_여부() {
        Participant player = new Player("mat", generateBlackjack(), 10000);

        boolean result = player.isBlackjack();

        assertThat(result).isTrue();
    }

    @DisplayName("카드가 블랙잭이 아닌 경우 false이다.")
    @Test
    void 플레이어_블랙잭_아닌_경우() {
        Participant player = new Player("mat", generateCards(), 10000);

        boolean result = player.isBlackjack();

        assertThat(result).isFalse();
    }

    @DisplayName("카드 점수가 21점을 초과한 경우 버스트이다.")
    @Test
    void 플레이어_점수_초과_버스트() {
        Participant player = new Player("sudal", generateBlackjack(), 10000);
        player.append(new Card(KING, SPADE));
        player.append(new Card(JACK, SPADE));

        boolean result = player.isBust();

        assertThat(result).isTrue();
    }

    @DisplayName("카드 점수가 21점 이하인 경우 버스트가 아니다.")
    @Test
    void 플레이어_정상() {
        Participant player = new Player("sudal", generateCards(), 10000);

        boolean result = player.isBust();

        assertThat(result).isFalse();
    }

    @DisplayName("카드 점수를 비교하여 승리한 경우 true를 반환한다.")
    @Test
    void 플레이어_점수_비교_승() {
        Participant dealer = new Dealer(generateTotalScoreNotMoreThan16Cards());
        Participant player = new Player("sudal", generateTotalScoreGraterThan17Cards(), 10000);

        boolean result = player.isWin(dealer);

        assertThat(result).isTrue();
    }

    @DisplayName("카드 점수를 비교하여 지는 경우 false를 반환한다.")
    @Test
    void 플레이어_점수_비교_패() {
        Participant dealer = new Dealer(generateTotalScoreGraterThan17Cards());
        Participant player = new Player("sudal", generateTotalScoreNotMoreThan16Cards(), 10000);

        boolean result = player.isWin(dealer);

        assertThat(result).isFalse();
    }

    @DisplayName("카드 점수를 비교하여 동일한 경우 true를 반환한다.")
    @Test
    void 플레이어_점수_동일() {
        Participant dealer = new Dealer(generateCards());
        Participant player = new Player("sudal", generateCards(), 10000);

        boolean result = player.isSameScore(dealer);

        assertThat(result).isTrue();
    }
}