package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import config.CardDeckFactory;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayesTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsTest(){
        //given
        Players players = new Players(new ArrayList<>(
                List.of(new Player("pobi"), new Player("lisa"))
        ));

        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();

        Dealer dealer = new Dealer(cardDeck);

        //when
        players.hitCards(dealer);

        //then
        List<Player> playerList = players.getPlayers();

        assertSoftly(softly -> {
            softly.assertThat(playerList.getFirst().getCardDeck().getCardsSize()).isEqualTo(2);
            softly.assertThat(playerList.get(1).getCardDeck().getCardsSize()).isEqualTo(2);
        });

    }
}
