package domain.blackjack;

import domain.participant.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {

    @DisplayName("카드를 두장씩 나눠준다.")
    @Test
    void beginDealing() {
        Players players = new Players(List.of("one", "two"));
        Dealer dealer = new Dealer();
        BlackJack blackJack = new BlackJack(players, dealer);
        blackJack.beginDealing((testParticipants, testDealer) -> {});

        Assertions.assertAll(
                () -> assertThat(dealer.getCardCount()).isEqualTo(2),
                () -> assertThat(players.getValue().get(0).getCardCount()).isEqualTo(2),
                () -> assertThat(players.getValue().get(1).getCardCount()).isEqualTo(2)
        );
    }

    @Test
    void name() {
        Players players = new Players(List.of("one", "two"));
        Dealer dealer = new Dealer();
        BlackJack blackJack = new BlackJack(players, dealer);

        Function<Name, HitOption> testFunction = new Function<Name, HitOption>() {
            int i = 0;
            @Override
            public HitOption apply(Name name) {
                if (i % 3 != 2) {
                    i++;
                    return HitOption.YES;
                }
                return HitOption.NO;
            }
        };

        blackJack.play(testFunction, Participant::getName);

        assertThat(players.getValue().get(0).getCardCount()).isEqualTo(2);
    }
}
