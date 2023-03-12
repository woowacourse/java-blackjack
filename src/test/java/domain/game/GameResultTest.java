package domain.game;

import domain.card.Deck;
import domain.card.Rank;
import domain.card.TestCardGenerator;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.info.PlayerInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

class GameResultTest {

    private GameResult gameResult;

    @BeforeEach
    public void beforeEach() {
        //given
        final List<Participant> participants = List.of(createParticipantWith("준팍", List.of(Rank.FIVE, Rank.EIGHT)),
                createParticipantWith("범블비", List.of(Rank.THREE, Rank.NINE)),
                createParticipantWith("파워", List.of(Rank.TWO, Rank.SEVEN)),
                createParticipantWith("서브웨이", List.of(Rank.JACK, Rank.TEN)));

        gameResult = GameResult.of(createDealerWith(List.of(Rank.ACE, Rank.ACE)), participants);
    }


    @Test
    @DisplayName("패자 리스트를 반환한다.")
    void getLosersTest() {
        //when
        final List<String> resultNames = gameResult.getLosers().stream()
                .map(Participant::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("파워"));
    }

    @Test
    @DisplayName("승자 리스트를 반환한다.")
    void getWinnersTest() {
        //when
        final List<String> resultNames = gameResult.getWinners().stream()
                .map(Participant::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("준팍", "서브웨이"));
    }

    @Test
    @DisplayName("무승부 리스트를 반환한다.")
    void getDrawParticipantsTest() {
        //when
        final List<String> resultNames = gameResult.getDrawParticipants().stream()
                .map(Participant::getName)
                .collect(toList());

        //then
        assertThat(resultNames).isEqualTo(List.of("범블비"));
    }

    private static Dealer createDealerWith(final List<Rank> ranks) {
        final Dealer dealer = Dealer.create();

        final Deck dealerDeck = Deck.from(TestCardGenerator.from(ranks));
        ranks.forEach(i -> dealer.takeCard(dealerDeck.dealCard()));

        return dealer;
    }

    private static Participant createParticipantWith(final String name, final List<Rank> ranks) {
        final Participant participant = Participant.of(getPlayerInfo(name));

        final Deck participantDeck = Deck.from(TestCardGenerator.from(ranks));
        ranks.forEach(i -> participant.takeCard(participantDeck.dealCard()));

        return participant;
    }

    private static PlayerInfo getPlayerInfo(final String name) {
        return new PlayerInfo.PlayerInfoBuilder(name)
                .setBetAmount(1000)
                .build();
    }

}
