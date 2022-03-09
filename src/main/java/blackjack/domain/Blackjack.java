package blackjack.domain;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Blackjack {
    private static final String ERROR_MESSAGE_PLAYER_NUMBER_EXCEED = "[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.";
    private static final String ERROR_MESSAGE_ILLEGAL_CHOICE_FORMAT = "[ERROR] y 또는 n 으로 입력해야 합니다.";
    private static final String CHOICE_YES = "y";
    private static final String CHOICE_NO = "n";
    private static final int MAX_PLAYER_NUMBER = 8;
    private static final int DEALER_ADDITIONAL_CARD_STANDARD = 16;

    private final Participant dealer;
    private final List<Participant> players;
    private final CardDeck cardDeck;

    private Blackjack(List<Participant> players) {
        this.dealer = Participant.createDealer();
        this.players = players;
        this.cardDeck = new CardDeck();
    }

    public static Blackjack createFrom(List<String> playerNames) {
        validatePlayerNumber(playerNames);
        List<Participant> players = playerNames.stream()
            .map(Participant::createPlayer)
            .collect(Collectors.toList());

        return new Blackjack(players);
    }

    private static void validatePlayerNumber(List<String> players) {
        if (players.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_NUMBER_EXCEED);
        }
    }

    public void handOutStartingCards() {
        for (int i = 0; i < 2; i++) {
            handOutCard();
        }
    }

    private void handOutCard() {
        dealer.receiveCard(cardDeck.pickCard());
        for (Participant player : players) {
            player.receiveCard(cardDeck.pickCard());
        }
    }

    public void handOutAdditionalCard(Participant player, String choice) {
        choice = choice.toLowerCase(Locale.ROOT);
        checkValidChoice(choice);
        if (choice.equals(CHOICE_YES)) {
            player.receiveCard(cardDeck.pickCard());
        }
    }

    private void checkValidChoice(String choice) {
        if (!(choice.equals(CHOICE_YES) || choice.equals(CHOICE_NO))) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_CHOICE_FORMAT);
        }
    }

    public void handOutAdditionalCard() {
        if (isDealerNeedAdditionalCard()) {
            dealer.receiveCard(cardDeck.pickCard());
        }
    }

    private boolean isDealerNeedAdditionalCard() {
        return dealer.getScore() <= DEALER_ADDITIONAL_CARD_STANDARD;
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }
}
