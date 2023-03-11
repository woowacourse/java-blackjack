package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class GameResultTest {

    private Participants participants;
    private Participant leo;
    private Participant dealer;

    @BeforeEach
    void setUp() {
        participants = Participants.from(List.of(Player.create(new Name("leo"), new BetAmount(new BigDecimal(1000)))));
        dealer = participants.findDealer();
        leo = participants.findPlayers().get(0);
    }

    @ParameterizedTest
    @MethodSource("bustStatusCardProvider")
    @DisplayName("참가자의 카드 합이 21이 넘으면 버스트이다.")
    void getParticipantsBustStatus(Card card, boolean isBust) {
        leo.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        leo.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        leo.receiveCard(card);

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), participants.findDealer());
        final Map<Participant, Boolean> participantsBustStatus = gameResult.getParticipantsBustStatus();

        Assertions.assertThat(participantsBustStatus.get(leo)).isEqualTo(isBust);
    }

    @Test
    void playerWinBlackjack() {
        leo.receiveCard(new Card(Suit.SPADE,Rank.KING));
        leo.receiveCard(new Card(Suit.SPADE,Rank.ACE));

        dealer.receiveCard(new Card(Suit.SPADE,Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART,Rank.KING));

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        Map<Participant, Integer> playerStatus = gameResult.getPlayerStatus();

        Assertions.assertThat(playerStatus.get(leo)).isEqualTo(1500);
    }

    @Test
    void playerDrawBlackjack() {
        leo.receiveCard(new Card(Suit.SPADE,Rank.KING));
        leo.receiveCard(new Card(Suit.SPADE,Rank.ACE));

        dealer.receiveCard(new Card(Suit.HEART,Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART,Rank.ACE));

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        Map<Participant, Integer> playerStatus = gameResult.getPlayerStatus();

        Assertions.assertThat(playerStatus.get(leo)).isEqualTo(1000);
    }

    @Test
    void playerLoseBust() {
        leo.receiveCard(new Card(Suit.SPADE,Rank.KING));
        leo.receiveCard(new Card(Suit.SPADE,Rank.JACK));
        leo.receiveCard(new Card(Suit.SPADE,Rank.TWO));

        dealer.receiveCard(new Card(Suit.HEART,Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART,Rank.ACE));

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        Map<Participant, Integer> playerStatus = gameResult.getPlayerStatus();

        Assertions.assertThat(playerStatus.get(leo)).isEqualTo(-1000);
    }

    @Test
    void playerDrawHandValue() {
        leo.receiveCard(new Card(Suit.SPADE,Rank.KING));
        leo.receiveCard(new Card(Suit.SPADE,Rank.JACK));

        dealer.receiveCard(new Card(Suit.HEART,Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART,Rank.JACK));

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        Map<Participant, Integer> playerStatus = gameResult.getPlayerStatus();

        Assertions.assertThat(playerStatus.get(leo)).isEqualTo(1000);
    }

    @Test
    void playerWinHandValue() {
        leo.receiveCard(new Card(Suit.SPADE,Rank.KING));
        leo.receiveCard(new Card(Suit.SPADE,Rank.JACK));

        dealer.receiveCard(new Card(Suit.HEART,Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART,Rank.NINE));

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        Map<Participant, Integer> playerStatus = gameResult.getPlayerStatus();

        Assertions.assertThat(playerStatus.get(leo)).isEqualTo(1000);
    }

    @Test
    void playerLoseHandValue() {
        leo.receiveCard(new Card(Suit.SPADE,Rank.KING));
        leo.receiveCard(new Card(Suit.SPADE,Rank.NINE));

        dealer.receiveCard(new Card(Suit.HEART,Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART,Rank.TEN));

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        Map<Participant, Integer> playerStatus = gameResult.getPlayerStatus();

        Assertions.assertThat(playerStatus.get(leo)).isEqualTo(-1000);
    }

    @Test
    void playerWinHandCount() {
        leo.receiveCard(new Card(Suit.SPADE,Rank.KING));
        leo.receiveCard(new Card(Suit.SPADE,Rank.JACK));

        dealer.receiveCard(new Card(Suit.HEART,Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART,Rank.EIGHT));
        dealer.receiveCard(new Card(Suit.HEART,Rank.TWO));

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        Map<Participant, Integer> playerStatus = gameResult.getPlayerStatus();

        Assertions.assertThat(playerStatus.get(leo)).isEqualTo(1000);
    }

    @Test
    void playerLoseHandCount() {
        leo.receiveCard(new Card(Suit.SPADE,Rank.KING));
        leo.receiveCard(new Card(Suit.HEART,Rank.EIGHT));
        leo.receiveCard(new Card(Suit.HEART,Rank.TWO));


        dealer.receiveCard(new Card(Suit.HEART,Rank.KING));
        dealer.receiveCard(new Card(Suit.SPADE,Rank.JACK));

        final GameResult gameResult = new GameResult(participants.makePlayerFinalHandValue(), dealer);
        Map<Participant, Integer> playerStatus = gameResult.getPlayerStatus();

        Assertions.assertThat(playerStatus.get(leo)).isEqualTo(-1000);
    }

    private static Stream<Arguments> bustStatusCardProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.CLOVER, Rank.TWO), true),
                Arguments.of(new Card(Suit.HEART, Rank.ACE), false)
        );
    }
    
}
