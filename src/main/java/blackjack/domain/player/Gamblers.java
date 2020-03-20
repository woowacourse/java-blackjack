package blackjack.domain.player;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.util.NullChecker;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Gamblers {

    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final String OUT_OF_RANGE_EXCEPTION_MESSAGE =
        String.format("참여 인원은 %d명 이상, %d명 이하이어야 합니다.", MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT);

    private final List<Gambler> gamblers;

    public Gamblers(Map<Name, BettingMoney> playerInfo) {
        validatePlayerInfo(playerInfo);
        this.gamblers = Collections.unmodifiableList(playerInfo.keySet().stream()
            .map(playerName -> new Gambler(playerName, playerInfo.get(playerName)))
            .collect(Collectors.toList()));
    }

    private void validatePlayerInfo(Map<Name, BettingMoney> playerInfo) {
        NullChecker.validateNotNull(playerInfo);
        if (playerInfo.size() < MINIMUM_PLAYER_COUNT || playerInfo.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(OUT_OF_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (Gambler gambler : gamblers) {
            gambler.drawCard(cardDeck, cardCount);
        }
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }

    public List<String> getGamblesNames() {
        return Collections.unmodifiableList(gamblers.stream()
            .map(Gambler::getName)
            .map(Name::toString)
            .collect(Collectors.toList()));
    }
}
