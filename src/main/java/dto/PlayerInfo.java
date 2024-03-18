package dto;

import domain.card.Card;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public record PlayerInfo(String name, List<CardInfo> cardInfos) {
    public static PlayerInfo from(final Player player) {
        final List<CardInfo> cardInfos = new ArrayList<>();

        for (final Card card : player.hand()) {
            cardInfos.add(new CardInfo(card.denomination().name(), card.suit().name()));
        }

        return new PlayerInfo(player.name(), cardInfos);
    }
}
