package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.AlwaysDescNumberMachine;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players(List.of("eden"));
        players.hasNextGuest();
    }

    @Test
    @DisplayName("Players 객체 생성 후 참가자 인원 확인")
    void createPlayers() {
        assertThat(players.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자가 1명일 때 다음 참가자가 있는지 확인")
    void checkExistGuest() {
        assertThat(players.hasNextGuest()).isTrue();
    }

    @Test
    @DisplayName("받은 카드가 두 장인지 확인")
    void checkStartWithTwoCards() {
        Deck deck = new Deck(new AlwaysDescNumberMachine());
        players.startWithTwoCards(deck);
        Cards dealerCards = players.getDealer().getDeck();

        Cards expectedCards = new Cards();
        expectedCards.addCard(new Card(Suit.CLUB, Rank.KING));
        expectedCards.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        assertThat(dealerCards).isEqualTo(expectedCards);
    }

    @Test
    @DisplayName("현재 턴의 플레이어 확인")
    void checkTurnPlayer() {
        Player turnPlayer = players.getTurnPlayer();

        assertThat(turnPlayer.getName()).isEqualTo("eden");
    }

    @Test
    @DisplayName("딜러 호출시 받는 플레이어가 딜러인지 확인")
    void checkDealer() {
        Player dealer = players.getDealer();

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("턴을 다음으로 돌리면 게스트가 더 있는지 확인")
    void checkNextGuest() {
        players.turnNextGuest();

        assertThat(players.hasNextGuest()).isFalse();
    }
}
