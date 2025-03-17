package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackJackGameTest {

    @Test
    @DisplayName("모든 플레이어에게 카드를 2장씩 나눠준다.")
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
        assertThat(blackJackGame.getParticipants()
                .get(1)
                .getCards()).hasSize(1);
    }

    @Test
    @DisplayName("플레이어 이름으로 해당 플레이어의 보여지는 카드 List를 반환한다")
    void should_return_player_shown_cards_by_name() {
        // given
        String playerName = "a";
        List<String> playerNames = List.of(playerName);
        Participants participants = ParticipantsFixture.createParticipants(playerNames);
        Card cardOfHeartAce = new Card(Shape.HEART, Rank.ACE);
        Card cardOfHeartQueen = new Card(Shape.HEART, Rank.QUEEN);
        CardDeck cardDeck = new CardDeck(List.of(cardOfHeartAce, cardOfHeartQueen));
        BlackJackGame blackJackGame = new BlackJackGame(participants, CardDeckFixture.createCardDeck());
        blackJackGame.giveCardToPlayer(playerName);
        blackJackGame.giveCardToPlayer(playerName);

        // when
        List<Card> playerShownCards = blackJackGame.getPlayerShownCards(playerName);

        // then
        assertThat(playerShownCards).hasSize(2);
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
        assertThat(blackJackGame.getParticipants()
                .get(0)
                .getCards()).hasSize(1);
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
