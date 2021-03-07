package blackjack.domain;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.ResultDto;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackManager {

    private static final int INIT_CARD_COUNT = 2;
    private static final String DELIMITER = " ";

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackManager(final Dealer dealer, final Players players) {
        this(CardDeck.newShuffledDeck(), dealer, players);
    }

    private BlackjackManager(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public void initDrawCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            this.dealer.addCard(cardDeck.draw());
            this.players.drawCards(cardDeck);
        }
    }

    public void drawCardDealer() {
        this.dealer.addCard(this.cardDeck.draw());
    }

    public void drawCardPlayer(final Player player) {
        this.players
            .findPlayerByName(new Name(player.getName()))
            .addCard(this.cardDeck.draw());
    }

    public boolean isDealerHitable() {
        return this.dealer.isHitable();
    }

    public ParticipantDto createDealerDto() {
        return new ParticipantDto(createCardDtos(this.dealer.getCards()), this.dealer.getScore());
    }

    public ParticipantDto createPlayerDto(final Player player) {
        return new ParticipantDto(player.getName(), createCardDtos(player.getCards()),
            player.getScore());
    }

    public List<ParticipantDto> createPlayerDtos() {
        return players.toList()
            .stream()
            .map(this::createPlayerDto)
            .collect(Collectors.toList());
    }

    private List<CardDto> createCardDtos(final List<Card> cards) {
        return cards.stream()
            .map(card -> new CardDto(card.getNumberName() + card.getPatternName()))
            .collect(Collectors.toList());
    }

    public ResultDto createDealerResultDto() {
        List<Result> results = getResults();
        return new ResultDto(
            getResultString(results, Result.WIN) + DELIMITER
                + getResultString(results, Result.LOSE) + DELIMITER
                + getResultString(results, Result.DRAW)
        );
    }

    private List<Result> getResults() {
        return this.players
            .toList()
            .stream()
            .map(this.dealer::judge)
            .map(Result::reverse)
            .collect(Collectors.toList());
    }

    private String getResultString(final List<Result> results, final Result result) {
        return results.stream()
            .filter(compareResult -> compareResult.equals(result))
            .count() + result.getResult();
    }

    public List<ResultDto> createPlayerResultDtos() {
        return this.players
            .toList()
            .stream()
            .map(player -> new ResultDto(player.getName(), this.dealer.judge(player).getResult()))
            .collect(Collectors.toList());
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(this.players
            .toList()
            .stream()
            .map(Player::getName)
            .collect(Collectors.toList())
        );
    }

    public Player findPlayerByName(final String name) {
        return this.players.findPlayerByName(new Name(name));
    }
}
