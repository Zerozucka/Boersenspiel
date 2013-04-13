public class ShareItem extends Asset {
    private Share companyShare;
    private long shareAmount; // amount of bought shares
    /*
     * ShareValue =^ passiver saldo, d.h. bei gewinn Negativ, bei verlust Positiv (weil er f�r das Geld steht, das f�r das Aktienpaket ausgegebene wurde. wie shulden Betrachten) wird wenn ich alles richtig in erinnerung haben auch so in der BWL gehandhabt ^^
     */
    private long shareValue; // value of all bought shares in cent

    /*
     * Konstruktor
     */
    public ShareItem(Share companyShare, long shareAmount) {
        super(companyShare.getName());
        this.shareAmount = shareAmount;
        this.companyShare = companyShare;

        shareValue = shareAmount * companyShare.getRate();
    }

    /*
     * getShareAmount();
     */
    public long getShareAmount() {
        return shareAmount;
    }

    /*
     * setShareAmount_v1 added update calculation for shareCount
     * 
     * Funktoniert nicht, siehe testcase2
     */
    // public void setShareAmount(long shareAmount) {
    // long tmp = this.shareAmount;
    // this.shareAmount = shareAmount;
    // shareValue += (tmp-shareAmount) * companyShare.getRate();
    // }

    /*
     * setShareAmount_v2
     */
    public void setShareAmount(long shareAmount) {
        long oldAmount = this.shareAmount;
        this.shareAmount = shareAmount;

        if (shareAmount > oldAmount)
            shareValue = shareValue + shareAmount * companyShare.getRate();
        else
            shareValue = shareValue - (oldAmount - shareAmount) * companyShare.getRate();
    }

    /*
     * getCompanyShare();
     */

    public Share getCompanyShare() {
        return companyShare;
    }

    /*
     * setCompanyShare(Share companyShare);
     */
    public void setCompanyShare(Share companyShare) {
        this.companyShare = companyShare;
    }

    /*
     * getShareValue();
     */
    public long getShareValue() {
        return shareValue;
    }

    public long getCurrentValue() {
        return shareAmount * companyShare.getRate();
    }

    /*
     * String toString();
     */
    public String toString() {
        return (name + " gesamter Saldo: " + shareValue + " Menge: " + shareAmount);
    }
}