package br.com.saboresdefamilia.customer.model;

public class Phone {
    private int id;
    private int phone;
    private int customers_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
    }

    @Override
    public String toString() {
        return String.valueOf(getPhone());
    }
}
