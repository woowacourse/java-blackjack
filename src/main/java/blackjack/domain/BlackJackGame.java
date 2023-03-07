package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participants.GameParticipants;
import blackjack.domain.participants.ResultOfGame;
import blackjack.view.DrawCommand;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final GameParticipants gameParticipants;
    private final Deck deck;

    public BlackJackGame(final GameParticipants gameParticipants, final Deck deck) {
        this.gameParticipants = gameParticipants;
        this.deck = deck;
    }

    public void distributeInitialCards() {
        gameParticipants.distributeInitialCards(deck);
    }

    public Card findDealerInitialCard() {
        return gameParticipants.findDealerCard()
                .get(0);
    }

    public Map<String, List<Card>> findPlayerNameAndCards() {
        return gameParticipants.findPlayerNameAndCards();
    }

    public List<String> findPlayerNames() {
        return gameParticipants.findPlayerNames();
    }

    public boolean isPlayerDrawable(final String playerName) {
        return gameParticipants.isPlayerDrawable(playerName);
    }

    public void drawCardOf(final String playerName, final DrawCommand drawCommand) {
        if (drawCommand == DrawCommand.DRAW) {
            gameParticipants.drawCardOf(playerName, deck.popCard());
        }
    }

    public List<Card> findCardsByPlayerName(final String playerName) {
        return gameParticipants.findCardsByPlayerName(playerName);
    }

    public int findDealerDrawCount() {
        return gameParticipants.findDealerDrawCount(deck);
    }

    //최종 결과
    public List<Card> findDealerCard() {
        return gameParticipants.findDealerCard();
    }

    public int findDealerScore() {
        return gameParticipants.findDealerScore();
    }

    public Map<Map<String, List<Card>>, Integer> findPlayerStatusByName() {
        return gameParticipants.findPlayerStatusByName();
    }

    //
    public ResultOfGame findResultOfGame() {
        return gameParticipants.findResultOfGame();
    }
}
