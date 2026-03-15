package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {
    private Referee referee;
    private Player player;
    private Dealer dealer;
    private Participants participants;
    private BettingBoard bettingBoard;

    @BeforeEach
    void setUp() {
        referee = new Referee();
        player = new Player(Name.from("고래"));
        dealer = new Dealer();
        participants = new Participants(new Players(List.of(player)), dealer);
        bettingBoard = new BettingBoard();
        bettingBoard.addBetting(player, new Bet(new BigDecimal("10000")));
    }

    @Test
    @DisplayName("딜러와 플레이어가 내츄럴 블랙잭이면 무승부로 처리되어 모두 수익이 0이다.")
    void bothBlackjack() {
        makeNatualBlackjack(player);
        makeNatualBlackjack(dealer);

        Profits profits = referee.calculateProfits(participants, bettingBoard);
        Map<Participant, Profit> result = profits.getParticipantProfits();

        assertThat(result.get(player).value()).isEqualByComparingTo("0");
        assertThat(result.get(dealer).value()).isEqualByComparingTo("0");
    }

    @Test
    @DisplayName("플레이어만 내츄럴 블랙잭이면 베팅액의 1.5배 수익을 얻고 딜러는 그만큼 잃는다.")
    void playerBlackjackOnly() {
        makeNatualBlackjack(player);
        makeScoreSevenTeen(dealer);

        Profits profits = referee.calculateProfits(participants, bettingBoard);
        Map<Participant, Profit> result = profits.getParticipantProfits();

        assertThat(result.get(player).value()).isEqualByComparingTo("15000");
        assertThat(result.get(dealer).value()).isEqualByComparingTo("-15000");
    }

    @Test
    @DisplayName("딜러만 내츄럴 블랙잭이면 플레이어는 베팅액을 모두 잃는다.")
    void dealerBlackjackOnly() {
        makeScoreSevenTeen(player);
        makeNatualBlackjack(dealer);

        Profits profits = referee.calculateProfits(participants, bettingBoard);
        Map<Participant, Profit> result = profits.getParticipantProfits();

        assertThat(result.get(player).value()).isEqualByComparingTo("-10000");
        assertThat(result.get(dealer).value()).isEqualByComparingTo("10000");
    }

    @Test
    @DisplayName("플레이어가 버스트되면 딜러 결과와 상관없이 플레이어는 베팅액을 잃는다.")
    void playerBust() {
        makeBust(player);
        makeBust(dealer);

        Profits profits = referee.calculateProfits(participants, bettingBoard);
        Map<Participant, Profit> result = profits.getParticipantProfits();

        assertThat(result.get(player).value()).isEqualByComparingTo("-10000");
        assertThat(result.get(dealer).value()).isEqualByComparingTo("10000");
    }

    @Test
    @DisplayName("플레이어는 버스트되지 않고 딜러만 버스트되면 플레이어는 1배의 수익을 얻는다.")
    void dealerBust() {
        makeScoreSevenTeen(player);
        makeBust(dealer);

        Profits profits = referee.calculateProfits(participants, bettingBoard);
        Map<Participant, Profit> result = profits.getParticipantProfits();

        assertThat(result.get(player).value()).isEqualByComparingTo("10000");
        assertThat(result.get(dealer).value()).isEqualByComparingTo("-10000");
    }

    @Test
    @DisplayName("둘 다 버스트나 블랙잭이 아닐 때, 점수가 높은 플레이어가 1배의 수익을 얻는다.")
    void higherScoreWin() {
        makeNormalBlackjack(player);
        makeScoreSevenTeen(dealer);

        Profits profits = referee.calculateProfits(participants, bettingBoard);
        Map<Participant, Profit> result = profits.getParticipantProfits();

        assertThat(result.get(player).value()).isEqualByComparingTo("10000");
        assertThat(result.get(dealer).value()).isEqualByComparingTo("-10000");
    }

    @Test
    @DisplayName("둘 다 버스트나 블랙잭이 아닐 때, 점수가 낮은 플레이어는 베팅액을 잃는다.")
    void lowerScoreLose() {
        makeScoreSevenTeen(player);
        makeNormalBlackjack(dealer);

        Profits profits = referee.calculateProfits(participants, bettingBoard);
        Map<Participant, Profit> result = profits.getParticipantProfits();

        assertThat(result.get(player).value()).isEqualByComparingTo("-10000");
        assertThat(result.get(dealer).value()).isEqualByComparingTo("10000");
    }

    @Test
    @DisplayName("점수가 같으면 무승부로 수익 변동이 없다.")
    void sameScoreDraw() {
        makeScoreSevenTeen(player);
        makeScoreSevenTeen(dealer);

        Profits profits = referee.calculateProfits(participants, bettingBoard);
        Map<Participant, Profit> result = profits.getParticipantProfits();

        assertThat(result.get(player).value()).isEqualByComparingTo("0");
        assertThat(result.get(dealer).value()).isEqualByComparingTo("0");
    }

    private void makeNatualBlackjack(Participant participant) {
        Deck deck = Deck.create();
        deck.shuffle(cards -> {
            cards.clear();
            cards.add(new Card(Suit.SPADE, Rank.ACE));
            cards.add(new Card(Suit.SPADE, Rank.TEN));
        });
        GameManager gameManager = new GameManager(deck);
        gameManager.dealCard(participant);
        gameManager.dealCard(participant);
    }

    private void makeScoreSevenTeen(Participant participant) {
        Deck deck = Deck.create();
        deck.shuffle(cards -> {
            cards.clear();
            cards.add(new Card(Suit.SPADE, Rank.ACE));
            cards.add(new Card(Suit.SPADE, Rank.SEVEN));
        });
        GameManager gameManager = new GameManager(deck);
        gameManager.dealCard(participant);
        gameManager.dealCard(participant);

        participant.stay();
    }

    private void makeNormalBlackjack(Participant participant) {
        Deck deck = Deck.create();
        deck.shuffle(cards -> {
            cards.clear();
            cards.add(new Card(Suit.SPADE, Rank.SEVEN));
            cards.add(new Card(Suit.SPADE, Rank.SEVEN));
            cards.add(new Card(Suit.SPADE, Rank.SEVEN));
        });
        GameManager gameManager = new GameManager(deck);
        gameManager.dealCard(participant);
        gameManager.dealCard(participant);
        gameManager.dealCard(participant);

        participant.stay();
    }

    private void makeBust(Participant participant) {
        Deck deck = Deck.create();
        deck.shuffle(cards -> {
            cards.clear();
            cards.add(new Card(Suit.SPADE, Rank.TEN));
            cards.add(new Card(Suit.SPADE, Rank.TEN));
            cards.add(new Card(Suit.SPADE, Rank.TEN));
        });
        GameManager gameManager = new GameManager(deck);
        gameManager.dealCard(participant);
        gameManager.dealCard(participant);
        gameManager.dealCard(participant);
    }
}
