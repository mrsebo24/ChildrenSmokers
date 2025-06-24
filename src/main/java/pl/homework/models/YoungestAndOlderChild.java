package pl.homework.models;

public class YoungestAndOlderChild {
    private Child youngest;
    private Child older;

    public YoungestAndOlderChild(Child youngest, Child older) {
        this.youngest = youngest;
        this.older = older;
    }

    public Child getYoungest() {
        return youngest;
    }

    public void setYoungest(Child youngest) {
        this.youngest = youngest;
    }

    public Child getOlder() {
        return older;
    }

    public void setOlder(Child older) {
        this.older = older;
    }

    @Override
    public String toString() {
        return "YoungestAndOlderChild{" +
                "youngest=" + youngest +
                ", older=" + older +
                '}';
    }
}
