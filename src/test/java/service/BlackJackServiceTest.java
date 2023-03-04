package service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.DrawnCards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import dto.response.WinLoseResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackServiceTest {

    private final BlackJackService blackJackService = new BlackJackService();


    @DisplayName("카드를 딜러에게 우선적으로 2장 분배한다.")
    @Test
    void split_to_dealer() {
        // given
        List<Card> cards = createFillCards();
        CardDeck cardDeck = CardDeck.createShuffled(cards);
        Players players = new Players(createPlayers("pobi"));
        Dealer dealer = new Dealer(new DrawnCards(new ArrayList<>()));

        Card expectedFirst = cards.get(0);
        Card expectedSecond = cards.get(1);
        // when
        blackJackService.splitCards(dealer, players, cardDeck);
        List<Card> actual = dealer.getDrawnCards();
        // then
        assertThat(actual).containsExactly(expectedFirst, expectedSecond);
    }

    @DisplayName("카드를 딜러에게 분배한 후 플레이어들에게 2장씩 분배한다.")
    @Test
    void split_card_player_after_dealer() {
        // given
        List<Card> cards = createFillCards();
        CardDeck cardDeck = CardDeck.createShuffled(cards);
        Players players = new Players(createPlayers("pobi", "ori"));
        Dealer dealer = new Dealer(new DrawnCards(new ArrayList<>()));

        Card expectedFirst = cards.get(4);
        Card expectedSecond = cards.get(5);
        // when
        blackJackService.splitCards(dealer, players, cardDeck);
        Player player = players.stream()
                .collect(Collectors.toList())
                .get(1);
        List<Card> actualCards = player.getDrawnCards();
        // then
        assertThat(actualCards).containsExactly(expectedFirst, expectedSecond);
    }

    @DisplayName("플레이어가 카드를 뽑는다.")
    @Test
    void draw_card_if_command_true() {
        // given
        List<Card> cards = createFillCards();
        CardDeck cardDeck = CardDeck.createShuffled(cards);
        List<Card> emptyCards = new ArrayList<>();
        Player player = new Player(new Name("ori"), new DrawnCards(emptyCards));
        Card expectedCard = cards.get(0);
        // when
        blackJackService.drawPlayerCard(cardDeck, player);
        List<Card> actualCards = player.getDrawnCards();
        // then
        assertThat(actualCards).containsExactly(expectedCard);
    }


    @DisplayName("플레이어가 조건을 만족하면 카드를 더 뽑을 수 있다.")
    @Test
    void player_can_draw_card_if_command_false() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.HEART, CardValue.JACK));
        Player player = new Player(new Name("ori"), new DrawnCards(cards));

        boolean expected = true;
        // when
        boolean actual = blackJackService.canPlayerDrawMore(player);
        // then
        assertThat(actual).isEqualTo(expected);
    }


    @DisplayName("플레이어의 점수가 버스트 넘버를 넘어가면 카드를 더 뽑지 못한다.")
    @Test
    void player_stop_draw_card_if_over_burst_number() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.SPADE, CardValue.JACK));
        cards.add(new Card(CardType.HEART, CardValue.JACK));
        cards.add(new Card(CardType.DIAMOND, CardValue.JACK));
        Player player = new Player(new Name("ori"), new DrawnCards(cards));

        boolean expected = false;
        // when
        boolean actual = blackJackService.canPlayerDrawMore(player);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("딜러가 조건을 만족하면 카드를 더 뽑는다.")
    @Test
    void dealer__draw_card() {
        // given
        List<Card> cards = new ArrayList<>();
        Card givenCard = new Card(CardType.SPADE, CardValue.JACK);
        cards.add(givenCard);

        Dealer dealer = new Dealer(new DrawnCards(cards));
        List<Card> rawDeck = createFillCards();
        CardDeck cardDeck = CardDeck.createShuffled(rawDeck);

        Card expectedCard = rawDeck.get(0);
        // when

        blackJackService.drawDealerCard(cardDeck, dealer);
        List<Card> drawnCards = dealer.getDrawnCards();

        // then
        assertThat(drawnCards)
                .containsExactly(givenCard, expectedCard);
    }

    @DisplayName("딜러가 조건을 만족하면 카드를 더 뽑을 수 있다.")
    @Test
    void dealer_can_draw_card() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.SPADE, CardValue.JACK));
        Dealer dealer = new Dealer(new DrawnCards(cards));

        boolean expected = true;
        // when
        boolean actual = blackJackService.canDealerDrawMore(dealer);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("딜러의 한계 넘버를 넘으면 더 이상 카드를 뽑지 못한다.")
    @Test
    void dealer_can_draw_card_if_score_over_limit() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.SPADE, CardValue.JACK));
        cards.add(new Card(CardType.HEART, CardValue.JACK));
        Dealer dealer = new Dealer(new DrawnCards(cards));

        boolean expected = false;
        // when
        boolean actual = blackJackService.canDealerDrawMore(dealer);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("딜러의 점수를 바탕으로 플레이어들의 승패를 계산한다.")
    @Test
    void get_win_loss_result() {
        // given
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(CardType.SPADE, CardValue.EIGHT));
        Player player = new Player(new Name("pobi"), new DrawnCards(playerCards));

        Players players = new Players(List.of(player));

        List<Card> dealerCard = new ArrayList<>();
        dealerCard.add(new Card(CardType.SPADE, CardValue.TWO));
        Dealer dealer = new Dealer(new DrawnCards(dealerCard));

        boolean expected = true;
        // when
        List<WinLoseResult> winLoseResults = blackJackService.getWinLoseResults(dealer, players);
        boolean actual = winLoseResults.get(0)
                .isWin();
        // then
        assertThat(actual).isEqualTo(expected);
    }

    private List<Card> createFillCards() {
        List<Card> cards = new ArrayList<>();

        for (CardType type : CardType.values()) {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(type, value));
            }
        }

        return cards;
    }

    public List<Player> createPlayers(String... names) {
        List<Card> cards = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(new Name(name), new DrawnCards(cards)));
        }

        return players;
    }
}
