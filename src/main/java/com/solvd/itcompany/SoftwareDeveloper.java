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
import com.solvd.itcompany.interfaces.IRefactor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class SoftwareDeveloper extends Employee implements IRefactor {
    private final static int MASK = 43;
    private final static int MAX_NUM_OF_PROG_LANGUAGES = 20;
    private final static String PROG_LANGUAGES_MESSAGE =
            "The list should not be empty or consist of more than "
            + MAX_NUM_OF_PROG_LANGUAGES + " programming languages.";
    private final static Logger LOGGER = LogManager.getLogger(SoftwareDeveloper.class);

    private List<String> programLanguages = null;

    public SoftwareDeveloper() {}

    public SoftwareDeveloper(String firstName, String lastName, LocalDate birthday,
                             String telephoneNumber, String email, Contract contract,
                             String positionName, BigDecimal salaryInUSDPerMonth,
                             List<String> programLanguages) throws ZeroOrTooManyCharactersException,
                             ZeroOrTooManyUSDException, DateTimeException {
        super(firstName, lastName, birthday, telephoneNumber, email, contract,
                positionName, salaryInUSDPerMonth);

        if (programLanguages.isEmpty()
                || programLanguages.size() > MAX_NUM_OF_PROG_LANGUAGES) {
            LOGGER.info(PROG_LANGUAGES_MESSAGE);
            System.exit(1);
        }

        this.programLanguages = programLanguages;
    }

    public List<String> getProgramLanguages() {
        return programLanguages;
    }

    public void setProgramLanguages(List<String> programLanguages) {
        if (programLanguages.isEmpty()
                || programLanguages.size() > MAX_NUM_OF_PROG_LANGUAGES) {
            LOGGER.info(PROG_LANGUAGES_MESSAGE);
            System.exit(1);
        }

        this.programLanguages = programLanguages;
    }

    @Override
    public String doJob() {
        return " is programming now.";
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

        SoftwareDeveloper that = (SoftwareDeveloper) object;
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
        return "SoftwareDeveloper{"
                + "firstName='" + getFirstName() + '\''
                + ", lastName='" + getLastName() + '\''
                + ", birthDay='" + getBirthday() + '\''
                + ", telephoneNumber='" + getTelephoneNumber() + '\''
                + ", email='" + getEmail() + '\''
                + ", contract=" + getContract()
                + ", positionName='" + getPositionName() + '\''
                + ", salaryInUSDPerMonth=" + getSalaryInUSDPerMonth()
                + ", programmingLanguages=" + getProgramLanguages()
                + '}';
    }
}
