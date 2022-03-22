package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.bet.BetMoney;
import blackjack.domain.card.Deck;
import blackjack.domain.card.JustTenSpadeDeck;
import blackjack.domain.card.RandomDeck;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("Players 클래스는 participant들을 입력받으면 정상적으로 생성된다.")
    void create_players() {
        Deck deck = new RandomDeck();
        Player aki = new Participant(new Name("aki"), deck, new BetMoney(10));
        Player alien = new Participant(new Name("alien"), deck, new BetMoney(10));
        List<Player> players = List.of(aki, alien);

        assertThatCode(() -> new Players(players, deck)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러를 찾아서 반환한다.")
    void get_dealer() {
        Deck deck = new RandomDeck();
        Player aki = new Participant(new Name("aki"), deck, new BetMoney(10));
        Player alien = new Participant(new Name("alien"), deck, new BetMoney(10));
        List<Player> akiAndAlien = List.of(aki, alien);
        Players players = new Players(akiAndAlien, deck);

        Player dealer = players.getDealer();

        assertThat(dealer).isInstanceOf(Dealer.class);
        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    @DisplayName("참가자를 List로 반환한다.")
    void get_participants() {
        Deck deck = new RandomDeck();
        Player aki = new Participant(new Name("aki"), deck, new BetMoney(10));
        Player alien = new Participant(new Name("alien"), deck, new BetMoney(10));
        List<Player> akiAndAlien = List.of(aki, alien);
        Players players = new Players(akiAndAlien, deck);

        List<Player> participants = players.getParticipants();

        assertThat(participants).allSatisfy(player -> {
            assertThat(player).isInstanceOf(Participant.class);
            assertThat(player.isDealer()).isFalse();
        });
    }

    @Test
    @DisplayName("Participant의 이름이 중복되면 에러를 출력한다.")
    void duplicate_name_error() {
        Deck deck = new RandomDeck();
        Player aki1 = new Participant(new Name("aki"), deck, new BetMoney(10));
        Player aki2 = new Participant(new Name("aki"), deck, new BetMoney(10));

        assertThatThrownBy(() -> new Players(List.of(aki1, aki2), deck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Participant의 이름이 딜러면 에러를 출력한다.")
    void same_dealer_name_error() {
        Deck deck = new RandomDeck();
        Player player = new Participant(new Name("딜러"), deck, new BetMoney(10));

        assertThatThrownBy(() -> new Players(List.of(player), deck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Players를 null로 생성을 시도하면 에러를 출력한다.")
    void create_by_players_null_error() {
        Deck deck = new RandomDeck();

        assertThatThrownBy(() -> new Players(null, deck))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Deck을 null로 생성을 시도하면 에러를 출력한다.")
    void create_by_deck_null_error() {
        Deck deck = new RandomDeck();
        Player alien = new Participant(new Name("alien"), deck, new BetMoney(10));

        assertThatThrownBy(() -> new Players(List.of(alien), null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Participant가 0명이면 에러를 출력한다.")
    void participant_empty_error() {
        Deck deck = new RandomDeck();

        assertThatThrownBy(() -> new Players(new ArrayList<>(), deck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("equals, hashCode, toString 테스트")
    void equals() {
        Deck deck = new JustTenSpadeDeck();
        Player p1 = new Participant(new Name("aki"), deck, new BetMoney(10));
        Player p2 = new Participant(new Name("alien"), deck, new BetMoney(10));
        Players o1 = new Players(List.of(p1, p2), deck);
        Players o2 = new Players(List.of(p1, p2), deck);
        Object o = new Object();

        assertThat(o1.equals(o2)).isTrue();
        assertThat(o1.equals(o1)).isTrue();
        assertThat(o1.equals(o)).isFalse();
        assertThat(o1.hashCode() == o2.hashCode()).isTrue();
        assertThat(o1.toString()).isEqualTo(o2.toString());
    }
}
