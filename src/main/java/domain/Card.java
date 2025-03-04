package domain;

import constant.CardNumber;
import constant.Emblem;

public record Card(
        CardNumber number,
        Emblem emblem
) {
}
