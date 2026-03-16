package domain.participant;

import static config.BlackjackGameConstant.INITIAL_CARD_DRAW_COUNT;

import domain.card.CardDeck;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Players {

    private final List<Player> playerList;

    private Players(List<Player> playerList) {
        this.playerList = playerList;
    }

    public static Players from(List<ParticipantInitialInformation> initialInformation) {
        return new Players(initialInformation.stream()
                .map(ParticipantInitialInformation::toPlayer)
                .toList()
        );
    }

    public Stream<Player> stream() {
        return playerList.stream();
    }

    public Players giveInitialCardBundle(CardDeck cardDeck) {
        playerList.forEach(player -> player.drawCards(cardDeck, INITIAL_CARD_DRAW_COUNT));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(playerList, players1.playerList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerList);
    }
}
