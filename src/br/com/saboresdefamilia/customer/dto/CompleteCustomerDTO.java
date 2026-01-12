package br.com.saboresdefamilia.customer.dto;

import br.com.saboresdefamilia.customer.model.Address;
import br.com.saboresdefamilia.customer.model.Customer;
import br.com.saboresdefamilia.customer.model.Phone;

import java.util.ArrayList;

public class CompleteCustomerDTO {
    private Customer customer;
    private ArrayList<Address> addresses;
    private ArrayList<Phone> phones;

    public CompleteCustomerDTO(Customer customer, ArrayList<Address> addresses, ArrayList<Phone> phones) {
        this.customer = customer;
        this.addresses = addresses;
        this.phones = phones;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public void setPhones(ArrayList<Phone> phones) {
        this.phones = phones;
    }
}
