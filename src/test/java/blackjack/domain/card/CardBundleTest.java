package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import blackjack.domain.game.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardBundleTest {
    private static final Card CLOVER4 = Card.of(CardRank.FOUR, CardSymbol.CLOVER);
    private static final Card CLOVER5 = Card.of(CardRank.FIVE, CardSymbol.CLOVER);
    private static final Card CLOVER6 = Card.of(CardRank.SIX, CardSymbol.CLOVER);

    @DisplayName("of 팩토리 메소드는 두개의 카드를 받아 CardBundle 인스턴스를 생성한다.")
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

    @DisplayName("getScore 는 각 카드가 지닌 값들의 합을 합산하여 반환한다.")
    @Test
    void getScore() {
        CardBundle cardBundle = CardBundle.of(CLOVER4, CLOVER5);

        Score actual = cardBundle.getScore();
        Score expected = Score.valueOf(9);

        assertThat(actual).isEqualTo(expected);
    }
}
