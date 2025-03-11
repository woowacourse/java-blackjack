package domain;

import domain.card.Card;
import domain.card.CardProvider;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantName;
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
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.ACE),
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
    void 참가자중_딜러를_반환한다() {
        GameManager gameManager = new GameManager(List.of("drago", "duei"), new GameManagerTest.TestCardProvider());
        Cards cardsOfDealer = new Cards(
                List.of(new Card(Suit.SPADE, Rank.KING), new Card(Suit.CLOVER, Rank.ACE)));

        Participant result = gameManager.findDealer();

        Participant expected = new Dealer(cardsOfDealer);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 딜러가_카드를_뽑으면_가지고있는_카드리스트에_추가된다() {
        GameManager gameManager = new GameManager(List.of("drago"), new GameManagerTest.TestCardProvider());

        Participant dealer = gameManager.findDealer();
        gameManager.drawCard(dealer);

        List<Card> expected = List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.KING));

        assertThat(gameManager.findDealer().getCards()).isEqualTo(expected);
    }

    @Test
    void 플레이어가_카드를_뽑으면_가지고있는_카드리스트에_추가된다() {
        GameManager gameManager = new GameManager(List.of("drago"), new GameManagerTest.TestCardProvider());

        Participant player = gameManager.findPlayers().getFirst();
        gameManager.drawCard(player);

        List<Card> expected = List.of(new Card(Suit.CLOVER, Rank.EIGHT),
                new Card(Suit.HEART, Rank.JACK),
                new Card(Suit.SPADE, Rank.KING));

        assertThat(gameManager.findPlayers().getFirst().getCards()).isEqualTo(expected);
    }

    @Test
    void 최종_게임_결과를_반환한다() {
        GameManager gameManager = new GameManager(List.of("drago", "duei"), new GameManagerTest.TestCardProvider());
        Cards cardsOfDrago = new Cards(
                List.of(new Card(Suit.CLOVER, Rank.EIGHT), new Card(Suit.HEART, Rank.JACK)));
        Cards cardsOfDuei = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT), new Card(Suit.SPADE, Rank.ACE)));
        Map<Participant, ResultStatus> result = gameManager.findGameResult();

        Map<Participant, ResultStatus> expected = Map.of(
                new Player(new ParticipantName("drago"), cardsOfDrago), ResultStatus.LOSE,
                new Player(new ParticipantName("duei"), cardsOfDuei), ResultStatus.LOSE
        );
        assertThat(result).isEqualTo(expected);
    }
}
