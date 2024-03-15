package domain.blackjack;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
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
        Players players = new Players(List.of(
                new Player(new Name("beginDealingTest1"), new BetAmount(1000)),
                new Player(new Name("beginDealingTest2"), new BetAmount(1000))
        ));
        Dealer dealer = new Dealer();
        BlackJack blackJack = new BlackJack(players, dealer);
        blackJack.beginDealing((testParticipants, testDealer) -> {
        });

        Assertions.assertAll(
                () -> assertThat(dealer.getCardCount()).isEqualTo(2),
                () -> assertThat(players.getValue().get(0).getCardCount()).isEqualTo(2),
                () -> assertThat(players.getValue().get(1).getCardCount()).isEqualTo(2)
        );
    }

    @DisplayName("사용자가 Bust가 아닐 때 Hit을 하길 원하면 카드를 1장 추가한다.")
    @Test
    void play() {
        Player playTest1 = new Player(new Name("playTest1"), new BetAmount(1000));
        playTest1.receiveCard(new Card(Shape.CLOVER, Rank.TEN));
        playTest1.receiveCard(new Card(Shape.SPADE, Rank.NINE));
        playTest1.receiveCard(new Card(Shape.SPADE, Rank.TWO));
        Players players = new Players(List.of(
                playTest1,
                new Player(new Name("playTest2"), new BetAmount(1000))
        ));
        Dealer dealer = new Dealer();
        BlackJack blackJack = new BlackJack(players, dealer);

        Function<Name, HitOption> testFunction = new Function<Name, HitOption>() {
            @Override
            public HitOption apply(Name name) {
                return HitOption.YES;
            }
        };

        blackJack.play(testFunction, participantDto -> {});

        assertThat(players.getValue().get(0).getCardCount()).isEqualTo(4);
    }
}
