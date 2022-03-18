package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    void createStatuses() {
        List<Status> statuses = List.of(
                Status.of(2, 21),
                Status.of(2, 20),
                Status.of(3, 21),
                Status.of(3, 20),
                Status.of(3, 22)
        );

        assertThat(statuses).contains(Status.BLACKJACK, Index.atIndex(0));
        assertThat(statuses).contains(Status.STAND, Index.atIndex(1));
        assertThat(statuses).contains(Status.STAND, Index.atIndex(2));
        assertThat(statuses).contains(Status.STAND, Index.atIndex(3));
        assertThat(statuses).contains(Status.BUST, Index.atIndex(4));
    }

    @Test
    void checkInvalidStatus() {
        assertThatThrownBy(() -> Status.of(0, 22))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateMargin() {
        Status blackJack = Status.of(2, 21);
        Status stand = Status.of(3, 21);
        Status bust = Status.of(3, 22);
        assertThat(blackJack.calculateWinMargin(1000)).isEqualTo(1500);
        assertThat(stand.calculateWinMargin(1000)).isEqualTo(1000);
        assertThat(bust.calculateWinMargin(1000)).isEqualTo(1000);
    }
}
