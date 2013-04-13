/*
 * Asset ist für die Vermögenswerte der Spieler zuständig
 * (abstrakte Klasse)
 */
public abstract class Asset {
    public String name;

    public Asset(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String toString();

    public boolean equals(Asset a) {
        return this.equals(a);
    }
}