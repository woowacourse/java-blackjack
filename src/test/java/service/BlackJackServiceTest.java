package service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Account;
import domain.Card;
import domain.CardDeck;
import domain.Dealer;
import domain.DrawCommand;
import domain.DrawnCards;
import domain.Message;
import domain.Name;
import domain.Player;
import domain.Players;
import domain.Status;
import domain.Type;
import domain.Value;
import dto.DrawnCardsInfo;
import dto.ParticipantResult;
import generator.CardDeckGenerator;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackServiceTest {

    private final BlackJackService blackJackService = new BlackJackService();

    @Test
    @DisplayName("딜러와 플레이어들에게 카드를 나눠준다.")
    void split_cards_to_dealer_and_players() {
        // given
        DrawnCards emptyCards = new DrawnCards(new ArrayList<>());
        CardDeck cardDeck = CardDeckGenerator.create();

        Player player = new Player(new Status(new Name("pobi"), new Account(10000)), emptyCards);
        Players players = new Players(List.of(player));
        Dealer dealer = new Dealer(emptyCards);

        // when
        List<DrawnCardsInfo> result = blackJackService.splitCards(dealer, players, cardDeck);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(result.size()).isEqualTo(2);
        softAssertions.assertThat(result.get(0).getName()).isEqualTo(Message.DEALER_NAME.getMessage());
        softAssertions.assertThat(result.get(1).getName()).isEqualTo(player.getName());
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("카드를 뽑아준다. (Command == y)")
    void draw_card_when_command_mean_draw() {
        // given
        DrawnCards emptyCards = new DrawnCards(new ArrayList<>());
        CardDeck cardDeck = CardDeckGenerator.create();

        Player player = new Player(new Status(new Name("pobi"), new Account(10000)), emptyCards);

        DrawCommand drawCommand = new DrawCommand("y");

        // when
        DrawnCardsInfo result = blackJackService.drawCards(cardDeck, player, drawCommand);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(player.openDrawnCards().size()).isEqualTo(1);
        softAssertions.assertThat(result.getDrawnCards().size()).isEqualTo(1);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("카드를 뽑지 않는다. (Command == n)")
    void draw_card_when_command_mean_stop() {
        // given
        DrawnCards emptyCards = new DrawnCards(new ArrayList<>());
        CardDeck cardDeck = CardDeckGenerator.create();

        Player player = new Player(new Status(new Name("pobi"), new Account(10000)), emptyCards);

        DrawCommand drawCommand = new DrawCommand("n");

        // when
        DrawnCardsInfo result = blackJackService.drawCards(cardDeck, player, drawCommand);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(player.openDrawnCards().size()).isEqualTo(0);
        softAssertions.assertThat(result.getDrawnCards().size()).isEqualTo(0);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("플레이어가 뽑은 수의 총합이 버스트 넘버 초과하면 false를 반환한다.")
    void returns_false_when_player_cards_numbers_sum_over_bust() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        Card card3 = new Card(Type.DIAMOND, Value.EIGHT);

        DrawnCards overBurstNumberCards = new DrawnCards(List.of(card1, card2, card3));

        Player player = new Player(new Status(new Name("pobi"), new Account(10000)), overBurstNumberCards);
        DrawCommand drawCommand = new DrawCommand(Message.DRAW_COMMAND.getMessage());

        // when
        boolean result = blackJackService.canDrawMore(player, drawCommand);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어가 카드를 더 뽑겠냐는 질문에 stop을 원하면 false를 반환한다.")
    void returns_false_when_player_do_not_want_more_card() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);

        DrawnCards cards = new DrawnCards(List.of(card1, card2));

        Player player = new Player(new Status(new Name("pobi"), new Account(10000)), cards);
        DrawCommand drawCommand = new DrawCommand(Message.STOP_COMMAND.getMessage());

        // when
        boolean result = blackJackService.canDrawMore(player, drawCommand);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어가 카드를 더 뽑고 싶어하고, 플레이어의 카드 총 합이 버스트 넘버를 초과하지 않는다면 true를 반환한다.")
    void returns_true_when_player_want_more_card_and_not_bust() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);

        DrawnCards cards = new DrawnCards(List.of(card1, card2));

        Player player = new Player(new Status(new Name("pobi"), new Account(10000)), cards);
        DrawCommand drawCommand = new DrawCommand(Message.DRAW_COMMAND.getMessage());

        // when
        boolean result = blackJackService.canDrawMore(player, drawCommand);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드 총합이 16이하라면 카드를 뽑는다.")
    void dealer_draws_card_when_cards_sum_under_boundary() {
        // given
        CardDeck cardDeck = CardDeckGenerator.create();

        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        List<Card> givenCards = List.of(card1, card2);
        int sumBeforeDrawn = card1.getScore() + card2.getScore();

        DrawnCards drawnCards = new DrawnCards(givenCards);

        Dealer dealer = new Dealer(drawnCards);

        // when
        blackJackService.pickDealerCard(cardDeck, dealer);

        // then
        assertThat(dealer.calculateCardScore() > sumBeforeDrawn).isTrue();
    }

    @Test
    @DisplayName("딜러가 뽑은 카드의 총합이 16이하라면 true를 반환한다.")
    void returns_true_when_dealer_cards_sum_under_boundary() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        List<Card> givenCards = List.of(card1, card2);

        DrawnCards drawnCards = new DrawnCards(givenCards);

        Dealer dealer = new Dealer(drawnCards);

        // when
        boolean result = blackJackService.canDealerDrawMore(dealer);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러와 플레이어의 게임 결과를 반환한다.")
    void returns_dealer_and_players_result() {
        // given
        DrawnCards emptyCards = new DrawnCards(new ArrayList<>());

        Player player = new Player(new Status(new Name("pobi"), new Account(10000)), emptyCards);
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(emptyCards);

        // when
        List<ParticipantResult> result = blackJackService.getParticipantsCardsResults(dealer, players);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(result.size()).isEqualTo(2);
        softAssertions.assertThat(result.get(0).getName()).isEqualTo(dealer.getName());
        softAssertions.assertThat(result.get(0).getScore()).isEqualTo(dealer.calculateCardScore());
        softAssertions.assertThat(result.get(1).getName()).isEqualTo(player.getName());
        softAssertions.assertThat(result.get(1).getScore()).isEqualTo(player.calculateCardScore());
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("딜러, 플레이어 둘다 BUST라면, 딜러의 승리이다.")
    void calculate_account_if_dealer_and_player_bust_dealer_win() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        Card card3 = new Card(Type.DIAMOND, Value.EIGHT);
        List<Card> givenCards = List.of(card1, card2, card3);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        int givenAccount = 1000;
        Dealer dealer = new Dealer(drawnCards);
        Player player = new Player(new Status(new Name("pobi"), new Account(givenAccount)), drawnCards);
        Players players = new Players(List.of(player));

        // when
        blackJackService.calculateGameResults(dealer, players);

        // then
        assertThat(dealer.getAccount()).isEqualTo(1000);
        assertThat(player.getAccount()).isEqualTo(-givenAccount);
    }

    @Test
    @DisplayName("딜러, 플레이어 둘다 블랙잭이라면, 플레이어는 배팅금을 돌려받는다.")
    void calculate_account_if_dealer_and_player_black_jack() {
        // given
        Card card1 = new Card(Type.CLUB, Value.ACE);
        Card card2 = new Card(Type.SPADE, Value.KING);
        List<Card> givenCards = List.of(card1, card2);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        int givenAccount = 1000;
        Dealer dealer = new Dealer(drawnCards);
        Player player = new Player(new Status(new Name("pobi"), new Account(givenAccount)), drawnCards);
        Players players = new Players(List.of(player));

        // when
        blackJackService.calculateGameResults(dealer, players);

        // then
        assertThat(dealer.getAccount()).isEqualTo(0);
        assertThat(player.getAccount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러, 플레이어 둘다 BUST가 아니고, 플레이어의 점수가 더 높은 경우 플레이어가 상금을 탄다.")
    void calculate_account_if_dealer_and_player_not_bust_player_win() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        List<Card> givenCards = List.of(card1, card2);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        int givenAccount = 1000;
        Dealer dealer = new Dealer(new DrawnCards(List.of(card1)));
        Player player = new Player(new Status(new Name("pobi"), new Account(givenAccount)), drawnCards);
        Players players = new Players(List.of(player));

        // when
        blackJackService.calculateGameResults(dealer, players);

        // then
        assertThat(player.getAccount()).isEqualTo(1500);
    }

    @Test
    @DisplayName("딜러, 플레이어 둘다 BUST가 아니고, 딜러의 점수가 더 높은 경우 딜러가 상금을 탄다.")
    void calculate_account_if_dealer_and_player_not_bust_dealer_win() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        List<Card> givenCards = List.of(card1, card2);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        int givenAccount = 1000;
        Dealer dealer = new Dealer(drawnCards);
        Player player = new Player(new Status(new Name("pobi"), new Account(givenAccount)),
                new DrawnCards(List.of(card1)));
        Players players = new Players(List.of(player));

        // when
        blackJackService.calculateGameResults(dealer, players);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(player.getAccount()).isEqualTo(givenAccount * -1);
        softAssertions.assertThat(dealer.getAccount()).isEqualTo(givenAccount);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("딜러가 파산이고, 플레이어는 파산이 아닌 경우 플레이어가 상금을 탄다.")
    void calculate_account_if_dealer_bust_and_player_not_bust_player_win() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        Card card3 = new Card(Type.SPADE, Value.EIGHT);
        List<Card> givenCards = List.of(card1, card2, card3);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        int givenAccount = 1000;
        int expectedPlayerAccount = (int) (givenAccount * 1.5);
        int expectedDealerAccount = (int) (givenAccount * 0.5 * -1);

        Dealer dealer = new Dealer(drawnCards);
        Player player = new Player(new Status(new Name("pobi"), new Account(givenAccount)),
                new DrawnCards(List.of(card1)));
        Players players = new Players(List.of(player));

        // when
        blackJackService.calculateGameResults(dealer, players);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(player.getAccount()).isEqualTo(expectedPlayerAccount);
        softAssertions.assertThat(dealer.getAccount()).isEqualTo(expectedDealerAccount);
        softAssertions.assertAll();

    }

    @Test
    @DisplayName("딜러가 파산이 아니고, 플레이어는 파산인 경우 딜러가 상금을 탄다.")
    void calculate_account_if_dealer_not_bust_and_player_bust_dealer_win() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        Card card3 = new Card(Type.SPADE, Value.EIGHT);
        List<Card> givenCards = List.of(card1, card2, card3);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        int givenAccount = 1000;

        Dealer dealer = new Dealer(new DrawnCards(List.of(card1)));
        Player player = new Player(new Status(new Name("pobi"), new Account(givenAccount)), drawnCards);
        Players players = new Players(List.of(player));

        // when
        blackJackService.calculateGameResults(dealer, players);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(player.getAccount()).isEqualTo(givenAccount * -1);
        softAssertions.assertThat(dealer.getAccount()).isEqualTo(givenAccount);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("딜러와 플레이어의 이름과 수익을 dto로 반환해준다.")
    void returns_dto_of_players_and_dealer_name_and_account() {
        // given
        Card card1 = new Card(Type.CLUB, Value.EIGHT);
        Card card2 = new Card(Type.SPADE, Value.EIGHT);
        Card card3 = new Card(Type.SPADE, Value.EIGHT);
        List<Card> givenCards = List.of(card1, card2, card3);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        int givenAccount = 1000;

        Dealer dealer = new Dealer(new DrawnCards(List.of(card1)));
        Player player = new Player(new Status(new Name("pobi"), new Account(givenAccount)), drawnCards);
        Players players = new Players(List.of(player));

        // when
        List<ParticipantResult> results = blackJackService.getParticipantsCardsResults(dealer, players);

        // then
        assertThat(results.size()).isEqualTo(2);
    }
}
