package domain;

import domain.card.Card;
import domain.card.CardProvider;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.ParticipantName;
import domain.participant.ParticipantNames;
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
    void 플레이어의_카드를_뽑는다() {
        ParticipantNames participantNames = new ParticipantNames(List.of(new ParticipantName("drago")));
        GameManager gameManager = new GameManager(participantNames,
                Map.of(new ParticipantName("drago"), new BettingAmount(10000)), new GameManagerTest.TestCardProvider());

        List<Player> players = gameManager.getPlayers();
        Player player = players.getFirst();
        gameManager.drawCardForPlayer(player);

        List<Card> expected = List.of(new Card(Suit.CLOVER, Rank.EIGHT),
                new Card(Suit.HEART, Rank.JACK),
                new Card(Suit.SPADE, Rank.KING));

        assertThat(player.getCards()).isEqualTo(expected);
    }

    @Test
    void 딜러가_카드를_뽑아야하는_상황이면_true를_반환한다() {
        ParticipantNames participantNames = new ParticipantNames(List.of(new ParticipantName("drago")));
        GameManager gameManager = new GameManager(participantNames,
                Map.of(new ParticipantName("drago"), new BettingAmount(10000)), new GameManagerTest.TestCardProvider());

        assertThat(gameManager.shouldDealerHit()).isTrue();
    }

    @Test
    void 딜러가_카드를_뽑지않아도_되는_상황이면_false를_반환한다() {
        ParticipantNames participantNames = new ParticipantNames(
                List.of(new ParticipantName("drago"), new ParticipantName("duei")));
        GameManager gameManager = new GameManager(participantNames,
                Map.of(new ParticipantName("drago"), new BettingAmount(10000),
                        new ParticipantName("duie"), new BettingAmount(10000)),
                new GameManagerTest.TestCardProvider());

        assertThat(gameManager.shouldDealerHit()).isFalse();
    }

    @Test
    void 딜러의_카드를_뽑는다() {
        ParticipantNames participantNames = new ParticipantNames(List.of(new ParticipantName("drago")));
        GameManager gameManager = new GameManager(participantNames,
                Map.of(new ParticipantName("drago"), new BettingAmount(10000)), new GameManagerTest.TestCardProvider());

        Dealer dealer = gameManager.getDealer();
        gameManager.drawCardForDealer();

        List<Card> expected = List.of(new Card(Suit.DIAMOND, Rank.FIVE),
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.KING));

        assertThat(dealer.getCards()).isEqualTo(expected);
    }

    @Test
    void 최종_플레이어들의_수익을_반환한다() {
        ParticipantNames participantNames = new ParticipantNames(List.of(new ParticipantName("drago"), new ParticipantName("duei")));
        GameManager gameManager = new GameManager(participantNames,
                Map.of(new ParticipantName("drago"), new BettingAmount(10000),
                        new ParticipantName("duei"), new BettingAmount(20000)),
                new GameManagerTest.TestCardProvider());
        Cards cardsOfDrago = new Cards(
                List.of(new Card(Suit.CLOVER, Rank.EIGHT), new Card(Suit.HEART, Rank.JACK)));
        Cards cardsOfDuei = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.FIVE), new Card(Suit.SPADE, Rank.QUEEN)));

        Map<Player, Integer> expected = Map.of(
                new Player(new ParticipantName("drago"), new BettingAmount(10000), cardsOfDrago), -10000,
                new Player(new ParticipantName("duei"), new BettingAmount(20000), cardsOfDuei), -20000
        );

        assertThat(gameManager.findPlayersProfits()).isEqualTo(expected);
    }

    @Test
    void 최종_딜러의_수익을_반환한다() {
        ParticipantNames participantNames = new ParticipantNames(List.of(new ParticipantName("drago"), new ParticipantName("duei")));
        GameManager gameManager = new GameManager(participantNames,
                Map.of(new ParticipantName("drago"), new BettingAmount(10000),
                        new ParticipantName("duei"), new BettingAmount(20000)),
                new GameManagerTest.TestCardProvider());

        int expected = 30000;

        assertThat(gameManager.findDealerProfit()).isEqualTo(expected);
    }
}
