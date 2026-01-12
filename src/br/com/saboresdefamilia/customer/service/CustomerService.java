package br.com.saboresdefamilia.customer.service;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.customer.dao.AddressDAO;
import br.com.saboresdefamilia.customer.dao.CustomersDAO;
import br.com.saboresdefamilia.customer.dao.PhoneDAO;
import br.com.saboresdefamilia.customer.dto.CompleteCustomerDTO;
import br.com.saboresdefamilia.customer.model.Address;
import br.com.saboresdefamilia.customer.model.Customer;
import br.com.saboresdefamilia.customer.model.Phone;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {

    public static Customer createNewCustomer(String name, String description) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setDescription(description);
        return customer;
    }

    public static Address createNewAddress(String road, int number, String complement) {
        Address address = new Address();
        address.setAddress(road);
        address.setNumber(number);
        address.setComplement(complement);
        return address;
    }
    public static Phone createNewPhone(int number) {
        Phone phone = new Phone();
        phone.setPhone(number);
        return phone;
    }

    public static void registerNewCustomerInDatabase(Customer cliente,
                                                     ArrayList<Address> addresses,
                                                     ArrayList<Phone> phones) {
        Connection conn = null;
        try {

            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            CustomersDAO.create(cliente, conn);

            int idCliente = cliente.getId();

            for (Address address : addresses) {
                address.setCustomers_id(idCliente);
                AddressDAO.create(address, conn);
            }

            for (Phone phone : phones) {
                phone.setCustomers_id(idCliente);
                PhoneDAO.create(phone, conn);
            }

            conn.commit();


        } catch (SQLException e) {
            try {
                conn.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    public static ArrayList<Customer> listUsersByName(String name) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            return CustomersDAO.selectByName(name, conn);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    public static CompleteCustomerDTO getCompleteCustomerByCustomer(Customer customer) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            ArrayList<Address> addresses = AddressDAO.readAddresses(customer.getId(), conn);
            ArrayList<Phone> phones = PhoneDAO.readPhones(customer.getId(), conn);

            return new CompleteCustomerDTO(customer, addresses, phones);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    public static void updateUserDataInDatabase(CompleteCustomerDTO completeCustomerDTO) {
        Connection conn = null;
        try {

            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            AddressDAO.deleteByCustomerID(completeCustomerDTO.getCustomer().getId(), conn);
            CustomersDAO.update(completeCustomerDTO.getCustomer(), conn);

            for (Address address : completeCustomerDTO.getAddresses()) {
                address.setCustomers_id(completeCustomerDTO.getCustomer().getId());
                AddressDAO.create(address, conn);

            }

            PhoneDAO.deleteByCustomerID(completeCustomerDTO.getCustomer().getId(), conn);
            for (Phone phone : completeCustomerDTO.getPhones()) {
                phone.setCustomers_id(completeCustomerDTO.getCustomer().getId());
                PhoneDAO.create(phone, conn);
            }

            conn.commit();


        } catch (SQLException e) {
            try {
                conn.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
}
