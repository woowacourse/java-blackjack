package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("초기 분배 시 플레이어에게 두 장의 카드가 분배된다.")
    @Test
    void distributeCardsSuccessfully() {
        Players players = Players.makePlayers(List.of("pobi"));
        Dealer dealer = Dealer.from();
        Participants participants = new Participants(players, dealer);
        PlayingCards deck = PlayingCards.createShuffledDeck();

        participants.distributeCards(deck);
        List<Player> allPlayers = players.getAllPlayers();
        List<String> playerCards = Arrays.stream(allPlayers.getFirst().getCardStatus().split(",")).toList();

        assertThat(playerCards).hasSize(2);
    }

    @DisplayName("덱에 남은 카드가 부족할 때 초기 분배를 시도하면 예외가 발생한다.")
    @Test
    void distributeCardsThrowsExceptionOnShortDeck() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        Participants participants = new Participants(players, Dealer.from());
        PlayingCards shortDeck = PlayingCards.from(List.of(
            new Card(Rank.TWO, Suit.SPADE),
            new Card(Rank.THREE, Suit.SPADE)
        ));

        assertThatThrownBy(() -> participants.distributeCards(shortDeck))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("뽑을 카드가 남아있지 않습니다.");
    }

    @DisplayName("딜러가 카드를 더 받아야 하는 상황인지 정확히 판단한다.")
    @Test
    void isDealerDrawWorksCorrectly() {
        Players players = Players.makePlayers(List.of("pobi"));
        Participants participants = new Participants(players, Dealer.from());
        PlayingCards dealerCards16 = PlayingCards.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SIX, Suit.HEART)
        ));

        participants.dealerDraw(dealerCards16);

        assertThat(participants.isDealerDraw()).isTrue();
    }
}
