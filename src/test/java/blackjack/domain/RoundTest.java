/*
package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.AbstractUser;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

class RoundTest {
    private final List<AbstractUser> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        AbstractUser dealer = new Dealer();

        dealer.addFirstCards(Arrays.asList(
                Card.of("스페이드", "10"),
                Card.of("하트", "4")
        ));
        users.add(dealer);

        AbstractUser player = new Player("pobi");
        player.addFirstCards(Arrays.asList(
                Card.of("스페이드", "10"),
                Card.of("하트", "5")
        ));
        users.add(player);

        AbstractUser player2 = new Player("jason");
        player2.addFirstCards(Arrays.asList(
                Card.of("스페이드", "2"),
                Card.of("하트", "3")
        ));
        users.add(player2);
    }

    @DisplayName("승패 결과 테스트")
    @Test
    void result_generate_test() {
        Deck deck = Deck.generateByRandomCard();
        Round round = new Round(deck, new Users(users));

        Map<String, Queue<Outcome>> results = round.findResults();

        Queue<Outcome> firstPlayerOutcomes = results.get(round.getPlayers().get(0).getName());
        Queue<Outcome> secondPlayerOutcomes = results.get(round.getPlayers().get(1).getName());

        Assertions.assertThat(firstPlayerOutcomes.poll()).isEqualTo(Outcome.WIN);
        Assertions.assertThat(secondPlayerOutcomes.poll()).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러가 버스터 일때 승패 체크 테스트")
    @Test
    void result_buster_test() {

        Deck deck = Deck.generateByRandomCard();
        Round round = new Round(deck, new Users(users));
        round.getDealer().addCard(Card.of("스페이드", "10"));
        round.getPlayers().get(0).addCard(Card.of("스페이드", "9"));

        Map<String, Queue<Outcome>> results = round.findResults();

        Queue<Outcome> firstPlayerOutcomes = results.get(round.getPlayers().get(0).getName());      //딜러 24, 플레이어 24, 딜러 승,  플레이어 패
        Queue<Outcome> secondPlayerOutcomes = results.get(round.getPlayers().get(1).getName());    //딜러 24, 플레이어 5, 딜러 패 플레이어 승

        Assertions.assertThat(firstPlayerOutcomes.poll()).isEqualTo(Outcome.LOSE);
        Assertions.assertThat(secondPlayerOutcomes.poll()).isEqualTo(Outcome.WIN);
    }
}
*/
