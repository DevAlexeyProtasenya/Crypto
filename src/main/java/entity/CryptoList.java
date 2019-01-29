package entity;

import java.util.Objects;

public class CryptoList {
    private int idCrypto;
    private int idOrder;
    private String nameCrypto;
    private String abbreviationCrypto;
    private String iconCrypto;
    private String infoCrypto;
    private String amount;

    public CryptoList(int idCrypto, int idOrder, String nameCrypto, String abbreviationCrypto, String iconCrypto, String infoCrypto, String amount) {
        this.idCrypto = idCrypto;
        this.idOrder = idOrder;
        this.nameCrypto = nameCrypto;
        this.abbreviationCrypto = abbreviationCrypto;
        this.iconCrypto = iconCrypto;
        this.infoCrypto = infoCrypto;
        this.amount = amount;
    }

    public int getIdCrypto() {
        return idCrypto;
    }

    public void setIdCrypto(int idCrypto) {
        this.idCrypto = idCrypto;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getNameCrypto() {
        return nameCrypto;
    }

    public void setNameCrypto(String nameCrypto) {
        this.nameCrypto = nameCrypto;
    }

    public String getAbbreviationCrypto() {
        return abbreviationCrypto;
    }

    public void setAbbreviationCrypto(String abbreviationCrypto) {
        this.abbreviationCrypto = abbreviationCrypto;
    }

    public String getIconCrypto() {
        return iconCrypto;
    }

    public void setIconCrypto(String iconCrypto) {
        this.iconCrypto = iconCrypto;
    }

    public String getInfoCrypto() {
        return infoCrypto;
    }

    public void setInfoCrypto(String infoCrypto) {
        this.infoCrypto = infoCrypto;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoList that = (CryptoList) o;
        return idCrypto == that.idCrypto &&
                idOrder == that.idOrder &&
                Objects.equals(nameCrypto, that.nameCrypto) &&
                Objects.equals(abbreviationCrypto, that.abbreviationCrypto) &&
                Objects.equals(iconCrypto, that.iconCrypto) &&
                Objects.equals(infoCrypto, that.infoCrypto) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCrypto, idOrder, nameCrypto, abbreviationCrypto, iconCrypto, infoCrypto, amount);
    }
}
