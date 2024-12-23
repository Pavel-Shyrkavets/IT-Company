/*
 * %W% %E% Pavel Shyrkavets
 *
 * Copyright (c) 2011-2024 Solvd, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Solvd,
 * Inc. ("Confidential Information.") You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Solvd.
 *
 * SOLVD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SOLVD SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.solvd.itcompany;

import com.solvd.itcompany.exceptions.DateTimeException;
import com.solvd.itcompany.exceptions.ZeroOrTooManyCharactersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public abstract class Person {
    private final static int MAX_FIRST_NAME_LENGTH = 50;
    private final static int MAX_LAST_NAME_LENGTH = 50;
    private final static int MIN_AGE_IN_YEARS = 18;
    private final static int MAX_TEL_NUMBER_LENGTH = 15;
    private final static int MAX_EMAIL_LENGTH = 320;
    private final static String FIRST_NAME_MESSAGE =
            "The first name length should not be equal to 0 "
            + "or exceed " + MAX_FIRST_NAME_LENGTH + " characters.";
    private final static String LAST_NAME_MESSAGE =
            "The last name length should not be equal to 0 "
            + "or exceed " + MAX_LAST_NAME_LENGTH + " characters.";
    private final static String BIRTHDAY_MESSAGE =
            "The person's age should not be less than " + MIN_AGE_IN_YEARS + " years.";
    private final static String TEL_NUMBER_MESSAGE =
            "The telephone number length should not be equal to 0 "
            + "or exceed " + MAX_TEL_NUMBER_LENGTH + " characters.";
    private final static String EMAIL_LENGTH_MESSAGE =
            "The email length should not be equal to 0 "
            + "or exceed " + MAX_EMAIL_LENGTH + " characters.";
    private final static Logger LOGGER = LogManager.getLogger(Person.class);

    private String firstName = "";
    private String lastName = "";
    private LocalDate birthday = null;
    private String telephoneNumber = "";
    private String email = "";
    private Contract contract = null;

    public Person() {}

    public Person(String firstName, String lastName, LocalDate birthday,
                  String telephoneNumber, String email, Contract contract)
                  throws ZeroOrTooManyCharactersException, DateTimeException {
        if (firstName.isEmpty() || firstName.length() > MAX_FIRST_NAME_LENGTH) {
            LOGGER.error(FIRST_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(FIRST_NAME_MESSAGE);
        }

        if (lastName.isEmpty() || lastName.length() > MAX_LAST_NAME_LENGTH) {
            LOGGER.error(LAST_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(LAST_NAME_MESSAGE);
        }

        if (birthday.until(LocalDate.now()).getYears() < MIN_AGE_IN_YEARS) {
            LOGGER.error(BIRTHDAY_MESSAGE);
            throw new DateTimeException(BIRTHDAY_MESSAGE);
        }

        if (telephoneNumber.isEmpty()
                || telephoneNumber.length() > MAX_TEL_NUMBER_LENGTH) {
            LOGGER.info(TEL_NUMBER_MESSAGE);
            System.exit(1);
        }

        if (email.isEmpty() || email.length() > MAX_EMAIL_LENGTH) {
            LOGGER.error(EMAIL_LENGTH_MESSAGE);
            throw new ZeroOrTooManyCharactersException(EMAIL_LENGTH_MESSAGE);
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.contract = contract;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName)
            throws ZeroOrTooManyCharactersException {
        if (firstName.isEmpty() || firstName.length() > MAX_FIRST_NAME_LENGTH) {
            LOGGER.error(FIRST_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(FIRST_NAME_MESSAGE);
        }

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName)
            throws ZeroOrTooManyCharactersException {
        if (lastName.isEmpty() || lastName.length() > MAX_LAST_NAME_LENGTH) {
            LOGGER.error(LAST_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(LAST_NAME_MESSAGE);
        }

        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) throws DateTimeException {
        if (birthday.until(LocalDate.now()).getYears() < MIN_AGE_IN_YEARS) {
            LOGGER.error(BIRTHDAY_MESSAGE);
            throw new DateTimeException(BIRTHDAY_MESSAGE);
        }

        this.birthday = birthday;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        if (telephoneNumber.isEmpty()
                || telephoneNumber.length() > MAX_TEL_NUMBER_LENGTH) {
            LOGGER.info(TEL_NUMBER_MESSAGE);
            System.exit(1);
        }

        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ZeroOrTooManyCharactersException {
        if (email.isEmpty() || email.length() > MAX_EMAIL_LENGTH) {
            LOGGER.error(EMAIL_LENGTH_MESSAGE);
            throw new ZeroOrTooManyCharactersException(EMAIL_LENGTH_MESSAGE);
        }

        this.email = email;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
