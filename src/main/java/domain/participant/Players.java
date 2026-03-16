package domain.participant;


import domain.card.Card;
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

    public Players drawCards(CardDeck cardDeck, int count) {
        playerList.forEach(player -> {
            List<Card> cards = cardDeck.draw(count);
            player.drawCards(cards);
        });
        return this;
    }

    public List<ParticipantName> getPlayerNames() {
        return playerList.stream()
                .map(Participant::getName)
                .toList();
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
