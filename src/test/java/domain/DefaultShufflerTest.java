package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.DefaultShuffler;
import domain.card.Shuffler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultShufflerTest {

    private final Shuffler shuffler = new DefaultShuffler();

    @Test
    @DisplayName("카드 리스트를 섞는다.")
    public void 덱_셔플_성공() {
        // given
        final List<Card> origin = new ArrayList<>();
        for (final CardSuit suit : CardSuit.values()) {
            for (final CardRank rank : CardRank.values()) {
                origin.add(new Card(suit, rank));
            }
        }

        final List<Card> copy = new ArrayList<>(origin);

        // when
        shuffler.shuffle(copy);

        // then
        assertThat(copy)
                .hasSameSizeAs(origin)
                .containsExactlyInAnyOrderElementsOf(origin);

        // 1/52! 확률로 52장 순서가 동일한 경우가 뜰 수도 있으니, 이 점을 참고.
        assertThat(copy).isNotEqualTo(origin);
    }

}
