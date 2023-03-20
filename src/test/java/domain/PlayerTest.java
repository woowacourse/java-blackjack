package domain;

import domain.card.*;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {

    @Test
    @DisplayName("이름, 카드, 배팅액을 입력하면 플레이어 객체가 정상적으로 생성된다")
    void generatePlayer() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money = new Money(1000);

        assertDoesNotThrow(() -> new Player(name, cards, money));
    }

    @Test
    @DisplayName("보유하고 있는 초기 카드 2장의 합이 21일 경우 true를 리턴한다")
    void IsBlackjack() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.KING)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("보유하고 있는 초기 카드 2장의 합이 21이 아닐 경우 false를 리턴한다")
    void IsNotBlackjack() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21미만이면 true를 리턴한다")
    void canReceiveOneMoreCard() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertThat(player.canReceiveOneMoreCard()).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이상이면 true를 리턴한다")
    void canNotReceiveOneMoreCard() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TEN)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertThat(player.canReceiveOneMoreCard()).isFalse();
    }

    @Test
    @DisplayName("기존의 카드 그룹에 새로운 카드를 추가하면 카드의 개수가 1만큼 증가한다")
    void pickCard() {
        Name name = new Name("roy");
        Cards cards = new Cards(new ArrayList<>(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TEN))));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);
        CardDeck cardDeck = new CardDeck();
        int expectedCardSize = 3;

        player.pickCard(cardDeck);

        assertThat(player.getCards().size()).isEqualTo(expectedCardSize);
    }
}
