package domain.blackjackgame;

import static domain.card.Denomination.EIGHT;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.KING;
import static domain.card.Denomination.NINE;
import static domain.card.Denomination.TEN;
import static fixture.CardFixture.카드덱;
import static fixture.ParticipantFixture.참가자들;
import static fixture.ParticipantFixture.플레이어;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.deck.CardDeck;
import domain.card.deck.CardDeckFactory;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    @Test
    void 게임을_시작하면_딜러와_플레이어들에게_카드를_두_장씩_나눠준다() {
        CardDeck cardDeck = CardDeckFactory.createCardDeck(cards -> {
        });
        BlackjackGame blackjackGame = new BlackjackGame(cardDeck);
        Participants participants = 참가자들("뽀로로", "프린", "포비");

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

    @Test
    void 딜러는_플레이어가_얻은_수익만큼_잃는다() {
        CardDeck cardDeck = 카드덱(JACK, KING, NINE, TEN, EIGHT, NINE);
        BlackjackGame blackjackGame = new BlackjackGame(cardDeck);
        Participants participants = 참가자들(플레이어("프린", 10000), 플레이어("뽀로로", 10000));

        blackjackGame.initGame(participants);

        GameResult gameResult = blackjackGame.createGameResult(participants);
        assertThat(gameResult.getDealerResult()).isEqualTo(-20000);
    }
}
