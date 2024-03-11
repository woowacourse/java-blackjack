package domain;

import static util.CardsSupplier.createSevenScoreWithTwoCards;
import static util.CardsSupplier.createSixteenScoreWithTwoCards;
import static util.ParticipantSupplier.createDealer;
import static util.ParticipantSupplier.createPlayer;

import controller.dto.InitialCardStatus;
import controller.dto.ParticipantHandStatus;
import domain.card.Card;
import domain.card.Score;
import domain.card.Shape;
import domain.constants.CardCommand;
import domain.game.BlackJackGame;
import domain.game.Deck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RoundTest {

    @DisplayName("게임을 시작하고 참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void initializeBlackJackGame() {
        List<String> playerNames = Arrays.asList("pobi", "jason");
        BlackJackGame blackJackGame = BlackJackGame.from(playerNames);

        InitialCardStatus result = blackJackGame.initialize();

        Assertions.assertThat(result.initialCardSize()).isEqualTo(2);
        for (ParticipantHandStatus status : result.statuses()) {
            Assertions.assertThat(status.hand().size()).isEqualTo(getExpectedSize(status));
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
            BlackJackGame blackJackGame = new BlackJackGame(
                    new Participants(List.of(player)),
                    new Deck(List.of(
                            new Card(Score.TWO, Shape.CLOVER),
                            new Card(Score.FOUR, Shape.HEART),
                            new Card(Score.FOUR, Shape.DIAMOND)
                    ))
            );

            // when
            blackJackGame.giveCard(
                    player,
                    handStatus -> {
                    },
                    () -> CardCommand.HIT
            );

            // then
            Assertions.assertThat(player.getCardSize()).isEqualTo(4);
        }

        @DisplayName("딜러가 아닌 참가자는 카드의 점수가 21 이하여도 STAND 명령을 주면 카드를 그만 받는다.")
        @Test
        void giveCardsToPlayerWhileEnterHit() {
            // given
            Player player = createPlayer("pobi", createSixteenScoreWithTwoCards());
            BlackJackGame blackJackGame = new BlackJackGame(
                    new Participants(List.of(player)),
                    new Deck(List.of(
                            new Card(Score.TWO, Shape.CLOVER),
                            new Card(Score.FOUR, Shape.DIAMOND)
                    ))
            );

            // when
            blackJackGame.giveCard(
                    player,
                    handStatus -> {
                    },
                    () -> CardCommand.STAND
            );

            // then
            Assertions.assertThat(player.getCardSize()).isEqualTo(2);
        }

        @DisplayName("딜러는 카드의 점수가 17 미만인 동안 계속 카드를 받는다.")
        @Test
        void giveCardsToDealerWhileUnderSeventeen() {
            // given
            Dealer dealer = createDealer(createSevenScoreWithTwoCards());
            BlackJackGame blackJackGame = new BlackJackGame(
                    new Participants(List.of(dealer)),
                    new Deck(List.of(
                            new Card(Score.EIGHT, Shape.CLOVER),
                            new Card(Score.FIVE, Shape.DIAMOND),
                            new Card(Score.TWO, Shape.DIAMOND)
                    ))
            );

            // when
            blackJackGame.giveCard(
                    dealer,
                    handStatus -> {
                    },
                    () -> CardCommand.HIT
            );

            // then
            Assertions.assertThat(dealer.getCardSize()).isEqualTo(4);
        }
    }
}
