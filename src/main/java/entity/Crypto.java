package entity;

import java.util.Objects;

public class Crypto {
    private int idCrypto;
    private String nameCrypto;
    private String abbreviationCrypto;
    private String iconCrypto;
    private String infoCrypto;

    public Crypto(int idCrypto, String nameCrypto, String abbreviationCrypto, String iconCrypto, String infoCrypto) {
        this.idCrypto = idCrypto;
        this.nameCrypto = nameCrypto;
        this.abbreviationCrypto = abbreviationCrypto;
        this.iconCrypto = iconCrypto;
        this.infoCrypto = infoCrypto;
    }

    public int getIdCrypto() {
        return idCrypto;
    }

    public void setIdCrypto(int idCrypto) {
        this.idCrypto = idCrypto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crypto crypto = (Crypto) o;
        return idCrypto == crypto.idCrypto &&
                Objects.equals(nameCrypto, crypto.nameCrypto) &&
                Objects.equals(abbreviationCrypto, crypto.abbreviationCrypto) &&
                Objects.equals(iconCrypto, crypto.iconCrypto) &&
                Objects.equals(infoCrypto, crypto.infoCrypto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCrypto, nameCrypto, abbreviationCrypto, iconCrypto, infoCrypto);
    }
}