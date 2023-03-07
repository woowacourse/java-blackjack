package domain;

import domain.Card;
import domain.CardRank;
import domain.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @DisplayName("카드는 모양과 숫자를 가진다.")
    @Test
    void createCardSuccess() {
        Card card = Card.of(CardShape.HEART, CardRank.ACE);

        assertThat(card.getCardShapeName())
                .isEqualTo(CardShape.HEART.getName());
        assertThat(card.getScore())
                .isEqualTo(CardRank.ACE.getScore());
    }
}

