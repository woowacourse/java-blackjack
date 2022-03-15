package blackjack.domain.player;

import blackjack.domain.card.CardDeck;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Players {

    private static final String NO_DEALER_MESSAGE = "딜러가 존재하지 않습니다.";

    private final List<Player> value;

    public Players(final Player dealer, final List<Player> gamblers) {
        this.value = concatPlayers(dealer, gamblers);
    }

    private List<Player> concatPlayers(final Player dealer, final List<Player> gamblers) {
        return Stream.concat(
            Stream.of(dealer),
            gamblers.stream()
        ).collect(Collectors.toList());
    }

    public void receiveCard(final CardDeck cardDeck) {
        for (Player player : value) {
            cardDeck.drawTo(player);
        }
    }

    public List<Player> getValue() {
        return value;
    }

    public Dealer getDealer() {
        return (Dealer) value.stream()
            .filter(Player::isDealer)
            .findAny()
            .orElseThrow( () -> new RuntimeException(NO_DEALER_MESSAGE));
    }

    public List<Player> getGamblers() {
        return value.stream()
            .filter(player -> !player.isDealer())
            .collect(Collectors.toList());
    }
}
