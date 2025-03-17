package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.betting.BatMoney;
import domain.betting.BatMonies;
import domain.betting.Revenue;
import domain.betting.Revenues;
import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.card.Rank;
import domain.card.Shape;
import domain.fixture.ParticipantsFixture;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackJackGameTest {

    @Test
    @DisplayName("딜러를 포함한 모든 참가자들 List를 반환한다")
    void should_return_player_participants_except_dealer() {
        // given
        List<String> playerNames = List.of("a", "b");
        Participants participants = ParticipantsFixture.createParticipants(playerNames);
        CardDeck cardDeck = CardDeckFixture.createCardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);

        // when
        List<Participant> playerParticipants = blackJackGame.getParticipants();

        // then
        assertThat(playerParticipants).hasSize(3);
    }

    @Test
    @DisplayName("딜러를 제외한 플레이어들의 이름 List를 반환한다")
    void should_return_player_names_except_dealer() {
        // given
        List<String> playerNames = List.of("a", "b");
        Participants participants = ParticipantsFixture.createParticipants(playerNames);
        CardDeck cardDeck = CardDeckFixture.createCardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);

        // when
        List<String> playerNamesExceptDealer = blackJackGame.getPlayerNames();

        // then
        assertThat(playerNamesExceptDealer).containsExactlyElementsOf(playerNames);
    }

    @Nested
    @DisplayName("시작 카드 분배 케이스")
    class StartingCardCase {
        @Test
        @DisplayName("모든 참가자들에게 카드를 2장씩 나눠준다.")
        void should_give_starting_cards_to_participants() {
            // given
            List<String> playerNames = List.of("a", "b");
            Participants participants = ParticipantsFixture.createParticipants(playerNames);
            CardDeck cardDeck = CardDeckFixture.createCardDeck();
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);

            // when
            blackJackGame.giveStartingCardsToParticipants();

            // then
            assertAll(
                    blackJackGame.getParticipants()
                            .stream()
                            .map(player -> (Executable) () -> assertThat(player.getCards()).hasSize(2))
                            .toArray(Executable[]::new)
            );
        }

        @Test
        @DisplayName("블랙잭 시작 후 카드 2장을 받고, 딜러의 보여줄 손패를 반환한다")
        void should_return_dealer_first_shown_cards() {
            // given
            String playerName = "a";
            Participants participants = ParticipantsFixture.createParticipants(List.of(playerName));
            CardDeck cardDeck = CardDeckFixture.createCardDeck();
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveStartingCardsToParticipants();

            // when
            List<Card> dealerShownCards = blackJackGame.getDealerShownCard();

            // then
            assertThat(dealerShownCards).hasSize(1);
        }

        @Test
        @DisplayName("블랙잭 시작 후 카드 2장을 받고, 플레이어의 보여줄 손패를 반환한다")
        void should_return_player_first_shown_cards() {
            // given
            String playerName = "a";
            Participants participants = ParticipantsFixture.createParticipants(List.of(playerName));
            CardDeck cardDeck = CardDeckFixture.createCardDeck();
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveStartingCardsToParticipants();

            // when
            List<Card> playerShownCards = blackJackGame.getPlayerShownCards(playerName);

            // then
            assertThat(playerShownCards).hasSize(2);
        }
    }

    @Nested
    @DisplayName("카드 추가 받기 케이스")
    class GiveCardCase {
        private static Stream<Arguments> playerCardsArguments() {
            return Stream.of(
                    Arguments.arguments(
                            List.of(new Card(Shape.HEART, Rank.KING),
                                    new Card(Shape.HEART, Rank.QUEEN),
                                    new Card(Shape.HEART, Rank.ACE)), true),
                    Arguments.arguments(
                            List.of(new Card(Shape.HEART, Rank.KING),
                                    new Card(Shape.HEART, Rank.NINE),
                                    new Card(Shape.HEART, Rank.TWO)), true),
                    Arguments.arguments(
                            List.of(new Card(Shape.HEART, Rank.KING),
                                    new Card(Shape.HEART, Rank.NINE),
                                    new Card(Shape.HEART, Rank.THREE)), false)
            );
        }

        @ParameterizedTest
        @DisplayName("플레이어 이름으로 해당 플레이어가 카드를 더 받을 수 있는지 확인한다")
        @MethodSource("playerCardsArguments")
        void should_return_true_when_can_player_pick_by_name(List<Card> cards, boolean expected) {
            // given
            String playerName = "a";
            List<String> playerNames = List.of(playerName);
            Participants participants = ParticipantsFixture.createParticipants(playerNames);
            CardDeck cardDeck = new CardDeck(cards);
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            for (int cardIndex = 0; cardIndex < cards.size(); ++cardIndex) {
                blackJackGame.giveCardToPlayer(playerName);
            }

            // when
            boolean canPick = blackJackGame.canPlayerPick(playerName);

            // then
            assertThat(canPick).isEqualTo(expected);
        }

        @Test
        @DisplayName("플레이어 이름으로 해당 플레이어에게 카드를 한 장 준다")
        void should_give_card_to_player_by_name() {
            // given
            String playerName = "a";
            List<String> playerNames = List.of(playerName);
            Participants participants = ParticipantsFixture.createParticipants(playerNames);
            CardDeck cardDeck = CardDeckFixture.createCardDeck();
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);

            // when
            blackJackGame.giveCardToPlayer(playerName);

            // then
            Participant player = blackJackGame.findByName(playerName);
            assertThat(player.getCards()).hasSize(1);
        }

        @Test
        @DisplayName("플레이어가 카드를 추가로 받았는지 확인한다")
        void should_return_true_when_player_has_received_card() {
            // given
            String playerName = "a";
            List<String> playerNames = List.of(playerName);
            Participants participants = ParticipantsFixture.createParticipants(playerNames);
            CardDeck cardDeck = CardDeckFixture.createCardDeck();
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveStartingCardsToParticipants();
            blackJackGame.giveCardToPlayer(playerName);

            // when
            boolean hasReceivedCard = blackJackGame.hasPlayerReceivedCard(playerName);

            // then
            assertThat(hasReceivedCard).isEqualTo(true);
        }

        @Test
        @DisplayName("플레이어가 카드를 추가로 받았는지 확인한다")
        void should_return_false_when_player_has_not_received_card() {
            // given
            String playerName = "a";
            List<String> playerNames = List.of(playerName);
            Participants participants = ParticipantsFixture.createParticipants(playerNames);
            CardDeck cardDeck = CardDeckFixture.createCardDeck();
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveStartingCardsToParticipants();

            // when
            boolean hasReceivedCard = blackJackGame.hasPlayerReceivedCard(playerName);

            // then
            assertThat(hasReceivedCard).isEqualTo(false);
        }

        private static Stream<Arguments> dealerCardsArguments() {
            return Stream.of(
                    Arguments.arguments(
                            List.of(new Card(Shape.HEART, Rank.SEVEN),
                                    new Card(Shape.HEART, Rank.SIX),
                                    new Card(Shape.HEART, Rank.THREE)), true),
                    Arguments.arguments(
                            List.of(new Card(Shape.HEART, Rank.SEVEN),
                                    new Card(Shape.HEART, Rank.SIX),
                                    new Card(Shape.HEART, Rank.FOUR)), false)
            );
        }

        @ParameterizedTest
        @DisplayName("딜러가 카드를 더 받을 수 있는지 확인한다")
        @MethodSource("dealerCardsArguments")
        void should_return_true_when_can_dealer_pick(List<Card> cards, boolean expected) {
            // given
            Participants participants = ParticipantsFixture.createParticipants(List.of("_"));
            CardDeck cardDeck = new CardDeck(cards);
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            for (int cardIndex = 0; cardIndex < cards.size(); ++cardIndex) {
                blackJackGame.giveCardToDealer();
            }

            // when
            boolean canPick = blackJackGame.canDealerPick();

            // then
            assertThat(canPick).isEqualTo(expected);
        }

        @Test
        @DisplayName("딜러에게 카드를 한 장 준다")
        void should_give_card_to_dealer() {
            // given
            Participants participants = ParticipantsFixture.createParticipants(List.of("_"));
            CardDeck cardDeck = CardDeckFixture.createCardDeck();
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);

            // when
            blackJackGame.giveCardToDealer();

            // then
            Participant dealer = blackJackGame.getDealer();
            assertThat(dealer.getCards()).hasSize(1);
        }

    }

    @Nested
    @DisplayName("수익 계산 테스트")
    class RevenueCase {
        @Test
        @DisplayName("플레이어가 블랙잭 승리의 경우 수익을 계산한다")
        void should_return_revenue_when_player_blackjack_win() {
            // given
            String playerName = "a";
            int batMoneyAmount = 10000;
            BatMoney batMoney = new BatMoney(playerName, batMoneyAmount);
            BatMonies batMonies = new BatMonies(List.of(batMoney));

            CardDeck cardDeck = new CardDeck(List.of(
                    CardFixture.cardOfHeartTwo,
                    CardFixture.cardOfHeartThree,
                    CardFixture.cardOfHeartAce,
                    CardFixture.cardOfHeartKing
            ));
            Participants participants = ParticipantsFixture.createParticipants(List.of(playerName));
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToPlayer(playerName);
            blackJackGame.giveCardToPlayer(playerName);

            // when
            Revenues revenues = blackJackGame.calculateRevenue(batMonies);

            // then
            Revenue playerRevenue = revenues.getRevenues().getFirst();
            int expected = (int) (batMoneyAmount * 1.5);
            assertThat(playerRevenue.money()).isEqualTo(expected);
        }

        @Test
        @DisplayName("플레이어가 일반적인 승리의 경우 수익을 계산한다")
        void should_return_revenue_when_player_win() {
            // given
            String playerName = "a";
            int batMoneyAmount = 10000;
            BatMoney batMoney = new BatMoney(playerName, batMoneyAmount);
            BatMonies batMonies = new BatMonies(List.of(batMoney));

            CardDeck cardDeck = new CardDeck(List.of(
                    CardFixture.cardOfHeartTwo,
                    CardFixture.cardOfHeartThree,
                    CardFixture.cardOfHeartQueen,
                    CardFixture.cardOfHeartKing
            ));
            Participants participants = ParticipantsFixture.createParticipants(List.of(playerName));
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToPlayer(playerName);
            blackJackGame.giveCardToPlayer(playerName);

            // when
            Revenues revenues = blackJackGame.calculateRevenue(batMonies);

            // then
            Revenue playerRevenue = revenues.getRevenues().getFirst();
            int expected = (int) (batMoneyAmount * 1.0);
            assertThat(playerRevenue.money()).isEqualTo(expected);
        }

        @Test
        @DisplayName("플레이어가 무승부인 경우 수익을 계산한다")
        void should_return_revenue_when_player_draw() {
            // given
            String playerName = "a";
            int batMoneyAmount = 10000;
            BatMoney batMoney = new BatMoney(playerName, batMoneyAmount);
            BatMonies batMonies = new BatMonies(List.of(batMoney));

            CardDeck cardDeck = new CardDeck(List.of(
                    CardFixture.cardOfHeartTwo,
                    CardFixture.cardOfHeartFive,
                    CardFixture.cardOfHeartThree,
                    CardFixture.cardOfHeartFour
            ));
            Participants participants = ParticipantsFixture.createParticipants(List.of(playerName));
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToPlayer(playerName);
            blackJackGame.giveCardToPlayer(playerName);

            // when
            Revenues revenues = blackJackGame.calculateRevenue(batMonies);

            // then
            Revenue playerRevenue = revenues.getRevenues().getFirst();
            int expected = (int) (batMoneyAmount * 0);
            assertThat(playerRevenue.money()).isEqualTo(expected);
        }

        @Test
        @DisplayName("플레이어가 일반적인 패배의 경우 수익을 계산한다")
        void should_return_revenue_when_player_lose() {
            // given
            String playerName = "a";
            int batMoneyAmount = 10000;
            BatMoney batMoney = new BatMoney(playerName, batMoneyAmount);
            BatMonies batMonies = new BatMonies(List.of(batMoney));

            CardDeck cardDeck = new CardDeck(List.of(
                    CardFixture.cardOfHeartKing,
                    CardFixture.cardOfHeartQueen,
                    CardFixture.cardOfHeartTwo,
                    CardFixture.cardOfHeartThree
            ));
            Participants participants = ParticipantsFixture.createParticipants(List.of(playerName));
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToPlayer(playerName);
            blackJackGame.giveCardToPlayer(playerName);

            // when
            Revenues revenues = blackJackGame.calculateRevenue(batMonies);

            // then
            Revenue playerRevenue = revenues.getRevenues().getFirst();
            int expected = (int) (batMoneyAmount * -1.0);
            assertThat(playerRevenue.money()).isEqualTo(expected);
        }

        @Test
        @DisplayName("플레이어가 블랙잭 패배인 경우 수익을 계산한다")
        void should_return_revenue_when_player_blackjack_lose() {
            // given
            String playerName = "a";
            int batMoneyAmount = 10000;
            BatMoney batMoney = new BatMoney(playerName, batMoneyAmount);
            BatMonies batMonies = new BatMonies(List.of(batMoney));

            CardDeck cardDeck = new CardDeck(List.of(
                    CardFixture.cardOfHeartAce,
                    CardFixture.cardOfHeartKing,
                    CardFixture.cardOfHeartQueen,
                    CardFixture.cardOfHeartJack
            ));
            Participants participants = ParticipantsFixture.createParticipants(List.of(playerName));
            BlackJackGame blackJackGame = new BlackJackGame(participants, cardDeck);
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToDealer();
            blackJackGame.giveCardToPlayer(playerName);
            blackJackGame.giveCardToPlayer(playerName);

            // when
            Revenues revenues = blackJackGame.calculateRevenue(batMonies);

            // then
            Revenue playerRevenue = revenues.getRevenues().getFirst();
            int expected = (int) (batMoneyAmount * -1.0);
            assertThat(playerRevenue.money()).isEqualTo(expected);
        }
    }
}

