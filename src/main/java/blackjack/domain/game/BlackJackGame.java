package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Players;
import blackjack.view.DrawCommand;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final GameParticipants gameParticipants;
    private final Deck deck;

    private BlackJackGame(final GameParticipants gameParticipants, final Deck deck) {
        this.gameParticipants = gameParticipants;
        this.deck = deck;
    }

    public static BlackJackGame of(final List<String> playerNames, final Deck deck) {
        return new BlackJackGame(new GameParticipants(Players.from(playerNames)), deck);
    }

    public void distributeInitialCards() {
        gameParticipants.distributeInitialCards(deck);
    }


    /**
     * 질문 :
     * find로 시작하는 메서드의 경우 대부분 view에 필요한 값을 반환하고
     * gameParticipants의 메서드를 그대로 리턴합니다.
     * <p>
     * 예를들어, 딜러의 initial 카드 + 플레이어의 initial 카드를 gameParticipants에서 받아오고
     * 이를 조합하여 일종의 dto와 같은, view를 위한 객체(예를들면 InitialCardsOfGame)를 블랙잭 내부에서 생성해 컨트롤러에 반환하는 것은
     * 문제가 없을까요?
     * 예시)
     * `public InitialCardsOfGame findInitialCardsOfGame() {
     * Card initialCardOfDealer = gameParticipants.getDealerCards().get(0);
     * Map<String, List<Card>> initialCardsOfPlayers = gameParticipants.getPlayerCards();
     * return new InitialCardsOfGame(initialCardOfDealer, initialCardsOfPlayers);`
     * }
     */

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

    public void drawCardOfPlayerByName(final String playerName, final DrawCommand drawCommand) {
        if (drawCommand == DrawCommand.DRAW) {
            gameParticipants.drawCardOfPlayerByName(playerName, deck);
        }
    }

    public List<Card> findCardsOfPlayerByName(final String playerName) {
        return gameParticipants.findCardsOfPlayerByName(playerName);
    }

    public int findDealerDrawCount() {
        return gameParticipants.findDealerDrawCount(deck);
    }

    public int findDealerDrawPoint() {
        return gameParticipants.findDealerDrawPoint();
    }

    public List<Card> findDealerCard() {
        return gameParticipants.findDealerCard();
    }

    public int findDealerScore() {
        return gameParticipants.findDealerScore();
    }

    public Map<Map<String, List<Card>>, Integer> findPlayerStatusByName() {
        return gameParticipants.findPlayerStatusByName();
    }

    public ResultOfGame findResultOfGame() {
        return gameParticipants.findResultOfGame();
    }
}
