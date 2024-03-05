package model.player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import model.card.Cards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("딜러가 1명 초과면 예외가 발생한다.")
    @Test
    void validateDealerOverOne() {
        Assertions.assertThatThrownBy(() ->
                        new Players(List.of(new Dealer(),
                        new Dealer(),
                        new Participant("켬미")),
                        new Cards()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러가 1명 미만이면 예외가 발생한다.")
    @Test
    void validateDealerUnderOne() {
        Assertions.assertThatThrownBy(() ->
                        new Players(List.of(
                                new Participant("켬미")),
                                new Cards()))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
