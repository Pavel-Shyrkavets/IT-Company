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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeJobContract extends Contract {
    private final static int MASK;
    private final static int MAX_POSITION_NAME_LENGTH = 255;
    private final static BigDecimal MAX_SALARY_IN_USD_PER_MONTH =
            new BigDecimal("100000.00");
    private final static String POSITION_NAME_MESSAGE =
            "The position name length should not be equal to 0 "
            + "or exceed " + MAX_POSITION_NAME_LENGTH + " characters.";
    private final static String SALARY_MESSAGE =
            "The employee's salary per month should not exceed "
            + MAX_SALARY_IN_USD_PER_MONTH + " USD.";
    private final static Logger LOGGER =
            LogManager.getLogger(EmployeeJobContract.class);

    private String positionName = "";
    private BigDecimal salaryInUSDPerMonth = null;

    static {
        MASK = 91;
    }

    public EmployeeJobContract() {}

    public EmployeeJobContract(String contractType, LocalDate startDateOfContract,
                               String positionName, BigDecimal salaryInUSDPerMonth)
                               throws ZeroOrTooManyCharactersException, DateTimeException,
                               ZeroOrTooManyUSDException {
        super(contractType, startDateOfContract);

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

        EmployeeJobContract that = (EmployeeJobContract) object;
        return Objects.equals(getContractTypeName(), that.getContractTypeName())
                && Objects.equals(getStartDateOfContract(), that.getStartDateOfContract())
                && Objects.equals(getPositionName(), that.getPositionName())
                && Objects.equals(getSalaryInUSDPerMonth(), that.getSalaryInUSDPerMonth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContractTypeName(), getStartDateOfContract(),
                            getPositionName(), getSalaryInUSDPerMonth(), MASK);
    }

    @Override
    public String toString() {
        return "EmployeeJobContract{"
                + "positionName='" + getPositionName() + '\''
                + "startDateOfContract='" + getStartDateOfContract() + '\''
                + "salaryInUSDPerMonth='" + getSalaryInUSDPerMonth() + '\''
                + '}';
    }
}
