package assetpackage;

import animalpackage.Share;

public class ShareItem extends Asset {
    private Share companyShare;
    private long shareAmount; // amount of bought shares
    private long shareValue; // passiver Saldo, d.h. bei Gewinn negativ, bei Verlust positiv
                             // (weil er fuer das Geld steht, das fuer das Aktienpaket ausgegeben
                             // wurde. Wie Schulden zu betrachten)

    /**
     * ShareItem(Share companyShare, long shareAmount)
     */
    public ShareItem(Share companyShare, long shareAmount) {
        super(companyShare.getName());
        this.shareAmount = shareAmount;
        this.companyShare = companyShare;

        shareValue = shareAmount * companyShare.getRate();
    }

    /**
     * getShareAmount()
     */
    public long getShareAmount() {
        return shareAmount;
    }

    /**
     * setShareAmount(long shareAmount)
     */
    public void setShareAmount(long shareAmount) {
        long oldAmount = this.shareAmount;
        this.shareAmount = shareAmount;

        if (shareAmount > oldAmount)
            shareValue = shareValue + shareAmount * companyShare.getRate();
        else
            shareValue = shareValue - (oldAmount - shareAmount) * companyShare.getRate();
    }

    /**
     * getCompanyShare()
     */
    public Share getCompanyShare() {
        return companyShare;
    }

    /**
     * setCompanyShare(Share companyShare)
     */
    public void setCompanyShare(Share companyShare) {
        this.companyShare = companyShare;
    }

    /**
     * getShareValue();
     */
    public long getShareValue() {
        return shareValue;
    }

    /**
     * getCurrentValue()
     */
    public long getCurrentValue() {
        return shareAmount * companyShare.getRate();
    }

    /**
     * String toString()
     */
    public String toString() {
        return (name + " gesamter Saldo: " + shareValue + " Menge: " + shareAmount);
    }
}