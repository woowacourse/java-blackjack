package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.ShuffledDeck;
import domain.card.Suit;
import domain.user.AllWinningAmountDto;
import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import domain.user.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("모든 참가자에게 초기 카드를 지급한다.")
    @Test
    void 초기_카드_지급() {
        // given
        Map<String, Integer> playerBettingMoneyTable = new LinkedHashMap<>() {{
            put("Player1", 1_000);
            put("Player2", 2_000);
        }};
        Participants participants = Participants.from(playerBettingMoneyTable);
        participants.drawInitialCardsEachParticipant(ShuffledDeck.createByCount(6));
        // when
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        // then
        assertThat(dealer.getCards()).hasSize(2);
        players.forEach(player -> assertThat(player.getCards()).hasSize(2));
    }

    @DisplayName("모든 플레이어의 결과를 반환한다.")
    @Test
    void 결과_계산() {
        Map<String, Integer> playerBettingMoneyTable = new LinkedHashMap<>() {{
            put("Player1", 1_000);
            put("Player2", 2_000);
        }};
        Participants participants = Participants.from(playerBettingMoneyTable);
        participants.drawInitialCardsEachParticipant(ShuffledDeck.createByCount(6));

        AllWinningAmountDto allWinningAmountDto = participants.calculateWinningAmountOfAllPlayers();
        List<Player> players = participants.getPlayers();

        players.forEach(player ->
                assertThat(allWinningAmountDto.getPlayerNameWinningAmounts().get(player.getNameValue())).isInstanceOf(Integer.class));
    }

    @DisplayName("딜러의 점수가 16 이하이면 hit 한다.")
    @Test
    void 딜러_히트() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        Deck determinedDeck = DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.ACE), // 딜러가 hit할 카드
                new Card(Suit.CLOVER, Rank.FOUR), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.TEN), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.SIX) // 딜러에게 주어지는 카드 1
        ));
        participants.drawInitialCardsEachParticipant(determinedDeck);
        // when
        participants.hitOrStayByDealer(determinedDeck);
        Dealer dealer = participants.getDealer();
        // then
        assertThat(dealer.calculateScore()).isEqualTo(17);
    }

    @DisplayName("딜러의 점수가 17 이상이면 stay 한다.")
    @Test
    void 딜러_스테이() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        Deck determinedDeck = DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.ACE), // 딜러가 hit할 카드
                new Card(Suit.CLOVER, Rank.FOUR), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.TEN), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.SEVEN) // 딜러에게 주어지는 카드 1
        ));
        participants.drawInitialCardsEachParticipant(determinedDeck);
        // when
        participants.hitOrStayByDealer(determinedDeck);
        Dealer dealer = participants.getDealer();
        // then
        assertThat(dealer.calculateScore()).isEqualTo(17);
    }

    @DisplayName("딜러의 점수가 플레이어의 점수보다 높으면 플레이어는 베팅 금액만큼의 돈을 잃는다.")
    @Test
    void 플레이어_패배() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.FIVE), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FOUR), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.FIVE), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE) // 딜러에게 주어지는 카드 1
        )));
        // when
        AllWinningAmountDto allWinningAmountDto = participants.calculateWinningAmountOfAllPlayers();
        // then
        assertThat(allWinningAmountDto.getPlayerNameWinningAmounts().get(player.getNameValue())).isEqualTo(-1_000);
    }

    @DisplayName("플레이어가 bust이면 베팅 금액만큼의 돈을 잃는다.")
    @Test
    void 플레이어_패배_버스트() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.KING), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.KING), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.FIVE), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE) // 딜러에게 주어지는 카드 1
        )));
        player.addCard(new Card(Suit.CLOVER, Rank.TWO));
        // when
        AllWinningAmountDto allWinningAmountDto = participants.calculateWinningAmountOfAllPlayers();
        // then
        assertThat(allWinningAmountDto.getPlayerNameWinningAmounts().get(player.getNameValue())).isEqualTo(-1_000);
    }

    @DisplayName("플레이어가 딜러보다 점수가 높으면 베팅 금액만큼의 돈을 얻는다.")
    @Test
    void 플레이어_승리() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.KING), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.KING), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.FIVE), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE) // 딜러에게 주어지는 카드 1
        )));
        // when
        AllWinningAmountDto allWinningAmountDto = participants.calculateWinningAmountOfAllPlayers();
        // then
        assertThat(allWinningAmountDto.getPlayerNameWinningAmounts().get(player.getNameValue())).isEqualTo(1_000);
    }

    @DisplayName("플레이어와 딜러가 같은 점수이면 베팅 금액을 돌려받는다.")
    @Test
    void 플레이어_무승부() {
        // given
        Player player = Player.of("Player", 1_000);
        Participants participants = new Participants(new Players(List.of(player)), new Dealer());
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.FIVE), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.FIVE), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.FIVE) // 딜러에게 주어지는 카드 1
        )));
        // when
        AllWinningAmountDto allWinningAmountDto = participants.calculateWinningAmountOfAllPlayers();
        // then
        assertThat(allWinningAmountDto.getPlayerNameWinningAmounts().get(player.getNameValue())).isEqualTo(0);
    }

    @DisplayName("딜러만 bust이면 플레이어는 베팅 금액만큼의 돈을 얻는다.")
    @Test
    void 플레이어_승리_딜러_버스트() {
        // given
        Player player = Player.of("Player", 1_000);
        Dealer dealer = new Dealer();
        Participants participants = new Participants(new Players(List.of(player)), dealer);
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.KING), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.KING), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.KING), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.SIX) // 딜러에게 주어지는 카드 1
        )));
        dealer.addCard(new Card(Suit.CLOVER, Rank.TWO));
        // when
        AllWinningAmountDto allWinningAmountDto = participants.calculateWinningAmountOfAllPlayers();
        // then
        assertThat(allWinningAmountDto.getPlayerNameWinningAmounts().get(player.getNameValue())).isEqualTo(1_000);
    }

    @DisplayName("플레이어만 블랙잭이면 베팅 금액의 1.5배에 해당하는 금액을 딜러에게 받는다.")
    @Test
    void 플레이어_블랙잭() {
        // given
        Player player = Player.of("Player", 1_000);
        Dealer dealer = new Dealer();
        Participants participants = new Participants(new Players(List.of(player)), dealer);
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.ACE), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.KING), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.KING), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.SIX) // 딜러에게 주어지는 카드 1
        )));
        // when
        AllWinningAmountDto allWinningAmountDto = participants.calculateWinningAmountOfAllPlayers();
        // then
        assertThat(allWinningAmountDto.getPlayerNameWinningAmounts().get(player.getNameValue())).isEqualTo(1_500);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 플레이어는 베팅 금액을 돌려받는다.")
    @Test
    void 플레이어_딜러_블랙잭() {
        // given
        Player player = Player.of("Player", 1_000);
        Dealer dealer = new Dealer();
        Participants participants = new Participants(new Players(List.of(player)), dealer);
        participants.drawInitialCardsEachParticipant(DeterminedDeck.from(List.of(
                new Card(Suit.CLOVER, Rank.ACE), // Player에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.KING), // Player에게 주어지는 카드 1
                new Card(Suit.CLOVER, Rank.ACE), // 딜러에게 주어지는 카드 2
                new Card(Suit.CLOVER, Rank.KING) // 딜러에게 주어지는 카드 1
        )));
        // when
        AllWinningAmountDto allWinningAmountDto = participants.calculateWinningAmountOfAllPlayers();
        // then
        assertThat(allWinningAmountDto.getPlayerNameWinningAmounts().get(player.getNameValue())).isEqualTo(0);
    }
}
