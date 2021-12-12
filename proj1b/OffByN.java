public class OffByN implements CharacterComparator {

    private int n;

    public void OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}
