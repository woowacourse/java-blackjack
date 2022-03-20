package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.deck.OnlyTenSpadePickDeck;
import blackjack.domain.dto.ResponseCardResultDto;
import blackjack.domain.dto.ResponseInitHandDto;
import blackjack.domain.dto.ResponseOutcomeDto;
import blackjack.domain.outcome.Outcome;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.UserName;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    @Test
    @DisplayName("게임에 참여할 사람들의 이름을 입력받으면 블랙잭 게임을 시작한다.")
    void start() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Players players = blackjackGame.start(List.of("pobi", "jason"));

        assertThat(players.hasPlayerName("pobi")).isTrue();
        assertThat(players.hasPlayerName("jason")).isTrue();
    }

    @Test
    @DisplayName("takeInitHand 메서드는 딜러와 플레이어에게 카드 2장씩을 나눠준다.")
    void take_init_hand() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        ResponseInitHandDto initHandDto = blackjackGame.takeInitHand(new OnlyTenSpadePickDeck());

        assertThat(initHandDto.getDealer().getCards().get()).hasSize(2);
        assertThat(initHandDto.getPlayers().get().get(0).getCards().get()).hasSize(2);
        assertThat(initHandDto.getPlayers().get().get(1).getCards().get()).hasSize(2);
    }

    @Test
    @DisplayName("takePlayerCard 메서드는 플레이어가 한장의 카드를 뽑게 한다.")
    void take_player_card() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player(new UserName("aki"));
        blackjackGame.takePlayerCard(player, new OnlyTenSpadePickDeck());

        assertThat(player.getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("takeDealerCard 메서드는 딜러가 한장의 카드를 뽑게 한다.")
    void take_dealer_card() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        Dealer dealer = blackjackGame.takeDealerCard(new OnlyTenSpadePickDeck());

        assertThat(dealer.getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("calculateCardResult 메서드는 현재 유저들의 카드 결과를 계산한다.")
    void calculate_card_result() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Players players = blackjackGame.start(List.of("pobi", "jason"));
        players.get()
                .stream()
                .forEach(player -> blackjackGame.takePlayerCard(player, new OnlyTenSpadePickDeck()));
        ResponseCardResultDto cardResultDto = blackjackGame.calculateCardResult();

        assertThat(cardResultDto.getCardResult().get("pobi").getTotalScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("calculateOutcome 메서드는 경기 결과를 계산한다.")
    void calculate_outcome() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Players players = blackjackGame.start(List.of("pobi"));
        players.get()
                .stream()
                .forEach(player -> blackjackGame.takePlayerCard(player, new OnlyTenSpadePickDeck()));
        ResponseOutcomeDto outcomeDto = blackjackGame.calculateOutcome();
        Map<Player, Outcome> playerOutcomes = outcomeDto.getPlayerOutcomes();

        assertThat(playerOutcomes.values()).contains(Outcome.WIN);
    }

    @Test
    @DisplayName("isPlayerFinished 메서드는 플레이어의 카드의 총합이 21 이상이면 끝났다고 판단한다.")
    void is_player_finished_true() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.ACE, CardType.DIAMOND));
        player.hit(Card.of(CardNumber.TEN, CardType.HEART));

        assertThat(blackjackGame.isPlayerFinished(player)).isTrue();
    }

    @Test
    @DisplayName("isPlayerFinished 메서드는 플레이어의 카드의 총합이 20 이하면 끝나지 않았다고 판단한다.")
    void is_player_finished_false() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));
        player.hit(Card.of(CardNumber.TEN, CardType.HEART));

        assertThat(blackjackGame.isPlayerFinished(player)).isFalse();
    }

    @Test
    @DisplayName("isDealerFinished 메서드는 딜러의 카드의 총합이 17 이상이면 끝났다고 판단한다.")
    void is_dealer_finished_true() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        Dealer dealer = blackjackGame.takeDealerCard(new OnlyTenSpadePickDeck());
        dealer.hit(Card.of(CardNumber.SEVEN, CardType.DIAMOND));

        assertThat(blackjackGame.isDealerFinished()).isTrue();
    }

    @Test
    @DisplayName("isDealerFinished 메서드는 딜러의 카드의 총합이 16 이하면 끝나지 않았다고 판단한다.")
    void is_dealer_finished_false() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        Dealer dealer = blackjackGame.takeDealerCard(new OnlyTenSpadePickDeck());
        dealer.hit(Card.of(CardNumber.SIX, CardType.DIAMOND));

        assertThat(blackjackGame.isDealerFinished()).isFalse();
    }
}
