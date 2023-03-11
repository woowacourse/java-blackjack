package blackjack.controller.dto;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.PlayerName;

public class ParticipantResponse {

    private static final int HIDDEN_SCORE = -1;

    private final String name;
    private final CardsResponse cardsResponse;

    private ParticipantResponse(final String name, final CardsResponse cardsResponse) {
        this.name = name;
        this.cardsResponse = cardsResponse;
    }

    public static ParticipantResponse ofPlayer(final PlayerName playerName, final BlackJackGame blackJackGame) {
        final int score = blackJackGame.showPlayerScore(playerName);
        final Hand hand = blackJackGame.showPlayerHand(playerName);
        return new ParticipantResponse(playerName.getValue(), CardsResponse.of(score, hand));
    }

    public static ParticipantResponse ofDealerForHidden(final BlackJackGame blackJackGame) {
        final Hand hand = blackJackGame.getDealerHiddenHand();
        return new ParticipantResponse(blackJackGame.getDealerName(), CardsResponse.of(HIDDEN_SCORE, hand));
    }

    public String getName() {
        return name;
    }

    public CardsResponse getCardsResponse() {
        return cardsResponse;
    }
}
