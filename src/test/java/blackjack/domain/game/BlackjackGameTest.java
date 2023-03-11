package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckMaker;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class BlackjackGameTest {
    private Dealer dealer;
    private Participants participants;
    private Deck deck;
    private static final DeckMaker DECK_MAKER = new DeckMaker();

    @BeforeEach
    void setting() {
        dealer = new Dealer();
        participants = new Participants(dealer, List.of("pobi", "crong"));
        deck = new Deck(DECK_MAKER.makeDeck(), (int size) -> 1);
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new BlackjackGame(deck));
    }

    @Test
    @DisplayName("모두에게 카드 두장을 나누어 주는지 테스트")
    void giveTwoCardEveryoneTest() {
        BlackjackGame blackjackGame = new BlackjackGame(deck);
        blackjackGame.giveTwoCardEveryone(participants);
        assertThat(dealer.getCards().size()).isEqualTo(2);
        participants.getPlayers().forEach(player -> {
            assertThat(player.getCards().size()).isEqualTo(2);
        });
    }

    @Test
    @DisplayName("카드를 뽑는 테스트")
    void drawCardTest() {
        BlackjackGame blackjackGame = new BlackjackGame(deck);
        blackjackGame.drawCard(dealer);

        assertThat(dealer.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 계속 히트할 수 있는 지 테스트")
    void isPlayerCanPlay() {
        BlackjackGame blackjackGame = new BlackjackGame(deck);
        Player player = participants.getPlayers().get(0);

        blackjackGame.drawCard(player);
        blackjackGame.drawCard(player);
        boolean order = true;
        int expected = player.getCards().size() + 1;
        assertThat(blackjackGame.isPlayerCanPlay(player, order)).isTrue();
        assertThat(player.getCards().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어가 버스트일때 계속 히트 할 수 없는 테스트")
    void isBustPlayerCantPlay() {
        BlackjackGame blackjackGame = new BlackjackGame(deck);
        Player player = participants.getPlayers().get(0);
        while (player.isNotBust()) {
            blackjackGame.drawCard(player);
        }
        boolean order = true;
        int expected = player.getCards().size();
        assertThat(blackjackGame.isPlayerCanPlay(player, order)).isFalse();
        assertThat(player.getCards().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어가 히트를 거절했을때")
    void isPlayerDontDraw() {
        BlackjackGame blackjackGame = new BlackjackGame(deck);
        Player player = participants.getPlayers().get(0);
        blackjackGame.drawCard(player);
        blackjackGame.drawCard(player);
        boolean order = false;
        int expected = player.getCards().size();
        assertThat(blackjackGame.isPlayerCanPlay(player, order)).isFalse();
        assertThat(player.getCards().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 16이하일때 히트하는지 테스트")
    void playDealerTest() {
        BlackjackGame blackjackGame = new BlackjackGame(deck);
        blackjackGame.drawCard(dealer);
        blackjackGame.drawCard(dealer);
        int expected = dealer.getCards().size() + 1;
        assertThat(blackjackGame.playDealer(dealer)).isTrue();
        assertThat(dealer.getCards().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 16이상일때 히트하는지 테스트")
    void cantPlayDealerTest() {
        BlackjackGame blackjackGame = new BlackjackGame(deck);
        while (!dealer.canNotHit()) {
            blackjackGame.drawCard(dealer);
        }
        int expected = dealer.getCards().size();
        assertThat(blackjackGame.playDealer(dealer)).isFalse();
        assertThat(dealer.getCards().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 파산 이후 드로우 하는지 테스트")
    void drawAfterDealerBust(){
        BlackjackGame blackjackGame = new BlackjackGame(deck);
        while(dealer.isNotBust()){
            blackjackGame.drawCard(dealer);
        }
        assertThat(blackjackGame.playDealer(dealer)).isFalse();
    }
}
