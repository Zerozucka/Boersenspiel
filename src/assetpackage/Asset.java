package assetpackage;


public abstract class Asset {
    public String name;

    /**
     * Asset(String name)
     */
    public Asset(String name) {
        this.name = name;
    }

    /**
     * getName()
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * toString
     */
    public abstract String toString();

    /**
     * equals(Asset a)
     */
    public boolean equals(Asset a) {
        return this.equals(a);
    }
}