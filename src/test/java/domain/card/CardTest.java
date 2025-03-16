package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("카드 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CardTest {

    @Test
    void 카드가_에이스이면_true를_반환한다() {
        Card aceCard = new Card(Suit.DIAMOND, Rank.ACE);

        assertThat(aceCard.isAce()).isTrue();
    }

    @Test
    void 카드가_에이스가_아니면_false를_반환한다() {
        Card notAceCard = new Card(Suit.DIAMOND, Rank.TWO);

        assertThat(notAceCard.isAce()).isFalse();
    }
}
