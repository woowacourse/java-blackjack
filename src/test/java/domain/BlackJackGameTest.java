package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static util.CardsSupplier.createSevenScoreWithTwoCards;
import static util.CardsSupplier.createSixteenScoreWithTwoCards;
import static util.ParticipantSupplier.createDealer;
import static util.ParticipantSupplier.createPlayer;

import controller.dto.InitialCardStatus;
import controller.dto.ParticipantHandStatus;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.constants.CardCommand;
import domain.game.BlackJackGame;
import domain.game.deck.Deck;
import domain.game.deck.DeckGenerator;
import domain.game.deck.RandomDeckGenerator;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {
    @DisplayName("게임을 시작하고 참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void initializeBlackJackGame() {
        List<String> playerNames = Arrays.asList("pobi", "jason");
        BlackJackGame blackJackGame = BlackJackGame.from(playerNames, new RandomDeckGenerator());

        InitialCardStatus result = blackJackGame.initialize();

        assertThat(result.initialCardSize()).isEqualTo(2);
        for (ParticipantHandStatus status : result.statuses()) {
            assertThat(status.hand().size()).isEqualTo(getExpectedSize(status));
        }
    }

    private int getExpectedSize(final ParticipantHandStatus status) {
        if (status.name().equals(Dealer.DEALER_NAME)) {
            return 1;
        }
        return 2;
    }

    @DisplayName("딜러와 플레이어가 돌아가면서 카드를 받는 테스트")
    @Nested
    class GiveCardTest {
        @DisplayName("딜러가 아닌 참가자는  HIT 명령을 주면 카드의 점수가 21 이하인 동안 카드를 계속 받는다.")
        @Test
        void giveCardsToPlayerWhileNotBusted() {
            // given
            Player player = createPlayer("pobi", createSixteenScoreWithTwoCards());
            CustomizedDeckGenerator generator = new CustomizedDeckGenerator();
            BlackJackGame blackJackGame = new BlackJackGame(
                    new Participants(List.of(player)),
                    generator.generate(
                            new Card(Denomination.TWO, Suit.CLOVER),
                            new Card(Denomination.FOUR, Suit.HEART),
                            new Card(Denomination.FOUR, Suit.DIAMOND)
                    )
            );

            // when
            blackJackGame.giveCard(
                    player,
                    handStatus -> {
                    },
                    () -> CardCommand.HIT
            );

            // then
            assertThat(player.getCardSize()).isEqualTo(4);
        }

        @DisplayName("딜러가 아닌 참가자는 카드의 점수가 21 이하여도 STAND 명령을 주면 카드를 그만 받는다.")
        @Test
        void giveCardsToPlayerWhileEnterHit() {
            // given
            Player player = createPlayer("pobi", createSixteenScoreWithTwoCards());
            CustomizedDeckGenerator generator = new CustomizedDeckGenerator();
            BlackJackGame blackJackGame = new BlackJackGame(
                    new Participants(List.of(player)),
                    generator.generate(
                            new Card(Denomination.TWO, Suit.CLOVER),
                            new Card(Denomination.FOUR, Suit.DIAMOND)
                    )
            );

            // when
            blackJackGame.giveCard(
                    player,
                    handStatus -> {
                    },
                    () -> CardCommand.STAND
            );

            // then
            assertThat(player.getCardSize()).isEqualTo(2);
        }

        @DisplayName("딜러는 카드의 점수가 17 미만인 동안 계속 카드를 받는다.")
        @Test
        void giveCardsToDealerWhileUnderSeventeen() {
            // given
            Dealer dealer = createDealer(createSevenScoreWithTwoCards());
            CustomizedDeckGenerator generator = new CustomizedDeckGenerator();
            BlackJackGame blackJackGame = new BlackJackGame(
                    new Participants(List.of(dealer)),
                    generator.generate(
                            new Card(Denomination.EIGHT, Suit.CLOVER),
                            new Card(Denomination.FIVE, Suit.DIAMOND),
                            new Card(Denomination.TWO, Suit.DIAMOND)
                    )
            );

            // when
            blackJackGame.giveCard(
                    dealer,
                    handStatus -> {
                    },
                    () -> CardCommand.HIT
            );

            // then
            assertThat(dealer.getCardSize()).isEqualTo(4);
        }
    }

    static class CustomizedDeckGenerator implements DeckGenerator {

        @Override
        public Deck generate(Card... cards) {
            return new Deck(List.of(cards));
        }
    }
}
