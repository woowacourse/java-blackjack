package blackjack.domain.card;

import blackjack.domain.Fixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardTest {

    @Test
    @DisplayName("카드 인스턴스 생성 확인")
    void createCard() {
        PlayingCard playingCard = Fixtures.SPADE_TWO;

        assertThat(playingCard).isInstanceOf(PlayingCard.class);
    }

    @Test
    @DisplayName("생성된 카드가 ACE인지 확인")
    void isAce() {
        PlayingCard playingCard = Fixtures.SPADE_ACE;

        assertThat(playingCard.isAce()).isTrue();
    }
}
