package entity;

import java.util.Objects;

public class Order {
    private int idOrder;
    private String creditCard;
    private String login;
    private String nameOnCard;
    private String year;
    private String month;
    private String cvv;

    public Order(int idOrder, String creditCard, String login, String nameOnCard, String year, String month, String cvv) {
        this.idOrder = idOrder;
        this.creditCard = creditCard;
        this.login = login;
        this.nameOnCard = nameOnCard;
        this.year = year;
        this.month = month;
        this.cvv = cvv;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder &&
                Objects.equals(creditCard, order.creditCard) &&
                Objects.equals(login, order.login) &&
                Objects.equals(nameOnCard, order.nameOnCard) &&
                Objects.equals(year, order.year) &&
                Objects.equals(month, order.month) &&
                Objects.equals(cvv, order.cvv);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOrder, creditCard, login, nameOnCard, year, month, cvv);
    }
}
