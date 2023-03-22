package airplane;

import java.time.LocalDate;

/**
 * IntelliJ IDEA 2021.2.2 (Ultimate Edition)<br>
 * Licensed to Gilberto Rouxinol<br>
 * For educational use only.<br>
 * <p>
 * Polytechnic Institute of Viseu<br>
 * School of Technology and Management of Viseu<br>
 * <p>
 * Class created by Gilberto Rouxinol on 2022<br>
 * Copyright © 2022 Gilberto Rouxinol<br>
 * All rights reserved<br><br>
 * <p>
 * Class to represent a customer<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class Customer {

    private long id;
    private String fullName;
    private String job;
    private String address;
    private LocalDate birthday;

    /**
     *
     * @param id the customer ID
     * @param fullName the customer full name
     * @param job the customer job
     * @param address the customer address
     * @param birthday the customer birthday
     */
    public Customer(long id,
                    String fullName,
                    String job,
                    String address,
                    LocalDate birthday) {
        this.id = id;
        this.fullName = fullName;
        this.job = job;
        this.address = address;
        this.birthday = birthday;
    }

    /**
     *
     * @return id, the customer ID
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return fullName, the customer full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @return job, the customer job
     */
    public String getJob() {
        return job;
    }

    /**
     *
     * @return address, the customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return birthday, the customer birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }
}
