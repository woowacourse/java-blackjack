package blackjack.domain.participant;

import static blackjack.domain.participant.Dealer.INIT_CARD_COUNT;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Players {

    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 8;

    private final List<Player> players;

    public Players(final List<String> names) {
        validateLength(names);
        this.players = names.stream()
                .map(Player::new)
                .collect(toList());
    }

    private void validateLength(final List<String> names) {
        if (MAX_PLAYER_COUNT < names.size() || names.size() < MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참가자의 수는 최소 " + MIN_PLAYER_COUNT + "명에서 최대 " + MAX_PLAYER_COUNT + "명이어야 합니다. 입력값: " + names);
        }
    }

    public void receiveSettingCards(final List<Card> cards) {
        validateSize(cards);

        int playerIndex = 0;
        for (int cardIndex = 0; cardIndex < cards.size(); cardIndex += INIT_CARD_COUNT) {
            Player player = players.get(playerIndex);
            player.receiveCard(cards.get(cardIndex));
            player.receiveCard(cards.get(cardIndex + 1));
            playerIndex++;
        }
    }

    private void validateSize(final List<Card> cards) {
        if (cards.size() != players.size() * INIT_CARD_COUNT) {
            throw new IllegalArgumentException("[ERROR] 초기 세팅 카드의 개수는 인원수의 " + INIT_CARD_COUNT + "배여야 합니다. 입력값:" + cards);
        }
    }

    public Map<Player, Result> makeResult(final int dealerScore) {
        return Result.makeResult(this.players, dealerScore);
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
