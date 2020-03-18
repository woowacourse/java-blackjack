package blackjack.domain.result;

import blackjack.domain.card.CardBundle;

interface MoneyRate {
    double getRate(CardBundle cardBundle);
}
