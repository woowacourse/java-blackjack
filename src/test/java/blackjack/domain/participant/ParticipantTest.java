package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.CardConstant.DIAMOND_ACE;
import static blackjack.domain.CardConstant.DIAMOND_JACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ParticipantTest {

    Participant participant;

    @BeforeEach
    void setUp() {
        participant = new TestParticipant(Name.from("test"), new ArrayList<>());
    }

    @Test
    @DisplayName("카드를 드로우할 수 있는지 확인")
    void drawTest() {
        assertThatNoException().isThrownBy(() -> participant.drawCard(DIAMOND_ACE));
    }

    @Test
    @DisplayName("히트 상태인지 확인")
    void canHit() {
        assertThat(participant.canHit()).isTrue();
    }

    @Test
    @DisplayName("참여자가 딜러인지 확인")
    void isDealerTest() {
        assertThat(participant.isDealer()).isFalse();
    }

    @Test
    @DisplayName("카드를 한 장 드로우 했을 경우, 핸드에 카드의 수가 하나 늘어나야 한다")
    void drawCardTest() {
        // given
        final int expected = 1;

        // when
        participant.drawCard(DIAMOND_ACE);
        final int actual = participant.handSize();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("점수를 가져올 수 있는지 확인")
    void getScoreTest() {
        // given
        participant.drawCard(DIAMOND_ACE);
        participant.drawCard(DIAMOND_JACK);
        final Score expected = Score.from(21);

        // when
        final Score actual = participant.getScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 이름 목록을 가지고 오는지 확인")
    void getCardNamesTest() {
        // given
        participant.drawCard(DIAMOND_ACE);
        participant.drawCard(DIAMOND_JACK);
        final List<String> expected = List.of("A다이아몬드", "J다이아몬드");

        // when
        final List<String> actual = participant.getCardNames();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("참여자의 이름을 제대로 가져오는지 확인")
    void getNameTest() {
        // given
        final String expected = "test";

        // when
        final String actual = participant.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 한 장의 정보를 제대로 가져오는지 확인")
    void getCardTest() {
        // given
        participant.drawCard(DIAMOND_ACE);
        participant.drawCard(DIAMOND_JACK);
        final Card expected = DIAMOND_ACE;

        // when
        final Card actual = participant.getCard(0);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}

class TestParticipant extends Participant {

    public TestParticipant(final Name name, final List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
