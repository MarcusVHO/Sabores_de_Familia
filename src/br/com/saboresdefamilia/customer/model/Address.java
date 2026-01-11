package br.com.saboresdefamilia.customer.model;

public class Address {
    private int id;
    private String address;
    private int number;
    private String complement;
    private int customers_id;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
    }

    @Override
    public String toString() {
        return getAddress() + " | " + getNumber() + " | " + getComplement();
    }
}
