package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CardBundleBuilder;

public class CardBundleTest {

    private static final int ACE_BONUS_SCORE = 10;

    private CardBundle cardBundle;
    private Card cloverAce;
    private Card spadeJack;

    @BeforeEach
    void setUp() {
        cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
        spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);

        this.cardBundle = new CardBundleBuilder()
                .cards(cloverAce, spadeJack)
                .build();
    }

    @Test
    void 플레이어의_카드에_에이스가_있는지_확인한다() {
        Assertions.assertThat(cardBundle.hasAce()).isTrue();
    }

    @Test
    void 기본점수합을_계산한다() {
        int actualScore = cardBundle.getBasicScore();
        int expectedScore = cloverAce.getScore() + spadeJack.getScore();
        Assertions.assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    void 최종점수합을_계산한다() {
        int actualScore = cardBundle.getResultScore();
        int expectedScore = cloverAce.getScore() + spadeJack.getScore() + ACE_BONUS_SCORE;

        Assertions.assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    void 플레이어_블랙잭을_테스트한다() {
        Assertions.assertThat(cardBundle.isBlackjack())
                .isTrue();
    }

}
