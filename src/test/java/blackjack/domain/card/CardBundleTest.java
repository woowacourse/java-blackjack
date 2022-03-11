package blackjack.domain.card;

import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import blackjack.domain.game.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardBundleTest {

    @DisplayName("of 팩토리 메소드는 두 개의 카드를 받아 CardBundle 인스턴스를 생성한다.")
    @Test
    void of_initsNewCardBundleWithTwoCards() {
        CardDeck cardDeck = new CardDeck();
        CardBundle cardBundle = CardBundle.of(cardDeck.pop(), cardDeck.pop());

        assertThat(cardBundle).isNotNull();
    }

    @DisplayName("add 메서드로 새로운 카드를 추가할 수 있다.")
    @Test
    void add() {
        CardBundle cardBundle = CardBundle.of(CLOVER4, CLOVER5);
        cardBundle.add(CLOVER6);

        assertThat(cardBundle.getCards())
                .contains(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("add 메서드로 중복된 카드를 추가하려고 하면 예외가 발생한다.")
    @Test
    void add_throwsExceptionOnDuplicateCard() {
        CardBundle cardBundle = CardBundle.of(CLOVER4, CLOVER5);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> cardBundle.add(CLOVER5))
                .withMessage("중복된 카드는 존재할 수 없습니다.");
    }

    @DisplayName("getScore 메서드는 에이스가 없을 때 각 카드가 지닌 값들을 그대로 합산하여 반환한다.")
    @Test
    void getScore_noAce() {
        CardBundle cardBundle = CardBundle.of(CLOVER4, CLOVER5);

        Score actual = cardBundle.getScore();
        Score expected = Score.valueOf(9);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("getScore 메서드 실행시 에이스 1개가 포함된 경우에 대한 테스트")
    @Nested
    class SingleAceTest {

        @DisplayName("에이스를 11로 계산해도 21을 넘기지 않는다면 11로 계산한다.")
        @Test
        void getScore_aceIs11IfSumIsNotOver21() {
            CardBundle cardBundle = generateCardBundleOf(CLOVER_ACE, CLOVER4, CLOVER5);

            Score actual = cardBundle.getScore();
            Score expected = Score.valueOf(20);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("에이스를 11로 계산했을 때 21인 경우 11로 계산한다.")
        @Test
        void getScore_aceIs11IfSumIs21() {
            CardBundle cardBundle = generateCardBundleOf(CLOVER_ACE, CLOVER3, CLOVER7);

            Score actual = cardBundle.getScore();
            Score expected = Score.valueOf(21);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("에이스를 11로 계산했을 때 21을 넘는 경우 1로 계산한다.")
        @Test
        void getScore_aceIs1IfSumIsOver21() {
            CardBundle cardBundle = generateCardBundleOf(CLOVER_ACE, CLOVER4, CLOVER7);

            Score actual = cardBundle.getScore();
            Score expected = Score.valueOf(12);

            assertThat(actual).isEqualTo(expected);
        }
    }

    private CardBundle generateCardBundleOf(Card... cards) {
        if (cards.length < 2) {
            throw new IllegalArgumentException("최소 두 장의 카드를 입력하여 테스트해야 합니다.");
        }
        CardBundle cardBundle = CardBundle.of(cards[0], cards[1]);
        for (int i = 2; i < cards.length; i++) {
            cardBundle.add(cards[i]);
        }
        return cardBundle;
    }
}
