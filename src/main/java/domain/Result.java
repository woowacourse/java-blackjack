package domain;

import java.util.List;

public class Result {
    private User user;
    private List<Boolean> winningData;

    public Result(User user, List<Boolean> winningData) {
        this.user = user;
        this.winningData = winningData;
    }

    @Override
    public String toString() {
        return "user=" + user.getName() +
                ", winningData=" + winningData ;
    }
}
