package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.PlayerHand;
import blackjack.domain.Players;
import blackjack.domain.Wallet;
import blackjack.factory.SingDeckGenerator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackProcessManagerTest {

    BlackjackProcessManager blackjackProcessManager;
    Deck deck;

    @BeforeEach
    void init() {
        SingDeckGenerator singDeckGenerator = new SingDeckGenerator();
        blackjackProcessManager = new BlackjackProcessManager(singDeckGenerator);
        deck = blackjackProcessManager.generateDeck();
    }

    @DisplayName("처음에 플레이어에게 카드 2장 쥐어준다.")
    @Test
    void test1() {
        // given
        Hand hand = new Hand();
        PlayerHand playerHand = new PlayerHand(hand, Wallet.create());
        Player player = new Player("꾹이", playerHand);

        // when
        blackjackProcessManager.giveStartingCardsFor(deck, player);

        // then
        assertThat(hand.getAllCards()).hasSize(2);
    }

    @DisplayName("손에 카드 1장을 쥐어준다")
    @Test
    void test2() {
        Hand hand = new Hand();
        Dealer dealer = new Dealer(hand);
        // when
        blackjackProcessManager.giveCard(deck, dealer);

        // then
        assertThat(hand.getAllCards()).hasSize(1);
    }

    @DisplayName("덱을 설정한다.")
    @Test
    void test7() {
        // when & then
        assertThatCode(blackjackProcessManager::generateDeck)
                .doesNotThrowAnyException();
    }

    @DisplayName("이름들을 입력받아서 저장한다.")
    @Test
    void test9() {
        // given
        List<String> names = List.of("꾹이", "히로", "비타");

        // when
        Players players = blackjackProcessManager.generatePlayers(names);

        // the
        assertThat(players.getPlayers()).hasSize(3);
    }

    @DisplayName("딜러를 반환한다.")
    @Test
    void test10() {

        // when & then
        assertThatCode(blackjackProcessManager::generateDealer)
                .doesNotThrowAnyException();
    }
}
