package animalpackage;

public class Share {
    private final String name; // company name, final (can only be set by constructor)
    private long rate;

    /**
     * Share(String name, long rate)
     */
    public Share(String name, long rate) {
        this.name = name;
        this.rate = rate;
    }

    /**
     * getName();
     */
    public String getName() {
        return name;
    }

    /**
     * getRate();
     */
    public long getRate() {
        return rate;
    }

    /**
     * setRate();
     */
    public void setRate(long rate) {
        this.rate = rate;
    }

    /**
     * toString();
     */
    public String toString() {
        String rateInEuro = "" + rate/100;
        if (rate%100 < 10)
            rateInEuro += "." + "0" + rate%100;
        else
            rateInEuro += "." + rate%100;
        return (name + ": " + rateInEuro + "€");
    }
}