class CardFixture {
    public static Card cardOfHeartAce = new Card(Shape.HEART, Rank.ACE);
    public static Card cardOfHeartTwo = new Card(Shape.HEART, Rank.TWO);
    public static Card cardOfHeartThree = new Card(Shape.HEART, Rank.THREE);
    public static Card cardOfHeartFour = new Card(Shape.HEART, Rank.FOUR);
    public static Card cardOfHeartFive = new Card(Shape.HEART, Rank.FIVE);
    public static Card cardOfHeartSix = new Card(Shape.HEART, Rank.SIX);
    public static Card cardOfHeartSeven = new Card(Shape.HEART, Rank.SEVEN);
    public static Card cardOfHeartEight = new Card(Shape.HEART, Rank.EIGHT);
    public static Card cardOfHeartNine = new Card(Shape.HEART, Rank.NINE);
    public static Card cardOfHeartTen = new Card(Shape.HEART, Rank.TEN);
    public static Card cardOfHeartJack = new Card(Shape.HEART, Rank.JACK);
    public static Card cardOfHeartQueen = new Card(Shape.HEART, Rank.QUEEN);
    public static Card cardOfHeartKing = new Card(Shape.HEART, Rank.KING);
}

class CardBundleFixture {
    public static CardBundle createCardBundle() {
        return new CardBundle();
    }
}

class CardDeckFixture {
    public static CardDeck createCardDeck() {
        return new CardDeck(CardBundleFixture.createCardBundle()
                .getShuffledAllCards());
    }
}
