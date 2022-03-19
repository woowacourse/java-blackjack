package blackjack.domain.player;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.bet.BetMoney;
import blackjack.domain.card.Deck;
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
}
