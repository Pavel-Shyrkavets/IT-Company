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
import com.solvd.itcompany.interfaces.IDo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person implements IDo {
    private final static int MAX_POSITION_NAME_LENGTH = 255;
    private final static BigDecimal MAX_SALARY_IN_USD_PER_MONTH =
            new BigDecimal("100000.00");
    private final static String POSITION_NAME_MESSAGE =
            "The position name length should not be equal to 0 "
            + "or exceed " + MAX_POSITION_NAME_LENGTH + " characters.";
    private final static String SALARY_MESSAGE =
            "The employee's salary per month should not exceed "
            + MAX_SALARY_IN_USD_PER_MONTH + " USD.";
    private final static Logger LOGGER = LogManager.getLogger(Employee.class);

    private String positionName = "";
    private BigDecimal salaryInUSDPerMonth = null;
    private BloodType bloodType = null;

    public Employee() {}

    public Employee(String firstName, String lastName, LocalDate birthDay,
                    String telephoneNumber, String email, Contract contract,
                    String positionName, BigDecimal salaryInUSDPerMonth)
                    throws ZeroOrTooManyCharactersException, ZeroOrTooManyUSDException,
                    DateTimeException {
        super(firstName, lastName, birthDay, telephoneNumber, email, contract);

        if (positionName.isEmpty()
                || positionName.length() > MAX_POSITION_NAME_LENGTH) {
            LOGGER.error(POSITION_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(POSITION_NAME_MESSAGE);
        }

        if (salaryInUSDPerMonth.compareTo(MAX_SALARY_IN_USD_PER_MONTH) > 0) {
            LOGGER.error(SALARY_MESSAGE);
            throw new ZeroOrTooManyUSDException(SALARY_MESSAGE);
        }

        this.positionName = positionName;
        this.salaryInUSDPerMonth = salaryInUSDPerMonth;
    }

    public static BigDecimal getMaxSalaryInUsdPerMonth() {
        return MAX_SALARY_IN_USD_PER_MONTH;
    }

    public static String getSalaryMessage() {
        return SALARY_MESSAGE;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName)
            throws ZeroOrTooManyCharactersException {
        if (positionName.isEmpty()
                || positionName.length() > MAX_POSITION_NAME_LENGTH) {
            LOGGER.error(POSITION_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(POSITION_NAME_MESSAGE);
        }

        this.positionName = positionName;
    }

    public BigDecimal getSalaryInUSDPerMonth() {
        return salaryInUSDPerMonth;
    }

    public void setSalaryInUSDPerMonth(BigDecimal salaryInUSDPerMonth)
            throws ZeroOrTooManyUSDException {
        if (salaryInUSDPerMonth.compareTo(MAX_SALARY_IN_USD_PER_MONTH) > 0) {
            LOGGER.error(SALARY_MESSAGE);
            throw new ZeroOrTooManyUSDException(SALARY_MESSAGE);
        }

        this.salaryInUSDPerMonth = salaryInUSDPerMonth;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
}
