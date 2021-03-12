package blakcjack.domain.blackjackgame;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.card.Deck;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;
import blakcjack.domain.shufflestrategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackjackGameTest {
    private List<String> names;
    private Deck deck;

    @BeforeEach
    void setUp() {
        final ShuffleStrategy nonShuffleStrategy = (cards) -> {
        };
        names = Arrays.asList("pobi", "sakjung", "mediumBear");
        deck = new Deck(nonShuffleStrategy);
    }

    @DisplayName("객체 생성 성공")
    @Test
    void create() {
        assertThatCode(() -> new BlackjackGame(deck, names))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복 이름 검증")
    @Test
    void validateDuplicateNames() {
        assertThatThrownBy(() -> new BlackjackGame(deck, Arrays.asList("pobi", "pobi")))
                .isInstanceOf(GameInitializationFailureException.class);
    }

    @DisplayName("카드 한 장 나눠주기 성공")
    @Test
    void distributeOneCard() {
        final BlackjackGame blackjackGame = new BlackjackGame(deck, names);
        final List<Participant> players = blackjackGame.getPlayers();
        final Participant pobi = players.get(0);
        final Participant expected = new Player("pobi");
        expected.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.KING));

        blackjackGame.distributeOneCard(pobi);
        assertThat(pobi).isEqualTo(expected);
    }

    @DisplayName("딜러와 모든 플레이어에게 2장씩 카드 나눠주기 성공")
    @Test
    void initializeHands() {
        final BlackjackGame blackjackGame = new BlackjackGame(deck, names);
        blackjackGame.initializeHands();

        final List<Participant> players = blackjackGame.getPlayers();

        final List<Participant> expectedPlayers = createExpectedPlayers();
        assertThat(players).isEqualTo(expectedPlayers);
    }

    private List<Participant> createExpectedPlayers() {
        final Deck deck = new Deck((cards) -> {
        });
        final List<Participant> expectedPlayers = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            player.receiveCard(deck.drawCard());
            player.receiveCard(deck.drawCard());
            expectedPlayers.add(player);
        }
        return expectedPlayers;
    }

    @DisplayName("승패 판단 성공")
    @Test
    void judgeOutcome() {
        final BlackjackGame blackjackGame = new BlackjackGame(deck, names);
        blackjackGame.initializeHands();
        blackjackGame.getPlayers().get(0).receiveCard(Card.of(CardSymbol.HEART, CardNumber.TWO));
        blackjackGame.getDealer().receiveCard(Card.of(CardSymbol.HEART, CardNumber.SEVEN));

        final Map<String, Outcome> playersOutcome = blackjackGame.judgePlayersOutcome();
        assertThat(playersOutcome).isEqualTo(getExpectedPlayersOutcome());

        final Map<Outcome, Integer> dealerOutcome = blackjackGame.judgeDealerOutcome(playersOutcome);
        assertThat(dealerOutcome).isEqualTo(getExpectedDealerOutcome());
    }

    private Map<String, Outcome> getExpectedPlayersOutcome() {
        final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();
        playersOutcome.put("pobi", Outcome.LOSE);
        playersOutcome.put("sakjung", Outcome.DRAW);
        playersOutcome.put("mediumBear", Outcome.LOSE);
        return playersOutcome;
    }

    private Map<Outcome, Integer> getExpectedDealerOutcome() {
        final Map<Outcome, Integer> dealerOutcome = new LinkedHashMap<>();
        dealerOutcome.put(Outcome.WIN, 2);
        dealerOutcome.put(Outcome.DRAW, 1);
        dealerOutcome.put(Outcome.LOSE, 0);
        return dealerOutcome;
    }
}