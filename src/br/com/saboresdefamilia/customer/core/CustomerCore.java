package br.com.saboresdefamilia.customer.core;

import br.com.saboresdefamilia.customer.dto.CompleteCustomerDTO;
import br.com.saboresdefamilia.customer.model.Address;
import br.com.saboresdefamilia.customer.model.Customer;
import br.com.saboresdefamilia.customer.model.Phone;
import br.com.saboresdefamilia.customer.service.CustomerService;

import java.util.ArrayList;

public class CustomerCore {
    private Customer customer;
    private  ArrayList<Address> addresses = new ArrayList<>();
    private ArrayList<Phone> phones = new ArrayList<>();

    private ArrayList<Customer> searchResultCustomer = new ArrayList<>();
    private CompleteCustomerDTO completeCustomerDTO;

    public void setPhones(ArrayList<Phone> phones) {
        this.phones = phones;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public Address getAddress(int index) {
        if (addresses.size() >= index) {
            return addresses.get(index);
        }
        return null;
    }

    public Phone getPhone(int index) {
        if (phones.size() >= index) {
            return phones.get(index);
        }
        return null;
    }

    public void createNewAddressInListAddresses(String road, int number, String complement) {
        Address address = CustomerService.createNewAddress(road, number, complement);
        addresses.add(address);
    }
    public void createNewPhoneInListPhones(int number) {
        Phone phone = CustomerService.createNewPhone(number);
        phones.add(phone);
    }

    public void createNewCustomer(String name, String description){
        customer = CustomerService.createNewCustomer(name, description);
    }

    public void removeAddressInListAddress(int index) {
        if (addresses.size() >= index) {
            addresses.remove(index);
        }
    }
    public void removePhoneInListPhones(int index) {
        if (phones.size() >= index) {
            phones.remove(index);
        }
    }

    public void registerNewCustomerInDb() {
        CustomerService.registerNewCustomerInDatabase(customer, addresses, phones);
    }

    public void searchCustomerByName(String name) {
        searchResultCustomer = CustomerService.listUsersByName(name);
    }

    public ArrayList<Customer> getResultSearchCustomers() {
        return searchResultCustomer;
    }

    public Customer getCustomerinSearchList(int index) {
        if (searchResultCustomer.size() >= index) {
            return searchResultCustomer.get(index);
        }
        return null;
    }

    public void setCurrentEditCustomer(Customer customer) {
        this.completeCustomerDTO = CustomerService.getCompleteCustomerByCustomer(customer);
    }

    public CompleteCustomerDTO getCompleteCustomerDTO() {
        return completeCustomerDTO;
    }

    public void updateUser() {
        CustomerService.updateUserDataInDatabase(completeCustomerDTO);
    }


}


