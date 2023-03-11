package service;

import static factory.DrawnCardsFactory.createBlackjackCards;
import static factory.DrawnCardsFactory.createEmptyCards;
import static factory.DrawnCardsFactory.createOverCards;
import static factory.DrawnCardsFactory.createStayCards;
import static factory.PlayerFactory.createPlayerWithBlackjackCards;
import static factory.PlayerFactory.createPlayerWithEmptyCards;
import static factory.PlayerFactory.createPlayerWithOverCards;
import static factory.PlayerFactory.createPlayerWithStayCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.card.CardDeck;
import domain.card.DrawnCards;
import domain.command.DrawCommand;
import domain.message.ExceptionMessage;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;
import generator.CardDeckGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    @DisplayName("플레이어와 딜러에게 카드를 2장씩 나눠준다.")
    void split_cards_init_to_participants() {
        // given
        Player player = createPlayerWithEmptyCards("choonsik");
        Players players = new Players(List.of(player));
        Dealer dealer = new Dealer(createEmptyCards());

        // when
        calculatorService.splitCards(players, dealer, CardDeckGenerator.create());

        // then
        assertSoftly(softly -> {
            softly.assertThat(player.getDrawnCards().size()).isEqualTo(2);
            softly.assertThat(dealer.getDrawnCards().size()).isEqualTo(2);
        });
    }

    @Test
    @DisplayName("플레이어가 카드를 더 뽑겠냐는 질문에 stop을 원하면 false를 반환한다.")
    void returns_false_when_player_do_not_want_more_card() {
        // given
        Player player = createPlayerWithStayCards("choonsik");
        DrawCommand drawCommand = new DrawCommand(ExceptionMessage.STOP_COMMAND.getMessage());

        // when
        boolean result = calculatorService.canDrawMore(player, drawCommand);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어가 카드를 더 뽑고 싶어하고, 플레이어의 카드 총 합이 버스트 넘버를 초과하지 않는다면 true를 반환한다.")
    void returns_true_when_player_want_more_card_and_not_bust() {
        // given
        Player player = createPlayerWithStayCards("choonsik");
        DrawCommand drawCommand = new DrawCommand(ExceptionMessage.DRAW_COMMAND.getMessage());

        // when
        boolean result = calculatorService.canDrawMore(player, drawCommand);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드 총합이 16이하라면 카드를 뽑는다.")
    void dealer_draws_card_when_cards_sum_under_boundary() {
        // given
        CardDeck cardDeck = CardDeckGenerator.create();
        DrawnCards drawnCards = createStayCards();
        int sumBeforeDrawn = drawnCards.calculateScore();
        Dealer dealer = new Dealer(drawnCards);

        // when
        calculatorService.pickDealerCard(cardDeck, dealer);

        // then
        assertThat(dealer.calculateCardScore() > sumBeforeDrawn).isTrue();
    }

    @Test
    @DisplayName("딜러가 뽑은 카드의 총합이 16이하라면 true를 반환한다.")
    void returns_true_when_dealer_cards_sum_under_boundary() {
        // given
        Dealer dealer = new Dealer(createStayCards());

        // when
        boolean result = calculatorService.canDealerDrawMore(dealer);

        // then
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("딜러, 플레이어 둘다 BUST라면, 딜러의 승리이다.")
    void calculate_account_if_dealer_and_player_bust_dealer_win() {
        // given
        Dealer dealer = new Dealer(createOverCards());
        Player player = createPlayerWithOverCards("choonsik");
        Players players = new Players(List.of(player));
        int playerAccount = player.getAccount();

        // when
        calculatorService.calculateGameResults(players, dealer);

        // then
        assertSoftly(softly -> {
            softly.assertThat(player.getAccount()).isEqualTo(-playerAccount);
            softly.assertThat(dealer.getAccount()).isEqualTo(playerAccount);
        });
    }

    @Test
    @DisplayName("딜러, 플레이어 둘다 블랙잭이라면, 플레이어는 배팅금을 돌려받는다.")
    void calculate_account_if_dealer_and_player_black_jack() {
        // given
        Dealer dealer = new Dealer(createBlackjackCards());
        Player player = createPlayerWithBlackjackCards("choonsik");
        Players players = new Players(List.of(player));
        int playerAccount = player.getAccount();
        int dealerAccount = dealer.getAccount();

        // when
        calculatorService.calculateGameResults(players, dealer);

        // then
        assertSoftly(softly -> {
            softly.assertThat(player.getAccount()).isEqualTo(playerAccount);
            softly.assertThat(dealer.getAccount()).isEqualTo(dealerAccount);
        });
    }

    @Test
    @DisplayName("딜러, 플레이어 둘다 BUST가 아니고, 플레이어의 점수가 더 높은 경우 플레이어가 상금을 탄다.")
    void calculate_account_if_dealer_and_player_not_bust_player_win() {
        // given
        Dealer dealer = new Dealer(createEmptyCards());
        Player player = createPlayerWithStayCards("choonsik");
        Players players = new Players(List.of(player));
        int playerAccount = player.getAccount();

        // when
        calculatorService.calculateGameResults(players, dealer);

        // then
        assertThat(player.getAccount() > playerAccount).isTrue();
    }

    @Test
    @DisplayName("딜러, 플레이어 둘다 BUST가 아니고, 딜러의 점수가 더 높은 경우 딜러가 상금을 탄다.")
    void calculate_account_if_dealer_and_player_not_bust_dealer_win() {
        // given
        Dealer dealer = new Dealer(createStayCards());
        Player player = createPlayerWithEmptyCards("jay");
        Players players = new Players(List.of(player));
        int playerAccount = player.getAccount();

        // when
        calculatorService.calculateGameResults(players, dealer);

        // then
        assertSoftly(softly -> {
            softly.assertThat(player.getAccount()).isEqualTo(playerAccount * -1);
            softly.assertThat(dealer.getAccount()).isEqualTo(playerAccount);
        });
    }

    @Test
    @DisplayName("딜러가 파산이고, 플레이어는 파산이 아닌 경우 플레이어가 상금을 탄다.")
    void calculate_account_if_dealer_bust_and_player_not_bust_player_win() {
        // given
        Dealer dealer = new Dealer(createOverCards());
        Player player = createPlayerWithStayCards("choonsik");
        Players players = new Players(List.of(player));
        int playerBettingAccount = player.getAccount();
        int dealerAccount = dealer.getAccount();

        // when
        calculatorService.calculateGameResults(players, dealer);

        // then
        assertSoftly(softly -> {
            softly.assertThat(player.getAccount() > playerBettingAccount).isTrue();
            softly.assertThat(dealerAccount > dealer.getAccount()).isTrue();
        });
    }

    @Test
    @DisplayName("딜러가 파산이 아니고, 플레이어는 파산인 경우 딜러가 상금을 탄다.")
    void calculate_account_if_dealer_not_bust_and_player_bust_dealer_win() {
        // given
        Dealer dealer = new Dealer(createStayCards());
        Player player = createPlayerWithOverCards("choonsik");
        Players players = new Players(List.of(player));
        int playerBettingAccount = player.getAccount();
        int dealerInitAccount = dealer.getAccount();

        // when
        calculatorService.calculateGameResults(players, dealer);

        // then
        assertSoftly(softly -> {
            softly.assertThat(player.getAccount()).isEqualTo(-playerBettingAccount);
            softly.assertThat(dealer.getAccount() > dealerInitAccount).isTrue();
        });
    }
}
