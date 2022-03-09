package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJack {
    private static final String ERROR_MESSAGE_PLAYER_NUMBER_EXCEED = "[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.";
    private static final String ERROR_MESSAGE_ILLEGAL_CHOICE_FORMAT = "[ERROR] y 또는 n 으로 입력해야 합니다.";
    private static final String CHOICE_YES = "y";
    private static final String CHOICE_NO = "n";
    private static final int MAX_PLAYER_NUMBER = 8;
    private static final int DEALER_ADDITIONAL_CARD_STANDARD = 16;

    private final Participant dealer;
    private final List<Participant> players;
    private final Map<Participant, Boolean> result;

    private BlackJack(Participant dealer, List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
        this.result = new LinkedHashMap<>();
    }

    public static BlackJack createFrom(List<String> playerNames) {
        validatePlayerNumber(playerNames);
        List<Participant> players = playerNames.stream()
            .map(Participant::createPlayer)
            .collect(Collectors.toList());

        return new BlackJack(Participant.createDealer(), players);
    }

    private static void validatePlayerNumber(List<String> players) {
        if (players.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_NUMBER_EXCEED);
        }
    }

    public void receiveStartingCards() {
        for (int i = 0; i < 2; i++) {
            receiveCard();
        }
    }

    private void receiveCard() {
        handOutCardTo(dealer);
        for (Participant player : players) {
            handOutCardTo(player);
        }
    }

    public void receiveAdditionalCard(Participant player, String choice) {
        choice = choice.toLowerCase(Locale.ROOT);
        checkValidChoice(choice);
        if (choice.equals(CHOICE_YES)) {
            handOutCardTo(player);
        }
    }

    private void checkValidChoice(String choice) {
        if (!(choice.equals(CHOICE_YES) || choice.equals(CHOICE_NO))) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_CHOICE_FORMAT);
        }
    }

    public void handOutCardTo(Participant participant) {
        participant.receiveCard(CardDeck.pickCard());
    }

    public boolean isDealerNeedAdditionalCard() {
        return dealer.getScore() <= DEALER_ADDITIONAL_CARD_STANDARD;
    }

    public Map<Participant, Boolean> getResult() {
        result.clear();
        players.forEach(player -> result.put(player, isWin(player)));
        return result;
    }

    private boolean isWin(Participant player) {
        return dealer.getScore() < player.getScore();
    }


    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }
}
