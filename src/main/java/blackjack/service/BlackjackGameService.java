package blackjack.service;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Card;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.user.Name;
import blackjack.domain.user.Participant;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.dto.CardDto;
import blackjack.dto.UserCardsDto;

import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.*;

public class BlackjackGameService {
    private static final int DEALER_MINIMUM_SCORE = 16;

    private BlackjackGame blackjackGame;

    private BlackjackGameService(Users users) {
        blackjackGame = BlackjackGame.create(users);
    }

    public static BlackjackGameService createByUsers(Users users) {
        return new BlackjackGameService(users);
    }

    public void firstDraw() {
        blackjackGame.firstDraw();
    }

    public List<Name> getUserNameList() {
        return blackjackGame.getUsersStream()
                .map(Participant::getName)
                .collect(toList());
    }

    public CardDto getDealerOpenedCard() {
        Card dealerFirstCard = blackjackGame.getDealer().getFirstCard();
        return new CardDto(dealerFirstCard.getSuitLetter(), dealerFirstCard.getValueLetter());
    }

    public UserCardsDto getAllUserCurrentCards() {
        return blackjackGame.getUsersStream()
                .collect(
                        collectingAndThen(
                                toMap(
                                        user -> user.getName().toString()
                                        , this::createCardDtos
                                ), UserCardsDto::new));
    }

    private List<CardDto> createCardDtos(User user) {
        return user.getCards().stream()
                .map(card -> new CardDto(card.getSuitLetter(), card.getValueLetter()))
                .collect(toList());
    }

    public boolean existCanContinueUser() {
        return blackjackGame.existCanContinueUser();
    }

    public Name getCurrentUserName() {
        return blackjackGame.findFirstCanPlayUser().getName();
    }

    public void drawCardToDealer() {
        blackjackGame.drawToDealer();
    }

    public boolean canDealerMoreDraw() {
        return blackjackGame.getDealer().calculateScore() <= DEALER_MINIMUM_SCORE;
    }

    public ScoreBoard createScoreBoard() {
        return blackjackGame.createScoreBoard();
    }

    public UserCardsDto progressTurnAndGetResult(Boolean isContinue) {
        User playingUser = blackjackGame.findFirstCanPlayUser();
        userDrawOrStop(isContinue, playingUser);

        HashMap<String, List<CardDto>> userNameAndCards = new HashMap<>();
        userNameAndCards.put(playingUser.getName().toString(), createCardDtos(playingUser));
        return new UserCardsDto(userNameAndCards);
    }

    private void userDrawOrStop(Boolean isContinue, User playingUser) {
        if (isContinue) {
            playingUser.drawCard(blackjackGame.draw());
            return;
        }
        playingUser.stopUser();
    }
}
