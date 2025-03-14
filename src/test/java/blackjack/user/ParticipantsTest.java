package blackjack.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Nested
    @DisplayName("중복 이름 테스트")
    class CreatePlayerNameTest {

        @Test
        @DisplayName("중복된 이름이 없는 경우 참가자를 생성할 수 있다.")
        void createParticipantsWithNotDuplicate() {
            Dealer dealer = new Dealer(new CardHand(17));
            List<Player> players = List.of(
                new Player(new PlayerName("a"), new CardHand(21)),
                new Player(new PlayerName("b"), new CardHand(21)),
                new Player(new PlayerName("c"), new CardHand(21))
            );

            assertThatCode(() -> new Participants(dealer, players)).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("중복된 이름이 없는 경우 참가자를 생성할 수 없다.")
        void createParticipantsWithDuplicate() {
            Dealer dealer = new Dealer(new CardHand(17));
            List<Player> players = List.of(
                new Player(new PlayerName("a"), new CardHand(21)),
                new Player(new PlayerName("a"), new CardHand(21)),
                new Player(new PlayerName("c"), new CardHand(21))
            );

            assertThatThrownBy(() -> new Participants(dealer, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 가진 플레이어가 있습니다.");
        }

        @Test
        @DisplayName("25명의 참가자는 게임을 진행할 수 없다.")
        void createParticipantsOver25() {
            Dealer dealer = new Dealer(new CardHand(17));
            List<Player> players = IntStream.rangeClosed('a', 'z')
                .mapToObj(
                    c -> new Player(new PlayerName(String.valueOf((char) c)), new CardHand(21)))
                .toList();

            assertThatThrownBy(() -> new Participants(dealer, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 25명까지만 참가 가능합니다.");
        }
    }

    @Nested
    @DisplayName("카드 배부 테스트")
    class DistributeCardTest {

        @Test
        @DisplayName("딜러에게 카드를 2장씩 배부할 수 있다.")
        void distributeCardsToDealer() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Dealer dealer = new Dealer(new CardHand(17));
            List<Player> players = List.of(new Player(new PlayerName("sana"), new CardHand(21)));

            Participants participants = new Participants(dealer, players);
            participants.addInitialCardsToDealer(cardDeck);

            assertThat(dealer.getCards().openCards()).hasSize(2);
        }

        @Test
        @DisplayName("플레이어에게 카드를 2장씩 배부할 수 있다.")
        void distributeCardsToPlayers() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Dealer dealer = new Dealer(new CardHand(17));
            List<Player> players = List.of(
                new Player(new PlayerName("sana"), new CardHand(21)),
                new Player(new PlayerName("iffff"), new CardHand(21)));

            Participants participants = new Participants(dealer, players);
            participants.addInitialCardsToPlayers(cardDeck);

            assertAll(() -> {
                assertThat(players.getFirst().getCards().openCards()).hasSize(2);
                assertThat(players.getLast().getCards().openCards()).hasSize(2);
            });
        }

        @Test
        @DisplayName("딜러에게 추가 카드를 1장씩 배부할 수 있다.")
        void distributeExtraCardsToDealer() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.THREE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Dealer dealer = new Dealer(new CardHand(17));
            List<Player> players = List.of(new Player(new PlayerName("sana"), new CardHand(21)));

            Participants participants = new Participants(dealer, players);
            participants.addInitialCardsToDealer(cardDeck);
            participants.addExtraCardToDealer(cardDeck); // 총 3장

            assertThat(dealer.getCards().openCards()).hasSize(3);
        }

        @Test
        @DisplayName("플레이어에게 추가 카드를 1장씩 배부할 수 있다.")
        void distributeExtraCardsToPlayers() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.THREE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Dealer dealer = new Dealer(new CardHand(17));
            List<Player> players = List.of(
                new Player(new PlayerName("sana"), new CardHand(21)),
                new Player(new PlayerName("iffff"), new CardHand(21)));

            Participants participants = new Participants(dealer, players);
            participants.addInitialCardsToPlayers(cardDeck);

            participants.addExtraCardToPlayer(cardDeck, new PlayerName("sana"));
            participants.addExtraCardToPlayer(cardDeck, new PlayerName("iffff"));

            assertAll(() -> {
                assertThat(players.getFirst().getCards().openCards()).hasSize(3);
                assertThat(players.getLast().getCards().openCards()).hasSize(3);
            });
        }
    }
}
