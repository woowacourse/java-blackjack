package blackjackgame.domain.game;

import blackjackgame.domain.user.DealerStatus;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.Players;
import blackjackgame.domain.user.User;
import blackjackgame.domain.user.dto.NameDto;
import blackjackgame.domain.user.dto.ProfitDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Cards cards;

    public BlackJackGame(Players players, Dealer dealer, Cards cards) {
        this.players = players;
        this.dealer = dealer;
        this.cards = cards;
    }

    public void drawDefaultCard() {
        dealer.receiveCards(cards.drawCards(INITIAL_CARD_COUNT));
        for (Player player : players.getPlayers()) {
            player.receiveCards(cards.drawCards(INITIAL_CARD_COUNT));
        }
    }

    public void hit(User user) {
        user.receiveCard(cards.drawCard());
    }

    public void hitUntilSatisfyingDealerMinimumScore() {
        while (DealerStatus.UNDER_MIN_SCORE.equals(dealer.getStatus())) {
            dealer.receiveCard(cards.drawCard());
        }
    }

    public void judgeWinner() {
        Referee referee = new Referee(players, dealer);
        referee.judgeWinner();
    }

    public Map<NameDto, List<Card>> getSetUpResult() {
        Map<NameDto, List<Card>> setUpResult = new LinkedHashMap<>();

        setUpResult.put(new NameDto(dealer.getName()), List.of(dealer.getFirstCard()));

        for (Player player : players.getPlayers()) {
            setUpResult.put(new NameDto(player.getName()), player.cards());
        }

        return setUpResult;
    }

   public Map<NameDto, ProfitDto> getBetResult() {
       FinalResult finalResult = new FinalResult(players, dealer);
       Map<User, ProfitDto> betResultByUser = finalResult.getBetResultByPlayer();

       Map<NameDto, ProfitDto> betResultByName = new LinkedHashMap<>();
       for (Entry<User, ProfitDto> betResutByUserEntry : betResultByUser.entrySet()) {
           String userName = betResutByUserEntry.getKey().getName();
           betResultByName.put(new NameDto(userName), betResutByUserEntry.getValue());
       }

       return betResultByName;
   }

    public int getDealerExtraDrawCount() {
        return dealer.getExtraDrawCount(INITIAL_CARD_COUNT);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
