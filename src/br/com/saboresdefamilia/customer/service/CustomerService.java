package br.com.saboresdefamilia.customer.service;

import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.customer.dao.AddressDAO;
import br.com.saboresdefamilia.customer.dao.CustomersDAO;
import br.com.saboresdefamilia.customer.dao.PhoneDAO;
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
}
