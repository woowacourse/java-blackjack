public record Card(Rank rank, Suit suit) {
    public boolean isAce() {
        return Rank.isAce(rank);
    }

    public boolean isFaceCard() {
        return Rank.isFaceCard(rank);
    }

    public Integer getScore(){
        return rank.getScore();
    }
}
