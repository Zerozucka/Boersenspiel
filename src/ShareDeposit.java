/*
 * ShareDeposit
 */
public class ShareDeposit extends Asset {
    private ShareItem[] itemArray = new ShareItem[10]; // contains all shareItems of one user

    /*
     * Konstruktor
     */
    public ShareDeposit(String name) {
        super(name);
    }

    /*
     * getItemArray();
     */
    public ShareItem[] getItemArray() {
        return itemArray;
    }

    /*
     * addShareItem(ShareItem item); f�gt ein neues ShareItem am ende der itemArray ein
     */
    private void addShareItem(ShareItem item) {
        int currentPos = 0;

        while (itemArray[currentPos] != null)
            currentPos++;

        if (currentPos == itemArray.length) { // if there is no free position, adjusts array and calls the methode again to find the next free position
            itemArray = arrayAdjust(itemArray);
            addShareItem(item);
        } else
            // else adds the item to the free position
            itemArray[currentPos] = item;
    }

    /*
     * addShare(Share share, long amount); sucht in der itemArray nach der share, -> vorhanden, anzahl wird um amount erh�ht -> nicht vorhanden, neues ShareItem wird erstellt und hinzugef�gt
     * 
     * Funktioniert jetzt, falsche equals methode war ausgew�hlt
     */
    public void addShare(Share share, long amount) {
        int currentPos = 0;

        while (itemArray[currentPos] != null && !itemArray[currentPos].getCompanyShare().equals(share)
                && currentPos < itemArray.length)
            currentPos++;

        if (itemArray[currentPos] == null || currentPos == itemArray.length) {
            ShareItem tmp = new ShareItem(share, amount);
            addShareItem(tmp);
        } else {
            long tmp = itemArray[currentPos].getShareAmount(); // methode addShareamoutn hinzufuegen
            itemArray[currentPos].setShareAmount(tmp + amount);
        }
        System.out.println("Erfolgreich!");
    }

    /*
     * deleteShareItem(ShareItem item); sucht nach �bergebenem ShareItem und l�scht es
     */
    private void deleteShareItem(ShareItem item) { // methodes to delete items, get choosen by handover type
        int foundPos = 0;

        while (!itemArray[foundPos].equals(item) && foundPos < itemArray.length)
            foundPos++;

        if (foundPos == itemArray.length)
            System.out.println("Item not found");
        else
            deleteAtPos(itemArray, foundPos);
    }

    /*
     * deleteShareItem(String s) sucht nach ShareItem mit dem �bergebenem Namen un l�scht es
     */
    private void deleteShareItem(String s) {
        int foundPos = 0;

        while (itemArray[foundPos] != null && !itemArray[foundPos].getName().equals(s) && foundPos < itemArray.length)
            foundPos++;

        if (foundPos == itemArray.length)
            System.out.println("Item not found");
        else
            deleteAtPos(itemArray, foundPos);

    }

    /*
     * deleteShare(Share share, long amount); sucht nach einem ShareItem mit der �bergebenen Share in itemArray und vermindert die amount; wenn <= 0 l�schen
     */

    public void deleteShare(Share share, long amount) { // methodes to delete items, get choosen by handover type
        int foundPos = 0;

        while (itemArray[foundPos] != null && !itemArray[foundPos].getName().equals(share.getName())
                && foundPos < itemArray.length)
            foundPos++;

        if (itemArray[foundPos] == null || foundPos == itemArray.length) {
            System.out.println("Aktie nicht vorhanden!");
        } else {
            long tmp = itemArray[foundPos].getShareAmount();
            if (tmp <= amount) {
                itemArray = deleteAtPos(itemArray, foundPos);
            } else
                itemArray[foundPos].setShareAmount(tmp - amount);
            System.out.println("Erfolgreich!");
        }
    }

    public long getCurrentValue() {
        long value = 0;
        for (int c = 0; itemArray[c] != null && c < itemArray.length; c++)
            value = value + itemArray[c].getCurrentValue();
        return value;
    }

    /*
     * ShareItemArrayOps
     */
    private ShareItem[] arrayAdjust(ShareItem[] array) { // increases the array length +10
        ShareItem[] newArray = new ShareItem[array.length + 10];

        for (int c = 0; c < array.length; c++)
            newArray[c] = array[c];

        return newArray;
    }

    private ShareItem[] deleteAtPos(ShareItem[] array, int pos) {
        ShareItem[] newArray = new ShareItem[array.length];

        for (int c = 0; c < pos; c++)
            newArray[c] = array[c];

        for (int c = pos + 1; c < newArray.length; c++)
            newArray[c - 1] = array[c];

        return newArray;
    }

    /*
     * toString()
     */
    public String toString() {
        String s = "";

        for (int c = 0; c < itemArray.length; c++) {
            if (itemArray[c] == null) {
                break;
            }
            s = s + itemArray[c].toString() + "\n";
        }

        return s;
    }
}
