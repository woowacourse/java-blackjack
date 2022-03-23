package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.bet.BetMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.deck.OnlyTenSpadePickDeck;
import blackjack.domain.dto.ResponseCardResultDto;
import blackjack.domain.dto.ResponseInitHandDto;
import blackjack.domain.dto.ResponseProfitDto;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.domain.user.UserName;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    @Test
    @DisplayName("게임에 참여할 사람들의 이름을 입력받으면 블랙잭 게임을 시작한다.")
    void start() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        Players players = blackjackGame.getPlayers();

        assertThat(players.hasPlayerName("pobi")).isTrue();
        assertThat(players.hasPlayerName("jason")).isTrue();
    }

    @Test
    @DisplayName("takeInitHand 메서드는 딜러와 플레이어에게 카드 2장씩을 나눠준다.")
    void take_init_hand() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        blackjackGame.takeInitHand(new OnlyTenSpadePickDeck());
        ResponseInitHandDto initHandDto
                = new ResponseInitHandDto(blackjackGame.getDealer(), blackjackGame.getPlayers());

        assertThat(initHandDto.getDealer().getCards().get()).hasSize(2);
        assertThat(initHandDto.getPlayers().get().get(0).getCards().get()).hasSize(2);
        assertThat(initHandDto.getPlayers().get().get(1).getCards().get()).hasSize(2);
    }

    @Test
    @DisplayName("takePlayerCard 메서드는 플레이어가 한장의 카드를 뽑게 한다.")
    void take_player_card() {
        BlackjackGame blackjackGame = new BlackjackGame();
        User player = new Player(new UserName("aki"));
        blackjackGame.takePlayerCard(player, new OnlyTenSpadePickDeck());

        assertThat(player.getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("takeDealerCard 메서드는 딜러가 한장의 카드를 뽑게 한다.")
    void take_dealer_card() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        blackjackGame.takeDealerCard(new OnlyTenSpadePickDeck());
        User dealer = blackjackGame.getDealer();

        assertThat(dealer.getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("calculateCardResult 메서드는 현재 유저들의 카드 결과를 계산한다.")
    void calculate_card_result() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        Players players = blackjackGame.getPlayers();
        players.get()
                .stream()
                .forEach(player -> blackjackGame.takePlayerCard(player, new OnlyTenSpadePickDeck()));
        ResponseCardResultDto cardResultDto = blackjackGame.calculateCardResult();

        assertThat(cardResultDto.getCardResult().get("pobi").getTotalScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("calculateProfit 메서드는 최종 수익을 계산한다.")
    void calculate_profit() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi"));
        Players players = blackjackGame.getPlayers();
        players.get()
                .stream()
                .forEach(player -> blackjackGame.takePlayerCard(player, new OnlyTenSpadePickDeck()));
        Map<String, BetMoney> playerNameAndBets = new LinkedHashMap<>();
        playerNameAndBets.put("pobi", new BetMoney("10000"));
        ResponseProfitDto profitDto = blackjackGame.calculateProfit(playerNameAndBets);

        assertThat(profitDto.getDealerProfit()).isEqualTo(-10000);
        assertThat(profitDto.getPlayersProfit().get("pobi")).isEqualTo(10000);
    }

    @Test
    @DisplayName("isPlayerFinished 메서드는 플레이어의 카드의 총합이 21 이상이면 끝났다고 판단한다.")
    void is_player_finished_true() {
        BlackjackGame blackjackGame = new BlackjackGame();
        User player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.ACE, CardType.DIAMOND));
        player.hit(Card.of(CardNumber.TEN, CardType.HEART));

        assertThat(blackjackGame.isPlayerFinished(player)).isTrue();
    }

    @Test
    @DisplayName("isPlayerFinished 메서드는 플레이어의 카드의 총합이 20 이하면 끝나지 않았다고 판단한다.")
    void is_player_finished_false() {
        BlackjackGame blackjackGame = new BlackjackGame();
        User player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));
        player.hit(Card.of(CardNumber.TEN, CardType.HEART));

        assertThat(blackjackGame.isPlayerFinished(player)).isFalse();
    }

    @Test
    @DisplayName("isDealerFinished 메서드는 딜러의 카드의 총합이 17 이상이면 끝났다고 판단한다.")
    void is_dealer_finished_true() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));

        blackjackGame.takeDealerCard(new OnlyTenSpadePickDeck());
        blackjackGame.takeDealerCard(new OnlyTenSpadePickDeck());

        assertThat(blackjackGame.isDealerFinished()).isTrue();
    }

    @Test
    @DisplayName("isDealerFinished 메서드는 딜러의 카드의 총합이 16 이하면 끝나지 않았다고 판단한다.")
    void is_dealer_finished_false() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.start(List.of("pobi", "jason"));
        blackjackGame.takeDealerCard(new OnlyTenSpadePickDeck());

        assertThat(blackjackGame.isDealerFinished()).isFalse();
    }
}
