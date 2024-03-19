package domain.blackjackgame;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.EIGHT;
import static domain.card.Denomination.FIVE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.KING;
import static domain.card.Denomination.NINE;
import static domain.card.Denomination.SEVEN;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Denomination;
import domain.card.deck.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import fixture.CardFixture;
import fixture.ParticipantFixture;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    @Test
    void 게임을_시작하면_딜러와_플레이어들에게_카드를_두_장씩_나눠준다() {
        CardDeck cardDeck = createCardDeck(ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT);
        BlackjackGame blackjackGame = new BlackjackGame(cardDeck);
        Participants participants = createParticipants("뽀로로", "프린", "포비");

        blackjackGame.initGame(participants);

        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        assertAll(
                () -> assertThat(dealer.getAllCards()).hasSize(2),
                () -> assertThat(players.get(0).getAllCards()).hasSize(2),
                () -> assertThat(players.get(1).getAllCards()).hasSize(2),
                () -> assertThat(players.get(2).getAllCards()).hasSize(2)
        );
    }

    private CardDeck createCardDeck(Denomination... denominations) {
        return new CardDeck(Arrays.stream(denominations)
                .map(CardFixture::카드)
                .toList());
    }

    private Participants createParticipants(String... playerNames) {
        return Arrays.stream(playerNames)
                .map(ParticipantFixture::플레이어)
                .collect(collectingAndThen(toList(), Participants::new));
    }

    @Test
    void 딜러는_플레이어가_얻은_수익만큼_잃는다() {
        CardDeck cardDeck = createCardDeck(JACK, KING, NINE, TEN, EIGHT, NINE);
        BlackjackGame blackjackGame = new BlackjackGame(cardDeck);
        Participants participants = createParticipants(new Player("prin", 10000), new Player("roro", 10000));

        blackjackGame.initGame(participants);

        GameResult gameResult = blackjackGame.createGameResult(participants);
        assertThat(gameResult.getDealerResult()).isEqualTo(BigDecimal.valueOf(-20000));
    }

    private Participants createParticipants(Player... players) {
        return new Participants(Arrays.asList(players));
    }
}
