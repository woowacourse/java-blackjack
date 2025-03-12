package domain;

import domain.card.Card;
import domain.card.CardProvider;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantName;
import domain.participant.Participants;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 매니저 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GameManagerTest {

    private class TestCardProvider implements CardProvider {

        private final Deque<Card> cardQueue = new ArrayDeque<>(List.of(
                new Card(Suit.CLOVER, Rank.EIGHT),
                new Card(Suit.HEART, Rank.JACK),
                new Card(Suit.DIAMOND, Rank.FIVE),
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.CLOVER, Rank.ACE)
        ));

        @Override
        public List<Card> provideCards(int size) {
            List<Card> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                result.add(cardQueue.poll());
            }
            return result;
        }
    }

    @Test
    void 딜러가_카드를_뽑으면_가지고있는_카드리스트에_추가된다() {
        GameManager gameManager = new GameManager(List.of("drago"), Map.of("drago", 10000), new GameManagerTest.TestCardProvider());

        Participants participants = gameManager.findParticipants();
        Participant dealer = participants.findDealer();
        gameManager.drawCard(dealer);

        List<Card> expected = List.of(new Card(Suit.DIAMOND, Rank.FIVE),
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.KING));

        assertThat(dealer.getCards()).isEqualTo(expected);
    }

    @Test
    void 플레이어가_카드를_뽑으면_가지고있는_카드리스트에_추가된다() {
        GameManager gameManager = new GameManager(List.of("drago"), Map.of("drago", 10000), new GameManagerTest.TestCardProvider());

        Participants participants = gameManager.findParticipants();
        Participant player = participants.findPlayers().getFirst();
        gameManager.drawCard(player);

        List<Card> expected = List.of(new Card(Suit.CLOVER, Rank.EIGHT),
                new Card(Suit.HEART, Rank.JACK),
                new Card(Suit.SPADE, Rank.KING));

        assertThat(player.getCards()).isEqualTo(expected);
    }

    @Test
    void 플레이어가_카드를_뽑아야하는_상황이면_true를_반환한다() {
        GameManager gameManager = new GameManager(List.of("duei"), Map.of("duei", 10000), new GameManagerTest.TestCardProvider());

        Participants participants = gameManager.findParticipants();
        Participant duei = participants.findPlayers().getFirst();

        assertThat(gameManager.shouldPlayerHit(duei)).isTrue();
    }

    @Test
    void 플레이어가_카드를_뽑지않아도_되는_상황이면_false를_반환한다() {
        GameManager gameManager = new GameManager(List.of("duei"), Map.of("duei", 10000), new GameManagerTest.TestCardProvider());

        Participants participants = gameManager.findParticipants();
        Participant duei = participants.findPlayers().getFirst();
        gameManager.drawCard(duei);

        assertThat(gameManager.shouldPlayerHit(duei)).isFalse();
    }

    @Test
    void 딜러가_카드를_뽑아야하는_상황이면_true를_반환한다() {
        GameManager gameManager = new GameManager(List.of("duei"), Map.of("duei", 10000), new GameManagerTest.TestCardProvider());

        assertThat(gameManager.shouldDealerHit()).isTrue();
    }

    @Test
    void 딜러가_카드를_뽑지않아도_되는_상황이면_false를_반환한다() {
        GameManager gameManager = new GameManager(List.of("duei"), Map.of("duei", 10000), new GameManagerTest.TestCardProvider());

        Participants participants = gameManager.findParticipants();
        Participant dealer = participants.findDealer();
        gameManager.drawCard(dealer);

        assertThat(gameManager.shouldDealerHit()).isFalse();
    }

    @Test
    void 최종_게임_결과를_반환한다() {
        GameManager gameManager = new GameManager(
                List.of("drago", "duei"), Map.of("drago", 10000, "duei", 20000), new GameManagerTest.TestCardProvider());
        Cards cardsOfDrago = new Cards(
                List.of(new Card(Suit.CLOVER, Rank.EIGHT), new Card(Suit.HEART, Rank.JACK)));
        Cards cardsOfDuei = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.FIVE), new Card(Suit.SPADE, Rank.QUEEN)));
        Map<Participant, ResultStatus> result = gameManager.findGameResult();

        Map<Participant, ResultStatus> expected = Map.of(
                new Player(new ParticipantName("drago"), new BettingAmount(10000), cardsOfDrago), ResultStatus.LOSE,
                new Player(new ParticipantName("duei"), new BettingAmount(20000), cardsOfDuei), ResultStatus.LOSE
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 게임의_모든_참가자들을_반환한다() {
        GameManager gameManager = new GameManager(List.of("duei"), Map.of("duei", 10000), new GameManagerTest.TestCardProvider());

        ParticipantName name = new ParticipantName("duei");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cardsOfDuei = new Cards(
                List.of(new Card(Suit.CLOVER, Rank.EIGHT), new Card(Suit.HEART, Rank.JACK)));
        Player duei = new Player(name, bettingAmount, cardsOfDuei);

        Cards cardsOfDealer = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.FIVE), new Card(Suit.SPADE, Rank.QUEEN)));
        Dealer dealer = new Dealer(cardsOfDealer);

        Participants expected = new Participants(List.of(duei, dealer));

        assertThat(gameManager.findParticipants()).isEqualTo(expected);
    }
}
