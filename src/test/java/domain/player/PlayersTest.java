package domain.player;

import domain.card.Card;
import domain.card.CardHolder;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {
    @Test
    @DisplayName("딜러에게 특정한 카드를 줄 수 있다.")
    void whenGivingCardToDealer() {
        Dealer dealer = new Dealer(CardHolder.makeEmptyHolder());
        Players players = new Players(dealer, Collections.emptyList());

        Card card = new Card(Shape.SPADE, Number.FOUR);
        players.giveCardByName(dealer.getName(), card);

        assertThat(dealer.getCards()).contains(card);
    }

    @Test
    @DisplayName("딜러가 가진 점수가 17점 이상이면 카드를 더 가질 수 없다.")
    void givenDealerOrMore17_thenDealerShouldNotGetMoreCard() {
        Dealer dealer = new Dealer(
                new CardHolder(List.of(
                        new Card(Shape.HEART, Number.TEN),
                        new Card(Shape.DIAMOND, Number.SEVEN)
                )));
        Players players = new Players(dealer, Collections.emptyList());

        assertThat(players.shouldDealerGetCard()).isFalse();
    }

    @Test
    @DisplayName("딜러가 가진 점수가 16점 미만이면 카드를 더 가져야 한다.")
    void givenDealerOrLess16_thenDealerShouldGetMoreCard() {
        Dealer dealer = new Dealer(
                new CardHolder(List.of(
                        new Card(Shape.HEART, Number.TEN),
                        new Card(Shape.DIAMOND, Number.FIVE)
                )));
        Players players = new Players(dealer, Collections.emptyList());

        assertThat(players.shouldDealerGetCard()).isTrue();
    }

    @Test
    @DisplayName("주어진 이름에 해당하는 플레이어를 찾을 수 있다.")
    void givenName_thenReturnsPlayer() {
        Participant participant = new Participant(CardHolder.makeEmptyHolder(), Name.of("테스트"));
        Players players = new Players(new Dealer(CardHolder.makeEmptyHolder()), List.of(participant));

        Player findPlayer = players.findByName("테스트");
        assertThat(findPlayer).isEqualTo(participant);
    }

    @Test
    @DisplayName("주어진 이름에 해당하는 플레이어를 찾을 수 있다.")
    void givenInvalidName_thenThrowsException() {
        Participant participant = new Participant(CardHolder.makeEmptyHolder(), Name.of("테스트"));
        Players players = new Players(new Dealer(CardHolder.makeEmptyHolder()), List.of(participant));

        assertThatThrownBy(() -> players.findByName("없는회원"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}