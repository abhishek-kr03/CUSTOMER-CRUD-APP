package com.customer.model;

/**
 * Represents a customer with personal and contact information.
 */
public class Customer {
    private int id; // Unique identifier for the customer
    private String uuid; // Universal Unique Identifier for the customer
    private String firstName; // First name of the customer
    private String lastName; // Last name of the customer
    private String street; // Street address of the customer
    private String address; // Address (e.g., apartment number) of the customer
    private String city; // City where the customer resides
    private String state; // State where the customer resides
    private String email; // Email address of the customer
    private String phone; // Phone number of the customer

    /**
     * Default constructor.
     */
    public Customer() {
        // Default constructor for creating an empty customer object
    }

    /**
     * Parameterized constructor for initializing a customer object with specified values.
     *
     * @param uuid       the UUID of the customer
     * @param firstName  the first name of the customer
     * @param lastName   the last name of the customer
     * @param street     the street address of the customer
     * @param address    the address (e.g., apartment number) of the customer
     * @param city       the city where the customer resides
     * @param state      the state where the customer resides
     * @param email      the email address of the customer
     * @param phone      the phone number of the customer
     */
    public Customer(String uuid, String firstName, String lastName, String street, String address, String city, String state, String email, String phone) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the customer object.
     * 
     * @return a string containing the customer ID, UUID, first name, last name, street, address, city, state, email, and phone
     */
    @Override
    public String toString() {
        return id + " " + uuid + " " + firstName + " " + lastName + " " + street + " " + address + " " + city + " " + state + " " + email + " " + phone;
    }
}
