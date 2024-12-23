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
import com.solvd.itcompany.exceptions.ZeroOrTooManyUSDException;
import com.solvd.itcompany.interfaces.ISign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class CEO extends Employee implements ISign {
    private final static int MASK = 41;
    private final static Logger LOGGER = LogManager.getLogger(CEO.class);

    public CEO() {}

    public CEO(String firstName, String lastName, LocalDate birthDay,
               String telephoneNumber, String email, Contract contract,
               String positionName, BigDecimal salaryInUSDPerMonth)
               throws ZeroOrTooManyCharactersException, ZeroOrTooManyUSDException,
               DateTimeException {
        super(firstName, lastName, birthDay, telephoneNumber, email, contract,
                positionName, salaryInUSDPerMonth);
    }

    @Override
    public void terminateContract(Person person, Contract contract) {
        LOGGER.info("The {} with {} {} is stopped.", contract.getContractTypeName(),
                    person.getFirstName(), person.getLastName());
    }

    @Override
    public String doJob() {
        return " is signing contracts now.";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (hashCode() != object.hashCode()) {
            return false;
        }

        CEO that = (CEO) object;
        return Objects.equals(getFirstName(), that.getFirstName())
                && Objects.equals(getLastName(), that.getLastName())
                && getBirthday().isEqual(that.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getBirthday(), MASK);
    }

    @Override
    public String toString() {
        return "CEO{"
                + "firstName='" + getFirstName() + '\''
                + ", lastName='" + getLastName() + '\''
                + ", birthDay='" + getBirthday() + '\''
                + ", telephoneNumber='" + getTelephoneNumber() + '\''
                + ", email='" + getEmail() + '\''
                + ", contract=" + getContract()
                + ", positionName='" + getPositionName() + '\''
                + ", salaryInUSDPerMonth=" + getSalaryInUSDPerMonth()
                + '}';
    }
}
